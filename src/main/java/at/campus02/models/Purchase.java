package at.campus02.models;

import java.util.Date;

public class Purchase {

    public Integer id;
    public Date date;
    public Customer customer;
    public Item item;
    public Integer quantity;

    public Purchase() {
    }

    public Purchase(int id, Date date, Customer customer, Item item, Integer quantity) {
        this.id = id;
        this.date = date;
        this.customer = customer;
        this.item = item;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Purchase:"
                + "\n\tID: " + id
                + "\n\tDate: " + date
                + "\n\tQuantity: " + quantity
                + "\n" + item
                + customer;
    }
}