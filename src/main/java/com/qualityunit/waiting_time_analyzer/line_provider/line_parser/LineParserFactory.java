package com.qualityunit.waiting_time_analyzer.line_provider.line_parser;

import com.qualityunit.waiting_time_analyzer.exception.IncorrectDataException;
import com.qualityunit.waiting_time_analyzer.line.LineType;

public class LineParserFactory {
    public static LineParser getParser(LineType lineType) {
        if (lineType == LineType.C) {
            return WaitingTimeLineParser.getInstance();
        } else if (lineType == LineType.D) {
            return QueryLineParser.getInstance();
        } else {
            throw new IncorrectDataException("Line parser is not specified for this type of line: \"" + lineType + "\"");
        }
    }
}
