package model.pojo.itemtype;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.pojo.Item;

public class GPU implements Item {
    int id;
    String manufacturer;
    String name;
    String vram;
    String gpuFrequency;
    String price;

    @Override
    public void setAll(ResultSet rs) {
        try {

            setId(rs.getInt(1));
            setManufacturer(rs.getString(2));
            setName(rs.getString(3));
            setVram(rs.getString(4));
            setGpuFrequency(rs.getString(5));
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

    public String getVram() {
        return vram;
    }

    public void setVram(String vram) {
        this.vram = vram;
    }

    public String getGpuFrequency() {
        return gpuFrequency;
    }

    public void setGpuFrequency(String gpuFrequency) {
        this.gpuFrequency = gpuFrequency;
    }

    @Override
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
