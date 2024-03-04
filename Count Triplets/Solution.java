import java.io.*;
import java.util.*;
import java.util.stream.*;


public class Solution {



    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        Map<Long,Long> elementCountMap=new HashMap<>();
        Map<List<Long>,Long> pairsCountMap=new HashMap<>();
        Map<List<Long>,Long> tripletsCountMap=new HashMap<>();
        for(long element:arr){
            long middle=element%r==0?element/r:-1;
            long first= element%(r*r)==0?element/(r*r):-1;
            List<Long> pairsList=Arrays.asList(middle,element);
            List<Long>tripletList=Arrays.asList(first,middle,element);


            if (pairsCountMap.containsKey(Arrays.asList(first,middle))) {
                tripletsCountMap.computeIfPresent(tripletList,(k,v)->{
                    v+=pairsCountMap.get(Arrays.asList(first,middle));
                    return v;
                });
                tripletsCountMap.computeIfAbsent(tripletList, k->pairsCountMap.get(Arrays.asList(first,middle)));
            }

            if (elementCountMap.containsKey(middle)) {
                pairsCountMap.computeIfPresent(pairsList,(k,v)->{
                    v+=elementCountMap.get(middle);
                    return v;
                });
                pairsCountMap.computeIfAbsent(pairsList, k->elementCountMap.get(middle));
            }

            elementCountMap.computeIfPresent(element,(k,v)->++v);
            elementCountMap.computeIfAbsent(element,k->1l);

        }

        long count= tripletsCountMap.values().stream().mapToLong(l->l).sum();
        return count;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        long r=Long.parseLong(reader.readLine().split(" ")[1].trim());
        List<Long> array=Arrays.stream(reader.readLine().split(" ")).map(String::trim).map(Long::valueOf).collect(Collectors.toList());
        long result=countTriplets(array,r);
        System.out.println(result);
    }
}
