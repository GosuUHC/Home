package backend.application.dto.factory.ItemFactory;

import backend.application.dto.Item;
import backend.application.dto.itemtype.CPU;
import backend.application.dto.itemtype.GPU;
import backend.application.dto.itemtype.Motherboard;
import backend.application.dto.itemtype.RAM;

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
