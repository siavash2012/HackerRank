import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */

class Result {

    /*
     * Complete the 'triplets' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER_ARRAY b
     *  3. INTEGER_ARRAY c
     */

    public static long triplets(List<Integer> a, List<Integer> b, List<Integer> c) {
        long count=0;
        a=new ArrayList<>(new HashSet<>(a));
        b=new ArrayList<>(new HashSet<>(b));
        c=new ArrayList<>(new HashSet<>(c));
        Collections.sort(a);
        Collections.sort(c);

        for(int q:b){
            long smallerInA=Collections.binarySearch(a,q);
            if(smallerInA<0){
                smallerInA=-1*(smallerInA+1);
            }else{
                ++smallerInA;
            }
            
            long smallerInC=Collections.binarySearch(c,q);
            if(smallerInC<0){
                smallerInC=-1*(smallerInC+1);  
            }else{
                ++smallerInC;
            }
            
            count=count+(smallerInA*smallerInC);
            
        }
        
        return count;

    }


}

public class Solution{
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int lena = Integer.parseInt(firstMultipleInput[0]);

        int lenb = Integer.parseInt(firstMultipleInput[1]);

        int lenc = Integer.parseInt(firstMultipleInput[2]);

        List<Integer> arra = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> arrb = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> arrc = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        long ans = Result.triplets(arra, arrb, arrc);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
