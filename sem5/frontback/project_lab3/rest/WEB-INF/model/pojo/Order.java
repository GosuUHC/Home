package model.pojo;

public class Order {
    int id;
    String userLogin;
    String itemType;
    int itemid;
    String itemCount;
    String price;
    Item item;

    private Order(OrderBuilder orderBuilder) {
        id = orderBuilder.id;
        userLogin = orderBuilder.userLogin;
        itemType = orderBuilder.itemType;
        itemid = orderBuilder.itemid;
        itemCount = orderBuilder.itemCount;
        price = orderBuilder.price;
        item = orderBuilder.item;

    }

    public Item getItem() {
        return item;
    }

    public int getId() {
        return id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getitemType() {
        return itemType;
    }

    public int getItemid() {
        return itemid;
    }

    public String getItemCount() {
        return itemCount;
    }

    public String getPrice() {
        return price;
    }

    public static class OrderBuilder {
        int id;
        String userLogin;
        String itemType;
        int itemid;
        String itemCount;
        String price;
        Item item;

        public OrderBuilder(String userLogin, String itemType, int itemid, String itemCount) {
            this.userLogin = userLogin;
            this.itemType = itemType;
            this.itemid = itemid;
            this.itemCount = itemCount;
        }

        public OrderBuilder setId(int ordid) {
            this.id = ordid;
            return this;
        }

        public OrderBuilder setItem(Item item) {
            this.item = item;
            return this;
        }

        public OrderBuilder setPrice() {
            this.price = String.valueOf(Integer.valueOf(item.getPrice()) * Integer.valueOf(itemCount));
            return this;
        }

        public OrderBuilder setPrice(String price) {
            this.price = price;
            return this;
        }

        public Order build() {
            return new Order(this);
        }

    }
}
