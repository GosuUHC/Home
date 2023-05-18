package backend.infrastructure.out.repository.db.order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import backend.application.interfaces.repositories.itemsRepository.IItemsRepository;
import backend.application.interfaces.repositories.ordersRepository.IOrdersRepository;
import backend.domain.pojo.Item;
import backend.domain.pojo.Order;
import backend.domain.pojo.Order.OrderBuilder;
import backend.infrastructure.out.repository.db.order.entities.EOrder;
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
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
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
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    private Order buildOrder(EOrder eOrder, Item item) {
        OrderBuilder orderBuilder = new OrderBuilder(eOrder.getUserLogin(), eOrder.getItemType(),
                eOrder.getItemid(), eOrder.getItemCount()).setId(eOrder.getId());
        orderBuilder.setItem(item).setPrice(eOrder.getPrice()).setStatus(eOrder.getStatus());
        return orderBuilder.build();
    }

    @Override
    public Collection<Order> findAllByUsername(String username) {
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
                    .createQuery("SELECT o FROM EOrder o WHERE o.userLogin = :username", EOrder.class)
                    .setParameter("username", username).getResultList();
            userTransaction.commit();

            List<Order> orders = new ArrayList<Order>();

            for (EOrder eOrder : eOrders) {
                Item item = itemsRepository.findByIdAndType(eOrder.getItemid(), eOrder.getItemType());
                Logger.getLogger(OrdersRepository.class.getName()).log(Level.WARNING, item.toString());
                orders.add(buildOrder(eOrder, item));
            }
            return orders;

        } catch (Exception e) {
            Logger.getLogger(OrdersRepository.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
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

            entityManager.close();

            return buildOrder(eOrder, item);

        } catch (Exception e) {
            Logger.getLogger(OrdersRepository.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
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

            entityManager.close();

        } catch (Exception e) {
            Logger.getLogger(OrdersRepository.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public Collection<Order> findLimited(Integer limit, Integer skip) {
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

            List<EOrder> eOrders = entityManager.createQuery("SELECT o FROM EOrder o ORDER BY o.id", EOrder.class)
                    .setMaxResults(limit)
                    .setFirstResult(skip)
                    .getResultList();
            userTransaction.commit();

            List<Order> orders = new ArrayList<Order>();

            for (EOrder eOrder : eOrders) {
                Item item = itemsRepository.findByIdAndType(eOrder.getItemid(), eOrder.getItemType());
                orders.add(buildOrder(eOrder, item));
            }

            return orders;
        } catch (Exception e) {
            Logger.getLogger(OrdersRepository.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return null;
    }

    @Override
    public Collection<Order> findLimitedByUsername(String username, Integer limit, Integer skip) {
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
                    .createQuery("SELECT o FROM EOrder o WHERE o.userLogin = :username", EOrder.class)
                    .setParameter("username", username)
                    .setMaxResults(limit)
                    .setFirstResult(skip)
                    .getResultList();
            userTransaction.commit();

            List<Order> orders = new ArrayList<Order>();

            for (EOrder eOrder : eOrders) {
                Item item = itemsRepository.findByIdAndType(eOrder.getItemid(), eOrder.getItemType());
                orders.add(buildOrder(eOrder, item));
            }

            return orders;
        } catch (Exception e) {
            Logger.getLogger(OrdersRepository.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return null;
    }

    @Override
    public Long findTotalOrdersCount() {
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

            Long totalCount = entityManager.createQuery("SELECT COUNT(o) FROM EOrder o", Long.class).getSingleResult();

            userTransaction.commit();

            return totalCount;
        } catch (Exception e) {
            Logger.getLogger(OrdersRepository.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return null;
    }

    @Override
    public Long findTotalOrdersCountByUsername(String username) {
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

            Long totalCount = entityManager
                    .createQuery("SELECT COUNT(o) FROM EOrder o WHERE o.userLogin = :username", Long.class)
                    .setParameter("username", username)
                    .getSingleResult();

            userTransaction.commit();

            return totalCount;
        } catch (Exception e) {
            Logger.getLogger(OrdersRepository.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return null;
    }

}
