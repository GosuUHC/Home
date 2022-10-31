package other.implementation.registration;

import java.sql.SQLException;

import db.repository.factory.RepositoryFactory;
import other.implementation.authentication.AuthData;
import other.interfaces.IRegistrator;
import other.interfaces.IUsersRepository;

public class Registrator implements IRegistrator {

    IUsersRepository usersRepository = (IUsersRepository) RepositoryFactory.getFactoryObject()
            .createRepository("users");

    public boolean register(AuthData authData) {
        try {
            usersRepository.addNewUser(authData);
            return true;

        } catch (SQLException e) {
            return false;

        } catch (Exception e) {
            return false;
        }

    }
}
