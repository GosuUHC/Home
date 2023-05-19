package backend.infrastructure.out.repository.db.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import backend.application.dto.Item;
import backend.application.dto.factory.ItemFactory.IItemFactory;
import backend.application.dto.factory.ItemFactory.ItemFactory;
import backend.application.interfaces.out.repository.itemsRepository.IItemsRepository;
import backend.infrastructure.out.repository.db.item.entities.EItem;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.UserTransaction;

public class ItemsRepository implements IItemsRepository {

    @PersistenceUnit(unitName = "local_pg_pcParts_PersistenceUnit")
    private EntityManagerFactory entityManagerFactory;

    @Resource
    private UserTransaction userTransaction;

    @Override
    public Collection<Item> findAllByType(String itemType) {
        EntityManager entityManager;
        try {
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            Logger.getLogger(ItemsRepository.class.getName()).log(Level.WARNING, e.getMessage());
            return null;
        }
        try {
            userTransaction.begin();
            entityManager.joinTransaction();
            List<EItem> eItems = entityManager
                    .createQuery("SELECT i FROM " + getEntityName(itemType) + " i", EItem.class)
                    .getResultList();
            userTransaction.commit();

            List<Item> pojoItems = new ArrayList<>();
            IItemFactory itemFactory = ItemFactory.getFactoryObject();
            for (int i = 0; i < eItems.size(); i++) {
                Item pojoItem = itemFactory.createItem(itemType);
                pojoItem.setAll(eItems.get(i).getAll());
                pojoItems.add(pojoItem);
            }

            return pojoItems;

        } catch (Exception e) {
            Logger.getLogger(ItemsRepository.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }

        return null;
    }

    private String getEntityName(String itemType) {
        return switch (itemType.toLowerCase()) {
            case "cpu" -> "ECPU";
            case "ram" -> "ERAM";
            case "gpu" -> "EGPU";
            case "motherboard" -> "EMB";

            default -> throw new IllegalArgumentException("Unknown type: " + itemType);
        };

    }

    @Override
    public Item findByIdAndType(int itemid, String itemType) {
        EntityManager entityManager;
        try {
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            Logger.getLogger(ItemsRepository.class.getName()).log(Level.WARNING, e.getMessage());
            return null;
        }
        try {
            userTransaction.begin();
            entityManager.joinTransaction();

            List<EItem> eItem = entityManager
                    .createQuery("SELECT i FROM " + getEntityName(itemType) + " i WHERE i.id = :id", EItem.class)
                    .setParameter("id", itemid).getResultList();

            userTransaction.commit();
            Item item = ItemFactory.getFactoryObject().createItem(itemType);
            item.setAll(eItem.get(0).getAll());
            return item;
        } catch (Exception e) {
            Logger.getLogger(ItemsRepository.class.getName()).log(Level.WARNING, e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }

        return null;
    }

    @Override
    public void deleteByIdAndType(Integer id, String type) {
    }

    @Override
    public void addNewItem(Item item, String type, Integer quantity) {
    }
}
