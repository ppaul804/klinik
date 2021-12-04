/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cliente;
import model.ClienteDAO;
import model.Contrato;
import model.ContratoDAO;
import model.Usuario;
import model.UsuarioDAO;

/**
 *
 * @author Danilo
 */
public class GerenciarContrato extends HttpServlet {

       
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            
        PrintWriter out = response.getWriter();
        String idContrato = request.getParameter("idContrato");
        String acao = request.getParameter("acao");
        String mensagem = "";
        
        Contrato contrato = new Contrato();
        
        if (acao.equals("listar")) {
            if (GerenciarLogin.verificarPermissao(request, response)) {
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_contrato.jsp");
                request.setAttribute("titulo", "Listar Contratos");
                disp.forward(request, response);
            } else {
                mensagem = "Acesso Negado a está função!";
            }
        }
        
        try {
                
            ContratoDAO ctDAO = new ContratoDAO();

            if (acao.equals("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    contrato = ctDAO.getCarregaPorId(Integer.parseInt(idContrato));              
                    if (contrato.getIdContrato()> 0) {
                        RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_altera_contrato.jsp");
                        request.setAttribute("contrato", contrato);
                        request.setAttribute("titulo", "Alterar Contrato");
                        disp.forward(request, response);

                    } else {
                        mensagem = "Contrato não encontrado!";
                    }
                } else {
                    mensagem = "Acesso Negado a está função!";
                }
            }

            

        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao executar!";
        }
        
        /*if (acao.equals("novo")) {
            if (GerenciarLogin.verificarPermissao(request, response)) {
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_contrato.jsp");
                request.setAttribute("titulo", "Novo Contrato");
                request.setAttribute("novo", acao);
                request.setAttribute("idCliente", idCliente);
                request.setAttribute("activeCT", "active");
                request.setAttribute("acao", "contrato");
                disp.forward(request, response);
                
            } else {
                mensagem = "Acesso Negado a está função!";
            }
        }
        
        if (acao.equals("finalizar")) {
            if (GerenciarLogin.verificarPermissao(request, response)) {
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_finalizar_contrato.jsp");
                request.setAttribute("titulo", "Finalizar Contrato");
                request.setAttribute("idCliente", idCliente);
                request.setAttribute("activeCT", "active");
                request.setAttribute("acao", "contrato");
                disp.forward(request, response);
                
            } else {
                mensagem = "Acesso Negado a está função!";
            }
        }*/
        
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href = 'gerenciar_contrato.do?acao=listar';");
        out.println("</script>");
        
    }

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            String status = request.getParameter("status");
            String idContrato = request.getParameter("idContrato");
            String idCliente = request.getParameter("idCliente");
            String idUsuario = request.getParameter("idUsuario");
            String data_contrato = request.getParameter("data_contrato");
            String mensagem = "";
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Usuario usuario = new Usuario();
            Cliente cliente = new Cliente();
            
            if (!idContrato.isEmpty()) {
                Contrato contrato = new Contrato();
                contrato.setIdContrato(Integer.parseInt(idContrato));
                
                try {
                    
                    ContratoDAO conDAO = new ContratoDAO();
                    UsuarioDAO uDAO = new UsuarioDAO();
                    ClienteDAO cDAO = new ClienteDAO();
                    
                    contrato.setAtendente(usuario = uDAO.getCarregaPorId(Integer.parseInt(idUsuario)));
                    contrato.setIdCliente(cliente = cDAO.getCarregaPorId(Integer.parseInt(idCliente)));
                    contrato.setStatus(status);
                    contrato.setData_contrato(sdf.parse(data_contrato));
                    
                } catch (Exception e) {
                    out.println(e);
                }
            } else {
            
                try {
                    ContratoDAO conDAO = new ContratoDAO();
                    Contrato contrato = (Contrato) session.getAttribute("contrato");
                    contrato.setStatus(status);
                    contrato.setData_contrato(sdf.parse(data_contrato));


                    if (conDAO.gravar(contrato)) {
                        mensagem = "Contrato realizada com sucesso!";
                    } else {
                        mensagem  = "Erro ao gravar no banco de dados!";
                    }

                } catch (Exception e) {
                    out.println(e);
                }
            }
            
            out.println("<script type='text/javascript'>");
            out.println("alert('"+mensagem+"');");
            out.println("location.href = 'gerenciar_cliente.do?acao=listar';");
            out.println("</script>");
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
