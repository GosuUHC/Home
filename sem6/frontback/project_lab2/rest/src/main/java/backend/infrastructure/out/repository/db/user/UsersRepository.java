package backend.infrastructure.out.repository.db.user;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import backend.application.implementation.authentication.User;
import backend.application.interfaces.repositories.usersRepository.IUsersRepository;
import backend.infrastructure.out.repository.db.user.entities.EUser;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.UserTransaction;

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
            Logger.getLogger(UsersRepository.class.getName()).log(Level.WARNING, e.getMessage());
            return;
        }
        try {
            userTransaction.begin();
            entityManager.joinTransaction();

            EUser userPersist = new EUser();
            userPersist.setLogin(user.getLogin());
            userPersist.setPassword(user.getPassword());
            userPersist.setRole(user.getRole());
            entityManager.persist(userPersist);

            userTransaction.commit();

        } catch (Exception e) {
            Logger.getLogger(UsersRepository.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }

    }

    @Override
    public boolean authorize(User user) throws SQLException {

        EntityManager entityManager;
        try {
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            Logger.getLogger(UsersRepository.class.getName()).log(Level.WARNING, e.getMessage());
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
            Logger.getLogger(UsersRepository.class.getName()).log(Level.WARNING, e.getMessage());
            return false;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }

    }

    @Override
    public void deleteUser(User user) throws Exception {

    }

    @Override
    public void updateUser(User user) throws Exception {

    }

    @Override
    public String getUserRole(User user) throws Exception {
        EntityManager entityManager;
        try {
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            Logger.getLogger(UsersRepository.class.getName()).log(Level.WARNING, e.getMessage());
            return "";
        }

        try {
            userTransaction.begin();
            entityManager.joinTransaction();

            String userRole = entityManager
                    .createQuery("SELECT u FROM EUser u WHERE u.login = :login AND u.password = :password", EUser.class)
                    .setParameter("login", user.getLogin()).setParameter("password", user.getPassword())
                    .getSingleResult().getRole();

            userTransaction.commit();
            return userRole;
        } catch (Exception e) {
            Logger.getLogger(UsersRepository.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }

        return "";
    }

}
