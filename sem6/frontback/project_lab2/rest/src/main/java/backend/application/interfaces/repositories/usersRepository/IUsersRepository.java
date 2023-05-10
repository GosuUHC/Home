package backend.application.interfaces.repositories.usersRepository;

import backend.application.implementation.authentication.User;
import backend.application.interfaces.repositories.IRepository;

public interface IUsersRepository extends IRepository {
    public boolean authorize(User user) throws Exception;

    public void addNewUser(User user) throws Exception;

    public void deleteUser(User user) throws Exception;

    public void updateUser(User user) throws Exception;

    public String getUserRole(User user) throws Exception;
}
