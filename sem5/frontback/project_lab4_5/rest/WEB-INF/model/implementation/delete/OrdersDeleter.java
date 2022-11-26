package model.implementation.delete;

import model.interfaces.delete.IOrdersDeleter;
import model.interfaces.repositories.ordersRepository.IOrdersRepository;

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
