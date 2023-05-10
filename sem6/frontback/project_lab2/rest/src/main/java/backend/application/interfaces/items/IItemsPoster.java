package backend.application.interfaces.items;

import backend.application.interfaces.repositories.itemsRepository.IItemsRepository;
import backend.domain.pojo.Item;

public interface IItemsPoster {
    public void addNewItem(Item item, String type, Integer quantity);

    public void injectItemsRepository(IItemsRepository itemsRepository);
}
