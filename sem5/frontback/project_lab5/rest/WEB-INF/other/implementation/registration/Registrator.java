package other.implementation.registration;

import java.sql.SQLException;

import other.implementation.authentication.User;
import other.interfaces.IRegistrator;
import other.interfaces.IUsersRepository;

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
