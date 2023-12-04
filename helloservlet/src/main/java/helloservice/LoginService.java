package helloservice;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;


import HelloRepository.UserRespository;
import helloservlet.entity.UserEntity;

public class LoginService {
	
		
	private UserRespository userRespository = new UserRespository();
	
	public boolean checkLogin(String email, String password, String remember, HttpServletResponse resp) {
		List<UserEntity> list = userRespository.findByEmailAndPassword(email, password);
		boolean isSuccess = list.size()>0;
		if(isSuccess && remember != null) {
			Cookie cookieEmail = new Cookie("email", email);
			Cookie cookiePassword = new Cookie("password", password);
			
			resp.addCookie(cookieEmail);
			resp.addCookie(cookiePassword);	
		}
		
		return isSuccess;
		
	}

}
