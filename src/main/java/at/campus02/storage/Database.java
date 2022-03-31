package at.campus02.storage;

import at.campus02.models.Customer;
import at.campus02.models.Item;
import at.campus02.models.Purchase;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Database {
    public static Map<Integer, Customer> customers = new HashMap<>();
    public static Map<Integer, Purchase> purchases = new HashMap<>();
    public static Map<Integer, Item> items = new HashMap<>();

    public static void setupSampleDatabase() {
        Customer c1 = new Customer(1, "Susi Sorglos", "susi@sorglos.at", "Zu Hause 1, 8010 Daheim");
        Customer c2 = new Customer(2, "Rudi Sorglos", "rudi@sorglos.at", "Zu Hause 1, 8010 Daheim");
        Item i1 = new Item(1, "Schoko", "Dunkle Schokolade, 100g", BigDecimal.valueOf(1.90));
        Item i2 = new Item(2, "Keks", "Knusperkeks, 150g", BigDecimal.valueOf(2.19));
        Calendar cal = Calendar.getInstance();
        cal.set(2022, Calendar.MARCH, 24);
        Date d1 = cal.getTime();
        cal.set(2020, Calendar.FEBRUARY, 27);
        Date d2 = cal.getTime();
        Purchase p1 = new Purchase(1, d1, c1, i1, 3);
        Purchase p2 = new Purchase(2, d1, c1, i2, 1);
        Purchase p3 = new Purchase(3, d2, c2, i2, 2);
        customers.put(1, c1);
        customers.put(2, c2);
        items.put(1, i1);
        items.put(2, i2);
        purchases.put(1, p1);
        purchases.put(2, p2);
        purchases.put(3, p3);
    }
}
