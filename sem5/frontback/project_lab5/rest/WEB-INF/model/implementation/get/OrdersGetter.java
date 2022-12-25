package model.implementation.get;

import java.util.Collection;

import model.interfaces.get.IOrdersGetter;
import model.interfaces.repositories.ordersRepository.IOrdersRepository;
import model.pojo.Order;

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
