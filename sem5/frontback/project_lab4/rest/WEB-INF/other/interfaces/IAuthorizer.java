package other.interfaces;

import other.implementation.authentication.AuthData;

public interface IAuthorizer {
    public boolean authorize(AuthData authData);
}
