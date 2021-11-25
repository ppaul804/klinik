/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Perfil;
import model.PerfilDAO;

/**
 *
 * @author Danilo
 */
public class GerenciarPerfil extends HttpServlet {

    
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
        String acao = request.getParameter("acao");
        String idPerfil = request.getParameter("idPerfil");
        String mensagem = "";
        
        Perfil perfil = new Perfil();
        
        if (acao.equals("listar")) {
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_perfil.jsp");
            request.setAttribute("titulo", "Listar Perfis");
            request.setAttribute("activeP", "active");
            disp.forward(request, response);
        }
        if (acao.equals("cadastrar")) {
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_perfil.jsp");
            request.setAttribute("titulo", "Cadastrar Perfil");
            request.setAttribute("activeP", "active");
            disp.forward(request, response);
        }   
        
        try {
                
            PerfilDAO uDAO = new PerfilDAO();

            if (acao.equals("alterar")) {
                perfil = uDAO.getCarregaPorId(Integer.parseInt(idPerfil));              
                if (perfil.getIdPerfil()> 0) {
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_perfil.jsp");
                    request.setAttribute("perfil", perfil);
                    request.setAttribute("titulo", "Alterar Perfil");
                    request.setAttribute("activeP", "active");
                    disp.forward(request, response);

                } else {
                    mensagem = "Perfil não encontrado";
                }
            }

            if (acao.equals("deletar")) {
                perfil.setIdPerfil(Integer.parseInt(idPerfil));
                if (uDAO.deletar(perfil)) {
                    mensagem = "Perfil excluido com sucesso!";

                } else {
                    mensagem = "Erro ao desativar o perfil!";
                }
            }

        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao executar!";
        }
        
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href = 'gerenciar_perfil.do?acao=listar';");
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
        String idPerfil = request.getParameter("idPerfil");
        String nome = request.getParameter("nome");
        
        String mensagem = "";
        
        Perfil perfil = new Perfil();
        
        if (!idPerfil.isEmpty()) {
            perfil.setIdPerfil(Integer.parseInt(idPerfil));
        }
        
        if (nome.equals("") || nome.isEmpty()) {
            mensagem = "Campos obrigatórios devem ser preenchidos!";
        } else { 
            
            try {
                
                perfil.setNome(nome);
                
                PerfilDAO pDAO = new PerfilDAO();
                if (pDAO.gravar(perfil)) {
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
        out.println("location.href = 'gerenciar_perfil.do?acao=listar';");
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
