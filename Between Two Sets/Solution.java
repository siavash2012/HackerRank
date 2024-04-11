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
     * Complete the 'getTotalX' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER_ARRAY b
     */

    public static int getTotalX(List < Integer > a, List < Integer > b) {
        // Write your code here
        int count = 0;
        Set < Integer > bFactors = allFactors(b);
        OUTER: for (int factor: bFactors) {
            for (int divisor: a) {
                if (factor % divisor != 0) continue OUTER;
            }
            ++count;
        }

        return count;

    }

    static int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }

    static Set < Integer > allFactors(List < Integer > list) {
        int g = list.get(0);
        HashSet < Integer > divisors = new HashSet < Integer > ();
        for (int i = 1; i < list.size(); i++) {
            g = gcd(list.get(i), g);
        }
        for (int i = 1; i * i <= g; i++) {
            if (g % i == 0) {
                divisors.add(i);
                if (g / i != i)
                    divisors.add(g / i);
            }
        }
        return divisors;

    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List < Integer > arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List < Integer > brr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int total = Result.getTotalX(arr, brr);

        bufferedWriter.write(String.valueOf(total));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
