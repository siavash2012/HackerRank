import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */

class Result {

    /*
     * Complete the 'sherlockAndAnagrams' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int sherlockAndAnagrams(String s) {
        // Write your code here

        Map<List<String>,Integer> map=new HashMap<>();
        for(int i=0;i<s.length();++i){
            for(int j=i+1;j<=s.length();++j){
                List<String> sList=Arrays.stream(s.substring(i,j).split("")).collect(Collectors.toList());
                Collections.sort(sList);
                map.computeIfPresent(sList,(k,v)->++v);
                map.putIfAbsent(sList,1);
            }
        }
        return map.values().stream().reduce(0,(a,b)->a+((b*(b-1))/2));

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        while(reader.ready()){
            String str=reader.readLine();
            int answ= Result.sherlockAndAnagrams(str);
            System.out.println(answ);
        }
        reader.close();
    }
}
