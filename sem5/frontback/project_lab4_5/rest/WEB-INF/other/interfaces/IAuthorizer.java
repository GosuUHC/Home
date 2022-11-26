package other.interfaces;

import other.implementation.authentication.User;

public interface IAuthorizer {
    public boolean authorize(User user);

    public void injectUsersRepository(IUsersRepository usersRepository);
}
