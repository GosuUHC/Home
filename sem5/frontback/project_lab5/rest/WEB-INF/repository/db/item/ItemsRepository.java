package repository.db.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.UserTransaction;
import model.interfaces.repositories.itemsRepository.IItemsRepository;
import model.pojo.Item;
import model.pojo.factory.ItemFactory.IItemFactory;
import model.pojo.factory.ItemFactory.ItemFactory;
import repository.db.item.Entities.EItem;

public class ItemsRepository implements IItemsRepository {

    @PersistenceUnit(unitName = "local_pg_pcParts_PersistenceUnit")
    private EntityManagerFactory entityManagerFactory;
 
    @Resource
    private UserTransaction userTransaction;

    @Override
    public void addNewItem() {

    }

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
            List<EItem> eItems = entityManager.createQuery("SELECT i FROM " + getEntityName(itemType) + " i")
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
                    .createQuery("SELECT i FROM " + getEntityName(itemType) + " i WHERE i.id = :id")
                    .setParameter("id", itemid).getResultList();

            userTransaction.commit();
            Item item = ItemFactory.getFactoryObject().createItem(itemType);
            item.setAll(eItem.get(0).getAll());
            return item;
        } catch (Exception e) {
            Logger.getLogger(ItemsRepository.class.getName()).log(Level.WARNING, e.getMessage());
        }

        return null;
    }

}
