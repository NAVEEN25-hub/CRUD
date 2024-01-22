package Ex;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String repass = request.getParameter("repass");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		ArrayList <data> li = new ArrayList<data>();
		if(pass.equals(repass) && pass.length()>=8){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ex","root","Naveen@123");
			data d = new data();
			d.setName(name);
			d.setPassword(repass);
			li.add(d);
			PreparedStatement st = con.prepareStatement("update details set pass = '"+d.getPassword()+"' where ename = '"+d.getName()+"'");
			int rs = st.executeUpdate();
			if(rs>0){
				out.print("Password Changed Successfully<br>");
				out.print("<br>");
				out.print("<a href = 'MVC.jsp'>Back</a>");
				}
			else{
				out.print("Invalid Username");
				out.print("<br>");
				out.print("<a href = 'FrondPageUpdate.jsp'>Back</a>");
			}
		}
		catch (ClassNotFoundException | SQLException e) {
			
		}
		}
		else {
			out.print("Enter Correct Re-EnterPassword and Must 8 Characters");
			out.print("<br>");
			out.print("<a href = 'FrondPageUpdate.jsp'>Back</a>");
		}
	}

}
