package model.interfaces.repository.itemsRepository;

import java.util.Collection;

import model.interfaces.repository.IRepository;
import model.pojo.Item;

public interface IItemsRepository extends IRepository {
    public Collection<Item> findAllByType(String itemType);

    public Item findByIdAndType(int itemid, String itemType);

    public void addNewItem(); // will be changed
}
