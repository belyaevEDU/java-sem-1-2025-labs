package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class StreamAPIMethods {
    public static double getAverageOfIntList(List<Integer> list) {
        return list.stream()
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0.0);
    }

    public static List<String> transformStringList(List<String> list) {
        return list.stream()
                .map(e -> "_new_" + e.toUpperCase())
                .toList();
    }

    public static List<Integer> getDistinctPower2s(List<Integer> list) {
        return list.stream()
                .distinct()
                .map(e -> e * e)
                .toList();
    }

    public static Collection<String> task4IdkHowToCallThis(Collection<String> collection, char start) {
        return collection.stream()
                .filter(e -> !e.isEmpty())
                .filter(e -> e.charAt(0) == start)
                .sorted()
                .toList();
    }

    public static Object getLastElementOrThrow(Collection<?> collection) throws IllegalArgumentException {
        return collection.stream()
                .skip(!collection.isEmpty() ? collection.size() - 1 : 0)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static int getSumOfEvenNumbers(int[] array) {
        return Arrays.stream(array)
                .filter(e -> e % 2 == 0)
                .sum();
    }

    public static Map<Character, String> strListToMap(List<String> list) {
        return list.stream()
                .collect(Collectors.toMap(e -> e.charAt(0),
                        e -> e.substring(1), (r1, r2) -> r1));
    }
}
