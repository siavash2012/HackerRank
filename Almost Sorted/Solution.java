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
     * Complete the 'almostSorted' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void almostSorted(List<Integer> arr) {
    // Write your code here
    var sorted=new ArrayList<>(arr);
    Collections.sort(sorted);
    List<Integer>differentIndices=new ArrayList<>();
    int count=0;
    for(int i=0;i<arr.size();++i){
        if(! (arr.get(i).equals(sorted.get(i)))){
            differentIndices.add(i);
        }
    }
    if(differentIndices.size()==0){
        System.out.println("yes");
        return;
    }
    if(differentIndices.size()==2){
        Collections.swap(arr,differentIndices.get(0),differentIndices.get(1));
        if(arr.equals(sorted)){
            System.out.println("yes");
            System.out.println("swap"+" "+(differentIndices.get(0)+1)+" "+(differentIndices.get(1)+1));
            return;
        }
    }
    if(differentIndices.size()>2){
        int start=differentIndices.get(0);
        int end=differentIndices.get(differentIndices.size()-1)+1;
        var first=arr.subList(0,start);
        var second=arr.subList(start,end);
        Collections.reverse(second);
        var third=arr.subList(end,arr.size());
        var result=new ArrayList<>();
        result.addAll(first);
        result.addAll(second);
        result.addAll(third);
        if(result.equals(sorted)){
            System.out.println("yes");
            System.out.println("reverse"+" "+(start+1)+" "+end);
            return;
        }
    }
    System.out.println("no");

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result.almostSorted(arr);

        bufferedReader.close();
    }
}
