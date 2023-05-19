package backend.application.implementation.items;

import backend.application.dto.Item;
import backend.application.interfaces.in.items.IItemsPoster;
import backend.application.interfaces.out.repository.itemsRepository.IItemsRepository;

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
