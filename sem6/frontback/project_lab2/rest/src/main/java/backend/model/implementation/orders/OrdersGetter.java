package backend.model.implementation.orders;

import java.util.Collection;

import backend.model.interfaces.orders.IOrdersGetter;
import backend.model.interfaces.repositories.ordersRepository.IOrdersRepository;
import backend.model.pojo.Order;

public class OrdersGetter implements IOrdersGetter {

    private IOrdersRepository ordersRepository;

    @Override
    public void injectOrdersRepository(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Collection<Order> findAllByUsername(String login) {
        return ordersRepository.findAllByUsername(login);
    }

    @Override
    public Order findById(int ordid) {
        return ordersRepository.findById(ordid);
    }
}
