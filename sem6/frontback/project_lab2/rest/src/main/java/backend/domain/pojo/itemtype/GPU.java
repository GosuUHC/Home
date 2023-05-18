package backend.domain.pojo.itemtype;

import backend.domain.pojo.Item;

public class GPU implements Item {
    private int id;
    private String manufacturer;
    private String name;
    private String vram;
    private String gpuFrequency;
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
            setVram((String) params[3]);
            setGpuFrequency((String) params[4]);
            setPrice((String) params[5]);
            setImg((String) params[6]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
