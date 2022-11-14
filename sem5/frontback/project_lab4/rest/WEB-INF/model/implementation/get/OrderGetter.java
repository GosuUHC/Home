package model.implementation.get;

import java.util.Collection;

import db.repository.factory.RepositoryFactory;
import model.interfaces.get.IOrdersGetter;
import model.interfaces.repository.ordersRepository.IOrdersRepository;
import model.pojo.Order;

public class OrderGetter implements IOrdersGetter {

    IOrdersRepository ordersRepository = (IOrdersRepository) RepositoryFactory.getFactoryObject()
            .createRepository("orders");

    @Override
    public Collection<Order> findAllByUsername(String login) {
        return ordersRepository.findAllByUsername(login);
    }

    @Override
    public Order findById(int ordid) {
        return ordersRepository.findById(ordid);
    }

}
