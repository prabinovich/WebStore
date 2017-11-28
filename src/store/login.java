package store;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

	private static String dbaccount = "appuser"; 
	private static String dbpassword = "Password1234%"; 
	private static String server = "localhost:3306";
	private static String database = "webstore"; 
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://" + server, dbaccount ,dbpassword);

                Statement st = conn.createStatement();
                st.executeQuery("USE " + database);
                String sql = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
                System.out.println(sql);
                ResultSet rs = st.executeQuery(sql);

                if (rs.next()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    response.sendRedirect("store.jsp");
                } else {
                    out.println("Invalid username and/or password");
                    out.println("<p>");
                    out.println("<a href=\"login.jsp\">Login again</a>");
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            out.close();
        }
    }
}