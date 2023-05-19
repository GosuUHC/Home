package backend.application.interfaces.in.items;

import java.util.Collection;

import backend.application.dto.Item;
import backend.application.interfaces.out.repository.itemsRepository.IItemsRepository;

public interface IItemsGetter {
    public Collection<Item> getAllByType(String itemType);

    public Item getByIdAndType(int itemid, String itemType);

    public void injectItemsRepository(IItemsRepository itemsRepository);
}
