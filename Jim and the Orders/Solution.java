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
     * Complete the 'jimOrders' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts 2D_INTEGER_ARRAY orders as parameter.
     */

    public static List<Integer> jimOrders(List<List<Integer>> orders) {
    // Write your code here
    List<List<Integer>>ordersClone=new ArrayList<>(orders);
    for(int i=0;i<ordersClone.size();++i){
        ordersClone.get(i).add(i+1);
    }
    Comparator<List<Integer>> comp=new Comparator<>(){
        public int compare(List<Integer> a, List<Integer>b){
            int sumA=a.get(0)+a.get(1);
            int sumB=b.get(0)+b.get(1);
            if(sumA!=sumB){
                return sumA-sumB;
            }else{
                return a.get(2)-b.get(2);
            }
        }
    };
    Collections.sort(ordersClone,comp);
    return ordersClone.stream().map(a->a.get(2)).collect(Collectors.toList());

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> orders = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                orders.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = Result.jimOrders(orders);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining(" "))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
