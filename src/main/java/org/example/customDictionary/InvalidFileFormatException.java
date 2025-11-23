package org.example.customDictionary;

import java.io.IOException;

public class InvalidFileFormatException extends IOException {
    public InvalidFileFormatException() {
        super("InvalidFileFormatException: incorrect file format for implemented dictionary");
    }

    public InvalidFileFormatException(String message) {
        super(message);
    }

    public InvalidFileFormatException(Throwable cause) {
        super(cause);
    }

    public InvalidFileFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
