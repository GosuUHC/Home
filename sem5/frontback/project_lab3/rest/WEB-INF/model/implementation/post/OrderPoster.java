package model.implementation.post;

import db.repository.factory.RepositoryFactory;
import model.interfaces.post.IOrderPoster;
import model.interfaces.repository.itemsRepository.IItemsRepository;
import model.interfaces.repository.ordersRepository.IOrdersRepository;
import model.pojo.Order.OrderBuilder;

public class OrderPoster implements IOrderPoster {

    private IOrdersRepository ordersRepository = (IOrdersRepository) RepositoryFactory.getFactoryObject()
            .createRepository("orders");
    private IItemsRepository itemsRepository = (IItemsRepository) RepositoryFactory.getFactoryObject()
            .createRepository("items");

    @Override
    public void addNewOrder(String userLogin, int itemid, String itemCount, String itemType) {
        OrderBuilder orderBuilder = new OrderBuilder(userLogin, itemType, itemid, itemCount);

        orderBuilder.setId(ordersRepository.getMaxId() + 1);
        orderBuilder.setItem(itemsRepository.findByIdAndType(itemid, itemType));
        orderBuilder.setPrice();

        ordersRepository.addNewOrder(orderBuilder.build());
    }

}
