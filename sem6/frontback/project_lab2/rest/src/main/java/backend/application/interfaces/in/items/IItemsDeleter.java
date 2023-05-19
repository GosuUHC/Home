package backend.application.interfaces.in.items;

public interface IItemsDeleter {
    public void deleteItemByTypeAndId(Integer id, String type);
}
