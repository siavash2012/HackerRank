import java.io.*;
import java.util.*;

/* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */

public class Solution{

    static int max=Integer.MIN_VALUE;

    static int find(int i, Map < Integer, Integer > nodeParentMap) {
        if (nodeParentMap.get(i) == i) {
            return i;
        } else {
            int parent = nodeParentMap.get(i);
            nodeParentMap.put(i, find(parent, nodeParentMap));
            return nodeParentMap.get(i);
        }
    }

    public static void unionBySize(int i, int j, Map < Integer, Integer > nodeParentMap, Map < Integer, Integer > sizeMap) {
        int iRep = find(i, nodeParentMap);
        int jRep = find(j, nodeParentMap);
        // If the elements are in the same set, no need to unite
        if (iRep == jRep) {
            return;
        }
        // Get the size of i tree
        int iSize = sizeMap.get(iRep);
        // Get the size of j tree
        int jSize = sizeMap.get(jRep);
        // If i size is less than j size
        if (iSize < jSize) {
            // Then move i under j
            nodeParentMap.put(iRep, jRep);
            // Increment j size by i size and update max
            sizeMap.put(jRep, sizeMap.get(jRep) + sizeMap.get(iRep));
            if(sizeMap.get(jRep)>max)max=sizeMap.get(jRep);
        }
        // Else if j size is less than i size
        else {
            // Then move j under i
            nodeParentMap.put(jRep, iRep);
            // Increment i size by j size and update max
            sizeMap.put(iRep, sizeMap.get(iRep) + sizeMap.get(jRep));
            if(sizeMap.get(iRep)>max)max=sizeMap.get(iRep);
        }
    }


    static int[] maxCircle(int[][] queries) {
        max=Integer.MIN_VALUE;
        Map < Integer, Integer > nodeParentMap = new HashMap < > ();
        Map < Integer, Integer > sizeMap = new HashMap < > ();
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int j = queries[i][0];
            int k = queries[i][1];
            if (nodeParentMap.get(j) == null && nodeParentMap.get(k) == null) {
                nodeParentMap.put(k, j);
                nodeParentMap.put(j, j);
                sizeMap.put(j, sizeMap.getOrDefault(j, 0) + 2);
                if(sizeMap.get(j)>max)max=sizeMap.get(j);
            } else if (nodeParentMap.get(j) != null && nodeParentMap.get(k) == null) {
                int parent = find(j, nodeParentMap);
                nodeParentMap.put(k, parent);
                sizeMap.put(parent, sizeMap.get(parent) + 1);
                if(sizeMap.get(parent)>max)max=sizeMap.get(parent);
            } else if (nodeParentMap.get(j) == null && nodeParentMap.get(k) != null) {
                int parent = find(k, nodeParentMap);
                nodeParentMap.put(j, parent);
                sizeMap.put(parent, sizeMap.get(parent) + 1);
                if(sizeMap.get(parent)>max)max=sizeMap.get(parent);
            } else if (nodeParentMap.get(j) != null && nodeParentMap.get(k) != null) {
                unionBySize(j, k, nodeParentMap, sizeMap);
            }
            result[i] = max;
        }
        return result;
    }


    public static void main(String[] args) throws IOException {
        final Scanner scanner = new Scanner(System.in);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] queries = new int[q][2];

        for (int i = 0; i < q; i++) {
            String[] queriesRowItems = scanner.nextLine().trim().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        int[] ans = maxCircle(queries);

        for (int i = 0; i < ans.length; i++) {
            bufferedWriter.write(String.valueOf(ans[i]));

            if (i != ans.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
