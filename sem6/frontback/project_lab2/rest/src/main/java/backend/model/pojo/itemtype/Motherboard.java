package backend.model.pojo.itemtype;


import backend.model.pojo.Item;

public class Motherboard implements Item {
    int id;
    String manufacturer;
    String name;
    String socket;
    String chipset;
    String price;

    @Override
    public void setAll(Object[] params) {
        try {

            setId((int) params[0]); 
            setManufacturer((String) params[1]);
            setName((String) params[2]);
            setSocket((String) params[3]);
            setChipset((String) params[4]);
            setPrice((String) params[5]);

        } catch (Exception e) {
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

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    @Override
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
