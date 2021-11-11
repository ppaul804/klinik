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
 * @author GREGORI
 */
public class GerenciarMenu extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String mensagem = "";

        String acao = request.getParameter("acao");
        String idMenu = request.getParameter("idMenu");

        Menu m = new Menu();

        try {

            MenuDAO mDAO = new MenuDAO();

            if (acao.equals("alterar")) {

                m = mDAO.getCarregarPorID(Integer.parseInt(idMenu));

                if (m.getIdMenu() > 0) {
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_menu.jsp");

                    request.setAttribute("menu", m);
                    disp.forward(request, response);
                } else {
                    mensagem = "Menu não encontrado.";
                }

            }

            if (acao.equals("deletar")) {

                m.setIdMenu(Integer.parseInt(idMenu));

                if (mDAO.deletarMenu(m)) {
                    mensagem = "Menu removido.";
                } else {
                    mensagem = "Error ao remover Menu.";
                }
            }

        } catch (Exception e) {
            out.print("Error : " + e);
            mensagem = "Error: " + e;
        }

        out.println("<script type='text/javascript'>");
        out.println("location.href='listar_menu.jsp';");
        out.println("</script>");

    }

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

        Menu m = new Menu();

        try {
            MenuDAO mDAO = new MenuDAO();

            if (!idMenu.isEmpty()) {
                m.setIdMenu(Integer.parseInt(idMenu));
            }
            if (nome.equals("") || link.equals("") || exibir.equals("")) {
                mensagem = "Campos obrigatorio, deverao ser prenchidos!";

            } else {
                m.setNome(nome);
                m.setLink(link);
                m.setIcone(icone);
                m.setExibir(Integer.parseInt(exibir));

                if (mDAO.gravar(m)) {
                    mensagem = "saved success!!";
                } else {
                    mensagem = "Error ao cadastrar menu.";
                }
            }
        } catch (Exception e) {
            mensagem = "Error: " + e;
        }

        out.println("<script type='text/javascript'>");
        out.println("location.href='listar_menu.jsp';");
        out.println("</script>");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
