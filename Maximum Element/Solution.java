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
     * Complete the 'getMax' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY operations as parameter.
     */

   static LinkedList<Integer>q=new LinkedList<>();
   static PriorityQueue<Integer>pq=new PriorityQueue<>(Comparator.reverseOrder());

    public static List<Integer> getMax(List<String> operations) {
        
    // Write your code here
    q.clear();
    pq.clear();
    List<Integer>list=new ArrayList<>();
    for(String query:operations){
        if(query.startsWith("1")){
            String[] temp=query.split(" ");
            q.add(Integer.parseInt(temp[1]));
            pq.add(Integer.parseInt(temp[1]));
        }
        if(query.startsWith("2") && !q.isEmpty()){
            pq.remove(q.pollLast());
        }
        else if(query.startsWith("3") && !q.isEmpty()){
           list.add(pq.peek());
        }
    }
    return list;
   

    }

}



public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> ops = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<Integer> res = Result.getMax(ops);

        bufferedWriter.write(
            res.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
