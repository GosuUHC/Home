package backend.application.interfaces.authentication;

import backend.application.implementation.authentication.User;
import backend.application.interfaces.repositories.usersRepository.IUsersRepository;

public interface IAuthorizer {
    public boolean authorize(User user);

    public String getUserRole(User user);

    public void injectUsersRepository(IUsersRepository usersRepository);
}
