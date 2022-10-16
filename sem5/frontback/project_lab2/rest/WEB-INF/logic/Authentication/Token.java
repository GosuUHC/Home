package logic.Authentication;

public class Token {
    public static boolean checkToken(String login, String token) {
        String PL = login;

        return BCrypt.checkpw(PL, token);
    }

    public static String generateToken(String login) {
        String toHash = login;

        String hashed = BCrypt.hashpw(toHash, BCrypt.gensalt(12));

        return hashed;
    }
}
