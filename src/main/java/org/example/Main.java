package org.example;

import org.example.customDictionary.CustomDictionary;
import org.example.customDictionary.FileReadException;
import org.example.customDictionary.InvalidFileFormatException;
import org.example.customDictionary.TranslationProcessor;

import java.io.File;

public class Main {
    public static void main(String[] args) {

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