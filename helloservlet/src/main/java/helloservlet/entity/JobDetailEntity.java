package helloservlet.entity;

import java.util.List;

public class JobDetailEntity {
	private int idUser;
	private String userName;
	List<TaskEntity> taskEntities;
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<TaskEntity> getTaskEntities() {
		return taskEntities;
	}
	public void setTaskEntities(List<TaskEntity> taskEntities) {
		this.taskEntities = taskEntities;
	}
	

}
