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
     * Complete the 'gameOfThrones' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String gameOfThrones(String s) {
    // Write your code here
    var map=Arrays.stream(s.split("")).collect(Collectors.groupingBy(a->a,Collectors.counting()));
    int oddCounts=0,evenCounts=0;
    for(var entry:map.entrySet()){
        if(entry.getValue()%2==0)++evenCounts;
        else ++oddCounts;
    }
    if(s.length()%2==0 && oddCounts==0)return "YES";
    if(s.length()%2!=0 && oddCounts==1)return "YES";
    return "NO";
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.gameOfThrones(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
