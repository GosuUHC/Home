package backend.other.interfaces;

import backend.model.interfaces.repositories.IRepository;
import backend.other.implementation.authentication.User;

public interface IUsersRepository extends IRepository {
    public boolean authorize(User user) throws Exception;

    public void addNewUser(User user) throws Exception;

    public void deleteUser(User user) throws Exception;

    public void updateUser(User user) throws Exception;
}
