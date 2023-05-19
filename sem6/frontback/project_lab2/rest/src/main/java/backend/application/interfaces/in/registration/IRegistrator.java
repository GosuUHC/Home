package backend.application.interfaces.in.registration;

import backend.application.implementation.authentication.User;
import backend.application.interfaces.out.repository.usersRepository.IUsersRepository;

public interface IRegistrator {
    public boolean register(User authData);

    public void injectUsersRepository(IUsersRepository usersRepository);
}
