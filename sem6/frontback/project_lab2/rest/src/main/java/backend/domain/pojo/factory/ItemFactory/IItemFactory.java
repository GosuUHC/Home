package backend.domain.pojo.factory.ItemFactory;

import backend.domain.pojo.Item;

public interface IItemFactory {
    public Item createItem(String type);
}
