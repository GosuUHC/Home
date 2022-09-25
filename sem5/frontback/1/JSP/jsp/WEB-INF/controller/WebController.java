package controller;

import java.io.PrintWriter;

import controller.backController.AddingController;
import controller.backController.AuthController;
import controller.backController.DelController;
import controller.backController.MainController;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class WebController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
		} catch (Exception ex) {
		}
		String servletPath = request.getServletPath();

		IBack backController;

		if (servletPath.equals("/auth")) {
			forwardJSP("auth", request, response, printWriter);

		} else if (servletPath.equals("/main")) {

			if (isFirstConnection(request, response)) {
				backController = new AuthController();

			} else {
				backController = new MainController();
			}
			backController.action(request, response);

		} else if (servletPath.equals("/adding")) {
			if (isFirstConnection(request, response)) {
				forwardJSP("auth", request, response, printWriter);

			} else {
				forwardJSP("adding", request, response, printWriter);
				
			}

		} else {
			forwardJSP("auth", request, response, printWriter);
		}

	}

	private boolean isFirstConnection(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		Object login = session.getAttribute("logg");
		Object password = session.getAttribute("passs");

		if (login == null || password == null) {
			return true;
		}
		return false;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
		} catch (Exception ex) {
		}

		try {

			String servletPath = request.getServletPath();
			IBack BackController;

			if (servletPath.equals("/adding")) {
				forwardJSP("adding", request, response, printWriter);
				BackController = new AddingController();
				BackController.action(request, response);
				return;
			}

			if (servletPath.equals("/delete")) {
				BackController = new DelController();
				BackController.action(request, response);
				BackController = new MainController();
				BackController.action(request, response);
				return;
			}

			if (servletPath.equals("/main")) {
				BackController = new AuthController();
				BackController.action(request, response);
				BackController = new MainController();
				BackController.action(request, response);
				return;
			}

		} catch (Exception ex) {
			printWriter.println("Error: " + ex.getMessage());
		}
	}

	private void forwardJSP(String view, HttpServletRequest request, HttpServletResponse response,
			PrintWriter printWriter) {
		try {
			request.getRequestDispatcher("/WEB-INF/views/" + view + ".jsp").forward(request, response);

		} catch (Exception ex) {
			printWriter.println("Error: " + ex.getMessage());
		}
	}
}