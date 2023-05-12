package backend.infrastructure.in.rest.token;

public class TokenManager implements ITokenManager {

    private TokenIssuer tokenIssuer;
    private TokenValidator tokenValidator;

    public TokenManager() { // singletons
        this.tokenIssuer = TokenIssuerFactory.createInstance();
        this.tokenValidator = TokenValidatorFactory.createInstance();
    }

    public boolean checkToken(String token) {
        return tokenValidator.validate(token);
    }

    @Override
    public String generateToken(String login, String role) {
        return tokenIssuer.issueToken(login, role);
    }

    @Override
    public boolean checkToken(String token, String role) {
        return tokenValidator.validate(token, role);
    }
}
