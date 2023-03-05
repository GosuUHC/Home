package backend.other.implementation.registration;

import java.sql.SQLException;

import backend.other.implementation.authentication.User;
import backend.other.interfaces.IRegistrator;
import backend.other.interfaces.IUsersRepository;

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
