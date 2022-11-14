package model.pojo.itemtype;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.pojo.Item;

public class RAM implements Item {
    int id;
    String manufacturer;
    String name;
    String technology;
    String capacity;
    String price;

    @Override
    public void setAll(ResultSet rs) {
        try {

            setId(rs.getInt(1));
            setManufacturer(rs.getString(2));
            setName(rs.getString(3));
            setTechnology(rs.getString(4));
            setCapacity(rs.getString(5));
            setPrice(rs.getString(6));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    @Override
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
