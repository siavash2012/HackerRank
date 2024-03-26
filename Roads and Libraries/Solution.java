import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

/* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */


class Result{

    /*
     * Complete the 'roadsAndLibraries' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER c_lib
     *  3. INTEGER c_road
     *  4. 2D_INTEGER_ARRAY cities
     */


    public static long roadsAndLibraries(int n, int c_lib, int c_road, List<List<Integer>> cities) {
        // Write your code here
        long cost=0;
        Map<Integer,List<Integer>> map=new HashMap<>();
        for(List<Integer> list:cities){
            map.computeIfPresent(list.get(0),(k,v)->{
                v.add(list.get(1));
                return v;
            });
            map.putIfAbsent(list.get(0),new ArrayList<>(Arrays.asList(list.get(1))));
            map.computeIfPresent(list.get(1),(k,v)->{
                v.add(list.get(0));
                return v;
            });
            map.putIfAbsent(list.get(1),new ArrayList<>(Arrays.asList(list.get(0))));
        }
        long diff=n-map.size();
        List<Integer>nodes=new ArrayList<>(map.keySet());
        Set<Integer>visited=new HashSet<>();

        while(!(visited.size() ==nodes.size())){
            for(Integer source:nodes){
                if(!visited.contains(source)){
                    long count=1;
                    Queue<Integer>queue=new LinkedList<>();
                    visited.add(source);
                    queue.offer(source);
                    while(! queue.isEmpty() ){
                        Integer current=queue.poll();
                        for(Integer neighbor:map.get(current)){
                            if(!visited.contains(neighbor)){
                                ++count;
                                visited.add(neighbor);
                                queue.add(neighbor);
                            }
                        }
                    }
                    if(c_road<c_lib){
                        cost+=((count-1)*c_road)+c_lib;
                    }
                    else{
                        cost+=count*c_lib;
                    }
                }
            }
        }
        return cost+diff*c_lib;
    }
}

public class Solution{
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                int c_lib = Integer.parseInt(firstMultipleInput[2]);

                int c_road = Integer.parseInt(firstMultipleInput[3]);

                List<List<Integer>> cities = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
                    try {
                        cities.add(
                                Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                        .map(Integer::parseInt)
                                        .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                long result = Result.roadsAndLibraries(n, c_lib, c_road, cities);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
