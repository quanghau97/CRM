//package helloservlet;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import helloservlet.config.MysqlConfig;
//import helloservlet.entity.UserEntity;
//
//@WebServlet(name="loginServlet", urlPatterns = {"/login"})
//public class LoginServlet extends HttpServlet {
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		// TODO Auto-generated method stub
//		
////		HttpSession session = req.getSession();
////		String cybersoft = (String)session.getAttribute("cybersoft");
////		System.out.println("Session" + cybersoft);
//		
////		Cookie cookie = new Cookie("username","NguyenVanA");
////		cookie.setMaxAge(5*60*60);
////		
////		Cookie cookiePassword = new Cookie("passwword", "123456");
////		
////		
////		resp.addCookie(cookie);
////		resp.addCookie(cookiePassword);
////		
//		
//		// Lấy danh sách cookie từ request người dùng
//		
//		Cookie[] listCookie = req.getCookies();
//		String email ="";
//		String password= "";
//		try {
//			for (int i = 0; i < listCookie.length; i++) {
//				// Kiểm tra tên cookie có tên username không?
//					if(listCookie[i].getName().equals("email")){
//					}if(listCookie[i].getName().equals("password")) {
//					password = listCookie[i].getValue();				
//					}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//		// Duyệt qua từng cookie trong list
//		// Trả giá trị (tham số) cho file jsp ở requestDispatcher
//		req.setAttribute("email", email);
//		req.setAttribute("password", password);
//
//		req.getRequestDispatcher("login.jsp").forward(req, resp);
//		// tên phải trùng với file html.
//	}
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		// Bước 1: Nhận tham số người dùng truyền lên(doPOST)
//		
//		String email =req.getParameter("email");
//		
//		
//		String password = req.getParameter("password");
//		
//		String remember = req.getParameter("remember");
//		
//		// Bước 2: Chuẩn bị câu query (truy vấn)
//		String query ="SELECT *\r\n"
//				+ "FROM users u \r\n"
//				+ "WHERE u.email = ? AND u.password = ? ";
//		
//		// Bước 3: Mở kết nối cơ sở dữ liệu 
//		Connection connection = MysqlConfig.getConnection();
//		
//		//Bước 4: Truyền câu query vào CSDL vừa mở kết nối thông qua PrepareStatement
//		try {
//		PreparedStatement preparedStatement =  connection.prepareStatement(query);
//		// Gán giá trị tham số dấu chấm ? trong câu query
//		preparedStatement.setString(1, email);
//		preparedStatement.setString(2, password);
//		
//		// Bước 5: thực thi câu query
//		// Có 2 cách thực thi: 
//		// executeQuery : Dành cho câu truy vấn là câu SELECT => luôn trả ra ResultSet
//		// exxcuteUpdate : Dành cho tất cả câu truy vấn còn lại ngoài câu SELECT. VD : INSERT, UPDATE, DELETE,...
//		ResultSet resultSet =  preparedStatement.executeQuery();
//		// tạo list UserEntity để lưu trữ từng dòng dữ liệu query được
//		List<UserEntity> listUser = new ArrayList<UserEntity>();
//		
//		// Bước 6: Duyệt từng dòng dữ liệu query được và gán vào List<UserEntity>
//		while (resultSet.next()) {
//			UserEntity entity = new UserEntity();
//			entity.setId(resultSet.getInt("id")); // resultSet.getInt(Tên cột Database) Lấy giá trị
//			// của cột id gán vào thuộc tính id của userentity
//			entity.setFullname(resultSet.getString("fullname"));
//			
//			listUser.add(entity);
//		}
//		// Kiểm tra đăng nhập bằng cách kiểm tra listuser có giá trị hay không
//		if (listUser.size()>0) {
//			System.out.println("Đăng nhập thành công!");
//			// Nếu khác null thì người dùng có click vào ô "lưu tài khoản"
//			if(remember != null) {
//				Cookie cookieEmail = new Cookie("email", email);
//				Cookie cookiePassword = new Cookie("password", password);
//				
//				resp.addCookie(cookieEmail);
//				resp.addCookie(cookiePassword);
//				
//				
//				
//			}
//			
//		}else {
//			System.out.println("Đăng nhập thất bại!");
//		}
//		 
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//		
//		
//		req.getRequestDispatcher("login.jsp").forward(req, resp);
//		
//	}
//	
//	
//
//}
