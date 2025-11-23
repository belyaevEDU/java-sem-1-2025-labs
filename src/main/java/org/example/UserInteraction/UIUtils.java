package org.example.UserInteraction;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

class UIUtils {
    public static void listAllArrayIndexToVal(ArrayList<?> arrayList) {
        for (int index = 0; index < arrayList.size(); index++) {
            System.out.println(index + ": " + arrayList.get(index));
        }
    }

    public static Optional<Integer> safeUserQueryForInt(Scanner scanner) {
        Optional<Integer> result = Optional.empty();
        try {
            result = Optional.of(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Non-numeric input");
        }
        return result;
    }

    public static void waitForUserToContinue(Scanner scanner) {
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }
}
