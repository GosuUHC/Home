package db.repository.factory;

import db.repository.item.ItemsRepository;
import db.repository.order.OrdersRepository;
import db.repository.user.UsersRepository;
import model.interfaces.repository.IRepository;

public class RepositoryFactory implements IRepositoryFactory {

    @Override
    public IRepository createRepository(String type) {
        if (type.equals("") || type == null) {
            return null;
        }

        return switch (type.toLowerCase()) {
            case "orders" -> new OrdersRepository();
            case "items" -> new ItemsRepository();
            case "users" -> new UsersRepository();

            default -> throw new IllegalArgumentException("Unknown type:" + type);
        };
    }

    public static RepositoryFactory getFactoryObject() {
        return new RepositoryFactory();
    }
}
