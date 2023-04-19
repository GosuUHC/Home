package backend.model.interfaces.repositories.itemsRepository;

import java.util.Collection;

import backend.model.interfaces.repositories.IRepository;
import backend.model.pojo.Item;

public interface IItemsRepository extends IRepository {
    public Collection<Item> findAllByType(String itemType);

    public Item findByIdAndType(int itemid, String itemType);

    public void addNewItem(Item item, String type, Integer quantity);

    public void deleteByIdAndType(Integer id, String type);
}
