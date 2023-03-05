package backend.model.interfaces.delete;

public interface IItemsDeleter {
    public void deleteItemByTypeAndId(Integer id, String type);
}
