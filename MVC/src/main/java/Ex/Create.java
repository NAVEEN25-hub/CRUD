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
@WebServlet("/Create")
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String mobile=request.getParameter("mob");
		PrintWriter out = response.getWriter();
		ArrayList<data> li = new ArrayList<data>();
		response.setContentType("text/html");
		if(validate(name)) {
			if(pass.length()>=8) {
				if(mobile.length()==10) {
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ex","root","Naveen@123");
			data m = new data();
			m.setName(name);
			m.setPassword(pass);
			m.setMobile(mobile);
			li.add(m);
			PreparedStatement st = con.prepareStatement("insert into details values (?,?,?)");
			st.setString(1,m.getName());
			st.setString(2,m.getPassword());
			st.setString(3,m.getMobile());
			int rs = st.executeUpdate();
			if(rs>0) {
				out.print("Siginup Success");
				out.print("<br>");
				out.print("<a href = 'MVC.jsp'>Back</a>");
			}
			else {
				out.print("not success");
			}
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
				}
				else {
					out.print("Invalid Mobile Number");
					out.print("<br>");
					out.print("<a href = 'FrondPageCreate'>Back</a>");
				}
		}
			else {
				out.print("Invalid Password - Must 8 Characters");
				out.print("<br>");
				out.print("<a href = 'FrondPageCreate.jsp'>Back<?a>");
			}
		}
		else {
			out.print("Invalid Name");
			out.print("<br>");
			out.print("<a href = 'FrondPageCreate.jsp'>Back</a>");
		}
		}
	public static boolean validate(String name) {
		return name.matches("[a-zA-Z]+$");
	}
}
