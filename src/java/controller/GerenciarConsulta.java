package controller;

import helper.Helper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Consulta;
import model.ConsultaDAO;
import model.constantes.Acao;
import model.constantes.Parametro;

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
        PrintWriter out = response.getWriter();
        String mensagem = null;

        try {
            
            helper.exibirMensagemServlet(out, mensagem, "gerenciar_consulta.do?acao=listar");
        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao executar";
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String mensagem = null;
        //String id;
        try {
            
            helper.exibirMensagemServlet(out, mensagem, "gerenciar_consulta.do?acao=listar");
        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao executar";
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
