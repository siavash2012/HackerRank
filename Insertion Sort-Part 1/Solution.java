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
     * Complete the 'insertionSort1' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY arr
     */

    public static void insertionSort1(int n, List<Integer> arr) {
    // Write your code here
    
    for(int i=arr.size()-1;i>0;--i){
        int temp=arr.get(i);
        int j=i;
        while(j>0 && arr.get(j-1)>=temp){
            arr.set(j,arr.get(j-1));
            System.out.println(arr.stream().map(a->String.valueOf(a)).
            collect(Collectors.joining(" ")));
            --j;
        }
        arr.set(j,temp);
        System.out.println(arr.stream().map(a->String.valueOf(a)).
            collect(Collectors.joining(" ")));
        break;
    }

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result.insertionSort1(n, arr);

        bufferedReader.close();
    }
}
