package backend.application.interfaces.items;

public interface IItemsDeleter {
    public void deleteItemByTypeAndId(Integer id, String type);
}
