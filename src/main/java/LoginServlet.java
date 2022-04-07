import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet"},
        initParams= {
                @WebInitParam(name = "user", value = "Mak"),
                @WebInitParam(name = "password", value = "123")
        }
)

public class LoginServlet extends HttpServlet {
    Validation validation;
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validation=new Validation();
        PrintWriter out = response.getWriter();

        String name = request.getParameter("user");
        String pwd = request.getParameter("password");


        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");

        if(validation.validateName(name))
        {
            if (userID.equals(name) && password.equals(pwd)) {
                request.setAttribute("user", name);
                request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
            }else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");

                out.println("<font color = red> Enter user name or password is wrong.</font> ");
                rd.include (request, response);
            }

        }
        else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            out.println("<font color = red> Name must be starts with Cap and has minimum 3 characters </font> ");
            rd.include (request, response);
        }

    }

}
