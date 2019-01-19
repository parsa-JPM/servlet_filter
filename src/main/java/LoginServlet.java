import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/LoginServlet",name = "login")
public class LoginServlet extends HttpServlet {
    private final String userID = "parsa";
    private final String password = "1234";


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");

        if (user.equals(userID) && pwd.equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", userID);
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30 * 60);
            /**
             * Notice that there is no session validation logic in the this JSP.
             * It contains link to another JSP page, CheckoutPage.jsp.
             */
            resp.sendRedirect("LoginSuccess.jsp");
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            PrintWriter writer = resp.getWriter();
            writer.println("<font color=red>Either user name or password is wrong.</font>");
            dispatcher.include(req, resp);
        }
    }
}
