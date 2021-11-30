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
public class GerenciarMenuPerfil extends HttpServlet {


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
        String mensagem = "";
        String idPerfil = request.getParameter("idPerfil");
        String acao = request.getParameter("acao");
        
        try {

            PerfilDAO pDAO = new PerfilDAO();
            Perfil perfil = new Perfil();
            
            if (acao.equals("gerenciar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    perfil = pDAO.getCarregaPorId(Integer.parseInt(idPerfil));
                    if (perfil.getIdPerfil() > 0) {
                        RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_menu_perfil.jsp");
                        request.setAttribute("titulo", "Gerenciar Perfil/Menu");
                        request.setAttribute("activeP", "active");
                        request.setAttribute("perfilVinculado", perfil);
                        disp.forward(request, response);

                    } else {
                        mensagem = "Perfil não encontrado!";
                    }
                } else {
                    mensagem = "Acesso Negado a está função!";
                }      
            }
            if (acao.equals("desvincular")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    String idMenu = request.getParameter("idMenu");
                    if (idMenu.equals("")) {
                        mensagem = "O campo idMenu deve ser preenchido!";

                    } else {
                        if (pDAO.desvincular(Integer.parseInt(idPerfil), Integer.parseInt(idMenu))) {
                            mensagem = "Desvinculado com sucesso!";
                        } else {
                            mensagem = "Erro ao desvincular!";
                        }

                    }
                } else {
                    mensagem = "Acesso Negado a está função!";
                }      
            } 

        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao executar";
        }
        
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href = 'gerenciar_menu_perfil.do?acao=gerenciar&idPerfil="+idPerfil+"';");
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
        String mensagem = "";
        String idPerfil = request.getParameter("idPerfil");
        String idMenu = request.getParameter("idMenu");
        System.out.println(idMenu + idPerfil);
        
        try {
            PerfilDAO pDAO = new PerfilDAO();
            
            if (idPerfil.equals("") || idMenu.equals("")) {
                mensagem = "Campos obrigatórios devem ser preenchidos!";
                
            } else {
                if (pDAO.vincular(Integer.parseInt(idPerfil), Integer.parseInt(idMenu))) {
                    mensagem = "Vinculado com sucesso!";
                } else {
                    mensagem = "Erro ao vincular o Menu ao Perfil!";
                }
                
            }
            
        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao executar!";
        }
        
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href = 'gerenciar_menu_perfil.do?acao=gerenciar&idPerfil="+idPerfil+"';");
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
