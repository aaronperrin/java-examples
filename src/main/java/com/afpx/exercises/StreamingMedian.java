package com.afpx.exercises;

import java.util.PriorityQueue;
import java.util.Scanner;

public class StreamingMedian {
    PriorityQueue<Integer> lower;
    PriorityQueue<Integer> upper;
    Integer median = null;

    public StreamingMedian(int size) {
        lower = new PriorityQueue<>(size / 2 + 1, (l, r) -> Integer.compare(r, l));
        upper = new PriorityQueue<>(size / 2 + 1, Integer::compare);
    }

    public void add(int val) {
        if (median != null) {
            if (val > median) {
                upper.add(val);
                lower.add(median);
            } else {
                lower.add(val);
                upper.add(median);
            }
            median = null;
        } else {
            if (lower.isEmpty() && upper.isEmpty()) {
                median = val;
            }
            else {
                upper.add(val);
                lower.add(upper.poll());
                median = lower.poll();
            }
        }
    }

    double getMedian() {
        if (median != null) {
            return median;
        } else {
            return (lower.peek() + upper.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        StreamingMedian median = new StreamingMedian(n);
        for (int i = 0; i < n; i++) {
            median.add(in.nextInt());
            System.out.println(median.getMedian());
        }
    }
}
