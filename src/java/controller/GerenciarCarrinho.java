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
import model.Contrato;
import model.ContratoServico;
import model.Servico;
import model.ServicoDAO;

/**
 *
 * @author Danilo
 */
public class GerenciarCarrinho extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GerenciarCarrinho</title>");            
            out.println("</head>");
            out.println("<body>");
            
            HttpSession session = request.getSession();
            try {
                Contrato contrato = (Contrato) session.getAttribute("contrato");
                ArrayList<ContratoServico> carrinho = contrato.getCarrinho();
                String acao = request.getParameter("acao");
                ServicoDAO sDAO = new ServicoDAO();
                
                if (acao.equals("add")) {
                    Servico servico = new Servico();
                    int idServico = Integer.parseInt(request.getParameter("idServico"));
                    servico = sDAO.getCarregaPorID(idServico);
                    int quantidade = Integer.parseInt(request.getParameter("quantidade"));
                    
                    ContratoServico contratoServico = new ContratoServico();
                    contratoServico.setServico(servico);
                    contratoServico.setQuantidade(quantidade);
                    contratoServico.setValor(servico.getValor());
                    carrinho.add(contratoServico);
                    contrato.setCarrinho(carrinho);
                    session.setAttribute("contrato", contrato);
                    response.sendRedirect("form_contrato.jsp?acao=c");
                    
                } else if(acao.equals("deletar")) {
                    int index = Integer.parseInt(request.getParameter("index"));
                    carrinho.remove(index);
                    contrato.setCarrinho(carrinho);
                    session.setAttribute("contrato", contrato);
                    response.sendRedirect("form_finalizar_contrato.jsp");
                }
                
                
                
            } catch (Exception e) {
                out.println(e);
            }
            
            out.println("<h1>Servlet GerenciarCarrinho at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
