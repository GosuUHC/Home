package repository.db.item.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"ram\"")
public class ERAM implements EItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    int id;

    @Column(name = "\"manufacturer\"")
    String manufacturer;

    @Column(name = "\"name\"")
    String name;

    @Column(name = "\"technology\"")
    String technology;

    @Column(name = "\"capacity\"")
    String capacity;

    @Column(name = "\"price\"")
    String price;

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

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
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
        fieldValues[3] = getTechnology();
        fieldValues[4] = getCapacity();
        fieldValues[5] = getPrice();
        return fieldValues;
    }

}
