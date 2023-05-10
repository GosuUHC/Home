package backend.application.implementation.items;

import backend.application.interfaces.items.IItemsPoster;
import backend.application.interfaces.repositories.itemsRepository.IItemsRepository;
import backend.domain.pojo.Item;

public class ItemsPoster implements IItemsPoster {

    private IItemsRepository itemsRepository;

    @Override
    public void injectItemsRepository(IItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Override
    public void addNewItem(Item item, String type, Integer quantity) {
        itemsRepository.addNewItem(item, type, quantity);
    }

}
