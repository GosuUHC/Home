package backend.application.implementation.orders;

import backend.application.dto.Order;
import backend.application.dto.OrderStatus;
import backend.application.dto.Order.OrderBuilder;
import backend.application.interfaces.in.orders.IOrdersUpdater;
import backend.application.interfaces.out.repository.ordersRepository.IOrdersRepository;

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

    @Override
    public Order updateOrderStatus(Integer orderid, String newStatus) {
        Order order = ordersRepository.findById(orderid);
        if (order == null) {
            return null;
        }

        Order changedOrder = new OrderBuilder(order.getUserLogin(), order.getitemType(), order.getItemid(),
                order.getItemCount())
                .setId(order.getId()).setItem(order.getItem()).setPrice(order.getPrice())
                .setStatus(OrderStatus.getValue(newStatus)).build();

        updateOrder(order, changedOrder);

        return changedOrder;
    }

}
