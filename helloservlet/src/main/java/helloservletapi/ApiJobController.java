package helloservletapi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import helloservice.JobService;
import helloservlet.payload.BasicResponse;

@WebServlet(name = "apiJobController", urlPatterns = { "/api/groupwork"})
public class ApiJobController extends HttpServlet {
	private JobService jobService = new JobService();
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		boolean isSuccess = jobService.deleteJobById(id);

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


}
