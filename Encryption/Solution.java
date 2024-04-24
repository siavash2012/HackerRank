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
     * Complete the 'encryption' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String encryption(String s) {
    // Write your code here
    String[] arr=s.replaceAll(" ","").split("");
    int columns=(int) Math.ceil(Math.sqrt(arr.length));
    StringBuilder sb=new StringBuilder();
    int start=0;
    int counter=0;
    int numLetters=0;
    while(true){
        int nextRow=start+counter*columns;
        if(nextRow<arr.length){
            sb.append(arr[nextRow]);
            ++numLetters;
            ++counter;
            if(numLetters==arr.length)break;
        }else{
            sb.append(" ");
            counter=0;
            ++start;
        }  
    }
    return sb.toString();
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.encryption(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
