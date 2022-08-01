import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {

    private static final int R = 256;

    // apply move-to-front encoding, reading from standard input and writing to
    // standard output
    public static void encode() {
        int[] position = new int[R];
        char[] iToc = new char[R];
        for (int i = 0; i < R; i++) {
            position[i] = i;
            iToc[i] = (char) i;
        }
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar(8);
            BinaryStdOut.write(position[c], 8);
            char next = iToc[0];
            for (int i = 1; i <= position[c]; i++) {
                char temp = iToc[i];
                iToc[i] = next;
                position[next] = i;
                next = temp;
            }
            iToc[0] = c;
            position[c] = 0;
        }
        BinaryStdOut.close();
        BinaryStdIn.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to
    // standard output
    public static void decode() {
        int[] position = new int[R];
        char[] iToc = new char[R];
        for (int i = 0; i < R; i++) {
            position[i] = i;
            iToc[i] = (char) i;
        }
        while (!BinaryStdIn.isEmpty()) {
            int c = BinaryStdIn.readInt(8);
            char x = iToc[c];
            BinaryStdOut.write(x, 8);
            char next = iToc[0];
            for (int i = 1; i <= position[x]; i++) {
                char temp = iToc[i];
                iToc[i] = next;
                position[next] = i;
                next = temp;
            }
            iToc[0] = x;
            position[x] = 0;
        }
        BinaryStdOut.close();
        BinaryStdIn.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-"))
            encode();
        else if (args[0].equals("+"))
            decode();
        else
            throw new IllegalArgumentException("Illegal command line argument");
    }

}