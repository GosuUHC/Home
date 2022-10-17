package db.pojo.factory.ItemFactory;

import db.pojo.CPU;
import db.pojo.GPU;
import db.pojo.Item;
import db.pojo.Motherboard;
import db.pojo.RAM;

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
