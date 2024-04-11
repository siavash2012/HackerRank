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
     * Complete the 'abbreviation' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING a
     *  2. STRING b
     */

    public static String abbreviation(String a, String b) {
    // Write your code here
    boolean[][] dp = new boolean[a.length()+1][b.length()+1];
    dp[0][0]=true;
    for (int i = 1; i <dp.length; i++) {
    dp[i][0]=true;
      for (int j = 1; j <dp[i].length; j++) {
        boolean isLowerCase=Character.isLowerCase(a.charAt(i-1));
        boolean matches = Character.toUpperCase(a.charAt(i-1)) == b.charAt(j-1);
        dp[i][j] = (matches && dp[i-1][j-1]) || (isLowerCase && dp[i-1][j]); 
      }
    }
    return dp[a.length()][b.length()] ? "YES" : "NO"; 
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String a = bufferedReader.readLine();

                String b = bufferedReader.readLine();

                String result = Result.abbreviation(a, b);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
