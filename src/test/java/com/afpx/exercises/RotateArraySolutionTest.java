package com.afpx.exercises;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.*;

public class RotateArraySolutionTest {

    @Rule
    public final SystemOutRule output = new SystemOutRule().enableLog();
    @Rule
    public final TextFromStandardInputStream input = TextFromStandardInputStream.emptyStandardInputStream();

    @Test
    public void test1() throws Exception {
        input.provideLines("4 0", "1 2 3 4");
        RotateArraySolution.run();
        assertEquals("1 2 3 4", output.getLog());
    }

    @Test
    public void test2() throws Exception {
        input.provideLines("4 1", "1 2 3 4");
        RotateArraySolution.run();
        assertEquals("2 3 4 1", output.getLog());
    }

    @Test
    public void test3() throws Exception {
        input.provideLines("4 4", "1 2 3 4");
        RotateArraySolution.run();
        assertEquals("1 2 3 4", output.getLog());
    }

    @Test
    public void test4() throws Exception {
        input.provideLines("4 6", "1 2 3 4");
        RotateArraySolution.run();
        assertEquals("3 4 1 2", output.getLog());
    }
}