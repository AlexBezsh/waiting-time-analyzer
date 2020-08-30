package com.qualityunit.waiting_time_analyzer.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class DataSourceHelper {

    public static BufferedReader getReader() {
        String filePath = ConsoleHelper.getFilePathFromUser();
        return getReader(filePath);
    }

    public static BufferedReader getReader(String filePath) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            return reader;
        } catch (FileNotFoundException e) {
            ConsoleHelper.print("File not found: \"" + filePath + "\"");
            return getReader();
        }
    }
}
