package backend.infrastructure.out.repository.db.item.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"gpu\"")
public class EGPU implements EItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;

    @Column(name = "\"manufacturer\"")
    private String manufacturer;

    @Column(name = "\"name\"")
    private String name;

    @Column(name = "\"vram\"")
    private String vram;

    @Column(name = "\"gpu_frequency\"")
    private String gpuFrequency;

    @Column(name = "\"price\"")
    private String price;

    @Column(name = "\"image_link\"")
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public Object[] getAll() {
        Object fieldValues[] = new Object[7];
        fieldValues[0] = getId();
        fieldValues[1] = getManufacturer();
        fieldValues[2] = getName();
        fieldValues[3] = getVram();
        fieldValues[4] = getGpuFrequency();
        fieldValues[5] = getPrice();
        fieldValues[6] = getImg();
        return fieldValues;
    }
}
