import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

/* code implemented by Siavash Khalaj */

class Result {

    /*
     * Complete the 'luckBalance' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. 2D_INTEGER_ARRAY contests
     */

    public static int luckBalance(int k, List<List<Integer>> contests) {
        // Write your code here
        int balance=0;
        int count=0;
        Comparator<List<Integer>> comp1= Comparator.comparing(a -> a.get(1));
        Comparator<List<Integer>> comp2= Comparator.<List<Integer>,Integer>comparing(a -> a.get(0)).reversed();
        PriorityQueue<List<Integer>> pq=new PriorityQueue<>(comp1.thenComparing(comp2));
        contests.forEach(pq::offer);
        while(!pq.isEmpty()){
            List<Integer>polled=pq.poll();
            if(polled.get(1).equals(0)){
                balance+=polled.get(0);
            }else if(polled.get(1).equals(1)){
                ++count;
                if(count<=k) {
                    balance += polled.get(0);
                }else{
                    balance-=polled.get(0);
                }
            }
        };
        return balance;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> contests = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                contests.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.luckBalance(k, contests);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
