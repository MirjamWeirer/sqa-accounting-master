package at.campus02.accounting;

import at.campus02.input.CustomerInput;
import at.campus02.input.ItemInput;
import at.campus02.input.PurchaseInput;

import java.io.EOFException;
import java.util.Scanner;

public class Navigation {
    private final Scanner scanner;
    private final PurchaseInput purchaseInput;
    private final CustomerInput customerInput;
    private final ItemInput itemInput;

    public Navigation() {
        this.scanner = new Scanner(System.in);
        this.purchaseInput = new PurchaseInput();
        this.customerInput = new CustomerInput();
        this.itemInput = new ItemInput();
    }

    public void mainMenu() {
        printMainMenu();
        while (true) {
            System.out.println("Your choice? (1-9, 0 to quit, m for menu)");
            if (scanner.hasNext("m")) {
                scanner.nextLine();
                printMainMenu();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (choice) {
                    case 1: customerInput.viewCustomer();   break;
                    case 2: customerInput.addCustomer();    break;
                    case 3: customerInput.removeCustomer(); break;
                    case 4: purchaseInput.viewPurchase();   break;
                    case 5: purchaseInput.addPurchase();    break;
                    case 6: purchaseInput.removePurchase(); break;
                    case 7: itemInput.viewItem();   break;
                    case 8: itemInput.addItem();    break;
                    case 9: itemInput.removeItem(); break;
                    case 0: return;
                    default:
                        break;
                }
            } catch (EOFException ignored) {}
        }
    }

    private void printMainMenu() {
        System.out.println("C02 Accounting =========\n"
                + "Customers --------------\n"
                + "\t1. View customer\n"
                + "\t2. Add customer\n"
                + "\t3. Remove customer\n"
                + "Purchases --------------\n"
                + "\t4. View purchase\n"
                + "\t5. Add purchase\n"
                + "\t6. Remove purchase\n"
                + "Items --------------\n"
                + "\t7. View item\n"
                + "\t8. Add item\n"
                + "\t9. Remove item\n");
    }
}
