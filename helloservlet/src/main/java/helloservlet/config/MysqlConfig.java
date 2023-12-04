package helloservlet.config;
/**
 * 
 * Clas dùng để khai báo thông tin cấu hình tạo kết nối tới CSDL
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConfig {
	
	// Hàm dùng để mở kết nối tới cơ sở dữ liệu
	public static Connection getConnection() {
		try {
		// Khai báo Diver sử dụng cho JDBC( từ khóa: tên Driver class.forname )
		Class.forName("com.mysql.cj.jdbc.Driver");
		// Khai báo thông tin CSDL mà JDBC sẽ kết nối tới
		return DriverManager.getConnection("jdbc:mysql://localhost:3307/crm_app", "root", "admin2797");
	}catch (Exception e) {
		// TODO: handle exception
		System.out.println("Lỗi kết nối CSDL" + e.getLocalizedMessage());
	}
		return null;
	}
}
