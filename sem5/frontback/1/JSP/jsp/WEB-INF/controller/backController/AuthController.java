package controller.backController;

import java.io.PrintWriter;

import DB.UsersUtil;
import controller.IBack;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthController implements IBack {

    public void action(HttpServletRequest request, HttpServletResponse response) {

        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
        } catch (Exception ex) {
        }

        Object login = request.getParameter("loginForm");
        Object password = request.getParameter("passwordForm");
        HttpSession session = request.getSession(true);

        try {
            if (UsersUtil.authorize((String) login, (String) password)) {
                session.setAttribute("logg", login);
                session.setAttribute("passs", password);
                return;
            } else {
                printWriter.write("INVALID LOGIN OR PASSWORD");
                session.removeAttribute("logg");
                session.removeAttribute("passs");
            }

        } catch (Exception e) {
            printWriter.write(e.getMessage());
        }

    }

}
