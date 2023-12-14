import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Patient")

public class Patient extends HttpServlet {
    
    Connection con;
    PreparedStatement pst;
    int row;
    
    public void doPost(HttpServletRequest req,HttpServletResponse rsp ) throws IOException,ServletException
    {
        
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","1019");
            String pid = req.getParameter("pid");
            String pfname = req.getParameter("pfname");
            String plname= req.getParameter("plname");
            
            pst = con.prepareStatement("insert into patient(pid,pfname,plname)values(?,?,?) ");
            pst.setString(1, pid);
            pst.setString(2, pfname);
            pst.setString(3, plname);
            row = pst.executeUpdate();
            
            out.println("<font color='green'>  Record Added   </font>");
 
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
           
             out.println("<font color='red'>  Record Failed   </font>");
        } 
    }
  
}