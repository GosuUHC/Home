package backend.application.interfaces.in.items;

import backend.application.dto.Item;
import backend.application.interfaces.out.repository.itemsRepository.IItemsRepository;

public interface IItemsPoster {
    public void addNewItem(Item item, String type, Integer quantity);

    public void injectItemsRepository(IItemsRepository itemsRepository);
}
