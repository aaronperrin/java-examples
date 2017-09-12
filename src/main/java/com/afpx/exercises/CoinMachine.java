package com.afpx.exercises;

import java.util.*;

public class CoinMachine {



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
        NavigableSet<Integer> subFactors = possibleFactors.headSet(targetSum, true);
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
