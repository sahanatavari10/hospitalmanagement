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

@WebServlet("/Editdoctorrun")
public class Editdoctorrun extends HttpServlet {    
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    int row;

    public void doGet(HttpServletRequest req,HttpServletResponse rsp ) throws IOException,ServletException
    {
        
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();
        
        String did = req.getParameter("did");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","1019");
           
           pst = con.prepareStatement("select * from doctor where did = ?");
           pst.setString(1, did);
           rs = pst.executeQuery();
           
           while(rs.next())
           {
               out.print("<form action='EditdoctorServlet' method='POST'");
                out.print("<table");
               
                out.print("<tr> <td>DoctorID</td>    <td> <input type='text' name ='did' id='did' value= '" + rs.getString("did") + "'/> </td> </tr>");
                 out.print("<tr> <td>Firstname</td>    <td> <input type='text' name ='dfname' id='dfname' value= '" + rs.getString("dfname") + "'/> </td> </tr>");
                 out.print("<tr> <td>Firstname</td>    <td> <input type='text' name ='dlname' id='dlname' value= '" + rs.getString("dlname") + "'/> </td> </tr>");
                  out.print("<tr>  <td colspan ='2'> <input type='submit'  value= 'Edit'/> </td> </tr>");
               out.print("</table");
               out.print("</form");
               
           }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
           
             out.println("<font color='red'>  Record Failed   </font>");
 
        }
    }

}