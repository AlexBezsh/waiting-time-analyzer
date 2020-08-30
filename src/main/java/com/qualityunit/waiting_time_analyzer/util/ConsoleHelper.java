package com.qualityunit.waiting_time_analyzer.util;

import java.io.*;

public class ConsoleHelper {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void print(String message) {
        System.out.println(message);
    }

    public static String readString(String message) {
        message = message + " Enter 'exit' to finish the program.";
        String s;
        while (true) {
            print(message);
            try {
                s = reader.readLine();
                if (s == null || s.trim().length() == 0) {
                    print("Empty string. Please try again.");
                } else if (s.equalsIgnoreCase("exit")) {
                    exitProgram("");
                } else {
                    break;
                }
            } catch (IOException ignored) {
            }
        }
        return s;
    }

    public static String getFilePathFromUser() {
        return readString("Enter an absolute path to file with data please.");
    }

    public static void exitProgram(String message) {
        print(message);
        print("Program finished.");
        System.exit(0);
    }
}
