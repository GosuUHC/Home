package backend.application.implementation.authentication;

import java.sql.SQLException;

import backend.application.interfaces.in.authentication.IAuthorizer;
import backend.application.interfaces.out.repository.usersRepository.IUsersRepository;

public class Authorizer implements IAuthorizer {

    private IUsersRepository usersRepository;

    @Override
    public void injectUsersRepository(IUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean authorize(User user) {

        try {
            return usersRepository.authorize(user);

        } catch (SQLException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getUserRole(User user) {
        try {
            return usersRepository.getUserRole(user);

        } catch (SQLException e) {
            return "";
        } catch (Exception e) {
            return "";
        }
    }

}
