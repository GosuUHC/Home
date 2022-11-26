package model.interfaces.get;

import java.util.Collection;

import model.interfaces.repositories.ordersRepository.IOrdersRepository;
import model.pojo.Order;

public interface IOrdersGetter {
    public Collection<Order> findAllByUsername(String login);

    public Order findById(int ordid);

    public void injectOrdersRepository(IOrdersRepository ordersRepository);
}
