package helloservletapi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import helloservice.RoleService;
import helloservlet.entity.RoleEntity;
import helloservlet.payload.BasicResponse;

@WebServlet(name = "apiRoleController", urlPatterns = { "/api/role"})
public class ApiRoleController extends HttpServlet {

	private RoleService roleService = new RoleService();

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		boolean isSuccess = roleService.deleteRole(id);

		/**
		 * { "statusCode" : 200, "message" : ""' "data" : true => dymanic }
		 * 
		 * payload:response, request
		 * 
		 */
		Gson gson = new Gson();

		BasicResponse basicResponse = new BasicResponse();
		basicResponse.setStatusCode(200);
		basicResponse.setMessage("");
		basicResponse.setData(isSuccess);

		String dataJson = gson.toJson(basicResponse);

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(dataJson);
		out.flush();
		System.out.println("Kiểm tra delete " + id);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Kiem tra get");
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String desc = req.getParameter("desc");
		boolean isSuccess = roleService.edit(desc, name,id);
		Gson gson = new Gson();

		BasicResponse basicResponse = new BasicResponse();
		basicResponse.setStatusCode(200);
		basicResponse.setMessage("");
		basicResponse.setData(isSuccess);

		String dataJson = gson.toJson(basicResponse);

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(dataJson);
		out.flush();
		System.out.println("Kiểm tra update " + id + name + desc);
	}
	

	
}
