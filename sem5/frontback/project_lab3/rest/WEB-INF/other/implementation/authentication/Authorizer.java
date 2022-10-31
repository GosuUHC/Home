package other.implementation.authentication;

import java.sql.SQLException;

import db.repository.factory.RepositoryFactory;
import other.interfaces.IAuthorizer;
import other.interfaces.IUsersRepository;

public class Authorizer implements IAuthorizer {

    IUsersRepository usersRepository = (IUsersRepository) RepositoryFactory.getFactoryObject()
            .createRepository("users");

    public boolean authorize(AuthData authData) {

        try {
            if (!usersRepository.authorize(authData)) {
                return false; // not in DB
            }
            return true;

        } catch (SQLException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
