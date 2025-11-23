package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.example.StreamAPIMethods.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        intList.add(16);
        intList.add(32);
        intList.add(64);
        intList.add(64);
        intList.add(128);
        intList.add(256);

        List<String> strList = new ArrayList<>();
        strList.add("aasdf");
        strList.add("chfda");
        strList.add("cdfda");
        strList.add("bashdf");
        strList.add("dkgfkfg");


        System.out.println(getAverageOfIntList(intList));
        System.out.println(transformStringList(strList));
        System.out.println(getDistinctPower2s(intList));
        System.out.println(task4IdkHowToCallThis(strList, 'c'));
        System.out.println(getLastElementOrThrow(strList));

        int[] array = new int[]{1, 2, 3, 4};
        System.out.println(getSumOfEvenNumbers(array));

        Map<Character, String> map1 = strListToMap(strList);

        System.out.println(map1);
    }
}