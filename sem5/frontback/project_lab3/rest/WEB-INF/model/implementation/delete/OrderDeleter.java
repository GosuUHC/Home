package model.implementation.delete;

import db.repository.factory.RepositoryFactory;
import model.interfaces.delete.IOrderDeleter;
import model.interfaces.repository.ordersRepository.IOrdersRepository;

public class OrderDeleter implements IOrderDeleter {

    private IOrdersRepository ordersRepository = (IOrdersRepository) RepositoryFactory.getFactoryObject()
            .createRepository("orders");

    @Override
    public void deleteById(int ordid) {
        ordersRepository.deleteById(ordid);
    }

}
