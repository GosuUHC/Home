package backend.model.interfaces.items;

public interface IItemsDeleter {
    public void deleteItemByTypeAndId(Integer id, String type);
}
