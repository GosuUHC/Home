package model.implementation.get;

import java.util.ArrayList;

import db.repository.factory.RepositoryFactory;
import model.interfaces.get.IItemGetter;
import model.interfaces.repository.itemsRepository.IItemsRepository;
import model.pojo.Item;

public class ItemGetter implements IItemGetter {

    IItemsRepository itemsRepository = (IItemsRepository) RepositoryFactory.getFactoryObject()
            .createRepository("items");

    @Override
    public ArrayList<Item> getAllByType(String itemType) {
        return (ArrayList<Item>) itemsRepository.findAllByType(itemType);
    }

    @Override
    public Item getByIdAndType(int itemid, String itemType) {
        return itemsRepository.findByIdAndType(itemid, itemType);
    }

}
