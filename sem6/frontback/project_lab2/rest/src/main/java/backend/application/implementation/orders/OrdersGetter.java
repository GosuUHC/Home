package backend.application.implementation.orders;

import java.util.Collection;

import backend.application.dto.Order;
import backend.application.interfaces.in.orders.IOrdersGetter;
import backend.application.interfaces.out.repository.ordersRepository.IOrdersRepository;

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

    @Override
    public Collection<Order> findLimited(Integer limit, Integer skip) {
        return ordersRepository.findLimited(limit, skip);
    }

    @Override
    public Collection<Order> findLimitedPaged(Integer page, Integer pageSize) {
        Integer skipAmount = (page - 1) * pageSize;
        return ordersRepository.findLimited(pageSize, skipAmount);
    }

    @Override
    public Collection<Order> findLimitedByUsernamePaged(String username, Integer page, Integer pageSize) {
        Integer skipAmount = (page - 1) * pageSize;
        return ordersRepository.findLimitedByUsername(username, pageSize, skipAmount);
    }

    @Override
    public Long findTotalOrdersCount() {
        return ordersRepository.findTotalOrdersCount();
    }

    @Override
    public Long findTotalOrdersCountByUsername(String username) {
        return ordersRepository.findTotalOrdersCountByUsername(username);
    }

}
