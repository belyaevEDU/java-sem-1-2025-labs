package org.example;

import javafx.util.Pair;
import org.example.customDictionary.CustomDictionary;
import org.example.customDictionary.FileReadException;
import org.example.customDictionary.InvalidFileFormatException;
import org.example.customDictionary.TranslationProcessor;
import org.example.utils.Utils;

import java.io.File;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //System.out.println(Utils.isWordInSentence("a s d", "a"));

        String dictionaryPath = "src/main/resources/dictionary.txt";
        String inputFilePath = "src/main/resources/inputFile.txt";
        CustomDictionary dictionary = null;
        try {
            dictionary = new CustomDictionary(dictionaryPath);
        } catch (FileReadException | InvalidFileFormatException e) {
            throw new RuntimeException(e);
        }
        try {
            TranslationProcessor.translate(dictionary, new File(inputFilePath));
        } catch (FileReadException e) {
            throw new RuntimeException(e);
        }
    }
}