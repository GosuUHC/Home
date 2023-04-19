package backend.model.implementation.orders;

import backend.model.interfaces.orders.IOrdersDeleter;
import backend.model.interfaces.repositories.ordersRepository.IOrdersRepository;

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
