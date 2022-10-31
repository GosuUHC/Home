package db.repository.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBUtil;
import other.implementation.authentication.AuthData;
import other.interfaces.IUsersRepository;

public class UsersRepository implements IUsersRepository {

    @Override
    public void addNewUser(AuthData authData) throws SQLException {
        String update = "insert into users values(?, ?)";
        PreparedStatement ps = DBUtil.getConnection().prepareStatement(update);
        ps.setString(1, authData.getLogin());
        ps.setString(2, authData.getPassword());

        ps.executeUpdate();
        DBUtil.dbDisconnect();
    }

    @Override
    public boolean authorize(AuthData authData) throws SQLException {
        String query = "select * from users where login = ? and password = ?";
        PreparedStatement ps = DBUtil.getConnection().prepareStatement(query);
        ps.setString(1, authData.getLogin());
        ps.setString(2, authData.getPassword());
        ResultSet rsUsers = ps.executeQuery();

        boolean authorized = rsUsers.next();
        DBUtil.dbDisconnect();

        return authorized;

    }

    @Override
    public void deleteUser(AuthData authData) throws Exception {

    }

    @Override
    public void updateUser(AuthData authData) throws Exception {

    }

}
