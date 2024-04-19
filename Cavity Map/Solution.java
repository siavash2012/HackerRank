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
     * Complete the 'cavityMap' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING_ARRAY grid as parameter.
     */
     static boolean isEdge(int row,int column,int numRows,int numColumns){
         if(row==0 || row==numRows-1 || column==0 || column==numColumns-1){
             return true;
         }else{
             return false;
         }
     }

    public static List<String> cavityMap(List<String> grid) {
    // Write your code here
    List<String> result=new ArrayList<>();
    int numRows=grid.size();
    int numColumns=grid.get(0).length();
    for(int i=0;i<numRows;++i){
        StringBuilder sb=new StringBuilder(grid.get(i));
        for(int j=0;j<numColumns;++j){
            if(isEdge(i,j,numRows,numColumns)){
                continue;
            }
            int topRow=i-1;
            int bottomRow=i+1;
            int leftColumn=j-1;
            int rightColumn=j+1;
            int currentValue=(int)(grid.get(i).charAt(j));
            int topRowValue=(int)(grid.get(topRow).charAt(j));
            int bottomRowValue=(int)(grid.get(bottomRow).charAt(j));
            int leftColumnValue=(int)(grid.get(i).charAt(leftColumn));
            int rightColumnValue=(int)(grid.get(i).charAt(rightColumn));
            if(currentValue>topRowValue && currentValue>bottomRowValue && currentValue>leftColumnValue && currentValue>rightColumnValue){
                sb.setCharAt(j,'X');
            } 
        }
         result.add(sb.toString());
    }
    return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> grid = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<String> result = Result.cavityMap(grid);

        bufferedWriter.write(
            result.stream()
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
