package backend.application.interfaces.items;

import java.util.Collection;

import backend.application.interfaces.repositories.itemsRepository.IItemsRepository;
import backend.domain.pojo.Item;

public interface IItemsGetter {
    public Collection<Item> getAllByType(String itemType);

    public Item getByIdAndType(int itemid, String itemType);

    public void injectItemsRepository(IItemsRepository itemsRepository);
}
