package com.afpx.exercises;

import java.util.Scanner;

class RotateArraySolution {
    static void run() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int rotations = in.nextInt();
        int numbers[] = new int[n];

        for(int i=0; i < n; i++){
            int newIndex = (i + n - rotations % n) % n;
            numbers[newIndex] = in.nextInt();
        }

        for(int i = 0; i < n; i++) {
            System.out.print(numbers[i]);
            if(i != (n - 1)) {
                System.out.print(" ");
            }
        }
    }
}