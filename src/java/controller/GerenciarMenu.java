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
import model.Menu;
import model.MenuDAO;

/**
 *
 * @author Danilo
 */
public class GerenciarMenu extends HttpServlet {


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
        String idMenu = request.getParameter("idMenu");
        String mensagem = "";
        
        Menu menu = new Menu();
        
        if (acao.equals("listar")) {
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_menu.jsp");
            request.setAttribute("titulo", "Listar Menus");
            request.setAttribute("activeM", "active");
            disp.forward(request, response);
        }
        if (acao.equals("cadastrar")) {
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_menu.jsp");
            request.setAttribute("titulo", "Cadastrar Menu");
            request.setAttribute("activeM", "active");
            disp.forward(request, response);
        } 
        
        try {
                
            MenuDAO mDAO = new MenuDAO();

            if (acao.equals("alterar")) {
                menu = mDAO.getCarregaPorId(Integer.parseInt(idMenu));              
                if (menu.getIdMenu()> 0) {
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_menu.jsp");
                    request.setAttribute("menu", menu);
                    request.setAttribute("titulo", "Alterar Menu");
                    request.setAttribute("activeM", "active");
                    disp.forward(request, response);

                } else {
                    mensagem = "Menu não encontrado!";
                }
            }

            if (acao.equals("deletar")) {
                menu.setIdMenu(Integer.parseInt(idMenu));
                if (mDAO.deletar(menu)) {
                    mensagem = "Menu excluido com sucesso!";

                } else {
                    mensagem = "Erro ao desativar o menu!";
                }
            }

        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao executar!";
        }
        
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href = 'gerenciar_menu.do?acao=listar';");
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
        String idMenu = request.getParameter("idMenu");
        String nome = request.getParameter("nome");
        String link = request.getParameter("link");
        String icone = request.getParameter("icone");
        String exibir = request.getParameter("exibir");
        
        String mensagem = "";
        
        Menu menu = new Menu();
        
        if (!idMenu.isEmpty()) {
            menu.setIdMenu(Integer.parseInt(idMenu));
        }
        
        if (nome.equals("") || link.equals("") || exibir.equals("")) {
            mensagem = "Campos obrigatórios devem ser preenchidos!";
        } else { 
            
            try {
                
                menu.setNome(nome);
                menu.setLink(link);
                menu.setIcone(icone);
                menu.setExibir(Integer.parseInt(exibir));
                
                MenuDAO mDAO = new MenuDAO();
                if (mDAO.gravar(menu)) {
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
        out.println("location.href = 'gerenciar_menu.do?acao=listar';");
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
