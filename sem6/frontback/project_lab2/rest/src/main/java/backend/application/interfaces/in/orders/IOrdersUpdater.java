package backend.application.interfaces.in.orders;

import backend.application.dto.Order;
import backend.application.interfaces.out.repository.ordersRepository.IOrdersRepository;

public interface IOrdersUpdater {
    public void updateOrder(Order order, Order changedOrder);

    public Order updateOrderStatus(Integer orderid, String newStatus);

    public void injectOrdersRepository(IOrdersRepository ordersRepository);
}
