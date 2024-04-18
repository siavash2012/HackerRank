import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */

class Result {

    /*
     * Complete the 'minimumDistances' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    static int minDistance(List < Integer > col) {
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < col.size() - 1; ++i) {
            distance = Math.min(distance, col.get(i + 1) - col.get(i));
        }
        return distance;
    }

    public static int minimumDistances(List <Integer> a) {
        // Write your code here
        int distance = Integer.MAX_VALUE;
        var map = new HashMap < Integer,List <Integer>>();
        for (int i = 0; i < a.size(); ++i) {
            map.merge(a.get(i), new ArrayList <> (List.of(i)), (b, c) -> {
                b.addAll(c);
                return b;
            });
        }
       
        for (var e: map.entrySet()) {
            if (e.getValue().size() == 1) continue;
            distance = Math.min(distance, minDistance(e.getValue()));
        }

        return distance == Integer.MAX_VALUE ? -1 : distance;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List < Integer > a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.minimumDistances(a);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
