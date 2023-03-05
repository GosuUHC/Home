package backend.model.interfaces.repositories.ordersRepository;

import java.util.Collection;

import backend.model.interfaces.repositories.IRepository;
import backend.model.interfaces.repositories.itemsRepository.IItemsRepository;
import backend.model.pojo.Order;

public interface IOrdersRepository extends IRepository {
    public Order findById(int ordid);

    public Collection<Order> findAllByUsername(String username);

    public void addNewOrder(Order order);

    public void deleteById(int ordid);

    public void injectItemsRepository(IItemsRepository itemsRepository);

}
