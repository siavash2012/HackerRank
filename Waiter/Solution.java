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
     * Complete the 'waiter' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY number
     *  2. INTEGER q
     */
     static int[] primes=sieve();
     
     public static int[] sieve(){
         int[] arr=new int[9733+2];
         int p=2,mul=2;
         while(p<arr.length){
             while(p*mul<arr.length){
                 arr[p*mul++]=1;
             }
             ++p;
             mul=2;
             while(p<arr.length && arr[p]==1)++p; 
         }
         return arr;
     }

    public static List<Integer> waiter(List<Integer> number, int q) {
    // Write your code here
    int index=2;
    LinkedList<Integer> a=new LinkedList<Integer>();
    LinkedList<Integer> b=new LinkedList<Integer>();
    LinkedList<Integer>list=new LinkedList<>();
    list.addAll(number);
    List<Integer>ans=new ArrayList<>();
    
    for(int i=0;i<q;++i){
        while(primes[index]==1)++index;
        while(!list.isEmpty()){
            Integer poll=list.pollLast();
            if(poll%index!=0)a.add(poll);
            else b.addFirst(poll);
        }
        ans.addAll(b);
        b.clear();
        list.addAll(a);
        a.clear();
        ++index;
    }
    Collections.reverse(list);
    ans.addAll(list);
    return ans;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> number = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.waiter(number, q);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
