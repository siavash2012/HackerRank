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
     * Complete the 'decentNumber' function below.
     *
     * The function accepts INTEGER n as parameter.
     */

    public static void decentNumber(int n) {
    // Write your code here
    
    for(int i=n;i>=0;--i){
        int fives=i;
        int threes=n-i;
        if(fives%3==0 && threes%5==0){
            for(int j=0;j<fives;++j){
                System.out.print("5");
            }
            for(int j=0;j<threes;++j){
                System.out.print("3");
            }
            System.out.println();
            return;
        }
    }
    System.out.println("-1");

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                Result.decentNumber(n);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
