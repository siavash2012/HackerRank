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

/* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */

class Result {

    /*
     * Complete the 'makingAnagrams' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING s1
     *  2. STRING s2
     */

    public static int makingAnagrams(String s1, String s2) {
    // Write your code here
    int count=0;
    Map<String,Long> map1=Arrays.stream(s1.split("")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
    Map<String,Long> map2=Arrays.stream(s2.split("")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
    for(Map.Entry<String,Long> e:map1.entrySet()){
        long count1=e.getValue();
        long count2=map2.getOrDefault(e.getKey(),0L);
        count+=Math.abs(count1-count2);
        map2.remove(e.getKey());
    }
    for(Map.Entry<String,Long> e:map2.entrySet()){
        long count1=e.getValue();
        count+=count1;
    }
    return count;
    }

}

public class Solution2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = bufferedReader.readLine();

        String s2 = bufferedReader.readLine();

        int result = Result.makingAnagrams(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
