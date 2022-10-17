package db;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.pojo.Item;
import db.pojo.Order;
import db.pojo.factory.ItemFactory.ItemFactory;

public class UsersUtil {

    private static ResultSet getOrdersData(String login) throws SQLException, ClassNotFoundException {
        String query = "select * from orders where user_login = ?";
        PreparedStatement ps = DBUtil.getConnection().prepareStatement(query);
        ps.setString(1, login);
        ResultSet rsUserData = null;
        try {
            rsUserData = ps.executeQuery();
            return rsUserData;
        } catch (SQLException e) {
            throw e;
        } 
    }

    public static ArrayList<Order> getOrdersPojo(String login) throws SQLException, ClassNotFoundException {
        ResultSet rs = getOrdersData(login);
        ArrayList<Order> ordersList = new ArrayList<Order>();

        while (rs.next()) {
            Order order = new Order();
            order.setId(rs.getInt(1));
            order.setUserLogin(rs.getString("user_login"));
            order.setType(rs.getString("item_type"));
            order.setItemid(rs.getInt("item_id"));
            order.setItemCount(rs.getString("item_count"));
            order.setPrice(rs.getString("price"));
            order.setItem(getPartById(order.getItemid(), order.getType()));

            ordersList.add(order);
        }
        DBUtil.dbDisconnect();
        return ordersList;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println(getOrdersPojo("user1"));
    }

    public static Item getPartById(int id, String type) throws SQLException {
        String query = "select * from " + type + " where id = ?";
        PreparedStatement ps = DBUtil.getConnection().prepareStatement(query);
        ps.setInt(1, id);

        try {
            ResultSet rsUserData = ps.executeQuery();
            if (rsUserData.next()) {
                Item item;
                ItemFactory itemFactory = ItemFactory.getFactoryObject();
                item = itemFactory.createItem(type);
                item.setAll(rsUserData);
                
                return item;
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            DBUtil.dbDisconnect();
        }
        return null;
    }

    public static ArrayList<Item> getPartsByType(String type) throws SQLException {
        String query = "select * from " + type;
        PreparedStatement ps = DBUtil.getConnection().prepareStatement(query);

        try {
            ResultSet rsUserData = ps.executeQuery();
            ArrayList<Item> allItems = new ArrayList<Item>();
            ItemFactory itemFactory = ItemFactory.getFactoryObject();
            while (rsUserData.next()) {
                Item item = itemFactory.createItem(type);
                item.setAll(rsUserData);
                allItems.add(item);
            }
            return allItems;

        } catch (SQLException e) {
            throw e;
        } finally {
        }
    }

    public static void addOrder(String user, int itemid, String itemCount, String itemType)
            throws SQLException, NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {

        String update = "insert into orders values (?, ?, ?, ?, ?, ?)";
        String query = "select max(id) from orders"; // get maxid
        PreparedStatement ps = DBUtil.getConnection().prepareStatement(query);
        ResultSet idSet = ps.executeQuery();
        if (idSet.next()) {
            int newId = idSet.getInt(1) + 1;
            Item item = getPartById(itemid, itemType);

            // reflect
            Method method = item.getClass().getDeclaredMethod("getPrice");
            String price = (String) method.invoke(item);
            // reflect end

            String finalPrice = String.valueOf(Integer.valueOf(price) * Integer.valueOf(itemCount));

            ps = DBUtil.getConnection().prepareStatement(update);
            ps.setInt(1, newId);
            ps.setString(2, user);
            ps.setInt(3, itemid);
            ps.setString(4, itemCount);
            ps.setString(5, finalPrice);
            ps.setString(6, itemType);
            ps.executeUpdate();

        }

    }

    public static void deleteOrder(int id) throws SQLException {
        String update = "delete from orders where id = ?";
        PreparedStatement ps = DBUtil.getConnection().prepareStatement(update);
        ps.setInt(1, id);

        ps.executeUpdate();
    }

    public static boolean authorizeDB(String login, String password) throws SQLException, ClassNotFoundException {
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
        } finally {
            DBUtil.dbDisconnect();   
        }
    }

    public static void addUser(String login, String password) throws SQLException {
        String update = "insert into users values(?, ?)";
        PreparedStatement ps = DBUtil.getConnection().prepareStatement(update);
        ps.setString(1, login);
        ps.setString(2, password);
        ps.executeUpdate();
    }

}
