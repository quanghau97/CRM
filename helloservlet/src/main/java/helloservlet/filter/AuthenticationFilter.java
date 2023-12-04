//package helloservlet.filter;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.jasper.tagplugins.jstl.core.ForEach;
//
//import com.mysql.cj.protocol.a.NativeConstants.StringLengthDataType;
//
//import helloservlet.config.MysqlConfig;
//import helloservlet.entity.UserEntity;
//
//// Link kích hoạt filter
//@WebFilter(filterName = "authenFilter", urlPatterns = { "/role-add" })
//public class AuthenticationFilter implements Filter {
//	//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		
//		System.out.println("Đã kích hoạt filter!");
//		
//		
//		HttpServletRequest req = (HttpServletRequest)request;
//		HttpServletResponse resq = (HttpServletResponse)response;
//		
//		String contextPath = req.getContextPath();
//		
//		
//			// Lấy danh sách cookie người dùng gửi lên hệ thống:
//			Cookie[] cookies = req.getCookies();
//			String email = "";
//			String password = "";
//			for (Cookie cookie : cookies) {
//				System.out.println("Kiem tra: " + cookie.getName());
//				if(cookie.getName().equals("email")) {
//					email = cookie.getValue();
//				}
//				if(cookie.getName().equals("password")) {
//					password = cookie.getValue();
//				}
//				
//			}// Để ngoài vòng lặp for
//			// trim(): loại bỏ khoảng trắng
//			if(email.trim().length()>0 && password.trim().length()>0 ){
//				// Đã đăng nhập thành công
//				
//				// Bước 2: Chuẩn bị câu query (truy vấn)
//				String query ="SELECT *\r\n"
//						+ "FROM users u \r\n"
//						+ "WHERE u.email = ? AND u.password = ? ";
//				
//				// Bước 3: Mở kết nối cơ sở dữ liệu 
//				Connection connection = MysqlConfig.getConnection();
//				try {
//				//Bước 4: Truyền câu query vào CSDL vừa mở kết nối thông qua PrepareStatement
//				
//				// Startery 
//				
//				// Contraoller: Chỉ dùng để chứa các class khai báo đường dẫn và nhận tham số ( không sử lý logic code)
//				// Service: Chứa các class để xử lý logic cho các controller tương ứng
//                // Reposstory: Chứa các class trả ra dữ liệu c
//				PreparedStatement preparedStatement =  connection.prepareStatement(query);
//				// Gán giá trị tham số dấu chấm ? trong câu query
////				preparedStatement.setString(1, email);
////				preparedStatement.setString(2, password);
////				
//				// Bước 5: thực thi câu query
//				// Có 2 cách thực thi: 
//				// executeQuery : Dành cho câu truy vấn là câu SELECT => luôn trả ra ResultSet
//				// exxcuteUpdate : Dành cho tất cả câu truy vấn còn lại ngoài câu SELECT. VD : INSERT, UPDATE, DELETE,...
//				ResultSet resultSet =  preparedStatement.executeQuery();
//				// tạo list UserEntity để lưu trữ từng dòng dữ liệu query được
//				List<UserEntity> listUser = new ArrayList<UserEntity>();
//				
//				// Bước 6: Duyệt từng dòng dữ liệu query được và gán vào List<UserEntity>
//				while (resultSet.next()) {
//					UserEntity entity = new UserEntity();
//					entity.setId(resultSet.getInt("id")); // resultSet.getInt(Tên cột Database) Lấy giá trị
//					// của cột id gán vào thuộc tính id của userentity
//					entity.setFullname(resultSet.getString("fullname"));
//					
//					listUser.add(entity);
//				}
//				// Kiểm tra đăng nhập bằng cách kiểm tra listuser có giá trị hay không
//				if (listUser.size()>0) {
//					System.out.println("Đăng nhập thành công!");
//					// Nếu khác null thì người dùng có click vào ô "lưu tài khoản"
//					chain.doFilter(req, resq);
//				}else {
//					System.out.println("Đăng nhập thất bại!");
//					resq.sendRedirect(contextPath + "/login");
//				}
//				 
//				}catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
//			}else {
//				System.out.println("khong thay email & pass");
//				// quay lại trang đăng nhập
//				resq.sendRedirect(contextPath + "/login");
//			
//			}
//			
//	}
//}
//			
