/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Menu;
import model.Usuario;
import model.UsuarioDAO;

/**
 *
 * @author Danilo
 */
public class GerenciarLogin extends HttpServlet {
    
    private static HttpServletResponse response;

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
        
        request.getSession().removeAttribute("usuarioLogado");
        response.sendRedirect("form_login.jsp");
        
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
        
        GerenciarLogin.response = response;
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        
        ArrayList<String> erros = new ArrayList<String>();
        if (login == null || login.trim().isEmpty()) {
            erros.add("Preencha o login");
        }
        if (senha == null || senha.trim().isEmpty()) {
            erros.add("Preencha a senha");
        }
        if (erros.size() > 0) {
            String campos = "";
            for(String erro: erros) {
                campos = campos +"\\n"+ erro;
            }
            exibirMensagem("Preencha os campo(s): "+ campos);
        
        } else {
            
            try {
                
                UsuarioDAO uDAO = new UsuarioDAO();
                Usuario usuario = new Usuario();
                
                usuario = uDAO.getRecuperarUsuario(login);
                if (usuario.getIdUsuario() > 0 && usuario.getSenha().equals(senha)) {
                    if (usuario.getStatus() == 1) {
                        HttpSession sessao = request.getSession();
                        sessao.setAttribute("usuarioLogado", usuario);
                        response.sendRedirect("gerenciar_index.do?acao=index");
                    
                    } else {
                        exibirMensagem("Seu usuário não está ativo!");
                    }
                } else {
                    exibirMensagem("Login ou Senha inválidos!");
                }
                
            } catch (Exception e) {
                exibirMensagem("Usuario ou Perfil não encontrados!");
            }
            
        }
        
    }
    
    private static void exibirMensagem(String mensagem) {
        try {
            
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('"+ mensagem +"');");
            out.print("history.back();");
            out.print("</script>");
            out.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Usuario verificarAcesso(HttpServletRequest request, HttpServletResponse response) {
        
        Usuario usu = null;
        GerenciarLogin.response = response;
        
        try {
            
            HttpSession sessao = request.getSession();
            if (sessao.getAttribute("usuarioLogado") == null) {
                response.sendRedirect("form_login.jsp");
            } else {
                String uri = request.getRequestURI();
                String queryString = request.getQueryString();
                
                if (queryString != null) {
                    uri += "?"+ queryString;
                }
                
                usu = (Usuario) request.getSession().getAttribute("usuarioLogado");
                if (usu == null) {
                    sessao.setAttribute("mensagem", "Você não está autenticado!");
                    response.sendRedirect("form_login.jsp");
                } else {
                    boolean possuiAcesso = false;
                    for (Menu m: usu.getIdPerfil().getMenus()) {
                        if (uri.contains(m.getLink())) {
                            possuiAcesso = true;
                            break;
                        }
                    }
                    if (!possuiAcesso) {
                        exibirMensagem("Acesso Negado!");
                    }
                }
            }
            
        } catch (Exception e) {
            exibirMensagem("Exceção: "+ e.getMessage());
        }
        return usu;
    }
    
    public static boolean verificarPermissao(HttpServletRequest request, HttpServletResponse response) {
        
        Usuario usu = null;
        GerenciarLogin.response = response;
        boolean possuiAcesso = false;
        
        try {
            
            HttpSession sessao = request.getSession();
            if (sessao.getAttribute("usuarioLogado") == null) {
                response.sendRedirect("form_login.jsp");
                
            } else {
                String uri = request.getRequestURI();
                String queryString = request.getQueryString();
                
                if (queryString != null) {
                    uri += "?"+ queryString;
                }
                
                usu = (Usuario) request.getSession().getAttribute("usuarioLogado");
                if (usu == null) {
                    sessao.setAttribute("mensagem", "Você não está autenticado!");
                    response.sendRedirect("form_login.jsp");
                    
                } else {
                    for (Menu m: usu.getIdPerfil().getMenus()) {
                        if (uri.contains(m.getLink())) {
                            possuiAcesso = true;
                            break;
                        }
                    }
                }
            }
            
        } catch (Exception e) {
            exibirMensagem("Exceção: "+ e.getMessage());
        }
        return possuiAcesso;
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
