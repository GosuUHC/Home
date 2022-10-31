package model.interfaces.repository.ordersRepository;

import java.util.Collection;

import model.interfaces.repository.IRepository;
import model.pojo.Order;

public interface IOrdersRepository extends IRepository {
    public Order findById(int ordid);

    public Collection<Order> findAllByUsername(String username);

    public void addNewOrder(Order order);

    public void deleteById(int ordid);

    public int getMaxId();
}
