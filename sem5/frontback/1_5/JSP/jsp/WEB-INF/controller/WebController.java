package controller;

import java.io.PrintWriter;

import controller.factory.ControllerFactory;
import controller.factory.ControllerType;
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

		ControllerFactory controllerFactory = ControllerFactory.getFactoryObject();

		IBack backController;

		if (servletPath.equals("/auth")) {
			forwardJSP("auth", request, response, printWriter);

		} else if (servletPath.equals("/main")) {

			if (isFirstConnection(request, response)) {
				backController = controllerFactory.createController(ControllerType.auth);

			} else {
				backController = controllerFactory.createController(ControllerType.main);

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

			ControllerFactory controllerFactory = ControllerFactory.getFactoryObject();

			IBack backController;

			if (servletPath.equals("/adding")) {
				forwardJSP("adding", request, response, printWriter);
				backController = controllerFactory.createController(ControllerType.adding);
				backController.action(request, response);
				return;
			}

			if (servletPath.equals("/delete")) {
				backController = controllerFactory.createController(ControllerType.del);
				backController.action(request, response);

				//backController = controllerFactory.createController(ControllerType.main);
				//backController.action(request, response);
				return;
			}

			if (servletPath.equals("/main")) {
				backController = controllerFactory.createController(ControllerType.auth);
				backController.action(request, response);

				backController = controllerFactory.createController(ControllerType.main);
				backController.action(request, response);
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