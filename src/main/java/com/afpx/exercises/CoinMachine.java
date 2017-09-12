package com.afpx.exercises;

import java.util.*;

public class CoinMachine {

    /*
        Need findFactors tree data structure
        So that {3, 6, 7. 9} can be represented as
        3 -> {3}
        6 -> {6}, {3, 3}
        7 -> {7}
        9 -> {9}, {6, 3}

        target0 = 20
        current0 = 9
            target1 = target0 - current0 = 11
            current1 = 9
                target2 = target1 - current1 = 2
                current2 = NONE
                <----
            current1 = 7
                target2 = target1 - current1 = 4
                current2 = 3
                    target3 = target2 - current2 = 1
                    current3 = NONE
                    <---
                current2 = NONE
                <---
            current1 = 6
                target2 = target1 - current1 = 5
                current2 = 3
                    target3 = target2 - current2 = 2
                    current3 = NONE
                    <---
                current2 = NONE
                <---
            current1 = 3
                target2 = target1 - current1 = 8
                current2 = 7
                    target3 = target2 - current2 = 1
                    current3 = NONE
                    <---
                current2 = 6
                    target3 = target2 - current2 = 2
                    current3 = NONE
                    <---
                current2 = 3
                    target3 = target2 - current2 = 5
                    current3 = 3
                        target4 = target3 - current3 = 2
                        current4 = NONE
                        <---
                    current3 = NONE
                    <---
                current2 = NONE
                <---
           current1 = NONE
           <---
        current0 = 7
            target1 = target0 - current0 = 13
            current1 = 6
                target2 = target1 - current1 = 1
                current2 = NONE
                <--- {}
            current1 = 3
                target2 = target1 - current1 = 10
                current2 = 9
                    target3 = target2 - current2 = 1
                    current3 = NONE
                    <--- {}
                current2 = 7
                    target3 = target2 - current2 = 3
                    current3 = 3
                        target4 = target3 - current3 = 0
                    <--- {3}
                <--- {7, 3}
            <--- {3, 7, 3}
        <--- {7, 3, 7, 3}

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

        target0 = 9
        current0 = 9
            target1 = target0 - current0 = 0
        <--- {9}

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

    private NavigableSet<Integer> coins = new TreeSet<>();

    public ArrayList<Integer> findLargestFactors(int targetSum) {
        ArrayList<Integer> factors = new ArrayList<>();
        findFactors(factors, targetSum, coins);
        return factors;
    }

//    public ArrayList<ArrayList<Integer>> findAllFactors(int targetSum) {
//        ArrayList<ArrayList<Integer>> allFactors = new ArrayList<>();
//        ArrayList<Integer> largestFactors = findLargestFactors(targetSum);
//        if(!largestFactors.isEmpty()) {
//            allFactors.add(largestFactors);
//            for(int i = 0; i < largestFactors.size(); i++) {
//                findAllFactors(largestFactors)
//            }
//        }
//    }

    private boolean findFactors(ArrayList<Integer> outFactors, int targetSum, NavigableSet<Integer> possibleFactors) {
        // Limit the set of factors to only those that are less than the targetSum
        NavigableSet<Integer> subFactors = possibleFactors.headSet(targetSum, false);

        Iterator<Integer> iterator = subFactors.descendingIterator();
        while(iterator.hasNext()) {
            int factor = iterator.next();
            // If the factor equals the target, then the solution has been found
            if(factor == targetSum) {
                outFactors.add(factor);
                return true;
            }
            int nextTarget = targetSum - factor;
            boolean hasSolution = findFactors(outFactors, nextTarget, subFactors);
            if(hasSolution) {
                outFactors.add(factor);
                return true;
            }
        }

        // If there are no suitable factors left, then this path is invalid.
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int target = in.nextInt();
        int coinCount = in.nextInt();
        CoinMachine cm = new CoinMachine();
        for (int i = 0; i < coinCount; i++) {
            cm.addCoin(in.nextInt());
        }
        ArrayList<Integer> largestFactors = cm.findLargestFactors(target);
        System.out.println(largestFactors);
    }

    public void addCoin(int value) {
        if(!coins.contains(value)) {
            coins.add(value);
        }
    }
}
