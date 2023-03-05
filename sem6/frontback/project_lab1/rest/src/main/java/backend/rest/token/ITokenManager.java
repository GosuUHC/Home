package backend.rest.token;


public interface ITokenManager {
    public boolean checkToken(String login, String token);

    public String generateToken(String login);
}
