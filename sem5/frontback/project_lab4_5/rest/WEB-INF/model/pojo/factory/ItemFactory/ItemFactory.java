package model.pojo.factory.ItemFactory;

import model.pojo.Item;
import model.pojo.itemtype.CPU;
import model.pojo.itemtype.GPU;
import model.pojo.itemtype.Motherboard;
import model.pojo.itemtype.RAM;

public class ItemFactory implements IItemFactory {

    @Override
    public Item createItem(String type) {

        if (type == null) {
            return null;
        }

        return switch (type) {
            case "cpu" -> new CPU();
            case "gpu" -> new GPU();
            case "motherboard" -> new Motherboard();
            case "ram" -> new RAM();

            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };

    }

    public static ItemFactory getFactoryObject() {
        return new ItemFactory();
    }
}
