package store;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@SuppressWarnings("serial")
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String username = request.getParameter("username");
            username = SanitizationUtils.validateInput(username);
            
            String password = request.getParameter("password");
            password = SanitizationUtils.validateInput(password);
            
            try {
            	DBConnection db = new DBConnection();
        		ResultSet rs = db.checkUserLogin(username, password);

                if (rs.next()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    response.sendRedirect("store.jsp");
                } else {
                    out.println("Invalid username and/or password");
                    out.println("<p>");
                    out.println("<a href=\"login.jsp\">Login again</a>");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            out.close();
        }
    }
}