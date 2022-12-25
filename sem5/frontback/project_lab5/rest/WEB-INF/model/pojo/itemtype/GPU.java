package model.pojo.itemtype;

import model.pojo.Item;

public class GPU implements Item {
    int id;
    String manufacturer;
    String name;
    String vram;
    String gpuFrequency;
    String price;

    @Override
    public void setAll(Object[] params) {
        try {

            setId((int) params[0]); 
            setManufacturer((String) params[1]);
            setName((String) params[2]);
            setVram((String) params[3]);
            setGpuFrequency((String) params[4]);
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
