package backend.repository.db.order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import backend.model.interfaces.repositories.itemsRepository.IItemsRepository;
import backend.model.interfaces.repositories.ordersRepository.IOrdersRepository;
import backend.model.pojo.Item;
import backend.model.pojo.Order;
import backend.model.pojo.Order.OrderBuilder;
import backend.repository.db.order.Entities.EOrder;
import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.UserTransaction;

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

    private Order buildOrder(EOrder eOrder, Item item) {
        OrderBuilder orderBuilder = new OrderBuilder(eOrder.getUserLogin(), eOrder.getItemType(),
                eOrder.getItemid(), eOrder.getItemCount()).setId(eOrder.getId());
        orderBuilder.setItem(item).setPrice(eOrder.getPrice()).setStatus(eOrder.getStatus());
        return orderBuilder.build();
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

            List<Order> orders = new ArrayList<Order>();

            for (EOrder eOrder : eOrders) {
                Item item = itemsRepository.findByIdAndType(eOrder.getItemid(), eOrder.getItemType());
                orders.add(buildOrder(eOrder, item));
            }
            return orders;

        } catch (Exception e) {
            Logger.getLogger(OrdersRepository.class.getName()).log(Level.WARNING, e.getMessage());
        }

        return null;

    }

    @Override
    public Order findById(int ordid) {
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
            EOrder eOrder = entityManager.find(EOrder.class, ordid);
            userTransaction.commit();
            Item item = itemsRepository.findByIdAndType(eOrder.getItemid(), eOrder.getItemType());
            return buildOrder(eOrder, item);

        } catch (Exception e) {
            Logger.getLogger(OrdersRepository.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }

    @Override
    public void updateOrder(Order order, Order changedOrder) {
        EntityManager entityManager;
        try {
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            return;
        }

        try {
            userTransaction.begin();
            entityManager.joinTransaction();

            EOrder eOrder = entityManager.find(EOrder.class, order.getId());
            eOrder.setAll(changedOrder);
            entityManager.merge(eOrder);

            userTransaction.commit();

        } catch (Exception e) {
            Logger.getLogger(OrdersRepository.class.getName()).log(Level.WARNING, e.getMessage());
        }
    }

}
