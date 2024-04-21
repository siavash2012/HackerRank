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
     /* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */

    public static String superReducedString(String s) {
    // Write your code here
    var list=Arrays.stream(s.split("")).collect(Collectors.toCollection(LinkedList::new));
    var result=new LinkedList<String>();
    while(! list.isEmpty()){
        var poll=list.pollFirst();
        if(result.size()>0&& poll.equals(result.peekLast()))result.pollLast();
        else result.addLast(poll);
        if(list.size()>0 && list.peekFirst().equals(result.peekLast())){
            list.pollFirst();
            result.pollLast();
        }else if(list.size()>0){
            result.addLast(list.pollFirst());
        }
    }
    return result.isEmpty()?"Empty String":result.stream().collect(Collectors.joining(""));
    }

}

public class Solution2 {
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
