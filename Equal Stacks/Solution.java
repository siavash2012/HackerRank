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
     * Complete the 'equalStacks' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY h1
     *  2. INTEGER_ARRAY h2
     *  3. INTEGER_ARRAY h3
     */

    public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {
    // Write your code here
    List<List<Integer>>list=Arrays.asList(h1,h2,h3);
    List<Integer>sums=Arrays.asList(h1.stream().reduce(0,Integer::sum),h2.stream().reduce(0,Integer::sum), h3.stream().reduce(0,Integer::sum));
    
    while(true){
        int sum1=sums.get(0),sum2=sums.get(1),sum3=sums.get(2);
        if(sum1==sum2 && sum2==sum3)break;
        int max=Collections.max(sums);
        if(max==sum1){
            int poll=list.get(0).get(0);
            sums.set(0,sum1-poll);
            list.get(0).remove(0);
        }else if(max==sum2){
            int poll=list.get(1).get(0);
            sums.set(1,sum2-poll);
            list.get(1).remove(0);
        }else if(max==sum3){
            int poll=list.get(2).get(0);
            sums.set(2,sum3-poll);
            list.get(2).remove(0);
        } 
    }
    return sums.get(0);

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n1 = Integer.parseInt(firstMultipleInput[0]);

        int n2 = Integer.parseInt(firstMultipleInput[1]);

        int n3 = Integer.parseInt(firstMultipleInput[2]);

        List<Integer> h1 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> h2 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> h3 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.equalStacks(h1, h2, h3);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
