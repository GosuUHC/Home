package backend.application.interfaces.in.orders;

import backend.application.interfaces.out.repository.ordersRepository.IOrdersRepository;

public interface IOrdersDeleter {
    public void deleteById(int ordid);

    public void injectOrdersRepository(IOrdersRepository ordersRepository);
}
