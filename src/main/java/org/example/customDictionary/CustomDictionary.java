package org.example.customDictionary;

import javafx.util.Pair;
import org.example.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class CustomDictionary {
    private final HashSet<Pair<String, String>> memory;

    public CustomDictionary(String inputFilePath) throws FileReadException, InvalidFileFormatException {
        memory = this.read(new File(inputFilePath));
    }

    private HashSet<Pair<String, String>> read(File file) throws FileReadException, InvalidFileFormatException {
        HashSet<Pair<String, String>> result = new HashSet<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new FileReadException(e);
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split("\\|");
            if (split.length != 2) {
                throw new InvalidFileFormatException();
            }

            Pair<String, String> entry = new Pair<>(split[0].strip().toLowerCase(), split[1].strip().toLowerCase());
            result.add(entry);
        }

        return result;
    }

    public String getTranslation(String key) {
        key = key.toLowerCase();
        for (Pair<String, String> pair : memory) {
            if (pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return null;
    }

    public boolean hasTranslation(String key) {
        key = key.toLowerCase();
        return this.getTranslation(key) != null;
    }

    public List<String> getLongerKeys(String key) {
        key = key.toLowerCase();
        List<String> list = new ArrayList<>();
        for (Pair<String, String> pair : memory) {
            String curKey = pair.getKey();
            if (curKey.startsWith(key + " ") && !curKey.equals(key)) {
                list.add(curKey);
            }
        }
        return list;
    }
}
