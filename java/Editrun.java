import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Editrun")
public class Editrun extends HttpServlet {    
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    int row;

    public void doGet(HttpServletRequest req,HttpServletResponse rsp ) throws IOException,ServletException
    {
        
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();
        
        String pid = req.getParameter("pid");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","1019");
           
           pst = con.prepareStatement("select * from patient where pid = ?");
           pst.setString(1, pid);
           rs = pst.executeQuery();
           
           while(rs.next())
           {
               out.print("<form action='EditServlet' method='POST'");
                out.print("<table");
               
                out.print("<tr> <td>PatientID</td>    <td> <input type='text' name ='pid' id='pid' value= '" + rs.getString("pid") + "'/> </td> </tr>");
                 out.print("<tr> <td>Firstname</td>    <td> <input type='text' name ='pfname' id='pfname' value= '" + rs.getString("pfname") + "'/> </td> </tr>");
                 out.print("<tr> <td>Firstname</td>    <td> <input type='text' name ='plname' id='plname' value= '" + rs.getString("plname") + "'/> </td> </tr>");
                  out.print("<tr>  <td colspan ='2'> <input type='submit'  value= 'Edit'/> </td> </tr>");
               out.print("</table");
               out.print("</form");
               
           }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
           
             out.println("<font color='red'>  Record Failed   </font>");
 
        }
    }

}