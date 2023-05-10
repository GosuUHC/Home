package backend.application.implementation.items;

import java.util.ArrayList;

import backend.application.interfaces.items.IItemsGetter;
import backend.application.interfaces.repositories.itemsRepository.IItemsRepository;
import backend.domain.pojo.Item;

public class ItemsGetter implements IItemsGetter {

    private IItemsRepository itemsRepository;

    @Override
    public void injectItemsRepository(IItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Override
    public ArrayList<Item> getAllByType(String itemType) {
        return (ArrayList<Item>) itemsRepository.findAllByType(itemType);
    }

    @Override
    public Item getByIdAndType(int itemid, String itemType) {
        return itemsRepository.findByIdAndType(itemid, itemType);
    }

}
