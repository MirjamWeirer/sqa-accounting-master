package at.campus02.models;

import java.math.BigDecimal;

public class Item {

    public Integer id;
    public String name;
    public String description;
    public BigDecimal price;

    public Item() {
    }

    public Item(Integer id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item:"
                + "\n\tID: " + id
                + "\n\tName: " + name
                + "\n\tDescription: " + description
                + "\n\tPrice [EUR]: " + price
                + "\n";
    }
}
