package repository.db.order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.UserTransaction;
import model.interfaces.repositories.itemsRepository.IItemsRepository;
import model.interfaces.repositories.ordersRepository.IOrdersRepository;
import model.pojo.Item;
import model.pojo.Order;
import model.pojo.Order.OrderBuilder;
import repository.db.order.Entities.EOrder;

public class OrdersRepository implements IOrdersRepository {

    @Inject
    private IItemsRepository itemsRepository;

    @Override
    public void injectItemsRepository(IItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @PersistenceUnit(unitName = "local_pg_pcParts_PersistenceUnit")
    private EntityManagerFactory entityManagerFactory;

    @Resource
    private UserTransaction userTransaction;

    @Override
    public void addNewOrder(Order order) {
        EntityManager entityManager;
        try {
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            return;
        }

        try {
            userTransaction.begin();
            entityManager.joinTransaction();

            EOrder eOrder = new EOrder();
            eOrder.setAll(order);
            entityManager.persist(eOrder);

            userTransaction.commit();

        } catch (Exception e) {
            Logger.getLogger(OrdersRepository.class.getName()).log(Level.WARNING, e.getMessage());
        }

    }

    @Override
    public void deleteById(int ordid) {
        EntityManager entityManager;
        try {
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            Logger.getLogger(OrdersRepository.class.getName()).log(Level.WARNING, e.getMessage());
            return;
        }

        try {
            userTransaction.begin();
            entityManager.joinTransaction();

            entityManager.createQuery("DELETE FROM EOrder o WHERE o.id = :id")
                    .setParameter("id", ordid)
                    .executeUpdate();

            userTransaction.commit();

        } catch (Exception e) {
            Logger.getLogger(OrdersRepository.class.getName()).log(Level.WARNING, e.getMessage());
        }
    }

    @Override
    public Collection<Order> findAllByUsername(String userLogin) {
        EntityManager entityManager;
        try {
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            Logger.getLogger(OrdersRepository.class.getName()).log(Level.WARNING, e.getMessage());
            return null;
        }

        try {
            userTransaction.begin();
            entityManager.joinTransaction();

            List<EOrder> eOrders = entityManager
                    .createQuery("SELECT o FROM EOrder o WHERE o.userLogin = :userLogin", EOrder.class)
                    .setParameter("userLogin", userLogin).getResultList();
            userTransaction.commit();

            List<Order> orders = new ArrayList<>();

            for (EOrder eOrder : eOrders) {
                OrderBuilder orderBuilder = new OrderBuilder(eOrder.getUserLogin(), eOrder.getItemType(),
                        eOrder.getItemid(), eOrder.getItemCount());
                orderBuilder.setId(eOrder.getId());
                Item item = itemsRepository.findByIdAndType(eOrder.getItemid(), eOrder.getItemType());
                // Logger.getLogger(OrdersRepository.class.getName()).log(Level.WARNING,
                // item.toString());
                orderBuilder.setItem(item).setPrice(eOrder.getPrice());
                orders.add(orderBuilder.build());
            }
            return orders;

        } catch (Exception e) {
            Logger.getLogger(OrdersRepository.class.getName()).log(Level.WARNING, e.getMessage());
        }

        return null;

    }

    @Override
    public Order findById(int ordid) {
        return null;
    }

}
