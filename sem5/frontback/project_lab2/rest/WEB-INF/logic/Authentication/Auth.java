package logic.Authentication;

import DB.UsersUtil;

public class Auth {

    public static boolean authorize(AuthData authData) {
        String login = authData.getLogin();
        String password = authData.getPassword();

        try {
            if (!UsersUtil.authorizeDB(login, password)) {

                return false; // not in DB
            }
            return true;

        } catch (Exception e) {
            return false;
        }
    }

}
