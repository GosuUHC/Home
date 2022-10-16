package DB.POJO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CPU implements Item {
    int id;
    String manufacturer;
    String name;
    String socket;
    String frequency;
    String price;

    @Override
    public void setAll(ResultSet rs) {
        try {

            setId(rs.getInt(1));
            setManufacturer(rs.getString(2));
            setName(rs.getString(3));
            setSocket(rs.getString(4));
            setFrequency(rs.getString(5));
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

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
