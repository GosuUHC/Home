package controller.backController;

import java.io.PrintWriter;

import DB.UsersUtil;
import controller.IBack;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DelController implements IBack {

    public void action(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
        } catch (Exception ex) {
        }
        HttpSession session = request.getSession();

        String[] data = new String[5];

        data[0] = (String) session.getAttribute("logg");
        data[1] = ((String) request.getParameter("Surname")).trim();
        data[2] = ((String) request.getParameter("Name")).trim();
        data[3] = ((String) request.getParameter("MiddleName")).trim();
        data[4] = ((String) request.getParameter("Count")).trim();

        try {
            UsersUtil.deleteUserRow(data);
        } catch (Exception e) {
            printWriter.write("NO");
            printWriter.write(data[0]);
            printWriter.write(data[1]);
            printWriter.write(data[2]);
            printWriter.write(data[3]);
            printWriter.write(data[4]);
        }

    }

}
