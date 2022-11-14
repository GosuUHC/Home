package model.interfaces.post;

public interface IOrderPoster {
    public void addNewOrder(String login, int itemID, String itemCount, String itemType);
}
