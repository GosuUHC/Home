package other.interfaces;

import other.implementation.authentication.User;

public interface IRegistrator {
    public boolean register(User authData);

    public void injectUsersRepository(IUsersRepository usersRepository);
}
