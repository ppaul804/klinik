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
import model.Servico;
import model.ServicoDAO;

/**
 *
 * @author COUGAR
 */
public class GerenciarServico extends HttpServlet {

  

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
        String idServico= request.getParameter("idServico");
        String acao = request.getParameter("acao");
        String mensagem = "";
        
        Servico servico = new Servico();
        
        if (acao.equals("listar")) {
            if (GerenciarLogin.verificarPermissao(request, response)) {
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_servico.jsp");
                request.setAttribute("titulo", "Listar Serviços");
                request.setAttribute("activeS", "active");
                disp.forward(request, response);
            } else {
                mensagem = "Acesso Negado a está função!";
            }    
        }
        if (acao.equals("cadastrar")) {
            if (GerenciarLogin.verificarPermissao(request, response)) {
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_servico.jsp");
                request.setAttribute("titulo", "Cadastrar Serviço");
                request.setAttribute("activeS", "active");
                disp.forward(request, response);
            } else {
                mensagem = "Acesso Negado a está função!";
            }    
        }
        
        try{
            
            ServicoDAO servicoDAO = new ServicoDAO();
            
            if(acao.equals("alterar")){
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    servico = servicoDAO.getCarregaPorID(Integer.parseInt(idServico));
                    if(servico.getIdServico()>0){
                        RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_servico.jsp");
                        request.setAttribute("servico", servico);
                        request.setAttribute("titulo", "Alterar Serviço");
                        request.setAttribute("activeS", "active");
                        disp.forward(request, response);
                    }else{
                        mensagem = "Serviço não encontrado";
                    }
                } else {
                    mensagem = "Acesso Negado a está função!";
                }   
            }
            if(acao.equals("deletar")){
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    servico.setIdServico(Integer.parseInt(idServico));
                    if(servicoDAO.deletar(servico)){
                        mensagem = "Serviço deletado com sucesso";
                    }else{
                        mensagem = "Erro ao deletar o serviço do banco de dados";
                    }
                } else {
                    mensagem = "Acesso Negado a está função!";
                }
            }
        }catch(Exception e){
            out.print(e);
            mensagem = "Erro ao executar";
        }
        
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href = 'gerenciar_servico.do?acao=listar';");
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
        String idServico = request.getParameter("idServico");
        String nome = request.getParameter("nome");
        String quantidade = request.getParameter("quantidade");
        String valor = request.getParameter("valor");
        String mensagem = "";
        
        Servico servico = new Servico();
        if(!idServico.isEmpty()){
            servico.setIdServico(Integer.parseInt(idServico));
        }
        
        if (quantidade.isEmpty()) {
            servico.setQuantidade(0);
        }
        
        if(nome.equals("") || valor.equals("")){
            mensagem = "Campos obrigatórios deverão ser preenchidos.";
        }else{
            servico.setNome(nome);
            servico.setQuantidade(Integer.parseInt(quantidade));
            double novoValor=0;
            if(!valor.isEmpty()){
                novoValor = Double.parseDouble(valor.replace(".","").replace(",","."));
            }
            servico.setValor(novoValor);
            try{
                ServicoDAO servicoDAO = new ServicoDAO();
                if(servicoDAO.gravar(servico)){
                    mensagem = "Gravado com sucesso";
                }else{
                    mensagem = "Erro ao gravar o serviço no banco de dados";
                }
            }catch(Exception e){
                out.print(e);
                mensagem = "Erro ao executar!";
            }
        }
        
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href = 'gerenciar_servico.do?acao=listar';");
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
