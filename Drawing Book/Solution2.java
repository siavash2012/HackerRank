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
     * Complete the 'pageCount' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER p
     */

    public static int pageCount(int n, int p) {
    // Write your code here
    int last=n%2==0?n+1:n;
    var pageList=IntStream.rangeClosed(0,last).toArray();
    int countFromBeginning=-1,countFromEnd=-1;
    for(int i=0;i<pageList.length;i+=2){
        if(pageList[i]==p || pageList[i+1]==p){
            ++countFromBeginning;
            break;
        }
        ++countFromBeginning;
        
    }
    for(int i=pageList.length-1;i>=0;i-=2){
        if(pageList[i]==p || pageList[i-1]==p){
            ++countFromEnd;
            break;
        }
        ++countFromEnd;
    }
   
    return Math.min(countFromBeginning,countFromEnd);

    }

}

public class Solution2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int p = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.pageCount(n, p);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
