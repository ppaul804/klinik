/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Consulta;
import model.ServicoConsulta;
import model.Servico;
import model.ServicoDAO;

/**
 *
 * @author Pedro Paul
 */
public class GerenciarCarrinhoConsulta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GerenciarCarrinhoConsulta</title>");
            out.println("</head>");
            out.println("<body>");

            HttpSession session = request.getSession();
            try {
                Consulta consulta = (Consulta) session.getAttribute("consulta");
                List<ServicoConsulta> carrinho = consulta.getCarrinho();
                String acao = request.getParameter("acao");
                ServicoDAO sDAO = new ServicoDAO();

                if (acao.equals("add")) {
                    Servico servico = new Servico();
                    int idServico = Integer.parseInt(request.getParameter("idServico"));
                    servico = sDAO.getCarregaPorID(idServico);
                    int quantidade = Integer.parseInt(request.getParameter("quantidade"));

                    ServicoConsulta servicoConsulta = new ServicoConsulta();
                    servicoConsulta.setServico(servico);
                    servicoConsulta.setQuantidade(quantidade);
                    servicoConsulta.setValor(servico.getValor());
                    carrinho.add(servicoConsulta);
                    consulta.setCarrinho(carrinho);
                    session.setAttribute("consulta", consulta);
                    response.sendRedirect("form_consulta.jsp?acao=c");

                } else if (acao.equals("deletar")) {
                    int index = Integer.parseInt(request.getParameter("index"));
                    carrinho.remove(index);
                    consulta.setCarrinho(carrinho);
                    session.setAttribute("consulta", consulta);
                    response.sendRedirect("form_finalizar_consulta.jsp");
                }

            } catch (Exception e) {
                out.println(e);
            }

            out.println("<h1>Servlet GerenciarCarrinhoConsulta at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
