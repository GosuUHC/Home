package backend.application.implementation.orders;

import backend.application.interfaces.orders.IOrdersDeleter;
import backend.application.interfaces.repositories.ordersRepository.IOrdersRepository;

public class OrdersDeleter implements IOrdersDeleter {

    private IOrdersRepository ordersRepository;

    @Override
    public void injectOrdersRepository(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public void deleteById(int ordid) {
        ordersRepository.deleteById(ordid);
    }

}
