package backend.rest.token;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

class TokenIssuer {
    private Key key;

    public TokenIssuer(Key lKey) {
        key = lKey;
    }

    static enum Roles {
        Admin, User;
    };

    public String issueToken(String username) {

        LocalDateTime expiryPeriod = LocalDateTime.now().plusMinutes(600L);
        Date expirationDateTime = Date.from(
                expiryPeriod.atZone(ZoneId.systemDefault())
                        .toInstant());

        String compactJws = Jwts.builder()
                .setSubject(username)
                .claim("scope", Roles.User.name())
                .signWith(key, SignatureAlgorithm.HS256)
                .setIssuedAt(new Date())
                .setExpiration(expirationDateTime)
                .compact();

        return compactJws;
    }
}

class TokenIssuerFactory {
    private static TokenIssuer tokenIssuer = null;

    private static TokenIssuer _createInstance() {
        return new TokenIssuer(TokenKeyFactory.createInstance().getKey());
    }

    public static TokenIssuer createInstance() {
        if (tokenIssuer == null) {
            tokenIssuer = _createInstance();
        }
        return tokenIssuer;
    }
}
