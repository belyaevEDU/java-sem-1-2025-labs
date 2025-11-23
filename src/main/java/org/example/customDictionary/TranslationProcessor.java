package org.example.customDictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// handling punctuation
// the punctuation is at the end of the word
// so we remember the # of the words in the current token and the mark that is after it, apply after

public class TranslationProcessor {
    private static String removeAllPunctuation(String toTransform) {
        final char[] punctuation = new char[]{'.', '\'', '"', ',', ';', '(', ')', '#', '$', '!', '?'};

        StringBuilder builder = new StringBuilder();

        char[] charArray = toTransform.toCharArray();
        int spaceCurrent = 0;
        for (char c : charArray) {
            if (c == ' ') {
                spaceCurrent++;
            }

            boolean inArray = false;
            for (char c1 : punctuation) {
                if (c1 == c) {
                    inArray = true;
                }
            }
            if (!inArray) {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    private static void addSpaceIfNotEmpty(StringBuilder builder) {
        if (!builder.toString().isEmpty()) {
            builder.append(' ');
        }
    }

    private static boolean hasLongerKeyInFile(CustomDictionary dictionary, String key, String line, int start) {
        String[] words = line.split(" ");
        String[] wordsTrimmed = Arrays.copyOfRange(words, start, words.length);
        String lineTrimmed = String.join(" ", wordsTrimmed);

        List<String> keys = dictionary.getLongerKeys(key);
        if (keys.isEmpty()) {
            return false;
        }
        for (String keyInKeys : keys) {
            if (lineTrimmed.contains(keyInKeys)) {
                return true;
            }
        }
        return false;
    }

    public static void translate(CustomDictionary dictionary, File inputFile) throws FileReadException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            throw new FileReadException(e);
        }

        while (scanner.hasNextLine()) { // by word:
            StringBuilder resultLine = new StringBuilder();
            String line = scanner.nextLine();
            String[] words = line.split(" ");
            String currentToken = "";

            int currentCut = 0; // vars for keys with spaces
            boolean cutInProgress = false;

            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                word = word.strip();

                if (currentToken.isEmpty()) {
                    currentToken = removeAllPunctuation(word);

                    if (hasLongerKeyInFile(dictionary, currentToken, line, i) && !cutInProgress) {
                        currentCut = i;
                        cutInProgress = true; // im so fucking stupid its insane
                    } else if (dictionary.hasTranslation(currentToken)) {
                        addSpaceIfNotEmpty(resultLine);
                        resultLine.append(dictionary.getTranslation(currentToken));
                        currentToken = "";
                    } else {
                        addSpaceIfNotEmpty(resultLine);
                        resultLine.append(word);
                        currentToken = "";
                    }
                } else {
                    // token will not be empty if dict has longer key
                    currentToken += " ";
                    currentToken += removeAllPunctuation(word);
                    if (!hasLongerKeyInFile(dictionary, currentToken, line, currentCut)
                            && !dictionary.hasTranslation(currentToken)) {
                        throw new FileReadException("longer key failed");
                    }

                    if (dictionary.hasTranslation(currentToken)) {
                        addSpaceIfNotEmpty(resultLine);
                        resultLine.append(dictionary.getTranslation(currentToken));
                        currentToken = "";
                        currentCut = 0;
                        cutInProgress = false;
                    }
                }
            }
            System.out.println(resultLine);
        }
    }
}
