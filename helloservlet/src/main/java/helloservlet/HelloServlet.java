package helloservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * Annotation: @(ký hiệu)
 * /login
 * 
 */
@WebServlet(name = "helloServlet", urlPatterns = { "/hello" })  // định nghĩa đường dẫn
public class HelloServlet extends HttpServlet { // phải extends
	// GET, POST => Methods cách thức client gọi đường dẫn
	// muốn chạy phải định nghĩa đường dẫn
	/**
	 * Nhận tham số từ cilent gửi lên
	 *    - Giữa GET và POST cách nhận tham số sẽ giống nhau
	 *    - GET : Tham số sẽ truyền trực tiếp lên URL => ?tenthamso = giatri&tenthamso=giatri....
	 *    - POST : Tham số sẽ truyền thông qua thẻ FORM của HTML hoặc các ứng dụng,
	 *     code gọi link với phương thức POST(Tham số sẽ được truyền ngầm)
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Sẽ thực hiện khi người dùng gọi đường dẫn với phương thức GET
//		PrintWriter printWriter = resp.getWriter();
//		printWriter.write("Hello Servlet");
//		printWriter.close();
		
		
		// Lấy giá trị của tham số có tên là username
		String username = req.getParameter("username");
	//	 int age = Integer.parseInt(req.getParameter("age"));
		
		System.out.println("Kiểm tra " + username + "-" );
		
		HttpSession session = req.getSession();
		session.setAttribute("cybersoft", "Hello Session");
		
		
		req.getRequestDispatcher("Hello.jsp").forward(req, resp);
		// Cookie được lưu trữ trên máy của người dùng (không phải lưu trên sever)
		
		
		/**
		 * Controller: Chỉ dùng để chứa các class khai báo đường dẫn và nhận tham số ( không sử lý logic code)
		 * Service: Chứa các class để xử lý logic cho các controller tương ứng
		 * Repository: Chứa các class trả ra dữ liệu các câu query liên quan đến các bảng trong database. Tức là
				 chỉ thực thi câu query và trả ra kết quả câu query ( không xử lý logic code)
				 
		 */
		/**
		 * Controller --> Service --> Repository --> Service --> Controller
		 * 
		 * 
		 */
		/**
		 * Json có 2 loại:
		 * + JsonObject: Class
		 * 
		 */
	}

}
