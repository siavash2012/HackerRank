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
     * Complete the 'maximumPerimeterTriangle' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY sticks as parameter.
     */
     
    

    public static List<Integer> maximumPerimeterTriangle(List<Integer> sticks) {
    // Write your code here
    Comparator<List<Integer>> comp=new Comparator<>(){
        public int compare(List<Integer> a,List<Integer>b){
            int sumA=a.stream().reduce(0,Integer::sum);
            int sumB=b.stream().reduce(0,Integer::sum);
            int aMax=Collections.max(a);
            int aMin=Collections.min(a);
            int bMax=Collections.max(b);
            int bMin=Collections.min(b);
            if(sumA != sumB){
                return sumA-sumB;
            }else if(aMax !=bMax){
                return aMax-bMax;
            }else{
                return aMin-bMin;
            }
        }
    };
    List<List<Integer>> triangles=new ArrayList<>();
    sticks.sort(Comparator.reverseOrder());
    for(int i=0;i<sticks.size()-2;++i){
        int a=sticks.get(i);
        int b=sticks.get(i+1);
        int c=sticks.get(i+2);
        if((b+c)>a){
            triangles.add(List.of(a,b,c));
        }
    }
    if(triangles.isEmpty()) return List.of(-1);
    List<Integer> max=new ArrayList<>(Collections.max(triangles,comp));
    Collections.sort(max);
    return max;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> sticks = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.maximumPerimeterTriangle(sticks);

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
