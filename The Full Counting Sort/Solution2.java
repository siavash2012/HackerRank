
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
     * Complete the 'countSort' function below.
     *
     * The function accepts 2D_STRING_ARRAY arr as parameter.
     */

    public static void countSort(List<List<String>> arr) {
    // Write your code here
    var intValueElementMap=new TreeMap<Integer,List<String>>();
    for(int i=0;i<arr.size();++i){
        var list=arr.get(i);
        var index=Integer.parseInt(list.get(0));
        if(i<arr.size()/2){
            intValueElementMap.merge(index,new ArrayList<>(List.of("- ")),(a,b)->{
            a.addAll(b);
            return a;
            });
        }else{
            intValueElementMap.merge(index,new ArrayList<>(List.of(list.get(1)+" ")),(a,b)->{
            a.addAll(b);
            return a;
            });
        }
    }
    
    intValueElementMap.entrySet().stream().forEach(a->System.out.print(a.getValue().stream().collect(Collectors.joining(""))));
  }

}

public class Solution2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> arr = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                arr.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Result.countSort(arr);

        bufferedReader.close();
    }
}
