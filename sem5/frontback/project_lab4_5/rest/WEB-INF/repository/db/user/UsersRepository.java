package repository.db.user;

import java.sql.SQLException;
import java.util.List;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.UserTransaction;
import other.implementation.authentication.User;
import other.interfaces.IUsersRepository;
import repository.db.user.Entities.EUser;

public class UsersRepository implements IUsersRepository {

    @PersistenceUnit(unitName = "local_pg_pcParts_PersistenceUnit")
    private EntityManagerFactory entityManagerFactory;

    @Resource
    private UserTransaction userTransaction;

    @Override
    public void addNewUser(User user) throws SQLException {
        EntityManager entityManager;
        try {
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            return;
        }
        try {
            userTransaction.begin();
            entityManager.joinTransaction();

            EUser userPersist = new EUser();
            userPersist.setLogin(user.getLogin());
            userPersist.setPassword(user.getPassword());
            entityManager.persist(userPersist);

            userTransaction.commit();

        } catch (Exception e) {
            //// throw some exc
        }

    }

    @Override
    public boolean authorize(User user) throws SQLException {

        EntityManager entityManager;
        try {
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            return false;
        }
        try {
            userTransaction.begin();
            entityManager.joinTransaction();

            List<EUser> userFind = entityManager
                    .createQuery("SELECT u FROM EUser u WHERE u.login = :login AND u.password = :password", EUser.class)
                    .setParameter("login", user.getLogin()).setParameter("password", user.getPassword())
                    .getResultList();

            userTransaction.commit();
            return userFind.size() == 1;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public void deleteUser(User user) throws Exception {

    }

    @Override
    public void updateUser(User user) throws Exception {

    }

}
