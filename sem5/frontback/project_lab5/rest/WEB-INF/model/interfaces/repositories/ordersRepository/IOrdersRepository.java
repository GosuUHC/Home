package model.interfaces.repositories.ordersRepository;

import java.util.Collection;

import model.interfaces.repositories.IRepository;
import model.interfaces.repositories.itemsRepository.IItemsRepository;
import model.pojo.Order;

public interface IOrdersRepository extends IRepository {
    public Order findById(int ordid);

    public Collection<Order> findAllByUsername(String username);

    public void addNewOrder(Order order);

    public void deleteById(int ordid);

    public void injectItemsRepository(IItemsRepository itemsRepository);

}
