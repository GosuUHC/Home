package backend.application.interfaces.in.orders;

import backend.application.interfaces.out.repository.itemsRepository.IItemsRepository;
import backend.application.interfaces.out.repository.ordersRepository.IOrdersRepository;

public interface IOrdersPoster {
    public void addNewOrder(String login, int itemID, String itemCount, String itemType);

    public void injectOrdersRepository(IOrdersRepository ordersRepository);

    public void injectItemsRepository(IItemsRepository itemsRepository);
}
