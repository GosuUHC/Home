package backend.model.pojo.factory.ItemFactory;

import backend.model.pojo.Item;

public interface IItemFactory {
    public Item createItem(String type);
}
