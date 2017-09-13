package com.afpx.exercises;

import java.util.*;
import java.util.stream.Collectors;

public class CoinMachine {

    interface Factors {

    }

    static class CompositeFactors implements Factors {
        List<Factors> factors = new ArrayList<>();

        public CompositeFactors(Factors factors, int factor) {
            this.factors.add(factors);
            this.factors.add(new SingleFactor(factor));
        }

        @Override
        public String toString() {
            return factors.stream().map(Factors::toString).collect(Collectors.joining(","));
        }
    }

    static class SingleFactor implements Factors {
        int value;

        public SingleFactor(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }
    }

    static class NullFactor implements Factors {
        @Override
        public String toString() {
            return "";
        }
    }

    private HashMap<Integer, Factors> cache = new HashMap<>();
    private NavigableSet<Integer> coins = new TreeSet<>();

    public Optional<Factors> findLargestFactors(int targetSum) {
        return Optional.ofNullable(findFactors(targetSum, coins));
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

    private Factors findFactors(int targetSum, NavigableSet<Integer> possibleFactors) {
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
                Factors factors = new SingleFactor(factor);
                cache.put(targetSum, factors);
                return factors;
            }
            int nextTarget = targetSum - factor;
            Factors factors = findFactors(nextTarget, subFactors);
            if(factors != null) {
                factors = new CompositeFactors(factors, factor);
                cache.put(targetSum, factors);
                return factors;
            }
        }

        // If there are no suitable factors left, then this path is invalid.
        cache.put(targetSum, null);
        return null;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int target = in.nextInt();
        int coinCount = in.nextInt();
        CoinMachine cm = new CoinMachine();
        for (int i = 0; i < coinCount; i++) {
            cm.addCoin(in.nextInt());
        }
        Optional<Factors> largestFactors = cm.findLargestFactors(target);
        System.out.println(largestFactors);
    }

    public void addCoin(int value) {
        if(!coins.contains(value)) {
            coins.add(value);
            cache.put(value, new SingleFactor(value));
        }
    }
}
