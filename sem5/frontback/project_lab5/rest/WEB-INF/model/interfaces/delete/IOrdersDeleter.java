package model.interfaces.delete;

import model.interfaces.repositories.ordersRepository.IOrdersRepository;

public interface IOrdersDeleter {
    public void deleteById(int ordid);

    public void injectOrdersRepository(IOrdersRepository ordersRepository);
}
