package Ex;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dname = request.getParameter("dname");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ex","root","Naveen@123");
			data d = new data();
			d.setName(dname);
			PreparedStatement st = con.prepareStatement("delete from details where ename = '"+d.getName()+"'");
			int rs = st.executeUpdate();
			if(rs>0) {
				out.print("Deleted");
				out.print("<br>");
				out.print("<a href = 'MVC.jsp'>Back</a>");
				}
			else {
				out.print("Invalid Name");
				out.print("<a href = 'FrondPageDelete.jsp'>Back</a>");
			}	
		}
			catch (ClassNotFoundException | SQLException e) {
				
			}
		
	}
}
		