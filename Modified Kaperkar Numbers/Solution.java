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
     * Complete the 'kaprekarNumbers' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER p
     *  2. INTEGER q
     */
     
     static boolean isKaprekar(long n){
         String s=String.valueOf(n*n);
         int mid=s.length()/2;
         String left=s.substring(0,mid),right=s.substring(mid);
         left=left.length()==0?"0":left;
         return Integer.parseInt(left)+Integer.parseInt(right)==n;
     }

    public static void kaprekarNumbers(int p, int q) {
    var list=IntStream.rangeClosed(p,q).filter(Result::isKaprekar).boxed().collect(Collectors.toList());
    Collections.sort(list);
    String result=list.stream().map(e->String.valueOf(e)).collect(Collectors.joining(" "));
    System.out.println(list.size()>0?result:"INVALID RANGE");
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int p = Integer.parseInt(bufferedReader.readLine().trim());

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        Result.kaprekarNumbers(p, q);

        bufferedReader.close();
    }
}
