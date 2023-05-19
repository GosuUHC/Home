package backend.application.dto.factory.ItemFactory;

import backend.application.dto.Item;

public interface IItemFactory {
    public Item createItem(String type);
}
