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
     * Complete the 'hackerlandRadioTransmitters' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY x
     *  2. INTEGER k
     */
     
    

    public static int hackerlandRadioTransmitters(List<Integer> x, int k) {
    // Write your code here
    Collections.sort(x);
    int count=0;
    int i=0;
    while(i<x.size()){
            ++count;
            var mid=x.get(i)+k;
            var midIndex=Collections.binarySearch(x,mid);
            if(midIndex<0)midIndex=-midIndex-2;
            var j=midIndex;
            while(midIndex+1<x.size()&& x.get(j).equals(x.get(midIndex+1)))++midIndex;
            var last=x.get(midIndex)+k;
            var lastIndex=Collections.binarySearch(x,last);
            if(lastIndex<0)lastIndex=-lastIndex-2;
            j=lastIndex;
            while(lastIndex+1<x.size()&& x.get(j).equals(x.get(lastIndex+1)))++lastIndex;
            i=lastIndex+1;
    }
    return count;
    
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> x = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.hackerlandRadioTransmitters(x, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
