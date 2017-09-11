package com.afpx.exercises;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

public class BalancedBracketTester {
    public static boolean isBalanced(String line) {
        int unmatched = 0;
        Deque<Character> tokenStack = new ArrayDeque<>();
        for (int j = 0; j < line.length(); j++) {
            char c = line.charAt(j);
            switch (c) {
                case '}':
                    if(!tokenStack.isEmpty() && tokenStack.peek().equals('{')) {
                        tokenStack.pop();
                        unmatched--;
                    }
                    break;
                case ']':
                    if(!tokenStack.isEmpty() && tokenStack.peek().equals('[')) {
                        tokenStack.pop();
                        unmatched--;
                    }
                    break;
                case ')':
                    if(!tokenStack.isEmpty() && tokenStack.peek().equals('(')) {
                        tokenStack.pop();
                        unmatched--;
                    }
                    break;
                case '{':
                case '(':
                case '[':
                    tokenStack.push(c);
                    unmatched++;
                    break;
                default:
                    break;
            }
        }
        return unmatched == 0;
    }

    static void run() {
        Scanner in = new Scanner(System.in);
        int lines = in.nextInt();

        for (int i = 0; i < lines; i++) {
            String line = in.nextLine();

            if(isBalanced(line)) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }
}
