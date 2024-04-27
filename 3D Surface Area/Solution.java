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
     * Complete the 'surfaceArea' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY A as parameter.
     */

    public static int surfaceArea(List<List<Integer>> A) {
    // Write your code here
    int rows=A.size();
    int columns=A.get(0).size();
    int area=0;
    area+=(2*rows*columns);
    for(int height:A.get(rows-1)){
        area+=height;
    }
    for(int height:A.get(0)){
        area+=height;
    }
    for(int i=0;i<rows;++i){
        area+=(A.get(i).get(0));
        area+=(A.get(i).get(columns-1));
    }
    for(int i=0;i<rows-1;++i){
        for(int j=0;j<columns;++j){
            int height=Math.abs(A.get(i).get(j)-A.get(i+1).get(j));
            area+=height;
        }
    }
    for(int i=0;i<rows;++i){
        for(int j=0;j<columns-1;++j){
            int height=Math.abs(A.get(i).get(j)-A.get(i).get(j+1));
            area+=height;
        }
    }
    return area;
    

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int H = Integer.parseInt(firstMultipleInput[0]);

        int W = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> A = new ArrayList<>();

        IntStream.range(0, H).forEach(i -> {
            try {
                A.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.surfaceArea(A);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
