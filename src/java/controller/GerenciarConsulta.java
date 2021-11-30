package controller;

import helper.Helper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pedro Paul
 */
@WebServlet(name = "GerenciarConsulta", urlPatterns = {"/gerenciar_consulta.do"})
public class GerenciarConsulta extends HttpServlet {
    
    private Helper helper = new Helper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String mensagem = "";
            String acao = request.getParameter("acao");
            
            helper.exibirMensagemServlet(out, mensagem, "gerenciar_consulta.do?acao=listar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String mensagem = "";
            
            helper.exibirMensagemServlet(out, mensagem, "gerenciar_consulta.do?acao=listar");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
