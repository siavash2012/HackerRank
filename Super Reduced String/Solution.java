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
     * Complete the 'superReducedString' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */
     
    static List<String> reduce(List<String>s){
        List<String>result=new ArrayList<>();
        for(int i=0;i<s.size();++i){
            int count=0;
            String st=s.get(i);
            while(s.get(i).equals(st)){
                ++count;
                ++i;
                if(i>s.size()-1)break;
            }
            if(count%2!=0)result.add(st);
            --i;
        }
        return result;
    }

    public static String superReducedString(String s) {
    // Write your code here
    var list=Arrays.stream(s.split("")).collect(Collectors.toList());
    List<String> reduced=new ArrayList<String>();
    while(true){
        reduced=reduce(list);
        if(reduced.equals(list))break;
        list=reduced;
    }
    return reduced.isEmpty()?"Empty String":reduced.stream().collect(Collectors.joining(""));
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.superReducedString(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
