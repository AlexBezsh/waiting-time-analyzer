package com.qualityunit.waiting_time_analyzer.line_provider;

import com.qualityunit.waiting_time_analyzer.exception.IncorrectDataException;
import com.qualityunit.waiting_time_analyzer.line.LineType;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class AbstractLineProvider implements LineProvider {
    protected final BufferedReader source;
    protected final int linesNumber;
    protected int lineOrdinal = 1;

    public AbstractLineProvider(BufferedReader source) throws IOException {
        this.source = source;
        linesNumber = getLinesNumber();
        checkLinesNumber(linesNumber);
    }

    private int getLinesNumber() throws IOException {
        int linesNumber;
        try {
            linesNumber = Integer.parseInt(source.readLine());
        } catch (NumberFormatException e) {
            throw new IncorrectDataException("First line must be a number");
        }
        return linesNumber;
    }

    private void checkLinesNumber(int linesNumber) {
        if (linesNumber < 1 || linesNumber > 100_000) {
            throw new IncorrectDataException("Data source must contain from 1 to 100 000 lines.");
        }
    }

    protected String readLine() throws IOException {
        String line = source.readLine();
        if (line != null && line.trim().length() == 0) {
            throw new IncorrectDataException("Data source contains empty line");
        }
        return line;
    }

    protected LineType getLineType(String line) {
        LineType lineType;
        try {
            lineType = LineType.valueOf(String.valueOf(line.charAt(0)));
        } catch (IllegalArgumentException e) {
            throw new IncorrectDataException("Unable to define line type: \"" + line + "\"");
        }
        return lineType;
    }

    protected boolean checkSourceEnd(String line) {
        if (lineOrdinal >= linesNumber + 1) {
            if (line == null) {
                return true;
            } else {
                throw new IncorrectDataException("Data source contains more lines than was specified at the beginning.");
            }
        } else if (line == null) {
            throw new IncorrectDataException("Expected lines number: " + linesNumber + ". Actual: " + (lineOrdinal - 1) + ".");
        }
        return false;
    }

    protected void finish() throws IOException {
        source.close();
    }
}
