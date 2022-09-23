package controller;

import java.io.PrintWriter;

import DB.UsersUtil;
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

		if (servletPath.equals("/auth")) {
			forwardJSP("auth", request, response, printWriter);

		} else if (servletPath.equals("/main")) {

			if (isFirstConnection(request, response)) {
				forwardJSP("auth", request, response, printWriter);

			} else {
				forwardJSP("main", request, response, printWriter);
			}

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
			HttpSession session = request.getSession(true);

			Object login = request.getParameter("loginForm");
			Object password = request.getParameter("passwordForm");

			String servletPath = request.getServletPath();

			if (servletPath.equals("/adding")) {
				String[] attrs = new String[5];
				attrs[0] = (String) session.getAttribute("logg");
				attrs[1] = (String) request.getParameter("addingSurname");
				attrs[2] = (String) request.getParameter("addingName");
				attrs[3] = (String) request.getParameter("addingMiddleName");
				attrs[4] = (String) request.getParameter("addingCount");

				UsersUtil.insertUserData(attrs);

				forwardJSP("main", request, response, printWriter);
				return;
			}

			if (servletPath.equals("/delete")) {
				// Str
				// UsersUtil.deleteUserRow(number);
			}

			if (UsersUtil.authorize((String) login, (String) password)) {
				session.setAttribute("logg", login);
				session.setAttribute("passs", password);
				
				forwardJSP("main", request, response, printWriter);
				return;
			}
			printWriter.write("INVALID USER OR PASSWORD");
			session.removeAttribute("logg");
			session.removeAttribute("passs");

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