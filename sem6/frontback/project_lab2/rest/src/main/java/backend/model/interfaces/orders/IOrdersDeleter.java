package backend.model.interfaces.orders;

import backend.model.interfaces.repositories.ordersRepository.IOrdersRepository;

public interface IOrdersDeleter {
    public void deleteById(int ordid);

    public void injectOrdersRepository(IOrdersRepository ordersRepository);
}
