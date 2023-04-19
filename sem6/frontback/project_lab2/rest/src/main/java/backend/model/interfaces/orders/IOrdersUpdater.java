package backend.model.interfaces.orders;

import backend.model.interfaces.repositories.ordersRepository.IOrdersRepository;
import backend.model.pojo.Order;

public interface IOrdersUpdater {
    public void updateOrder(Order order, Order changedOrder);

    public void injectOrdersRepository(IOrdersRepository ordersRepository);
}
