package Ex;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Read")
public class Read extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out = response.getWriter();
	ArrayList<data> li = new ArrayList<data>();
	try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ex","root","Naveen@123");
			PreparedStatement st = con.prepareStatement("select * from details");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
			data d = new data();
			d.setName(rs.getString(1));
			d.setPassword(rs.getString(2));
			d.setMobile(rs.getString(3));
			li.add(d);
			}
		} 
	catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	out.print("<table border =1px dotted black style = 'border-collapse:collapse'>");
	out.print("<tr><th>USER_NAME </th><th>PASSWORD</th><th>MOB_NUMBER</th<tr>");
	for(data i : li) {
	    String fullname = i.name;
	    String fullpass = i.password;
	    String mobile = i.mobile;
	    out.print("<tr><td>" + fullname + "</td><td>" + fullpass + "</td><td>" + mobile +"</td></tr>");
	}
	out.print("</table>");
	out.print("<br>");
	out.print("<a href = 'MVC.jsp'>Back</a>");
	}
}
