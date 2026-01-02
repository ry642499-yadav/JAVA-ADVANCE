package registration;

import jakarta.servlet.ServletException;

import java.sql.Connection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;



/**
 * Servlet implementation class registration
 */
@WebServlet("/registration")
public class registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String name = request.getParameter("name");
        String address = request.getParameter("address");
        String ageStr = request.getParameter("age");
        String gender = request.getParameter("gender");
        String city = request.getParameter("city");

        // 🔹 Validation
        if (name == null || name.trim().isEmpty() ||
            address == null || address.trim().isEmpty() ||
            ageStr == null || ageStr.trim().isEmpty() ||
            gender == null ||
            city == null || city.trim().isEmpty()) {

            request.setAttribute("error", "All fields are mandatory!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
            if (age <= 0) {
                throw new NumberFormatException();
            }
        } catch (Exception e) {
            request.setAttribute("error", "❌ Invalid age!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // 🔹 JDBC Code
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/UserDB",
                "root",
                ""   // apna password
            );

            String sql = "INSERT INTO Registration_User(name,address,age,gender,city) VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, address);
            ps.setInt(3, age);
            ps.setString(4, gender);
            ps.setString(5, city);

            int i = ps.executeUpdate();

            if (i > 0) {
                request.setAttribute("success", "Registration Successful!");
            } else {
                request.setAttribute("error", "Registration Failed!");
            }

            con.close();

        } catch (Exception e) {
            request.setAttribute("error", "Database Error: " + e.getMessage());
        }

        request.getRequestDispatcher("register.jsp").forward(request, response);
	}

	
}
