package helloservice;

import java.util.List;

import HelloRepository.RoleRespository;
import helloservlet.entity.RoleEntity;

public class RoleService {
	private RoleRespository roleRespository = new RoleRespository();

	public boolean insert(String name, String description) {
		int count = roleRespository.excuteUpdate(name, description);
		return count > 0;
	}

	public List<RoleEntity> getAllRole() {
		return roleRespository.findAll();
	}

	public boolean deleteRole(int id) {
		return roleRespository.deleteById(id) > 0;
	}

	public boolean edit(String name, String desc, int id) {

		int count = roleRespository.editRolebyId(name, desc, id);
		return count > 0;
	}

}
