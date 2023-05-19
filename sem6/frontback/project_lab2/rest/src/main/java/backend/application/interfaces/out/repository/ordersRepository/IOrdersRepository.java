package backend.application.interfaces.out.repository.ordersRepository;

import java.util.Collection;

import backend.application.dto.Order;
import backend.application.interfaces.out.repository.IRepository;
import backend.application.interfaces.out.repository.itemsRepository.IItemsRepository;

public interface IOrdersRepository extends IRepository {
    public Order findById(int ordid);

    public Collection<Order> findAllByUsername(String username);

    public Collection<Order> findLimited(Integer limit, Integer skip);

    public Collection<Order> findLimitedByUsername(String username, Integer limit, Integer skip);

    public Long findTotalOrdersCount();

    public Long findTotalOrdersCountByUsername(String username);

    public void addNewOrder(Order order);

    public void deleteById(int ordid);

    public void injectItemsRepository(IItemsRepository itemsRepository);

    public void updateOrder(Order order, Order changedOrder);
}
