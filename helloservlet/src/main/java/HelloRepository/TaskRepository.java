package HelloRepository;

import java.io.ObjectInputFilter.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.JobEntity;
import helloservlet.entity.RoleEntity;
import helloservlet.entity.StatusEntity;
import helloservlet.entity.TaskEntity;
import helloservlet.entity.UserEntity;

public class TaskRepository {
	public List<TaskEntity> getTasks() {
		List<TaskEntity> list = new ArrayList<>();
		try {
			Connection connection = MysqlConfig.getConnection();
			String query = "select t.id, t.name as task_name, j.name as job_name,u.firstname, u.lastname, t.start_date, t.end_date, s.name as status_name\n"
					+ "from tasks t inner join users u on t.user_id = u.id\n"
					+ "inner join status s on t.status_id = s.id\n" + "inner join jobs j on t.job_id = j.id";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TaskEntity task = new TaskEntity();
				task.setId(rs.getInt("id"));
				task.setName(rs.getString("task_name"));
				task.setStartDate(rs.getString("start_date"));
				task.setEndDate(rs.getString("end_date"));

				JobEntity jobEntity = new JobEntity();
				jobEntity.setName(rs.getString("job_name"));
				task.setIdJob(jobEntity);

				UserEntity userEntity = new UserEntity();
				userEntity.setFullname(rs.getString("firstname") + " " + rs.getString("lastname"));
				task.setIdUser(userEntity);

				StatusEntity statusEntity = new StatusEntity();
				statusEntity.setName(rs.getString("status_name"));
				task.setIdStatus(statusEntity);

				list.add(task);
			}

			connection.close();
		} catch (Exception e) {
			System.out.println("Error get tasks " + e.getMessage());
		}
		return list;
	}

	public int insertTask(String name, String starDay, String endDay, int idUser, int idJob) {
		int result = 0;
		try {
			Connection connection = MysqlConfig.getConnection();
			String query = "insert into tasks (name, start_date, end_date, user_id, job_id, status_id)\n"
					+ "values (?, ?, ?, ?, ?, 1)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, starDay);
			ps.setString(3, endDay);
			ps.setInt(4, idUser);
			ps.setInt(5, idJob);

			result = ps.executeUpdate();

			connection.close();
		} catch (Exception e) {
			System.out.println("Error insert task " + e.getMessage());
		}
		return result;
	}

	public int deleteTaskById(int id) {
		int result = 0;
		try {
			Connection connection = MysqlConfig.getConnection();
			String query = "delete from tasks t where t.id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			result = ps.executeUpdate();

			connection.close();
		} catch (Exception e) {
			System.out.println("Error delete task " + e.getMessage());
		}
		return result;
	}

	public int updateTask(String name, String starDay, String endDay, int idUser, int idJob, int idStatus, int id) {
		int result = 0;
		try {
			Connection connection = MysqlConfig.getConnection();
			String query = "update tasks set name=?, start_date=?, end_date=?, user_id=?, job_id=?, status_id=? where id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, starDay);
			ps.setString(3, endDay);
			ps.setInt(4, idUser);
			ps.setInt(5, idJob);
			ps.setInt(6, idStatus);
			ps.setInt(7, id);
			result = ps.executeUpdate();

			connection.close();
		} catch (Exception e) {
			System.out.println("Error update task " + e.getMessage());
		}
		return result;
	}

	public List<TaskEntity> findTaskByUserId(int userId) {
		List<TaskEntity> tasks = new ArrayList<>();
		try {
			String query ="SELECT *\r\n"
					+ "FROM tasks t \r\n"
					+ "WHERE user_id = ?";
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				   TaskEntity task = new TaskEntity();
	                task.setId(rs.getInt("id"));
	                task.setName(rs.getString("name"));
	                task.setStartDate(rs.getString("start_date"));
	                task.setEndDate(rs.getString("end_date"));
	                JobEntity jobEntity = new JobEntity();
					jobEntity.setName(rs.getString("job_name"));
					task.setIdJob(jobEntity);

					UserEntity userEntity = new UserEntity();
					userEntity.setFullname(rs.getString("firstname") + " " + rs.getString("lastname"));
					task.setIdUser(userEntity);

					StatusEntity statusEntity = new StatusEntity();
					statusEntity.setName(rs.getString("status_name"));
					task.setIdStatus(statusEntity);

					tasks.add(task);
				
			}

			connection.close();

		} catch (Exception e) {
			System.out.println("Error findTaskByUserId " + e.getMessage());
		}

		return tasks;
	}

	public List<TaskEntity> findTaskById(int id) {
		List<TaskEntity> tasks = new ArrayList<>();
		try {
			String query ="SELECT *\r\n"
					+ "FROM tasks t \r\n"
					+ "WHERE id = ?";
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TaskEntity task = new TaskEntity();
				task.setId(rs.getInt("id"));
				task.setName(rs.getString("name"));
				task.setStartDate(rs.getString("start_date"));
				task.setEndDate(rs.getString("end_date"));
				UserEntity userEntity = new UserEntity();
				userEntity.setFullname(rs.getString("firstname") + " " + rs.getString("lastname"));
				task.setIdUser(userEntity);
				JobEntity jobEntity = new JobEntity();
				jobEntity.setName(rs.getString("job_name"));
				task.setIdJob(jobEntity);
				StatusEntity statusEntity = new StatusEntity();
				statusEntity.setName(rs.getString("status_name"));
				task.setIdStatus(statusEntity);
				
			}

			connection.close();

		} catch (Exception e) {
			System.out.println("Error findTaskByUserId " + e.getMessage());
		}

		return tasks;
	}

}
