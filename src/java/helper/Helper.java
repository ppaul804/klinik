package helper;

import java.io.PrintWriter;

/**
 *
 * @author Pedro Paul
 */
public class Helper {

    public void exibirMensagemServlet(final PrintWriter out, String mensagem, String link) {
        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href = '" + link + "';");
        out.println("</script>");
    }

}
