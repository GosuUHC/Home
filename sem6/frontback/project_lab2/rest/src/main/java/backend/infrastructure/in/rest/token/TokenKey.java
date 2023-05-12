package backend.infrastructure.in.rest.token;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

class TokenKey {
    private Key key;

    public TokenKey() {
        String keyStr = "318d166aead30a37bed1931fb6269fc0c216cb719d7c53e19aac526cdbd1c2f3e3ee508b896f306ff35915980d1"
                +
                "2aad82235fc5094aa82e44f4d91c267c91e5e800bf7a7a976417b85c991eb69b1e397ed9d5e160ad3d0dc9be14e916adf8b91ba3c92f"
                +
                "3822202877ccf5e08f100beb6ab2829f521f054947945c55ffdaf87fd";
        key = new SecretKeySpec(keyStr.getBytes(), "HmacSHA256");
    }

    public Key getKey() {
        return key;
    }
}

class TokenKeyFactory {
    private static TokenKey tokenKey = null;

    private static TokenKey _createInstance() {
        return new TokenKey();
    }

    public static TokenKey createInstance() {
        if (tokenKey == null) {
            tokenKey = _createInstance();
        }
        return tokenKey;
    }
}
