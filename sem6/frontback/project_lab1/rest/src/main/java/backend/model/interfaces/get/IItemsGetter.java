package backend.model.interfaces.get;

import java.util.Collection;

import backend.model.interfaces.repositories.itemsRepository.IItemsRepository;
import backend.model.pojo.Item;

public interface IItemsGetter {
    public Collection<Item> getAllByType(String itemType);

    public Item getByIdAndType(int itemid, String itemType);

    public void injectItemsRepository(IItemsRepository itemsRepository);
}
