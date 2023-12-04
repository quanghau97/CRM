package HelloRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import com.mysql.cj.MysqlConnection;
import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.RoleEntity;
import helloservlet.entity.UserEntity;

public class RoleRespository {
	public int deleteById(int id) {
		int count = 0;
		String query = "DELETE FROM roles r WHERE r.id= ?";
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

	public List<RoleEntity> findAll() {
		List<RoleEntity> listRole = new ArrayList<RoleEntity>();
		String query = "SELECT * FROM roles";
		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				RoleEntity roleEntity = new RoleEntity();
				roleEntity.setId(resultSet.getInt("id"));
				roleEntity.setName(resultSet.getString("name"));
				roleEntity.setDesc(resultSet.getString("description"));

				listRole.add(roleEntity);
			}

		} catch (Exception e) {
			System.out.println("Lỗi findAll" + e.getLocalizedMessage());
			// TODO: handle exception
		}
		return listRole;
	}

	public int excuteUpdate(String name, String description) {
		String query = "INSERT INTO roles( name, description ) VALUES (?, ?)";
		Connection connection = MysqlConfig.getConnection();
		int count = 0;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// Gán giá trị tham số dấu chấm ? trong câu query
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, description);

			// Bước 5: thực thi câu query

			// exxcuteUpdate : Dành cho tất cả câu truy vấn còn lại ngoài câu SELECT. VD :
			// INSERT, UPDATE, DELETE,...
			count = preparedStatement.executeUpdate();
			System.out.println("Giá trị " + name + description);
		} catch (Exception e) {
			System.out.println("Lỗi role-add " + e.getLocalizedMessage());

		}
		return count;
	}

	public int editRolebyId(String name, String description, int id) {
		int count = 0;
//		String query = "UPDATE roles SET name = ?, description =?\r\n" + "WHERE id = ?;";
		String query = "UPDATE roles SET name = ?, description =? WHERE id = ?;";
		Connection connection = MysqlConfig.getConnection();
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, description);
			preparedStatement.setInt(3, id);

			count = preparedStatement.executeUpdate();
			System.out.println("kt" + count);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi edit" + e.getLocalizedMessage());
		}

		return count;
	}

}
