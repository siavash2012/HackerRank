import java.io.*;
import java.util.*;
import java.util.stream.*;

/* Code implemented by Siavash Khalaj */

class Result {

    /*
     * Complete the 'flippingBits' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts LONG_INTEGER n as parameter.
     */
     
    public static String toBinaryString(long n){
        return String.format("%32S",Long.toBinaryString(n)).replaceAll(" ","0");
    }

    public static long flippingBits(long n) {
        String s=toBinaryString(n);
        String flipped=Arrays.stream(s.split("")).<String>map(e->{
            if(e.equals("0")){
                return "1";
            }else{
                return "0";
            }
        }).collect(Collectors.joining(""));
        
        return Long.valueOf(flipped,2);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                long n = Long.parseLong(bufferedReader.readLine().trim());

                long result = Result.flippingBits(n);

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
