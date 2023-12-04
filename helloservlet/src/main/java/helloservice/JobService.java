package helloservice;

import java.util.ArrayList;
import java.util.List;

import HelloRepository.JobRepository;
import helloservlet.entity.JobDetailEntity;
import helloservlet.entity.JobEntity;
import helloservlet.entity.TaskEntity;
import helloservlet.entity.UserEntity;

public class JobService {
	private JobRepository jobRepository = new JobRepository();

	public List<JobEntity> getAllJob() {

		return jobRepository.getJobs();
	}

	public boolean insert(String name, String starDate, String endDate) {
		int count = jobRepository.insertJob(name, starDate, endDate);
		return count > 0;
	}
	public boolean deleteJobById(int id) {
        int result = jobRepository.deleteJobById(id);
        return result > 0;
    }
	public boolean updateJob(String name, String starDay, String endDay, int id) {

		int count = jobRepository.updateJob(name, starDay, endDay, id);
		return count > 0;
	}
	public List<JobDetailEntity> getDetailsByJobId(int jobId) {
        List<JobDetailEntity> jobDetails = new ArrayList<>();

        List<UserEntity> users = jobRepository.getUserByJobId(jobId);
        for (UserEntity user: users) {
          JobDetailEntity jobDetail = new JobDetailEntity();
            jobDetail.setIdUser(user.getId());
            jobDetail.setUserName(user.getFirstname() + " " + user.getLastname());
            List<TaskEntity> tasks = jobRepository.getTaskByJobIdAndUserId(jobId, user.getId());
            jobDetail.setTaskEntities(tasks);

            jobDetails.add(jobDetail);
        }
        return jobDetails;
    }

}
