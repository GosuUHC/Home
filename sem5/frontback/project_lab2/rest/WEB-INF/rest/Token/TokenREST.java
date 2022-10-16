package rest.Token;

import logic.Authentication.Token;

public class TokenREST {

    public static boolean checkToken(String login, String token) {
        return Token.checkToken(login, token);
    }

    public static String generateToken(String login) {
        return Token.generateToken(login);
    }
}
