package backend.repository.db.order.Entities;

import backend.model.pojo.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"orders\"")
public class EOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    int id;

    @Column(name = "\"user_login\"")
    String userLogin;

    @Column(name = "\"item_type\"")
    String itemType;

    @Column(name = "\"item_id\"")
    int itemid;

    @Column(name = "\"item_count\"")
    String itemCount;

    @Column(name = "\"price\"")
    String price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setAll(Order order) {
        setUserLogin(order.getUserLogin());
        setItemType(order.getitemType());
        setItemid(order.getItemid());
        setItemCount(order.getItemCount());
        setPrice(order.getPrice());
    }

}
