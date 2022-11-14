package model.interfaces.get;

import java.util.Collection;

import model.pojo.Item;

public interface IItemGetter {
    public Collection<Item> getAllByType(String itemType);

    public Item getByIdAndType(int itemid, String itemType);
}
