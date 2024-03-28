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

class Result {

    /*
     * Complete the 'downToZero' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER n as parameter.
     */


    static Map < Integer, Integer > map = buildMap();
    static Map < Integer, Integer > buildMap() {
        Map <Integer, Integer> map = new HashMap <> ();
        map.put(0, 0);
        map.put(1, 1);
        for (int i = 2; i <= 1000000; ++i) {
            int divisor = 2;
            int min = map.get(i - 1);
            while (divisor * divisor <= i) {
                if (i % divisor == 0) {
                    min = Math.min(min, map.get(i / divisor));
                }
                ++divisor;
            }
            map.put(i, min + 1);
        }
        return map;
    }


    public static int downToZero(int n) {
        return map.get(n);
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                int result = Result.downToZero(n);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
