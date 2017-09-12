package com.afpx.exercises;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CoinMachineTest {
    @Test
    public void oneToOne() throws Exception {
        CoinMachine cm = new CoinMachine();
        cm.addCoin(1);
        ArrayList<Integer> largestFactors = cm.findLargestFactors(1);
        assertEquals(1, largestFactors.size());
        assertThat(largestFactors.get(0), is(1));
    }
}