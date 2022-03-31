package at.campus02.input;

import at.campus02.models.Customer;
import at.campus02.storage.Database;

import java.io.EOFException;
import java.util.Scanner;

public class CustomerInput {
    private final InputHelper inputHelper;

    public CustomerInput() {
        Scanner scanner = new Scanner(System.in);
        this.inputHelper = new InputHelper(scanner,System.out);
    }

    public void viewCustomer() throws EOFException {
        System.out.println("--- View customer ---");
        Integer number = inputHelper.getCustomerId(InputHelper.ID_MUST_EXIST);
        System.out.println(Database.customers.get(number));
    }

    public void addCustomer() throws EOFException {
        System.out.println("--- Add customer ---");
        Customer newCustomer = new Customer();

        newCustomer.id = inputHelper.getCustomerId(InputHelper.ID_MUST_NOT_EXIST);
        newCustomer.name = inputHelper.getString("Name");
        newCustomer.email = inputHelper.getString("Email");
        newCustomer.address = inputHelper.getString("Address");

        Database.customers.put(newCustomer.id, newCustomer);
        System.out.println("Customer added.");
    }

    public void removeCustomer() throws EOFException {
        System.out.println("--- Remove customer ---");
        Integer number = inputHelper.getCustomerId(InputHelper.ID_MUST_EXIST);
        Database.customers.remove(number);
        System.out.println("Customer removed.");
    }
}
