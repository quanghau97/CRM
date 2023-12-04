package helloservice;

import java.util.List;

import HelloRepository.RoleRespository;
import HelloRepository.UserRespository;
import helloservlet.entity.RoleEntity;
import helloservlet.entity.UserEntity;

public class UserService {

	private UserRespository userRespository = new UserRespository();
	private RoleRespository roleRespository = new RoleRespository();

	public List<UserEntity> getAllUsers(){
		
		
		return userRespository.getAllUsers();
	}
	public boolean userAdd(String firstname, String lastname, String email, String password, int phone, int roleId) {
		
		int count = userRespository.userAdd(firstname, lastname, email, password, phone, roleId);
		return count>0;
		
	}
	public boolean deleteUser(int id) {
		return userRespository.deleteById(id) > 0;
	}
	public boolean updateUser(String email, String password, int roleId, String firstname, String lastname, int id) {

		int count = userRespository.updateUser(email, password, roleId, firstname, lastname, roleId, id);
		return count > 0;
	}
	public UserEntity findUserById(int id) {
        return userRespository.findUserById(id);
    }
}
