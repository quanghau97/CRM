package HelloController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helloservice.JobService;
import helloservice.StatusService;
import helloservice.TaskService;
import helloservice.UserService;
import helloservlet.entity.JobEntity;
import helloservlet.entity.TaskEntity;

@WebServlet(name = "TaskController", urlPatterns = { "/task", "/task-add", "/task-delete", "/task-update" , "/task-details" })
public class TaskController extends HttpServlet {
	private TaskService taskService = new TaskService();
	private UserService userService = new UserService();
	private JobService jobService = new JobService();
	private StatusService statusService = new StatusService();
	int idEdit;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		System.out.println("kiem tra: " + path);
		if (path.equals("/task")) {
			System.out.println("kiem tra" + path);
			doGetTaskTable(req, resp);
		} else if (path.equals("/task-add")) {
			doGetTaskAdd(req, resp);
		} else if (path.equals("/task-delete")) {
			doGetTaskDelete(req, resp);
		} else if (path.equals("/task-update")) {
			doGetTaskUpdate(req, resp);
		} else if (path.equals("/tasl-details")) {
			doGetTasById(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/task-add")) {
			doPostTaskAdd(req, resp);
		} else if (path.equals("/task-update")) {
			doPostTaskUpdate(req, resp);

		}
	}

	protected void doGetTaskTable(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<TaskEntity> list = taskService.getAllTask();
		
		req.setAttribute("listTask", list);
		req.getRequestDispatcher("task.jsp").forward(req, resp);
	}

	private void doGetTaskAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("users", userService.getAllUsers());
		req.setAttribute("job", jobService.getAllJob());

		req.getRequestDispatcher("task-add.jsp").forward(req, resp);
	}

	private void doPostTaskAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("task-name");
		String starDay = req.getParameter("task-starDay");
		String endDay = req.getParameter("task-endDay");
		int idUser = Integer.parseInt(req.getParameter("userId"));
		int idJob = Integer.parseInt(req.getParameter("jobId"));
		name = setUTF8(name);

		boolean isSuccess = taskService.insert(name, starDay, endDay, idUser, idJob);
		req.getRequestDispatcher("task-add.jsp").forward(req, resp);

	}

	private String setUTF8(String string) {
		byte[] bytes = string.getBytes(StandardCharsets.ISO_8859_1);
		string = new String(bytes, StandardCharsets.UTF_8);

		return string;
	}

	private void doGetTaskDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		taskService.deleteTask(id);
		req.getRequestDispatcher("task.jsp").forward(req, resp);
	}

	private void doGetTaskUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("users", userService.getAllUsers());
		req.setAttribute("job", jobService.getAllJob());
		req.setAttribute("status", statusService.getAllStatus());
		idEdit = Integer.parseInt(req.getParameter("id"));

		req.getRequestDispatcher("task-update.jsp").forward(req, resp);
	}

	private void doPostTaskUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("task-name");
		String starDay = req.getParameter("task-starDay");
		String endDay = req.getParameter("task-endDay");
		int idUser = Integer.parseInt(req.getParameter("userId"));
		int idJob = Integer.parseInt(req.getParameter("jobId"));
		int idStatus = Integer.parseInt(req.getParameter("statusId"));
		name = setUTF8(name);

		boolean isSuccess = taskService.updateTask(name, starDay, endDay, idUser, idJob, idStatus, idEdit);
		
		resp.sendRedirect(req.getContextPath()+"/task");
	}
	protected void doGetTasById(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		List<TaskEntity> list = taskService.findTasksId(id);
		
		req.setAttribute("listTask", list);
		req.getRequestDispatcher("task-details.jsp").forward(req, resp);
	}
}
