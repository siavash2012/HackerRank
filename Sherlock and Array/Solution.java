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
     * Complete the 'balancedSums' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static String balancedSums(List<Integer> arr) {
    // Write your code here
    List<Integer>prefixSumsF=new ArrayList<>();
    List<Integer>prefixSumsR=new ArrayList<>();
    int prefixSumF=0;
    int prefixSumR=0;
    for(int n:arr){
        prefixSumF+=n;
        prefixSumsF.add(prefixSumF);
    }
    for(int i=arr.size()-1;i>=0;--i){
        prefixSumR+=arr.get(i);
        prefixSumsR.add(prefixSumR);
    }
    Collections.reverse(prefixSumsR);
    
    for(int i=0;i<arr.size();++i){
        int leftSum=i==0?0:prefixSumsF.get(i-1);
        int rightSum=i==arr.size()-1?0:prefixSumsR.get(i+1);
        if(rightSum==leftSum){
            return "YES";
        }
    }
    return "NO";

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, T).forEach(TItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                String result = Result.balancedSums(arr);

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
