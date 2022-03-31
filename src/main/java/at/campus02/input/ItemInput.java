package at.campus02.input;

import at.campus02.exchange.ExchangeRates;
import at.campus02.exchange.ExchangeRatesAPI;
import at.campus02.models.Item;
import at.campus02.storage.Database;

import java.io.EOFException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ItemInput {
    private final InputHelper inputHelper;

    public ItemInput() {
        Scanner scanner = new Scanner(System.in);
        this.inputHelper = new InputHelper(scanner, System.out);
    }

    public void viewItem() throws EOFException {
        System.out.println("--- View item ---");
        Integer number = inputHelper.getItemId(InputHelper.ID_MUST_EXIST);
        Item item = Database.items.get(number);
        System.out.print(item);

        try {
//            ExchangeRatesAPI exchangeRatesAPI = ExchangeRatesAPI.fromConfig("exchangeRates.properties");
//            ExchangeRates exchangeRates = exchangeRatesAPI.fromAPI();
            ExchangeRates exchangeRates = ExchangeRatesAPI.fromFile("exchangeRatesSample.json");
            System.out.println("\tPrice [GBP]: " + exchangeRates.eur_in_gbp(item.price) +
                    "\n\tPrice [USD]: " + exchangeRates.eur_in_usd(item.price) +
                    "\n\tPrice [CAD]: " + exchangeRates.eur_in_cad(item.price) +
                    "\n\tPrice [JPY]: " + exchangeRates.eur_in_jpy(item.price) +
                    "\n");
        } catch (IOException | URISyntaxException e) {
            System.out.println("[Exchange rates currently unavailable]");
        }
    }

    public void addItem() throws EOFException {
        System.out.println("--- Add item ---");
        Item newItem = new Item();

        newItem.id = inputHelper.getItemId(InputHelper.ID_MUST_NOT_EXIST);
        newItem.name = inputHelper.getString("Name");
        newItem.description = inputHelper.getString("Description");
        newItem.price = inputHelper.getDecimal("Price");

        Database.items.put(newItem.id, newItem);
        System.out.println("Item added.");
    }

    public void removeItem() throws EOFException {
        System.out.println("--- Remove item ---");
        Integer number = inputHelper.getItemId(InputHelper.ID_MUST_EXIST);
        Database.items.remove(number);
        System.out.println("Item removed.");
    }
}
