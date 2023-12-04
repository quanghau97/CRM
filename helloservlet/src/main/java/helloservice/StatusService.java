package helloservice;

import java.util.List;

import HelloRepository.StatusRepository;
import helloservlet.entity.JobEntity;
import helloservlet.entity.StatusEntity;

public class StatusService {
	private StatusRepository statusRepository = new StatusRepository();
	public List<StatusEntity> getAllStatus() {

		return statusRepository.getStatuses();
	}

}
