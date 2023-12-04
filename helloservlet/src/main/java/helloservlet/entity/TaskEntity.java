package helloservlet.entity;

public class TaskEntity {
	private int id;
	private String name;
	private String startDate;
	private String endDate;
	private JobEntity idJob;
	private UserEntity idUser;
	private StatusEntity idStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public JobEntity getIdJob() {
		return idJob;
	}

	public void setIdJob(JobEntity idJob) {
		this.idJob = idJob;
	}

	public UserEntity getIdUser() {
		return idUser;
	}

	public void setIdUser(UserEntity idUser) {
		this.idUser = idUser;
	}

	public StatusEntity getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(StatusEntity idStatus) {
		this.idStatus = idStatus;
	}

	
		
	

}
