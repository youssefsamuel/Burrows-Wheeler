import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        int first = 0;
        String s = BinaryStdIn.readString();
        StringBuilder sB = new StringBuilder();
        CircularSuffixArray cfa = new CircularSuffixArray(s);
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int index = cfa.index(i);
            if (index == 0) {
                sB.append(s.charAt(n - 1));
                first = i;
            } else
                sB.append(s.charAt(index - 1));
        }
        BinaryStdOut.write(first);
        for (int i = 0; i < n; i++) {
            BinaryStdOut.write(sB.charAt(i), 8);
        }
        BinaryStdIn.close();
        BinaryStdOut.close();
    }
    
    private static void sort(char[] a)
    {
        int r = 256;
        int n = a.length;
        char[] aux = new char[n];
        int[] count = new int[r+1];
        for (int i = 0; i < n; i++)
        count[a[i]+1]++;
        for (int v = 0; v < r; v++)
        count[v+1] += count[v];
        for (int i = 0; i < n; i++)
        aux[count[a[i]]++] = a[i];
        for (int i = 0; i < n; i++)
        a[i] = aux[i];
    }
    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString();
        char[] t = s.toCharArray();
        int n = s.length();
        int[] next = new int[n];
        char[] col1 = s.toCharArray();
        sort(col1);
        HashMap<Character, List<List<Integer>>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list1 = new ArrayList<>();
            char x = col1[i];
            if (!map.containsKey(x)) {
                list1.add(i);
                List<List<Integer>> lists = new ArrayList<>();
                lists.add(list1);
                lists.add(new ArrayList<Integer>());
                map.put(x, lists);

            } else {
                map.get(x).get(0).add(i);
            }
        }
        for (int i = 0; i < n; i++) {
            char x = t[i];
            map.get(x).get(1).add(i);
        }
        for (char x : map.keySet()) {
            int k = 0;
            for (int i : map.get(x).get(0)) {
                next[i] = map.get(x).get(1).get(k);
                k++;
            }
        }
        for (int i = 0; i < n; i++) {
            BinaryStdOut.write(col1[first], 8);
            first = next[first];
        }
        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-"))
            transform();
        else if (args[0].equals("+"))
            inverseTransform();
        else
            throw new IllegalArgumentException("Illegal command line argument");
    }

}