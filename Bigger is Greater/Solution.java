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
     * Complete the 'biggerIsGreater' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING w as parameter.
     */

    public static String biggerIsGreater(String w) {
    // Write your code here
    var map=new HashMap<Integer,List<String>>();
    var list=Arrays.stream(w.split("")).collect(Collectors.toList());
    for(int i=list.size()-2;i>=0;--i){
        String c=list.get(i);
        for(int j=i+1;j<list.size();++j){
            if(list.get(j).compareTo(c)>0){
                map.merge(i,new ArrayList<>(List.of(list.get(j))),(a,b)->{
                    a.addAll(b);
                    return a;
                });
            } 
        }
        if(map.size()>0)break;
    }
    StringBuilder sb=new StringBuilder();
    for(int i=list.size()-2;i>=0;--i){
        if(map.get(i)!=null){
            String head=w.substring(0,i);
            sb.append(head);
            String min=Collections.min(map.get(i));
            sb.append(min);
            var headList=Arrays.stream(head.split("")).collect(Collectors.toList());
            for(String s:headList){
                list.remove(s);
            }
            list.remove(min);
            Collections.sort(list);
            for(String s:list){
                sb.append(s);
            }
            return sb.toString();
            
        }
    }
    return "no answer";
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, T).forEach(TItr -> {
            try {
                String w = bufferedReader.readLine();

                String result = Result.biggerIsGreater(w);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
