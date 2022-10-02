package controller.backController;

import DB.UsersUtil;
import controller.IBack;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddingController implements IBack {

    public void action(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String[] attrs = new String[5];
        attrs[0] = (String) session.getAttribute("logg");
        attrs[1] = (String) request.getParameter("addingSurname");
        attrs[2] = (String) request.getParameter("addingName");
        attrs[3] = (String) request.getParameter("addingMiddleName");
        attrs[4] = (String) request.getParameter("addingCount");

        try {
            UsersUtil.insertUserData(attrs);
        } catch (Exception e) {

        }

    }

}
