package backend.infrastructure.in.rest.token;

public interface ITokenManager {
    public boolean checkToken(String token);

    public boolean checkToken(String token, String role);

    public String generateToken(String login, String role);

    public int TOKEN_LENGTH = 256;
}
