package db.pojo.factory.ItemFactory;

import db.pojo.Item;

public interface IItemFactory {
    public Item createItem(String type);
}
