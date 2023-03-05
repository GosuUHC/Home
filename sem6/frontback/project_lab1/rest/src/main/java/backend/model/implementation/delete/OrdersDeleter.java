package backend.model.implementation.delete;

import backend.model.interfaces.delete.IOrdersDeleter;
import backend.model.interfaces.repositories.ordersRepository.IOrdersRepository;

public class OrdersDeleter implements IOrdersDeleter {

    private IOrdersRepository ordersRepository;

    public OrdersDeleter() {
        
    }

    @Override
    public void injectOrdersRepository(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public void deleteById(int ordid) {
        ordersRepository.deleteById(ordid);
    }

}
