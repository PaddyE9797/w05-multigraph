package pakage1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BadFileException extends Exception {
    String fileInput;
    public BadFileException(String err) {
        super(err);
    }
}
