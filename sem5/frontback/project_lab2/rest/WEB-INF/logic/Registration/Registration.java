package logic.Registration;

import java.sql.SQLException;

import DB.UsersUtil;

public class Registration {

    public static boolean register(String login, String password) {
        try {
            UsersUtil.addUser(login, password);
            return true;
        } catch (SQLException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
        
    }
}
