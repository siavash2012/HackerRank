import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

/* Code implemented by Siavash Khalj (contactsiavash@gmail.com) */

class Result {

    /*
     * Complete the 'jumpingOnClouds' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY c as parameter.
     */

    public static int jumpingOnClouds(List<Integer> c) {
        AtomicInteger jumps=new AtomicInteger(Integer.MAX_VALUE);
        List<List<List<Integer>>> levelList=new ArrayList<>();
        levelList.add(new ArrayList<>());
        levelList.get(0).add(Arrays.asList(0));
        int index=1;
        boolean notReachedEnd=true;
        while(notReachedEnd){
            notReachedEnd=false;
            levelList.add(new ArrayList<>());
            for(int i=0;i<levelList.get(index-1).size();++i){
                int size=levelList.get(index-1).get(i).size();
                int lastZero=levelList.get(index-1).get(i).get(size-1);
                if(lastZero+1<c.size() && c.get(lastZero+1)==0){
                    levelList.get(index).add(new ArrayList<>());
                    levelList.get(index).get(levelList.get(index).size()-1).addAll(levelList.get(index-1).get(i));
                    levelList.get(index).get(levelList.get(index).size()-1).add(lastZero+1);
                    notReachedEnd=true;
                }
                if(lastZero+2<c.size() && c.get(lastZero+2)==0){
                    levelList.get(index).add(new ArrayList<>());
                    levelList.get(index).get(levelList.get(index).size()-1).addAll(levelList.get(index-1).get(i));
                    levelList.get(index).get(levelList.get(index).size()-1).add(lastZero+2);
                    notReachedEnd=true;
                }
            }
            ++index;
        }
        
        levelList.stream().flatMap(List::stream).filter(l->l.contains(c.size()-1)).forEach(
            l->{
                if(l.size()<jumps.get()){
                    jumps.set(l.size());
                }
            });
        return jumps.get()-1;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> c = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.jumpingOnClouds(c);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
