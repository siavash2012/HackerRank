import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'largestRectangle' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY h as parameter.
     */

    public static long largestRectangle(List<Integer> h) {
        long max=Long.MIN_VALUE;
        LinkedList<Integer>left=new LinkedList<>();
        LinkedList<Integer>right=new LinkedList<>();
        for(int i=0;i<h.size();++i){
            int current=h.get(i);
            int l=i-1;
            int r=i+1;
            while(l>=0 && h.get(l)>=current){
                left.addLast(h.get(l--));
            }
            while(r<h.size() && h.get(r)>=current){
                right.addLast(h.get(r++));
            }
            int count=right.size()+left.size();
            long area=current*(count+1);
            if(area>max)max=area;
            right.clear();
            left.clear();
        }
        return max;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> h = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        long result = Result.largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
