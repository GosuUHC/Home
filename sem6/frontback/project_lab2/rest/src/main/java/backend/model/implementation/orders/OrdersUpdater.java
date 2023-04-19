package backend.model.implementation.orders;

import backend.model.interfaces.orders.IOrdersUpdater;
import backend.model.interfaces.repositories.ordersRepository.IOrdersRepository;
import backend.model.pojo.Order;

public class OrdersUpdater implements IOrdersUpdater {

    private IOrdersRepository ordersRepository;

    @Override
    public void updateOrder(Order order, Order changedOrder) {
        ordersRepository.updateOrder(order, changedOrder);
    }

    @Override
    public void injectOrdersRepository(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }
    
}
