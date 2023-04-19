package backend.model.interfaces.orders;

import backend.model.interfaces.repositories.itemsRepository.IItemsRepository;
import backend.model.interfaces.repositories.ordersRepository.IOrdersRepository;

public interface IOrdersPoster {
    public void addNewOrder(String login, int itemID, String itemCount, String itemType);

    public void injectOrdersRepository(IOrdersRepository ordersRepository);

    public void injectItemsRepository(IItemsRepository itemsRepository);
}
