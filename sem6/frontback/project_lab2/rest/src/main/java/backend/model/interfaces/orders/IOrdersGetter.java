package backend.model.interfaces.orders;

import java.util.Collection;

import backend.model.interfaces.repositories.ordersRepository.IOrdersRepository;
import backend.model.pojo.Order;

public interface IOrdersGetter {
    public Collection<Order> findAllByUsername(String login);

    public Order findById(int ordid);

    public void injectOrdersRepository(IOrdersRepository ordersRepository);
}
