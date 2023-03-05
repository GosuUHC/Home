package backend.rest.token;

import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

class TokenValidator {
    private Key key;

    public TokenValidator(Key lKey) {
        key = lKey;
    }

    public String validate(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return claims.getBody().getSubject();
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException
                | IllegalArgumentException e) {
            return "UNMATCHED ROLE";
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
