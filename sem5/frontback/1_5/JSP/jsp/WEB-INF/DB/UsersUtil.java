package DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersUtil {

    private static ResultSet getData(String login) throws SQLException, ClassNotFoundException {
        String query = "select * from userdata where userlogin = ?";
        PreparedStatement ps = DBUtil.getConnection().prepareStatement(query);
        ps.setString(1, login);

        try {
            ResultSet rsUserData = ps.executeQuery();

            return rsUserData;
        } catch (SQLException e) {
            throw e;
        }
    }

    public static ArrayList<Person> getFormattedData(String login) throws SQLException, ClassNotFoundException {
        ResultSet rs = getData(login);
        ArrayList<Person> personsList = new ArrayList<Person>();

        while (rs.next()) {
            Person person = new Person();
            person.setSurname(rs.getString("surname"));
            person.setName(rs.getString("name"));
            person.setMiddleName(rs.getString("middlename"));
            person.setCount(rs.getString("count"));
            personsList.add(person);
        }
        return personsList;
    }

    public static boolean authorize(String login, String password) throws SQLException, ClassNotFoundException {
        String query = "select * from users where login = ? and password = ?";
        PreparedStatement ps = DBUtil.getConnection().prepareStatement(query);
        ps.setString(1, login);
        ps.setString(2, password);
        try {
            ResultSet rsUsers = ps.executeQuery();

            if (rsUsers.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void deleteUserRow(String data[]) throws SQLException, ClassNotFoundException {
        String update = "delete from userdata where userlogin = ? and surname = ? and name = ? and middlename = ? and count = ?";

        PreparedStatement ps = DBUtil.getConnection().prepareStatement(update);
        for (int i = 1; i <= data.length; i++) {
            ps.setString(i, data[i - 1]);
        }

        ps.executeUpdate();
    }

    public static void insertUserData(String[] data) throws SQLException, ClassNotFoundException {
        String update = "insert into userdata values(?, ?, ?, ?, ?)";
        PreparedStatement ps = DBUtil.getConnection().prepareStatement(update);
        for (int i = 1; i <= data.length; i++) {
            ps.setString(i, data[i - 1]);
        }
        ps.executeUpdate();
    }
}
