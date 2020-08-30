package com.qualityunit.waiting_time_analyzer.line_provider.line_parser;

import com.qualityunit.waiting_time_analyzer.line.Line;

public interface LineParser {
    Line parseLine(String line);
}
