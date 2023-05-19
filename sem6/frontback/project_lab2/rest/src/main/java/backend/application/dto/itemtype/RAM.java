package backend.application.dto.itemtype;

import backend.application.dto.Item;

public class RAM implements Item {
    private int id;
    private String manufacturer;
    private String name;
    private String technology;
    private String capacity;
    private String price;
    private String img;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public void setAll(Object[] params) {
        try {
            setId((int) params[0]);
            setManufacturer((String) params[1]);
            setName((String) params[2]);
            setTechnology((String) params[3]);
            setCapacity((String) params[4]);
            setPrice((String) params[5]);
            setImg((String) params[6]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
