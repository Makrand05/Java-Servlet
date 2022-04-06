import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet"},
        initParams= {
                @WebInitParam(name = "user", value = "Mak"),
                @WebInitParam(name = "password", value = "123")
        }
)

public class LoginServlet extends HttpServlet {

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pwd = request.getParameter("password");


        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        PrintWriter out = response.getWriter();

        if (userID.equals(user) && password.equals(pwd)) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
        }else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            out.println("<font color = red> Enter user name or password is wrong.</font> ");
            rd.include (request, response);
        }
    }

}
