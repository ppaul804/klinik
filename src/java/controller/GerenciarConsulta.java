package controller;

import helper.Helper;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cliente;
import model.ClienteDAO;
import model.Consulta;
import model.ConsultaDAO;
import model.Usuario;
import model.UsuarioDAO;

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
        String idConsultaParam = request.getParameter("idConsulta");
        int idConsulta = idConsultaParam != null ? Integer.parseInt(idConsultaParam) : 0;
        String acao = request.getParameter("acao");

        String mensagem = null;

        try {
            if (acao.equals("listar")) {
                mensagem = dispacheBasico(request, response, mensagem, "/listar_consulta.jsp", "Listar Consulta");
            }
            if (acao.equals("cadastrar")) {
                mensagem = dispacheBasico(request, response, mensagem, "/form_consulta.jsp", "Cadastrar Consulta");
            }

            Consulta consulta = new Consulta();
            ConsultaDAO consultaDAO = new ConsultaDAO();

            if (acao.equals("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    consulta = consultaDAO.getCarregaPorId(idConsulta);
                    if (consulta.getIdConsulta() > 0) {
                        RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_altera_consulta.jsp");
                        request.setAttribute("consulta", consulta);
                        request.setAttribute("titulo", "Alterar Consulta");
                        request.setAttribute("activeC", "active");
                        disp.forward(request, response);
                    } else {
                        mensagem = "Consulta não encontrada";
                    }
                } else {
                    mensagem = "Acesso Negado a está função!";
                }
            }

            if (acao.equals("deletar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    consulta.setIdConsulta(idConsulta);
                    if (consultaDAO.deletar(consulta)) {
                        mensagem = "Consulta desativada com sucesso!";

                    } else {
                        mensagem = "Erro ao desativar a Consulta!";
                    }
                } else {
                    mensagem = "Acesso Negado a está função!";
                }
            }

        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao executar!";
        }

        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href = 'gerenciar_consulta.do?acao=listar';");
        out.println("</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String status = request.getParameter("status");
        String idConsulta = request.getParameter("idConsulta");
        String idCliente = request.getParameter("idCliente");
        String idUsuario = request.getParameter("idUsuario");
        String data_hora = request.getParameter("data_consulta");
        String mensagem = "";

        Usuario usuario = new Usuario();
        Cliente cliente = new Cliente();

        if (!idConsulta.isEmpty()) {
            Consulta consulta = new Consulta();
            consulta.setIdConsulta(Integer.parseInt(idConsulta));

            try {

                ConsultaDAO conDAO = new ConsultaDAO();
                UsuarioDAO uDAO = new UsuarioDAO();
                ClienteDAO cDAO = new ClienteDAO();

                consulta.setAtendente(usuario = uDAO.getCarregaPorId(Integer.parseInt(idUsuario)));
                consulta.setCliente(cliente = cDAO.getCarregaPorId(Integer.parseInt(idCliente)));
                consulta.setStatus(status);

                LocalDateTime data = LocalDateTime.parse(data_hora, DateTimeFormatter.ISO_DATE_TIME);
                consulta.setData_consulta(data);

                if (conDAO.gravar(consulta)) {
                    mensagem = "Consulta alterado com sucesso!";
                } else {
                    mensagem = "Erro ao gravar no banco de dados!";
                }

            } catch (Exception e) {
                out.println(e);
            }
        } else {

            try {

                ConsultaDAO conDAO = new ConsultaDAO();
                Consulta consulta = (Consulta) session.getAttribute("consulta");
                consulta.setStatus(status);

                LocalDateTime data = LocalDateTime.parse(data_hora, DateTimeFormatter.ISO_DATE_TIME);
                consulta.setData_consulta(data);

                if (conDAO.gravar(consulta)) {
                    mensagem = "Consulta realizada com sucesso!";
                } else {
                    mensagem = "Erro ao gravar no banco de dados!";
                }

            } catch (Exception e) {
                out.println(e);
            }
        }

        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href = 'gerenciar_consulta.do?acao=listar';");
        out.println("</script>");
    }

    private String dispacheBasico(HttpServletRequest request, HttpServletResponse response, String mensagem, String caminhoPagina, String titulo)
            throws ServletException, IOException {
        if (GerenciarLogin.verificarPermissao(request, response)) {
            RequestDispatcher disp = getServletContext().getRequestDispatcher(caminhoPagina);
            request.setAttribute("titulo", titulo);
            request.setAttribute("activeC", "active");
            disp.forward(request, response);
        } else {
            mensagem = "Acesso Negado a está função!";
        }
        return mensagem;
    }

    private void exibirMensagemServlet(final PrintWriter out, String mensagem, String link) {
        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem != null ? mensagem : "" + "');");
        out.println("location.href = '" + link + "';");
        out.println("</script>");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
