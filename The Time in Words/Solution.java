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
     * Complete the 'timeInWords' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER h
     *  2. INTEGER m
     */

    public static String timeInWords(int h, int m) {
    // Write your code here
    Map<Integer,String> map=new HashMap<>();
    map.put(1,"one");
    map.put(2,"two");
    map.put(3,"three");
    map.put(4,"four");
    map.put(5,"five");
    map.put(6,"six");
    map.put(7,"seven");
    map.put(8,"eight");
    map.put(9,"nine");
    map.put(10,"ten");
    map.put(11,"eleven");
    map.put(12,"twelve");
    map.put(13,"thirteen");
    map.put(14,"fourteen");
    map.put(15,"quarter");
    map.put(16,"sixteen");
    map.put(17,"seventeen");
    map.put(18,"eighteen");
    map.put(19,"nineteen");
    map.put(20,"twenty");
    map.put(30,"half");
   
    if(m==0)return String.format("%s o' clock",map.get(h));
    if(1<=m && m<=30){
        int tens=m-(m%10);
        int ones=m%10;
        if(m>20 && m!=tens && m!=15 && m!=30) return String.format("%s %s minutes past %s",map.get(tens),map.get(ones),map.get(h));
        else if(m==15 || m==30) return String.format("%s past %s",map.get(m),map.get(h));
        else return String.format("%s minute past %s",map.get(m),map.get(h));
    }else{
        m=60-m;
        ++h;
        int tens=m-(m%10);
        int ones=m%10;
        if(m>20 && m!=tens && m!=15) return String.format("%s %s minutes to %s",map.get(tens),map.get(ones),map.get(h));
        else if(m==15) return String.format("%s to %s",map.get(m),map.get(h));
        else return String.format("%s minutes to %s",map.get(m),map.get(h));
    }

    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int h = Integer.parseInt(bufferedReader.readLine().trim());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.timeInWords(h, m);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
