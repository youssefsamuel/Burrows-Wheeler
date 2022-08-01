
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.Quick3string;

public class CircularSuffixArray {
    private final int n;
    private final int[] index;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new IllegalArgumentException();
        // Compute the original suffix array.
        this.n = s.length();
        String[] suffixes = new String[n];
        index = new int[n];
        HashMap<String, List<Integer>> sufToInd = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (i == 0)
                suffixes[i] = s.substring(i);
            else
                suffixes[i] = s.substring(i) + s.substring(0, i);
            if (!sufToInd.containsKey(suffixes[i])) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                sufToInd.put(suffixes[i], list);
            } else {
                sufToInd.get(suffixes[i]).add(i);
            }
        }
        Quick3string.sort(suffixes);
        for (int i = 0; i < n; i++) {
            int k = sufToInd.get(suffixes[i]).get(0);
            sufToInd.get(suffixes[i]).remove(0);
            index[i] = k;
        }
    }

    // length of s
    public int length() {
        return this.n;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= n)
            throw new IllegalArgumentException();
        return index[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        String s = "ABRACADABRA!";
        CircularSuffixArray cfa = new CircularSuffixArray(s);
        int n = cfa.length();
        for (int i = 0; i < n; i++) {
            System.out.println(cfa.index(i));
        }
        BinaryStdOut.close();
    }

}