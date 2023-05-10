package backend.application.interfaces.registration;

import backend.application.implementation.authentication.User;
import backend.application.interfaces.repositories.usersRepository.IUsersRepository;

public interface IRegistrator {
    public boolean register(User authData);

    public void injectUsersRepository(IUsersRepository usersRepository);
}
