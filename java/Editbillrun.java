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

@WebServlet("/Editbillrun")
public class Editbillrun extends HttpServlet {    
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    int row;

    public void doGet(HttpServletRequest req,HttpServletResponse rsp ) throws IOException,ServletException
    {
        
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();
        
        String bid = req.getParameter("bid");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","1019");
           
           pst = con.prepareStatement("select * from bill where bid = ?");
           pst.setString(1, bid);
           rs = pst.executeQuery();
           
           while(rs.next())
           {
               out.print("<form action='EditbillServlet' method='POST'");
                out.print("<table");
               
                out.print("<tr> <td>BillID</td>    <td> <input type='text' name ='bid' id='bid' value= '" + rs.getString("bid") + "'/> </td> </tr>");
                 out.print("<tr> <td>Amount</td>    <td> <input type='text' name ='amt' id='amt' value= '" + rs.getString("amt") + "'/> </td> </tr>");
                 out.print("<tr> <td>PatientID</td>    <td> <input type='text' name ='pid' id='pid' value= '" + rs.getString("pid") + "'/> </td> </tr>"); 
                 out.print("<tr>  <td colspan ='2'> <input type='submit'  value= 'Edit'/> </td> </tr>");
                  
               out.print("</table");
               out.print("</form");
               
           }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
           
             out.println("<font color='red'>  Record Failed   </font>");
 
        }
    }

}