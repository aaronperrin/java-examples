package com.afpx.exercises;

import java.util.*;

public class CoinMachine {

    private HashMap<Integer, ArrayList<Integer>> cache = new HashMap<>();
    private NavigableSet<Integer> coins = new TreeSet<>();

    public List<Integer> findLargestFactors(int targetSum) {
        return findFactors(targetSum, coins);
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

    private List<Integer> findFactors(int targetSum, NavigableSet<Integer> possibleFactors) {
        if(cache.containsKey(targetSum)) {
            return cache.get(targetSum);
        }
        // Limit the set of factors to only those that are less than the targetSum
        NavigableSet<Integer> subFactors = possibleFactors.headSet(targetSum, true);
        Iterator<Integer> iterator = subFactors.descendingIterator();
        while(iterator.hasNext()) {
            int factor = iterator.next();
            // If the factor equals the target, then the solution has been found
            if(factor == targetSum) {
                ArrayList<Integer> factors = new ArrayList<>();
                factors.add(factor);
                return factors;
            }
            int nextTarget = targetSum - factor;
            List<Integer> factors = findFactors(nextTarget, subFactors);
            if(!factors.isEmpty()) {
                factors.add(factor);
                return factors;
            }
        }

        // If there are no suitable factors left, then this path is invalid.
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int target = in.nextInt();
        int coinCount = in.nextInt();
        CoinMachine cm = new CoinMachine();
        for (int i = 0; i < coinCount; i++) {
            cm.addCoin(in.nextInt());
        }
        List<Integer> largestFactors = cm.findLargestFactors(target);
        System.out.println(largestFactors);
    }

    public void addCoin(int value) {
        if(!coins.contains(value)) {
            coins.add(value);
        }
    }
}
