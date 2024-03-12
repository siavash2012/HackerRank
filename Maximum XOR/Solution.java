import java.io.*;
import java.util.*;

/* Code implemented by Siavash Khalaj (Trie structure code is modified from Geeks for Geeks maximum XOR of numbers in an array) */

class Node {
    public Node one, zero;
}

class Trie {
    Node root;
    public Trie() {
        root = new Node();
    }

    public void insert(int n) {
        Node temp = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (n >> i) & 1;
            if (bit == 0) {
                if (temp.zero == null) {
                    temp.zero = new Node();
                }
                temp = temp.zero;
            } else {
                if (temp.one == null) {
                    temp.one = new Node();
                }
                temp = temp.one;
            }
        }
    }

    public int max_xor_helper(int value) {
        Node temp = root;
        String current_ans = "";
        for (int i = 31; i >= 0; i--) {
            int bit = (value >> i) & 1;
            if (bit == 0) {
                if (temp.one != null) {
                    temp = temp.one;
                    current_ans += "1";
                } else {
                    temp = temp.zero;
                    current_ans += "0";
                }
            } else if (bit == 1) {
                if (temp.zero != null) {
                    temp = temp.zero;
                    current_ans += "0";
                } else {
                    temp = temp.one;
                    current_ans += "1";
                }
            }
        }
        return Integer.valueOf(current_ans, 2);
    }
}

public class Solution {
    static int[] maxXor(int[] arr, int[] queries) {
        int[] result = new int[queries.length];
        Trie trie = new Trie();
        for (int i: arr) {
            trie.insert(i);
        }
        for (int i = 0; i < queries.length; ++i) {
            result[i] = queries[i] ^ trie.max_xor_helper(queries[i]);
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] queries = new int[m];

        for (int i = 0; i < m; i++) {
            int queriesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            queries[i] = queriesItem;
        }

        int[] result = maxXor(arr, queries);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
