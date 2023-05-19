package backend.application.implementation.items;

import java.util.ArrayList;

import backend.application.dto.Item;
import backend.application.interfaces.in.items.IItemsGetter;
import backend.application.interfaces.out.repository.itemsRepository.IItemsRepository;

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
