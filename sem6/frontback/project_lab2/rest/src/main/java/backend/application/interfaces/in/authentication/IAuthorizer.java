package backend.application.interfaces.in.authentication;

import backend.application.implementation.authentication.User;
import backend.application.interfaces.out.repository.usersRepository.IUsersRepository;

public interface IAuthorizer {
    public boolean authorize(User user);

    public String getUserRole(User user);

    public void injectUsersRepository(IUsersRepository usersRepository);
}
