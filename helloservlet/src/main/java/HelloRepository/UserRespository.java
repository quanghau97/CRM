package HelloRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.MysqlConnection;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.RoleEntity;
import helloservlet.entity.UserEntity;

public class UserRespository {
	public List<UserEntity> findByEmailAndPassword(String email, String password) {
		// Bước 2: Chuẩn bị câu query (truy vấn)
		String query = "SELECT *\r\n" + "FROM users u \r\n" + "WHERE u.email = ? AND u.password = ? ";

		// Bước 3: Mở kết nối cơ sở dữ liệu
		Connection connection = MysqlConfig.getConnection();
		// tạo list UserEntity để lưu trữ từng dòng dữ liệu query được
		List<UserEntity> listUser = new ArrayList<UserEntity>();

		// Bước 4: Truyền câu query vào CSDL vừa mở kết nối thông qua PrepareStatement
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// Gán giá trị tham số dấu chấm ? trong câu query
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);

			// Bước 5: thực thi câu query
			// Có 2 cách thực thi:
			// executeQuery : Dành cho câu truy vấn là câu SELECT => luôn trả ra ResultSet
			// exxcuteUpdate : Dành cho tất cả câu truy vấn còn lại ngoài câu SELECT. VD :
			// INSERT, UPDATE, DELETE,...
			ResultSet resultSet = preparedStatement.executeQuery();

			// Bước 6: Duyệt từng dòng dữ liệu query được và gán vào List<UserEntity>
			while (resultSet.next()) {
				UserEntity entity = new UserEntity();
				entity.setId(resultSet.getInt("id")); // resultSet.getInt(Tên cột Database) Lấy giá trị
				// của cột id gán vào thuộc tính id của userentity
				entity.setFirstname(resultSet.getString("firstname"));
				entity.setLastname(resultSet.getString("lastname"));

				listUser.add(entity);

			}

		} catch (Exception e) {
			System.out.println("Lỗi findByEmailAndPassword " + e.getLocalizedMessage());

		}
		return listUser;
	}

	public List<UserEntity> getAllUsers() {
		List<UserEntity> list = new ArrayList<UserEntity>();
		String query = "SELECT u.id ,u.firstname, u.lastname ,u.email ,r.name \r\n" + "FROM users u \r\n"
				+ "join roles r ON u.role_id = r.id  ";
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UserEntity user = new UserEntity();
				user.setId(resultSet.getInt("id"));
				user.setFirstname(resultSet.getString("firstname"));
				user.setLastname(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));

				// Coi lại chỗ role này đi77
				RoleEntity role = new RoleEntity();
				role.setName(resultSet.getString("name"));

				user.setIdRole(role);

				list.add(user);
			}

		} catch (Exception e) {
			System.out.println("Lỗi lấy danh sách user " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return list;
	}

	public int userAdd(String firstname, String lastname, String email, String password, int phone, int roleId) {
		String query = "INSERT INTO users (firstname, lastname,email,password,phone, role_id) Values" + " (?, ?, ?, ?, ?,?)";
		Connection connection = MysqlConfig.getConnection();
		int count = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, firstname);
			statement.setString(2, lastname);
			statement.setString(3, email);
			statement.setString(4, password);
			statement.setInt(5, phone);
			statement.setInt(6, roleId);

			count = statement.executeUpdate();
			

		} catch (Exception e) {
			System.out.println("Lỗi Add User" + e.getLocalizedMessage());
		}
		return count;

	}

	public int deleteById(int id) {
		int count = 0;
		String query = "DELETE FROM users u WHERE u.id= ?";
		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);

			count = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi Delete" + e.getLocalizedMessage());
		}
		return count;
	}
	public int updateUser(String email, String password, int roleId, String firstname, String lastname,int phone, int id) {
        int result = 0;
        try {
            Connection connection = MysqlConfig.getConnection();
            String query = "Update users \r\n"
            		+ "SET email =?, password =?, role_id =?, firstname =?, lastname =?\r\n"
            		+ "WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setInt(3, roleId);
            ps.setString(4, firstname);
            ps.setString(5, lastname);
          
            ps.setInt(6, id);
            result = ps.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error update user " + e.getMessage());
        }
        return result;
    }
	public UserEntity findUserById(int id) {
        UserEntity user = new UserEntity();
        try{
            String query = "select * from users where id = ?";
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                RoleEntity role = new RoleEntity();
				role.setName(rs.getString("role_id"));

				user.setIdRole(role);
            }

            connection.close();

        }catch (Exception e){
            System.out.println("Error findUserById " + e.getMessage());
        }

        return user;
    }
}
