package org.example.utils;

import java.util.Arrays;

public class Utils {
    public static int getCharOccurrences(String line, char c) {
        int result = 0;
        for (char ch : line.toCharArray()) {
            if (ch == c) {
                result++;
            }
        }
        return result;
    }

    public static boolean isWordInSentence(String line, String word) {
        String[] split = line.split(" ");
        String current = "";
        for (int i = 0; i < split.length; i++) {
            current += split[i];
            current += " ";
            if (line.contains(current)) {
                return true;
            }
        }
        return false;
    }
}
