package backend.infrastructure.in.rest.token;

import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

class TokenValidator {
    private Key key;

    public TokenValidator(Key lKey) {
        key = lKey;
    }

    public boolean validate(String token) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            String regenToken = TokenIssuerFactory.createInstance().issueToken(claims);
            return token.equals(regenToken);
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException
                | IllegalArgumentException | SignatureException e) {
            return false;
        }
    }

    public boolean validate(String token, String role) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            String roleFromClaims = "";
            roleFromClaims = (String) claims.get("role");

            if (!role.equals(roleFromClaims)) {
                return false;
            }
            String regenToken = TokenIssuerFactory.createInstance().issueToken(claims);
            return token.equals(regenToken);
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException
                | IllegalArgumentException | SignatureException e) {
            Logger.getLogger(TokenValidator.class.getName()).log(Level.INFO, e.getMessage());
            return false;
        }
    }
}

class TokenValidatorFactory {
    private static TokenValidator tokenValidator = null;

    private static TokenValidator _createInstance() {
        return new TokenValidator(TokenKeyFactory.createInstance().getKey());
    }

    public static TokenValidator createInstance() {
        if (tokenValidator == null) {
            tokenValidator = _createInstance();
        }
        return tokenValidator;
    }
}
