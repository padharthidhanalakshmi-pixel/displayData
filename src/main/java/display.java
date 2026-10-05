import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/display")
public class display extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {


        String username = req.getParameter("username");
        String password = req.getParameter("password");


        res.setContentType("text/html");


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_db", "root", "manager");
            if (con !=null){
                System.out.println("dbConnected");
            }
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from student");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "|" + rs.getString("username") + "|" + rs.getString("password"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}