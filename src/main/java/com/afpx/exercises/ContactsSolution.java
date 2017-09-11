package com.afpx.exercises;


import java.util.HashMap;
import java.util.Scanner;

public class ContactsSolution {
    static class TrieNode {
        private HashMap<Character, TrieNode> subNodes = new HashMap<>();
        public int hits;

        public void putIfAbsent(char ch) {
            subNodes.putIfAbsent(ch, new TrieNode());
        }

        public TrieNode getChild(char ch) {
            return subNodes.get(ch);
        }
    }

    static class SimpleWordTrie {
        TrieNode root = new TrieNode();

        SimpleWordTrie() {
        }

        public void add(String str) {
            TrieNode curr = root;
            for (int i = 0; i < str.length(); i++) {
                Character ch = str.charAt(i);
                curr.putIfAbsent(ch);
                curr = curr.getChild(ch);
                curr.hits++;
            }
        }

        public int getPrefixHits(String prefix) {
            TrieNode ithNode = root;

            for (int i = 0; i < prefix.length(); i++) {
                Character ithChar = prefix.charAt(i);
                ithNode = ithNode.getChild(ithChar);
                if (ithNode == null) {
                    return 0;
                }
            }
            return ithNode.hits;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        SimpleWordTrie trie = new SimpleWordTrie();
        for (int i = 0; i < n; i++) {
            String op = in.next();
            String contact = in.next();
            switch (op) {
                case "find":
                    System.out.println(trie.getPrefixHits(contact));
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
