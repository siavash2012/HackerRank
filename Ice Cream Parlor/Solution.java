import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */

class Result {

    /*
     * Complete the 'whatFlavors' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY cost
     *  2. INTEGER money
     */

    public static void whatFlavors(List<Integer> cost, int money) {
    // Write your code here
    List<Integer> result=new ArrayList<>();
    HashMap<Integer,List<Integer>> costIndex=new HashMap<>();
    for(int i=0;i<cost.size();++i){
        final int index=i+1;
        costIndex.computeIfPresent(cost.get(i),(k,v)->{
            v.add(index);
            return v;
        });
        costIndex.putIfAbsent(cost.get(i),new ArrayList<Integer>(List.of(index)));
    }
    
    Set<Map.Entry<Integer,List<Integer>>> set=costIndex.entrySet();
    for(Map.Entry<Integer,List<Integer>> entry:set){
        if(costIndex.containsKey(money-entry.getKey())){
            result.add(entry.getValue().get(0));
            costIndex.get(entry.getKey()).remove(0);
            result.add(costIndex.get(money-entry.getKey()).get(0));
            break;
        }
    }
    Collections.sort(result);
    System.out.println(result.stream().map(String::valueOf).collect(Collectors.joining(" ")));

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int money = Integer.parseInt(bufferedReader.readLine().trim());

                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> cost = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                Result.whatFlavors(cost, money);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
