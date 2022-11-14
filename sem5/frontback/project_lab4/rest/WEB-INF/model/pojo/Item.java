package model.pojo;

import java.sql.ResultSet;

public interface Item {
    public void setAll(ResultSet rs);

    public String getPrice();
}
