package at.campus02.input;

import at.campus02.storage.Database;

import java.io.EOFException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;

public class InputHelper {
    public static final int ID_MUST_EXIST = 1;
    public static final int ID_MUST_NOT_EXIST = 2;

    private final Scanner scanner;
    private final PrintStream out;

    public InputHelper(Scanner scanner, PrintStream out) {
        this.scanner = scanner;
        this.out = out;
    }

    public Integer getNumber(String prompt) throws EOFException {
        out.println(prompt + " (0 to abort) ");
        int input = scanner.nextInt();
        scanner.nextLine();
        if (input == 0) {
            throw new EOFException();
        }
        return input;
    }

    private Integer getId(String prompt, int mode, Function<Integer, Boolean> containsKey) throws EOFException {
        while (true) {
            out.println(prompt + " (0 to abort) ");
            Integer id = scanner.nextInt();
            scanner.nextLine();
            if (id == 0) {
                throw new EOFException();
            }
            if (mode == ID_MUST_EXIST && containsKey.apply(id)) {
                return id;
            } else if (mode == ID_MUST_NOT_EXIST && !containsKey.apply(id)) {
                return id;
            } else {
                System.out.println("Wrong ID.");
            }
        }
    }

    public Integer getPurchaseId(int mode) throws EOFException {
        return getId("Purchase ID", mode, Database.purchases::containsKey);
    }

    public Integer getCustomerId(int mode) throws EOFException {
        return getId("Customer ID", mode, Database.customers::containsKey);
    }

    public Integer getItemId(int mode) throws EOFException {
        return getId("Item ID", mode, Database.items::containsKey);
    }

    public String getString(String prompt) throws EOFException {
        out.println(prompt + " (empty string to abort) ");
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            throw new EOFException();
        }
        return input;
    }

    public BigDecimal getDecimal(String prompt) throws EOFException {
        while (true) {
            out.println(prompt + " (0 to abort) ");
            try {
                BigDecimal input = scanner.nextBigDecimal();
                scanner.nextLine();
                if (input.equals(BigDecimal.ZERO)) {
                    throw new EOFException();
                }
                return input;
            } catch(InputMismatchException e) {
                out.println("Wrong decimal format");
            }
        }
    }

    public Date getDate(String prompt) throws EOFException {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        while (true) {
            out.println(prompt + " (empty string to abort) ");
            try {
                String input = scanner.next("[0-9]+-[0-9]+-[0-9]+");
                scanner.nextLine();
                if (input.isEmpty()) {
                    throw new EOFException();
                }
                return dateformat.parse(input);
            } catch (ParseException | InputMismatchException e) {
                out.println("Wrong date format.");
                scanner.nextLine();
            }
        }
    }
}
