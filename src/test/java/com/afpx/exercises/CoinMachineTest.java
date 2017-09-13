package com.afpx.exercises;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CoinMachineTest {
    @Test
    public void simpleSingleFactor() throws Exception {
        CoinMachine cm = new CoinMachine();
        cm.addCoin(1);
        Optional<CoinMachine.Factors> largestFactors = cm.findLargestFactors(1);
        assertThat(largestFactors.get().toString(), is("1"));
    }

    /*
        coins = {3, 6, 7. 9}
        target0 = 20
        current0 = 9
            target1 = target0 - current0 = 11
            current1 = 9
                target2 = target1 - current1 = 2
                current2 = NONE
                <---- {}
            current1 = 7
                target2 = target1 - current1 = 4
                current2 = 3
                    target3 = target2 - current2 = 1
                    current3 = NONE
                    <--- {}
                current2 = NONE
                <--- {}
            current1 = 6
                target2 = target1 - current1 = 5
                current2 = 3
                    target3 = target2 - current2 = 2
                    current3 = NONE
                    <--- {}
                current2 = NONE
                <--- {}
            current1 = 3
                target2 = target1 - current1 = 8
                current2 = 7
                    target3 = target2 - current2 = 1
                    current3 = NONE
                    <--- {}
                current2 = 6
                    target3 = target2 - current2 = 2
                    current3 = NONE
                    <--- {}
                current2 = 3
                    target3 = target2 - current2 = 5
                    current3 = 3
                        target4 = target3 - current3 = 2
                        current4 = NONE
                        <--- {}
                    current3 = NONE
                    <--- {}
                current2 = NONE
                <--- {}
           current1 = NONE
           <--- {}
        current0 = 7
            target1 = target0 - current0 = 13
            current1 = 9
                target2 = target1 - current1 = 4
                current2 = 3
                    target3 = target2 - current2 = 1
                    current3 = NONE
                    <--- {}
                current2 = NONE
                <-- {}
            current1 = 7
                target2 = target1 - current1 = 6
                current2 = 6
                    target3 = target2 - current2 = 0
                <-- {6}
            <-- {7, 6}
        <-- {7, 7, 6}
         */
    @Test
    public void testExample1() throws Exception {
        CoinMachine cm = new CoinMachine();
        cm.addCoin(3);
        cm.addCoin(6);
        cm.addCoin(7);
        cm.addCoin(9);
        Optional<CoinMachine.Factors> largestFactors = cm.findLargestFactors(20);
        assertThat(largestFactors.get().toString(), is("6,7,7"));
    }

    /*
        target0 = 25
        current0 = 9
            target1 = target0 - current0 = 16
            current1 = 9
                target2 = target1 - current1 = 7
                current2 = 7
                    target3 = target2 - current2 = 0
                <--- {7}
            <--- {7, 9}
        <--- {7, 9, 9}
     */
    @Test
    public void testExample2() throws Exception {
        CoinMachine cm = new CoinMachine();
        cm.addCoin(3);
        cm.addCoin(6);
        cm.addCoin(7);
        cm.addCoin(9);
        Optional<CoinMachine.Factors> largestFactors = cm.findLargestFactors(25);
        assertThat(largestFactors.get().toString(), is("7,9,9"));
    }

    /*
        coins = {3, 6, 7. 9}
        target0 = 9
        current0 = 9
            target1 = target0 - current0 = 0
        <--- {9}
     */
    @Test
    public void testExample3() throws Exception {
        CoinMachine cm = new CoinMachine();
        cm.addCoin(3);
        cm.addCoin(6);
        cm.addCoin(7);
        cm.addCoin(9);
        Optional<CoinMachine.Factors> largestFactors = cm.findLargestFactors(9);
        assertThat(largestFactors.get().toString(), is("9"));
    }

    /*
        coins = {3, 6, 7. 9}
        target0 = 8
        current0 = 7
            target1 = target0 - current0 = 1
            current1 = NONE
            <--- {)}
        current0 = 6
            target1 = target0 - current0 = 2
            current1 = NONE
            <--- {}
        current0 = 3
            target1 = target0 - current0 = 5
            current1 = 3
                target2 = target1 - current1 = 2
                current2 = NONE
                <--- {}
            <--- {}
        <--- {}
     */
    @Test
    public void testExample4() throws Exception {
        CoinMachine cm = new CoinMachine();
        cm.addCoin(3);
        cm.addCoin(6);
        cm.addCoin(7);
        cm.addCoin(9);
        Optional<CoinMachine.Factors> largestFactors = cm.findLargestFactors(8);
        assertThat(largestFactors.isPresent(), is(false));
    }
}