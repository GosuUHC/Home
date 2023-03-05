package backend.model.pojo.factory.ItemFactory;

import backend.model.pojo.Item;
import backend.model.pojo.itemtype.CPU;
import backend.model.pojo.itemtype.GPU;
import backend.model.pojo.itemtype.Motherboard;
import backend.model.pojo.itemtype.RAM;

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
