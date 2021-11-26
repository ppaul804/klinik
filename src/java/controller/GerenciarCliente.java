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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.ClienteDAO;

/**
 *
 * @author André
 */
@WebServlet(name = "GerenciarCliente", urlPatterns = {"/gerenciar_cliente.do"})
public class GerenciarCliente extends HttpServlet {

    
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        String idCliente = request.getParameter("idCliente");
        String acao = request.getParameter("acao");
        String mensagem = "";
        
        Cliente c = new Cliente();
        
        if (acao.equals("listar")) {
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_cliente.jsp");
            request.setAttribute("titulo", "Listar Cliente");
            request.setAttribute("activeU", "active");
            disp.forward(request, response);
        }
        if (acao.equals("cadastrar")) {
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_cliente.jsp");
            request.setAttribute("titulo", "Cadastrar Cliente");
            request.setAttribute("activeU", "active");
            disp.forward(request, response);
        }
        try {
                
                ClienteDAO cDAO = new ClienteDAO();

                if (acao.equals("alterar")) {
                    c = cDAO.getCarregaPorId(Integer.parseInt(idCliente));              
                    if (c.getIdCliente() > 0) {
                        RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_cliente.jsp");
                        request.setAttribute("cliente", c);
                        request.setAttribute("titulo", "Alterar Cliente");
                        request.setAttribute("activeU", "active");
                        disp.forward(request, response);

                    } else {
                        mensagem = "Cliente não encontrado";
                    }
                }
                
                if (acao.equals("deletar")) {
                    c.setIdCliente(Integer.parseInt(idCliente));
                    if (cDAO.deletar(c)) {
                        mensagem = "Cliente desativado com sucesso!";
                        
                    } else {
                        mensagem = "Erro ao desativar o Cliente!";
                    }
                }
                
            } catch (Exception e) {
                out.print(e);
                mensagem = "Erro ao executar!";
            }
        
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href = 'gerenciar_cliente.do?acao=listar';");
        out.println("</script>");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String idCliente = request.getParameter("idCliente");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String data_de_nascimento = request.getParameter("data_de_nascimento");
        String cpf = request.getParameter("cpf");
        String rg = request.getParameter("rg");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String endereco = request.getParameter("endereco");
        String complemento = request.getParameter("complemento");
        String cidade = request.getParameter("cidade");
        String observacao = request.getParameter("observacao");
    
            
        String mensagem = "";
            
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Cliente c = new Cliente();
        
        if (!idCliente.isEmpty()) {
            c.setIdCliente(Integer.parseInt(idCliente));
        }
        
       
         else {  
            
            try {
                c.setNome(nome);
                c.setEmail(email);
                c.setData_de_nascimento(sdf.parse(data_de_nascimento));
                c.setCpf(cpf);
                c.setRg(rg);
                c.setTelefone(telefone);
                c.setSexo(sexo.charAt(0));
                c.setEndereco(endereco);
                c.setComplemento(complemento);
                c.setCidade(cidade);
                c.setObservacao(observacao);
                
                ClienteDAO cDAO = new ClienteDAO();
                if (cDAO.gravar(c)) {
                    mensagem = "Gravado com sucesso!";
                } else {
                    mensagem  = "Erro ao gravar no banco de dados!";
                }
                
            } catch (Exception e) {
                out.print(e);
                mensagem = "Erro ao executar!";
            }
        }
        
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href = 'gerenciar_cliente.do?acao=listar';");
        out.println("</script>");
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
