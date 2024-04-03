import java.io.*;

/* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */

class Result {

    /*
     * Complete the 'minimumPasses' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. LONG_INTEGER m
     *  2. LONG_INTEGER w
     *  3. LONG_INTEGER p
     *  4. LONG_INTEGER n
     */

    public static long minimumPasses(long m, long w, long p, long n) {
        // Write your code here
        return (long) minimumPasses((double) m, (double) w, p, n);
    }

    public static double minimumPasses(double m, double w, long p, long n) {
        double pass = 0D;
        double candiesMade = 0D;
        double candyRate;
        double minPasses = Double.MAX_VALUE;

        if (n <= p) {
            candyRate = m * w;
            double passesNeeded = Math.ceil(n / candyRate);
            return passesNeeded;
        }
        while (true) {
            candiesMade += (m * w);
            ++pass;
            candyRate = m * w;
            double passesNeeded = Math.ceil((n - candiesMade) / candyRate);
            if (passesNeeded < 0) passesNeeded = 0;
            minPasses = Math.min(minPasses, pass + passesNeeded);
            if (passesNeeded == 0) break;

            if (candiesMade < p) {
                passesNeeded = Math.ceil((p - candiesMade) / candyRate);
                pass += passesNeeded;
                candiesMade += (passesNeeded * m * w);
            }
            double totalBuy = Math.floor(candiesMade / p);
            candiesMade = candiesMade % p;
            if (m < w) {
                double temp = m;
                m = w;
                w = temp;
            }
            double diff = m - w;
            if (totalBuy > diff) {
                w += diff;
                totalBuy -= diff;
                double firstHalf = Math.floor(totalBuy / 2);
                double secondHalf = totalBuy - firstHalf;
                m += firstHalf;
                w += secondHalf;
            } else {
                w += totalBuy;
            }
        }
        return minPasses;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        long m = Long.parseLong(firstMultipleInput[0]);

        long w = Long.parseLong(firstMultipleInput[1]);

        long p = Long.parseLong(firstMultipleInput[2]);

        long n = Long.parseLong(firstMultipleInput[3]);

        long result = Result.minimumPasses(m, w, p, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
