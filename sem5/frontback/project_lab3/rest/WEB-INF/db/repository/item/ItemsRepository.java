package db.repository.item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import db.DBUtil;
import model.interfaces.repository.itemsRepository.IItemsRepository;
import model.pojo.Item;
import model.pojo.factory.ItemFactory.IItemFactory;
import model.pojo.factory.ItemFactory.ItemFactory;

public class ItemsRepository implements IItemsRepository {

    @Override
    public void addNewItem() {

    }

    @Override
    public Collection<Item> findAllByType(String itemType) {
        String query = "select * from " + itemType;
        try {
            PreparedStatement ps = DBUtil.getConnection().prepareStatement(query);
            ResultSet rsUserData = ps.executeQuery();
            Collection<Item> items = new ArrayList<Item>();
            IItemFactory itemFactory = ItemFactory.getFactoryObject();

            while (rsUserData.next()) {
                Item item = itemFactory.createItem(itemType);
                item.setAll(rsUserData);
                items.add(item);
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect();
        }
        return null;
    }

    @Override
    public Item findByIdAndType(int itemid, String itemType) {
        String query = "select * from " + itemType + " where id = ?";
        PreparedStatement ps;
        try {
            ps = DBUtil.getConnection().prepareStatement(query);
            ps.setInt(1, itemid);
            ResultSet rsUserData = ps.executeQuery();

            if (rsUserData.next()) {
                Item item;
                IItemFactory itemFactory = ItemFactory.getFactoryObject();
                item = itemFactory.createItem(itemType);
                item.setAll(rsUserData);

                return item;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect();
        }
        return null;
    }

}
