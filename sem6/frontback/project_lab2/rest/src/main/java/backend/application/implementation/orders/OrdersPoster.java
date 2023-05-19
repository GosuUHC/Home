package backend.application.implementation.orders;

import backend.application.dto.Item;
import backend.application.dto.OrderStatus;
import backend.application.dto.Order.OrderBuilder;
import backend.application.interfaces.in.orders.IOrdersPoster;
import backend.application.interfaces.out.repository.itemsRepository.IItemsRepository;
import backend.application.interfaces.out.repository.ordersRepository.IOrdersRepository;
import backend.domain.Factory;
import backend.domain.api.Priceable;

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

        Item item = itemsRepository.findByIdAndType(itemid, itemType);

        orderBuilder.setItem(item);
        Priceable priceCalculator = Factory.createCalculator();

        String price = String
                .valueOf(priceCalculator.calculatePrice(Integer.valueOf(item.getPrice()), Integer.valueOf(itemCount)));

        orderBuilder.setPrice(price);
        orderBuilder.setStatus(OrderStatus.processing);

        ordersRepository.addNewOrder(orderBuilder.build());
    }

}
