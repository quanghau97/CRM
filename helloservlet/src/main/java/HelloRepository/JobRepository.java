package HelloRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.MysqlConnection;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.JobEntity;
import helloservlet.entity.StatusEntity;
import helloservlet.entity.TaskEntity;
import helloservlet.entity.UserEntity;

public class JobRepository {
	 public List<JobEntity> getJobs() {
	        List<JobEntity> jobs = new ArrayList<>();
	        try {
	            Connection connection = MysqlConfig.getConnection();
	            String query = "select * from jobs";
	            PreparedStatement ps = connection.prepareStatement(query);
	            ResultSet rs = ps.executeQuery();

	            while(rs.next()) {
	                JobEntity job = new JobEntity();
	                job.setId(rs.getInt("id"));
	                job.setName(rs.getString("name"));
	                job.setStartDate(rs.getString("start_date"));
	                job.setEndDate(rs.getString("end_date"));
	                jobs.add(job);
	            }


	            connection.close();
	        } catch (Exception e) {
	            System.out.println("Error get jobs " + e.getMessage());
	        }
	        return jobs;
	    }
	 public int insertJob(String name, String starDate, String endDate) {
	        int result = 0;
	        try {
	            Connection connection = MysqlConfig.getConnection();
	            String query = "insert into jobs (name, start_date, end_date) values (?, ?, ?);";
	            PreparedStatement ps = connection.prepareStatement(query);
	            ps.setString(1, name );
	            ps.setString(2, starDate);
	            ps.setString(3, endDate);
	            result = ps.executeUpdate();

	            connection.close();
	        } catch (Exception e) {
	            System.out.println("Error insert job " + e.getMessage());
	        }
	        return result;
	    }
	 public int deleteJobById(int id) {
	        int result = 0;
	        try {
	            Connection connection = MysqlConfig.getConnection();
	            String query = "delete from jobs j where j.id = ?";
	            PreparedStatement ps = connection.prepareStatement(query);
	            ps.setInt(1, id);
	            result = ps.executeUpdate();

	            connection.close();
	        } catch (Exception e) {
	            System.out.println("Error delete job " + e.getMessage());
	        }
	        return result;
	    }
	 public int updateJob(String name, String starDay, String endDay, int id) {
	        int result = 0;
	        try {
	            Connection connection = MysqlConfig.getConnection();
	            String query = "update jobs set name=?, start_date=?, end_date=? where id=?";
	            PreparedStatement ps = connection.prepareStatement(query);
	            ps.setString(1, name);
	            ps.setString(2, starDay);
	            ps.setString(3, endDay);
	            ps.setInt(4, id);
	            result = ps.executeUpdate();

	            connection.close();
	        } catch (Exception e) {
	            System.out.println("Error update job " + e.getMessage());
	        }
	        return result;
	    }
	 public List<UserEntity> getUserByJobId(int jobId) {
	        List<UserEntity> users = new ArrayList<>();
	        try {
	            Connection connection = MysqlConfig.getConnection();
	            String query = "select distinct user_id, firstname, lastname\n" +
	                    "from users u inner join tasks t\n" +
	                    "on u.id = t.user_id and t.job_id = ?;";
	            PreparedStatement ps = connection.prepareStatement(query);
	            ps.setInt(1, jobId);
	            ResultSet rs = ps.executeQuery();

	            while(rs.next()) {
	                UserEntity user = new UserEntity();
	                user.setId(rs.getInt("user_id"));
	                user.setFirstname(rs.getString("firstname"));
	                user.setLastname(rs.getString("lastname"));
	                users.add(user);
	            }


	            connection.close();
	        } catch (Exception e) {
	            System.out.println("Error get users by jobId " + e.getMessage());
	        }
	        return users;
	    }
	 public List<TaskEntity> getTaskByJobIdAndUserId(int jobId, int userId) {
	        List<TaskEntity> tasks = new ArrayList<>();
	        try {
	            Connection connection = MysqlConfig.getConnection();
	            String query = "select * from tasks t\n" +
	                    "where t.user_id = ? and t.job_id = ?;";
	            PreparedStatement ps = connection.prepareStatement(query);
	            ps.setInt(1, userId);
	            ps.setInt(2, jobId);
	            ResultSet rs = ps.executeQuery();

	            while(rs.next()) {
	                TaskEntity task = new TaskEntity();
	                task.setId(rs.getInt("id"));
	                task.setName(rs.getString("name"));
	                task.setStartDate(rs.getString("start_date"));
	                task.setEndDate(rs.getString("end_date"));
	                StatusEntity statusEntity = new StatusEntity();
					statusEntity.setName(rs.getString("status_name"));
					task.setIdStatus(statusEntity);
	                tasks.add(task);
	            }


	            connection.close();
	        } catch (Exception e) {
	            System.out.println("Error get tasks by jobId and userId " + e.getMessage());
	        }
	        return tasks;
	    }
	

	
	 

}
