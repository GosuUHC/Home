package backend.infrastructure.out.repository.db.item.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"motherboard\"")
public class EMB implements EItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;

    @Column(name = "\"manufacturer\"")
    private String manufacturer;

    @Column(name = "\"name\"")
    private String name;

    @Column(name = "\"socket\"")
    private String socket;

    @Column(name = "\"chipset\"")
    private String chipset;

    @Column(name = "\"price\"")
    private String price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public Object[] getAll() {
        Object fieldValues[] = new Object[6];
        fieldValues[0] = getId();
        fieldValues[1] = getManufacturer();
        fieldValues[2] = getName();
        fieldValues[3] = getSocket();
        fieldValues[4] = getChipset();
        fieldValues[5] = getPrice();
        return fieldValues;
    }

}
