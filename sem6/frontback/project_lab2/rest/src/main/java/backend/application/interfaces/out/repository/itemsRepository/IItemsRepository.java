package backend.application.interfaces.out.repository.itemsRepository;

import java.util.Collection;

import backend.application.dto.Item;
import backend.application.interfaces.out.repository.IRepository;

public interface IItemsRepository extends IRepository {
    public Collection<Item> findAllByType(String itemType);

    public Item findByIdAndType(int itemid, String itemType);

    public void addNewItem(Item item, String type, Integer quantity);

    public void deleteByIdAndType(Integer id, String type);
}
