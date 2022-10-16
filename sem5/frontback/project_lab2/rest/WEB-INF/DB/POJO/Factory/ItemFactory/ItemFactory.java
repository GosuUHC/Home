package DB.POJO.Factory.ItemFactory;

import DB.POJO.CPU;
import DB.POJO.GPU;
import DB.POJO.Item;
import DB.POJO.Motherboard;
import DB.POJO.RAM;

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
