package other.interfaces;

import model.interfaces.repository.IRepository;
import other.implementation.authentication.AuthData;

public interface IUsersRepository extends IRepository {
    public boolean authorize(AuthData authData) throws Exception;

    public void addNewUser(AuthData authData) throws Exception;

    public void deleteUser(AuthData authData) throws Exception;

    public void updateUser(AuthData authData) throws Exception;
}
