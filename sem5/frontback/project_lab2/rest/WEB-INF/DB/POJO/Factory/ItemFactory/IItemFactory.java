package DB.POJO.Factory.ItemFactory;

import DB.POJO.Item;

public interface IItemFactory {
    public Item createItem(String type);
}
