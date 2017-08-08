package com.afpx.exercises;


import java.util.HashMap;
import java.util.Scanner;

public class ContactsSolution {
    static class SimpleWordTrie {
        private HashMap<Character, SimpleWordTrie> subNodes = new HashMap<>();
        private HashMap<Character, String> subNodeStrings = new HashMap<>();
        int count;

        void add(String item) {
            count++;
            if(item.isEmpty()) {
                return;
            }

            char c = item.charAt(0);
            String subString = item.substring(1);

            // If the sub trie already exists, use it
            if(subNodes.containsKey(c)) {
                subNodes.get(c).add(subString);
            }
            // if the subString already exists, replace the subString with a trie and add the new one.
            // (the subString is an optimization to avoid having to create an entire tree for only a single item)
            else if(subNodeStrings.containsKey(c)) {
                SimpleWordTrie subTrie = new SimpleWordTrie();
                subTrie.add(subNodeStrings.remove(c));
                subTrie.add(subString);
                subNodes.put(c, subTrie);
            }
            else {
                subNodeStrings.put(c, subString);
            }
        }

        int getMatchCount(String item) {
            if(item.isEmpty()) {
                return count;
            }
            char c = item.charAt(0);
            if(subNodeStrings.containsKey(c) && subNodeStrings.get(c).equals(item.substring(1))) {
                return 1;
            }
            if(subNodes.containsKey(c)) {
                return subNodes.get(c).getMatchCount(item.substring(1));
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        SimpleWordTrie trie = new SimpleWordTrie();
        for(int a0 = 0; a0 < n; a0++){
            String op = in.next();
            String contact = in.next();
            switch(op) {
                case "find":
                    System.out.println(trie.getMatchCount(contact));
                    break;
                case "add":
                    trie.add(contact);
                    break;
                default:
                    break;
            }
        }
        in.close();
    }
}
