package at.campus02.models;

public class Customer {

    public Integer id;
    public String name;
    public String email;
    public String address;

    public Customer() {
    }

    public Customer(Integer id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer: "
                + "\n\tID: " + id
                + "\n\tName: " + name
                + "\n\tEmail: " + email
                + "\n\tAddress: " + address
                + "\n";
    }
}
