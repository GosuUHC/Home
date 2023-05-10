package backend.application.interfaces.orders;

import backend.application.interfaces.repositories.ordersRepository.IOrdersRepository;

public interface IOrdersDeleter {
    public void deleteById(int ordid);

    public void injectOrdersRepository(IOrdersRepository ordersRepository);
}
