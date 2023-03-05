package backend.rest.token;

public class TokenREST implements ITokenManager {

    private TokenIssuer tokenIssuer;
    private TokenValidator tokenValidator;

    public TokenREST() { // singletons
        this.tokenIssuer = TokenIssuerFactory.createInstance();
        this.tokenValidator = TokenValidatorFactory.createInstance();
    }

    public boolean checkToken(String login, String token) {
        return login.equals(tokenValidator.validate(token));
    }

    @Override
    public String generateToken(String login) {
        return tokenIssuer.issueToken(login);
    }
}
