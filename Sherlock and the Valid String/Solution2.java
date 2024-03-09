import java.io.*;
import java.util.*;
import java.util.stream.*;

/* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */

class Result {

    /*
     * Complete the 'isValid' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

     public static String isValid(String s) {
        // Write your code here
        Map<String,Integer> stringCountMap=new HashMap<>();
        Map<Integer,List<String>> countStringMap;
        Arrays.stream(s.split("")).forEach(
                str-> {
                    stringCountMap.put(str, stringCountMap.getOrDefault(str, 0) + 1);

                });

        countStringMap=stringCountMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, e->new ArrayList<>(Arrays.asList(e.getKey())),(v1, v2)->{v1.addAll(v2);return v1;}));
        if(countStringMap.size()==1) return "YES";
        if(countStringMap.size()>2) return "NO";
        TreeMap<Integer,List<String>> treeMap=new TreeMap<>(countStringMap);
        Map.Entry<Integer,List<String>> first=treeMap.firstEntry();
        Map.Entry<Integer,List<String>> second=treeMap.lastEntry();
        if(first.getKey()==1 && first.getValue().size()==1)return "YES";
        if(second.getKey()-first.getKey() >1)return "NO";
        if(first.getValue().size()==1 || second.getValue().size()==1)return "YES";
        return "NO";
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        String result = Result.isValid(s);
        System.out.println(result);
        bufferedReader.close();
    }
}
