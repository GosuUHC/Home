package other.implementation.authentication;

import java.sql.SQLException;

import other.interfaces.IAuthorizer;
import other.interfaces.IUsersRepository;

public class Authorizer implements IAuthorizer {

    private IUsersRepository usersRepository;

    @Override
    public void injectUsersRepository(IUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean authorize(User user) {

        try {
            return usersRepository.authorize(user);

        } catch (SQLException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
