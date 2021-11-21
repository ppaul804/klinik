/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Perfil;
import model.Usuario;
import model.UsuarioDAO;

/**
 *
 * @author Danilo
 */
public class GerenciarUsuarios extends HttpServlet {


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
        String idUsuario = request.getParameter("idUsuario");
        String mensagem = "";
        String acao = request.getParameter("acao");
        
        Usuario u = new Usuario();
        
        if (acao.equals("listar")) {
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_usuario.jsp");
            request.setAttribute("titulo", "Listar Usuários");
            request.setAttribute("activeU", "active");
            disp.forward(request, response);
        }
        if (acao.equals("cadastrar")) {
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_usuario.jsp");
            request.setAttribute("titulo", "Cadastrar Usuário");
            request.setAttribute("activeU", "active");
            disp.forward(request, response);
        }
        
            try {
                
                UsuarioDAO uDAO = new UsuarioDAO();
                
                if (acao.equals("alterar")) {
                    u = uDAO.getCarregaPorId(Integer.parseInt(idUsuario));
                    if (u.getIdUsuario() > 0) {
                        RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_usuario.jsp");
                        request.setAttribute("usuario", u);
                        request.setAttribute("titulo", "Alterar Usuário");
                        request.setAttribute("activeU", "active");

                    } else {
                        mensagem = "Usuário não encontrado";
                    }
                }
                
                if (acao.equals("deletar")) {
                    u.setIdUsuario(Integer.parseInt(idUsuario));
                    if (uDAO.deletar(u)) {
                        mensagem = "Usuário desativado com sucesso!";
                        
                    } else {
                        mensagem = "Erro ao desativar o usuário!";
                    }
                }
                
            } catch (Exception e) {
                out.print(e);
                mensagem = "Erro ao executar!";
            }
        
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href = 'gerenciar_usuarios.do?acao=listar';");
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
        String idUsuario = request.getParameter("idUsuario");
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
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String status = request.getParameter("status");
        String idPerfil = request.getParameter("idPerfil");
            
        String mensagem = "";
            
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Usuario u = new Usuario();
        
        if (!idUsuario.isEmpty()) {
            u.setIdUsuario(Integer.parseInt(idUsuario));
        }
        if (nome.equals("") || login.equals("") || senha.equals("")) {
            mensagem = "Campos obrigatórios devem ser preenchidos!";
        } else {  
            
            try {
                u.setIdUsuario(Integer.parseInt(idUsuario));
                u.setNome(nome);
                u.setEmail(email);
                if (!data_de_nascimento.isEmpty()) {
                    u.setData_de_nascimento(sdf.parse(data_de_nascimento));
                }
                u.setCpf(cpf);
                u.setRg(rg);
                u.setTelefone(telefone);
                u.setSexo(sexo.charAt(0));
                u.setEndereco(endereco);
                u.setComplemento(complemento);
                u.setCidade(cidade);
                u.setLogin(login);
                u.setSenha(senha);
                u.setStatus(Integer.parseInt(status));
                Perfil p = new Perfil();
                p.setIdPerfil(Integer.parseInt(idPerfil));
                u.setIdPerfil(p);
                
                UsuarioDAO uDAO = new UsuarioDAO();
                if (uDAO.Gravar(u)) {
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
        out.println("location.href = 'gerenciar_usuarios.do?acao=listar';");
        out.println("</script>");
        //RequestDispatcher disp = getServletContext().getRequestDispatcher("/gerenciar_usuarios.do?acao=listar");
        //request.setAttribute("mensagem", mensagem);
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
