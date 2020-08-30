package com.qualityunit.waiting_time_analyzer.line_provider;

import com.qualityunit.waiting_time_analyzer.exception.IncorrectDataException;
import com.qualityunit.waiting_time_analyzer.line_provider.line_parser.*;
import com.qualityunit.waiting_time_analyzer.line.Line;
import com.qualityunit.waiting_time_analyzer.line.LineType;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Provides objects, inherited from abstract class <code>{@link Line}</code>,
 * using data from the source, passed to the constructor.
 * Lines are provided in the same order in which they appear in the data source.
 */
public class DirectLineProvider extends AbstractLineProvider {

    public DirectLineProvider(BufferedReader source) throws IOException {
        super(source);
    }

    /**
     * With every invocation reads next line from BufferedReader provided via constructor
     * and transforms it into object, inherited from abstract class <code>{@link Line}</code>.
     * <p>When the end of source reached, method closes BufferedReader and returns null.
     * Calling this method again will cause an IOException.
     *
     * @return object, inherited from abstract class <code>{@link Line}</code> or null if end of data source reached.
     * @throws IncorrectDataException if source contains incorrect data.
     * @throws IOException if an I/O exception occurs or in case of calling this method after all data has already been read.
     */
    public Line getNextLine() throws IOException {
        String line = readLine();
        if (checkSourceEnd(line)) {
            finish();
            return null;
        }
        lineOrdinal++;

        LineType lineType = getLineType(line);
        LineParser parser = LineParserFactory.getParser(lineType);

        return parser.parseLine(line);
    }
}
