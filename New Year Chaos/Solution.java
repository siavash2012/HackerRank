import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

/* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */

class Result {

    /*
     * Complete the 'minimumBribes' function below.
     *
     * The function accepts INTEGER_ARRAY q as parameter.
     */

    public static void minimumBribes(List<Integer> q){
        AtomicInteger index=new AtomicInteger(1);
        int numBribes=0;
        Map<Integer,Integer> indexElementMap=new HashMap<>();
        q.forEach(element->indexElementMap.put(index.getAndIncrement(),element));
        for(int i=q.size();i>0;--i){
            if(indexElementMap.get(i)-i>2){
                System.out.println("Too chaotic");
                return;
            }else if(indexElementMap.getOrDefault(i-2,0)==i) {
                indexElementMap.put(i-2,indexElementMap.get(i-1));
                indexElementMap.put(i-1,indexElementMap.get(i));
                numBribes +=2;
            }else if(indexElementMap.getOrDefault(i-1,0)==i){
                indexElementMap.put(i-1,indexElementMap.get(i));
                ++numBribes;
            }
        }
        System.out.println(numBribes);
    }


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> q = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                Result.minimumBribes(q);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
