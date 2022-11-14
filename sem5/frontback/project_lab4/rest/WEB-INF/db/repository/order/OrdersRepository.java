package db.repository.order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import db.DBUtil;
import db.repository.factory.RepositoryFactory;
import model.interfaces.repository.itemsRepository.IItemsRepository;
import model.interfaces.repository.ordersRepository.IOrdersRepository;
import model.pojo.Item;
import model.pojo.Order;
import model.pojo.Order.OrderBuilder;

public class OrdersRepository implements IOrdersRepository {

    IItemsRepository itemsRepository = (IItemsRepository) RepositoryFactory.getFactoryObject()
            .createRepository("items");

    @Override
    public void addNewOrder(Order order) {
        String update = "insert into orders values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = DBUtil.getConnection().prepareStatement(update);
            ps.setInt(1, getMaxId() + 1);
            ps.setString(2, order.getUserLogin());
            ps.setInt(3, order.getItemid());
            ps.setString(4, order.getItemCount());
            ps.setString(5, order.getPrice());
            ps.setString(6, order.getitemType());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect();
        }
    }

    @Override
    public void deleteById(int ordid) {
        String update = "delete from orders where id = ?";
        try {
            PreparedStatement ps = DBUtil.getConnection().prepareStatement(update);
            ps.setInt(1, ordid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect();
        }
    }

    @Override
    public Collection<Order> findAllByUsername(String username) {
        String query = "select * from orders where user_login = ?";
        try {
            PreparedStatement ps = DBUtil.getConnection().prepareStatement(query);
            ps.setString(1, username);
            ResultSet rsOrders = ps.executeQuery();

            Collection<Order> orders = new ArrayList<Order>();

            while (rsOrders.next()) {
                int id = rsOrders.getInt(1);
                String userLogin = rsOrders.getString(2);
                int itemid = rsOrders.getInt(3);
                String itemCount = rsOrders.getString(4);
                String price = rsOrders.getString(5);
                String itemType = rsOrders.getString(6);
                Item item = itemsRepository.findByIdAndType(itemid, itemType);

                OrderBuilder orderBuilder = new OrderBuilder(userLogin, itemType, itemid, itemCount);
                orderBuilder.setId(id);
                orderBuilder.setItem(item);
                orderBuilder.setPrice(price);
                orders.add(orderBuilder.build());
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect();
        }
        return null;
    }

    @Override
    public Order findById(int ordid) {
        String query = "select * from orders where id = ?";
        try {
            PreparedStatement ps = DBUtil.getConnection().prepareStatement(query);
            ps.setInt(1, ordid);
            ResultSet rsOrder = ps.executeQuery();

            if (rsOrder.next()) {

                int id = rsOrder.getInt(1);
                String userLogin = rsOrder.getString(2);
                String itemType = rsOrder.getString(3);
                int itemid = rsOrder.getInt(4);
                String itemCount = rsOrder.getString(5);
                String price = rsOrder.getString(6);
                Item item = itemsRepository.findByIdAndType(itemid, itemType);

                OrderBuilder orderBuilder = new OrderBuilder(userLogin, itemType, itemid, itemCount);
                orderBuilder.setId(id);
                orderBuilder.setItem(item);
                orderBuilder.setPrice(price);

                return orderBuilder.build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect();
        }
        return null;
    }

    @Override
    public int getMaxId() {
        String query = "select max(id) from orders"; // get maxid
        PreparedStatement ps;
        try {
            ps = DBUtil.getConnection().prepareStatement(query);
            ResultSet idSet = ps.executeQuery();
            if (idSet.next()) {
                return idSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(); /// /// // //
        }
        return Integer.MIN_VALUE;
    }

}
