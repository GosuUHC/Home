package backend.rest.token;

public interface ITokenManager {
    public boolean checkToken(String token);

    public String generateToken(String login);
}
