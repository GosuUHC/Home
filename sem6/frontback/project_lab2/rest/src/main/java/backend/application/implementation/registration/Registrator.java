package backend.application.implementation.registration;

import java.sql.SQLException;

import backend.application.implementation.authentication.User;
import backend.application.interfaces.in.registration.IRegistrator;
import backend.application.interfaces.out.repository.usersRepository.IUsersRepository;

public class Registrator implements IRegistrator {

    private IUsersRepository usersRepository;

    @Override
    public void injectUsersRepository(IUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean register(User user) {
        try {
            usersRepository.addNewUser(user);
            return true;

        } catch (SQLException e) {
            return false;

        } catch (Exception e) {
            return false;
        }

    }
}
