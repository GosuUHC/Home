package backend.model.implementation.post;

import backend.model.interfaces.post.IOrdersPoster;
import backend.model.interfaces.repositories.itemsRepository.IItemsRepository;
import backend.model.interfaces.repositories.ordersRepository.IOrdersRepository;
import backend.model.pojo.Order.OrderBuilder;

public class OrdersPoster implements IOrdersPoster {

    private IOrdersRepository ordersRepository;

    private IItemsRepository itemsRepository;

    @Override
    public void injectItemsRepository(IItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Override
    public void injectOrdersRepository(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public void addNewOrder(String userLogin, int itemid, String itemCount, String itemType) {
        OrderBuilder orderBuilder = new OrderBuilder(userLogin, itemType, itemid, itemCount);

        orderBuilder.setItem(itemsRepository.findByIdAndType(itemid, itemType));
        orderBuilder.setPrice();

        ordersRepository.addNewOrder(orderBuilder.build());
    }

}