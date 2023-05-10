package backend.domain.pojo.factory.ItemFactory;

import backend.domain.pojo.Item;
import backend.domain.pojo.itemtype.CPU;
import backend.domain.pojo.itemtype.GPU;
import backend.domain.pojo.itemtype.Motherboard;
import backend.domain.pojo.itemtype.RAM;

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
