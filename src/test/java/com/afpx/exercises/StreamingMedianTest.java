package com.afpx.exercises;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.*;

public class StreamingMedianTest {
    @Rule
    public final SystemOutRule output = new SystemOutRule().enableLog();
    @Rule
    public final TextFromStandardInputStream input = TextFromStandardInputStream.emptyStandardInputStream();

    @Test
    public void test1() throws Exception {
        input.provideLines("10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        StreamingMedian.main(new String[0]);
        assertEquals("1 2 3 4", output.getLog());
    }

    @Test
    public void getMedian() throws Exception {
    }
}