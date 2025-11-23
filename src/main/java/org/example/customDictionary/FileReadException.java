package org.example.customDictionary;

import java.io.IOException;

public class FileReadException extends IOException {
    public FileReadException() {
        super("FileReadException: dictionary reading failed");
    }

    public FileReadException(String message) {
        super(message);
    }

    public FileReadException(Throwable cause) {
        super(cause);
    }

    public FileReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
