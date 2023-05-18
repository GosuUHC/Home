package backend.model.implementation.post;

import backend.model.interfaces.post.IItemsPoster;
import backend.model.interfaces.repositories.itemsRepository.IItemsRepository;
import backend.model.pojo.Item;

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