package com.afpx.exercises;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.*;

public class ContactsSolutionTest {

    @Rule
    public final SystemOutRule output = new SystemOutRule().enableLog();
    @Rule
    public final TextFromStandardInputStream input = TextFromStandardInputStream.emptyStandardInputStream();

    @Test
    public void testAddSingle() throws Exception {

        input.provideLines("4", "add a", "find a", "find aa", "find b");
        ContactsSolution.main(new String[0]);
        assertEquals("1\n0\n0\n", output.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void testAddTwoSingles() throws Exception {

        input.provideLines("7", "add a", "add b", "find a", "find aa", "find b", "find bb", "find c");
        ContactsSolution.main(new String[0]);
        assertEquals("1\n0\n1\n0\n0\n", output.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void testAddTwoWhereOneContains() throws Exception {

        input.provideLines("5", "add ab", "add a", "find a", "find ab", "find abc");
        ContactsSolution.main(new String[0]);
        assertEquals("2\n1\n0\n", output.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void testAddFourOverlapping() throws Exception {

        input.provideLines("12", "add abc", "add aaa", "add aac", "add abb", "find abc", "find aaa", "find aac", "find abb", "find a", "find aa", "find ab", "find abd");
        ContactsSolution.main(new String[0]);
        assertEquals("1\n1\n1\n1\n4\n2\n2\n0\n", output.getLogWithNormalizedLineSeparator());
    }
}