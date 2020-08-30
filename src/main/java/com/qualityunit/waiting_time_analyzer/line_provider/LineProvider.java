package com.qualityunit.waiting_time_analyzer.line_provider;

import com.qualityunit.waiting_time_analyzer.line.Line;

import java.io.IOException;

public interface LineProvider {
    Line getLine() throws IOException;
}
