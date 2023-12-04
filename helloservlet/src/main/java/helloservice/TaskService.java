package helloservice;

import java.util.List;

import HelloRepository.JobRepository;
import HelloRepository.StatusRepository;
import HelloRepository.TaskRepository;
import HelloRepository.UserRespository;
import helloservlet.entity.JobEntity;
import helloservlet.entity.TaskEntity;

public class TaskService {
	private TaskRepository taskRepository = new TaskRepository();
	private UserRespository userRespository = new UserRespository();
	private JobRepository jobRepository = new JobRepository();
	private StatusRepository statusRepository = new StatusRepository();

	public List<TaskEntity> getAllTask() {

		return taskRepository.getTasks();
	}
	public boolean insert(String name, String starDay, String endDay, int idUser, int idJob) {
		int count = taskRepository.insertTask(name, starDay, endDay, idUser, idJob);
		return count > 0;
	}
	public boolean deleteTask(int id) {
		return taskRepository.deleteTaskById(id)>0;
	}
	public boolean updateTask(String name, String starDay, String endDay, int idUser, int idJob, int idStatus, int id) {
		int count = taskRepository.updateTask(name, starDay, endDay, idUser, idJob, idStatus, id);
		return count > 0;
    }
	public List<TaskEntity> findTasksByUserId(int id) {

		return taskRepository.findTaskByUserId(id);
	}
	public List<TaskEntity> findTasksId(int id) {

		return taskRepository.findTaskById(id);
	}	

}
