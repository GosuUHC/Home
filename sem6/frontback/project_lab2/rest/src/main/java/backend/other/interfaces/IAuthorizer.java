package backend.other.interfaces;

import backend.other.implementation.authentication.User;

public interface IAuthorizer {
    public boolean authorize(User user);

    public String getUserRole(User user);

    public void injectUsersRepository(IUsersRepository usersRepository);
}
