package model.interfaces.get;

import java.util.Collection;

import model.pojo.Order;

public interface IOrdersGetter {
    public Collection<Order> findAllByUsername(String login);

    public Order findById(int ordid);
}
