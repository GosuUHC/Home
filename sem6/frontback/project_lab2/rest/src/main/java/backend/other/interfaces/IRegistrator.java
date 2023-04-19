package backend.other.interfaces;

import backend.other.implementation.authentication.User;

public interface IRegistrator {
    public boolean register(User authData);

    public void injectUsersRepository(IUsersRepository usersRepository);
}
