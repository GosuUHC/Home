package backend.model.interfaces.items;

import backend.model.interfaces.repositories.itemsRepository.IItemsRepository;
import backend.model.pojo.Item;

public interface IItemsPoster {
    public void addNewItem(Item item, String type, Integer quantity);

    public void injectItemsRepository(IItemsRepository itemsRepository);
}
