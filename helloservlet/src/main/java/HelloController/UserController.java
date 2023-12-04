package HelloController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helloservice.RoleService;
import helloservice.TaskService;
import helloservice.UserService;
import helloservlet.entity.RoleEntity;
import helloservlet.entity.TaskEntity;
import helloservlet.entity.UserEntity;

@WebServlet(name = "UserController", urlPatterns = { "/users", "/user-add", "/user-delete", "/user-update",
		"/user-details" })
public class UserController extends HttpServlet {
	private UserService userService = new UserService();
	private RoleService roleService = new RoleService();
	private TaskService taskService = new TaskService();
	int idEdit;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/users")) {
			System.out.println("Test path dogetalluser");
			doGetUserTable(req, resp);

		} else if (path.equals("/user-add")) {
			System.out.println("kiểm tra get");
			doGetUserAdd(req, resp);
		} else if (path.equals("/user-delete")) {
			doGetUserDelete(req, resp);
		} else if (path.equals("/user-update")) {
			doGetEdit(req, resp);
		} else if (path.equals("/user-details")) {
			doGetUserDetails(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/user-add")) {
			System.out.println("kiểm tra post");
			doPostUserAdd(req, resp);
		} else if (path.equals("/user-update")) {
			doPostEdit(req, resp);
		}
	}

	private void doGetUserTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Test getallUser");
		req.setCharacterEncoding("UTF-8");
		List<UserEntity> list = userService.getAllUsers();
		req.setAttribute("listUser", list);
		req.getRequestDispatcher("user-table.jsp").forward(req, resp);

	}

	private void doGetUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("roles", roleService.getAllRole());

		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}

	protected void doPostUserAdd(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String firstname = req.getParameter("user-firstname");
		String lastname = req.getParameter("user-lastname");
		String email = req.getParameter("user-email");
		String password = req.getParameter("user-password");
		int phone = Integer.parseInt(req.getParameter("user-phone"));
		int roleId = Integer.parseInt(req.getParameter("roleId"));

		boolean isSuccess = userService.userAdd(firstname, lastname, email, password, phone, roleId);

		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}

	private void doGetUserDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		userService.deleteUser(id);
		req.getRequestDispatcher("user-table.jsp").forward(req, resp);
	}

	private void doGetEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String emailEdit = req.getParameter("email"); // Lấy name
		String passwordEdit = req.getParameter("password");// Lấy mô tả

		String firstnameEdit = req.getParameter("firstname");
		String lastnameEdit = req.getParameter("lastname");
	

		idEdit = Integer.parseInt(req.getParameter("id"));
		System.out.println("id" + idEdit);

		req.setAttribute("emailUpdate", emailEdit);
		req.setAttribute("passwordUpdate", passwordEdit);
		req.setAttribute("roles", roleService.getAllRole());
		req.setAttribute("firstnameUpdate", firstnameEdit);
		req.setAttribute("lastnameUpdate", lastnameEdit);
	
		req.getRequestDispatcher("user-update.jsp").forward(req, resp);

	}

	private void doPostEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("UTF-8");

		 String firstname = req.getParameter("user-firstname");
			String lastname = req.getParameter("user-lastname");
			String email = req.getParameter("user-email");
			String password = req.getParameter("user-password");
			
			int roleId = Integer.parseInt(req.getParameter("roleId"));

		

		boolean isSuccess = userService.updateUser(email, password, roleId, firstname, lastname, idEdit);
		System.out.println("test " + isSuccess);

		resp.sendRedirect(req.getContextPath()+"/users");

	}

	private void doGetUserDetails(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Test getallUser");
		int id = Integer.parseInt(req.getParameter("id"));

//		List<TaskEntity> taskEntities = taskService.findTasksByUserId(id);
		req.setAttribute("user", userService.findUserById(id));
		req.setAttribute("listTask", taskService.findTasksByUserId(id));
		req.getRequestDispatcher("user-details.jsp").forward(req, resp);

	}
}
