package com.qualityunit.waiting_time_analyzer.line_provider.line_parser;

import com.qualityunit.waiting_time_analyzer.exception.IncorrectDataException;
import com.qualityunit.waiting_time_analyzer.line.LineType;

import java.util.HashMap;
import java.util.Map;

public class LineParserFactory {

    private static final Map<LineType, LineParser> PARSERS = new HashMap<>();

    static {
        PARSERS.put(LineType.C, new WaitingTimeLineParser());
        PARSERS.put(LineType.D, new QueryLineParser());
    }

    public static LineParser getParser(LineType lineType) {
        LineParser parser = PARSERS.get(lineType);
        if (parser == null) {
            throw new IncorrectDataException("Line parser is not specified for this type of line: \"" + lineType + "\"");
        }
        return parser;
    }
}
