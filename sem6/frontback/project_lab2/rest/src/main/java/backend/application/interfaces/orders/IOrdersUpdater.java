package backend.application.interfaces.orders;

import backend.application.interfaces.repositories.ordersRepository.IOrdersRepository;
import backend.domain.pojo.Order;

public interface IOrdersUpdater {
    public void updateOrder(Order order, Order changedOrder);

    public Order updateOrderStatus(Integer orderid, String newStatus);

    public void injectOrdersRepository(IOrdersRepository ordersRepository);
}
