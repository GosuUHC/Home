package backend.application.interfaces.orders;

import java.util.Collection;

import backend.application.interfaces.repositories.ordersRepository.IOrdersRepository;
import backend.domain.pojo.Order;

public interface IOrdersGetter {
    public Collection<Order> findAllByUsername(String login);

    public Collection<Order> findLimited(Integer limit, Integer skip);

    public Collection<Order> findLimitedPaged(Integer page, Integer pageSize);

    public Collection<Order> findLimitedByUsernamePaged(String username, Integer page, Integer pageSize);

    public Long findTotalOrdersCount();

    public Long findTotalOrdersCountByUsername(String username);

    public Order findById(int ordid);

    public void injectOrdersRepository(IOrdersRepository ordersRepository);
}
