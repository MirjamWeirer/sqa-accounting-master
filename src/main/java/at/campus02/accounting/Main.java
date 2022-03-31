package at.campus02.accounting;

import at.campus02.storage.Database;

public class Main {

    public static void main(String[] args) {
        Database.setupSampleDatabase();
        Navigation navigation = new Navigation();
        navigation.mainMenu();
    }
}