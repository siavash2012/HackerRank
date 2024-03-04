import java.util.*;
import java.io.*;

/* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */

public class Solution {

    static void freqQuery(List<List<Integer>> queries) {
        Map<Integer,List<Integer>> elementListMap=new HashMap<>();
        Map<Integer,List<Integer>> frequencyElementMap=new HashMap<>();
        List<Integer> elementsWithSpecifiedFrequency=new ArrayList<>();
        queries.stream().forEach(query->{
            switch(query.get(0)){
                case 1:
                    elementListMap.computeIfPresent(query.get(1),(k,v)->{
                        if(frequencyElementMap.containsKey(v.size())){
                        frequencyElementMap.get(v.size()).remove(k);
                        }
                        v.add(query.get(1));
                        frequencyElementMap.computeIfPresent(v.size(),(k1,v1)->{
                            v1.add(query.get(1));
                            return v1;
                        });
                        frequencyElementMap.putIfAbsent(v.size(),new ArrayList<>(Arrays.asList(query.get(1))));
                        return v;
                    });
                    elementListMap.computeIfAbsent(query.get(1),k->{
                        ArrayList<Integer> v= new ArrayList<>(Arrays.asList(query.get(1)));
                        frequencyElementMap.computeIfPresent(v.size(),(k1,v1)->{
                            v1.add(query.get(1));
                            return v1;
                        });
                        frequencyElementMap.putIfAbsent(v.size(),new ArrayList<>(Arrays.asList(query.get(1))));
                        return v;
                    });
                    break;

                case 2:
                    elementListMap.computeIfPresent(query.get(1),(k,v)->{
                        if(frequencyElementMap.containsKey(v.size())){
                            frequencyElementMap.get(v.size()).remove(k);
                        }
                        v.remove(query.get(1));

                        frequencyElementMap.computeIfPresent(v.size(),(k1,v1)->{
                            v1.add(query.get(1));
                            return v1;
                        });
                        frequencyElementMap.putIfAbsent(v.size(),new ArrayList<>(Arrays.asList(query.get(1))));

                        return v;
                    });
                    break;

                case 3:
                    int frequency=query.get(1);
                    List<Integer> list=frequencyElementMap.get(frequency);
                    if(list!=null && list.size()>0){
                        elementsWithSpecifiedFrequency.add(1);
                    }else{
                        elementsWithSpecifiedFrequency.add(0);
                    }

                    break;
            }
        });

        elementsWithSpecifiedFrequency.forEach(System.out::println);
    }



    public static void main(String...args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        List<List<Integer>> queriesList=new ArrayList<>();
        while(reader.ready()){
            int[] array=Arrays.stream(reader.readLine().split(" ")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            queriesList.add(Arrays.asList(array[0],array[1]));
        }
        freqQuery(queriesList);



    }
}
