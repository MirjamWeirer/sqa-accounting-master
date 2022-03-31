package at.campus02.input;

import at.campus02.models.Purchase;
import at.campus02.storage.Database;

import java.io.EOFException;

public class PurchaseInput {
    private final InputHelper inputHelper;

    public PurchaseInput() {
        this.inputHelper = new InputHelper();
    }

    public void viewPurchase() throws EOFException {
        System.out.println("--- View purchase ---");
        Integer number = inputHelper.getPurchaseId(InputHelper.ID_MUST_EXIST);
        System.out.println(Database.purchases.get(number));
    }

    public void addPurchase() throws EOFException {
        System.out.println("--- Add purchase ---");
        Purchase newPurchase = new Purchase();

        newPurchase.id = inputHelper.getPurchaseId(InputHelper.ID_MUST_NOT_EXIST);
        newPurchase.date = inputHelper.getDate("Date of purchase (YYYY-MM-DD)");
        Integer supplierID = inputHelper.getCustomerId(InputHelper.ID_MUST_EXIST);
        newPurchase.customer = Database.customers.get(supplierID);
        Integer itemID = inputHelper.getItemId(InputHelper.ID_MUST_EXIST);
        newPurchase.item = Database.items.get(itemID);
        newPurchase.quantity = inputHelper.getNumber("Quantity");

        Database.purchases.put(newPurchase.id, newPurchase);
        System.out.println("Purchase added.");
    }

    public void removePurchase() throws EOFException {
        System.out.println("--- Remove purchase ---");
        Integer number = inputHelper.getPurchaseId(InputHelper.ID_MUST_EXIST);
        Database.purchases.remove(number);
        System.out.println("Purchase removed.");
    }
}
