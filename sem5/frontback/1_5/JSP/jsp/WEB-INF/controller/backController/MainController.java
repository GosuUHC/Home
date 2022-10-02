package controller.backController;

import java.util.ArrayList;

import DB.Person;
import DB.UsersUtil;
import controller.IBack;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MainController implements IBack {

    public void action(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        Object login = session.getAttribute("logg");

        ArrayList<Person> persons;
        if (login != null) {
            try {
                persons = UsersUtil.getFormattedData((String) login);
                request.setAttribute("list", persons);
                forwardJSP("main", request, response);
            } catch (Exception e) {

            }
        }
    }

    private void forwardJSP(String view, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/WEB-INF/views/" + view + ".jsp").forward(request, response);

        } catch (Exception ex) {

        }
    }

}
