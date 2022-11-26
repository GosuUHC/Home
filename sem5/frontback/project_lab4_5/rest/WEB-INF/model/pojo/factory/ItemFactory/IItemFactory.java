package model.pojo.factory.ItemFactory;

import model.pojo.Item;

public interface IItemFactory {
    public Item createItem(String type);
}
