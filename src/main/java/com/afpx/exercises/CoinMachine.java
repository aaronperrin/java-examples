package com.afpx.exercises;

import java.util.*;
import java.util.stream.Collectors;

public class CoinMachine {

    static class Factors implements Comparable<Factors> {
        List<Factors> factors = null;
        int sum;

        public Factors(int sum) {
            this.sum = sum;
        }

        public Factors(Factors factors, int factor) {
            this.factors = new ArrayList<>();
            this.factors.add(factors);
            this.factors.add(new Factors(factor));
            sum = factors.getSum() + factor;
        }

        public boolean isComposite() {
            return factors != null;
        }

        public List<Factors> getSubFactors() {
            return factors;
        }

        @Override
        public String toString() {
            if(factors != null) {
                return factors.stream().map(Factors::toString).collect(Collectors.joining(","));
            }
            return Integer.toString(sum);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return hashCode() == o.hashCode();
        }

        @Override
        public int hashCode() {
            if(factors == null) {
                return Integer.hashCode(sum);
            }
            return Objects.hash(factors, sum);
        }

        public int getSum() {
            return sum;
        }

        @Override
        public int compareTo(Factors o) {
            if(o.factors != null && factors != null) {
                if(o.factors.size() != factors.size()) {
                    return getSum() - o.getSum();
                }
                for(int i = 0; i < factors.size(); i++) {
                    int compare = o.factors.get(i).getSum() - factors.get(i).getSum();
                    if(compare != 0) {
                        return compare;
                    }
                }
            }
            else {
                return getSum() - o.getSum();
            }
            return 0;
        }
    }

    private HashMap<Integer, Factors> cache = new HashMap<>();
    private NavigableSet<Integer> coins = new TreeSet<>();

//    public NavigableSet<Factors> findAllFactors(int targetSum) {
//        NavigableSet<Factors> allFactors = new TreeSet<>();
//        Optional<Factors> largest = findLargestFactors(targetSum);
//        if(largest.isPresent()) {
//            Factors factors = largest.get();
//            allFactors.add(factors);
//            if (factors.isComposite()) {
//                List<Factors> subFactors = factors.getSubFactors();
//                for (int i = 0; i < subFactors.size(); i++) {
//                    NavigableSet<Factors> subSubFactors = findAllFactors(subFactors.get(i).getSum() - 1);
//                    allFactors.addAll(subSubFactors);
//                }
//            }
//            else {
//                allFactors.add(factors);
//            }
//        }
//        return allFactors;
//    }

    public Optional<Factors> findLargestFactors(int targetSum) {
        Factors factors = findLargestFactors(targetSum, coins);
        return Optional.ofNullable(factors);
    }

    private Factors findLargestFactors(int targetSum, NavigableSet<Integer> possibleFactors) {
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
                Factors factors = new Factors(factor);
                cache.put(targetSum, factors);
                return factors;
            }
            int nextTarget = targetSum - factor;
            Factors factors = findLargestFactors(nextTarget, subFactors);
            if(factors != null) {
                factors = new Factors(factors, factor);
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
        int coinCount = in .nextInt();
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
            cache.put(value, new Factors(value));
        }
    }
}
