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
import helloservlet.entity.JobEntity;
import helloservlet.entity.RoleEntity;

@WebServlet(name = "JobController", urlPatterns = { "/groupwork", "/groupwork-add", "/groupwork-delete",
		"/groupwork-update", "/groupwork-details" })
public class JobController extends HttpServlet {
	private JobService jobService = new JobService();
	int idEdit;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/groupwork")) {
			doGetJobTable(req, resp);
		} else if (path.equals("/groupwork-add")) {
			doGetJobAdd(req, resp);
		} else if (path.equals("/groupwork-delete")) {
			doGetJobDelete(req, resp);
		} else if (path.equals("/groupwork-update")) {
			doGetEdit(req, resp);
		} else if (path.equals("/groupwork-details")) {
			doGetDetailsByJobId(req, resp);

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/groupwork-add")) {
			doPostJobAdd(req, resp);
		} else if (path.equals("/groupword-update")) {
			doPostJobUpdate(req, resp);
		}
	}

	private void doGetJobTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<JobEntity> list = jobService.getAllJob();
		req.setAttribute("listJob", list);
		req.getRequestDispatcher("groupwork.jsp").forward(req, resp);

	}

	private void doGetJobAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
	}

	private void doPostJobAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("job-name");
		String starDate = req.getParameter("job-starDate");
		String endDate = req.getParameter("job-endDate");

		name = setUTF8(name);

		boolean isSuccess = jobService.insert(name, starDate, endDate);
		resp.sendRedirect(req.getContextPath()+"/groupwork");

	}

	private String setUTF8(String string) {
		byte[] bytes = string.getBytes(StandardCharsets.ISO_8859_1);
		string = new String(bytes, StandardCharsets.UTF_8);

		return string;
	}

	private void doGetJobDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		jobService.deleteJobById(id);
		req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
	}

	private void doGetEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		idEdit = Integer.parseInt(req.getParameter("id"));
		req.getRequestDispatcher("groupwork-update.jsp").forward(req, resp);

	}

	private void doPostJobUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("job-name");
		String starDate = req.getParameter("job-starDate");
		String endDate = req.getParameter("job-endDate");

		name = setUTF8(name);

		boolean isSuccess = jobService.updateJob(name, starDate, endDate, idEdit);
		System.out.println("Test" + isSuccess);
		resp.sendRedirect(req.getContextPath()+"/groupwork");

	}

	private void doGetDetailsByJobId(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 int id = Integer.parseInt(req.getParameter("id"));
         req.setAttribute("jobDetails", jobService.getDetailsByJobId(id));
         req.getRequestDispatcher("/groupwork-details.jsp").forward(req, resp);
	}
}
