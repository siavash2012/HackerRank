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
        Map<Integer,List<String>> countStringMap=new HashMap<>();
        Arrays.stream(s.split("")).forEach(
                str->{
                    stringCountMap.put(str,stringCountMap.getOrDefault(str,0)+1);
                    int count=stringCountMap.get(str);
                    countStringMap.computeIfPresent(count,(k,v)->{
                        v.add(str);
                        countStringMap.computeIfPresent(count-1,(kp,vp)->{
                            vp.remove(str);
                            return vp;
                        });
                        return v;
                    });
                    countStringMap.computeIfAbsent(count,k->{
                        ArrayList<String> v=new ArrayList<>(Arrays.asList(str));
                        countStringMap.computeIfPresent(count-1,(kp,vp)->{
                            vp.remove(str);
                            return vp;
                        });
                        return v;
                    });
                }
        );
        Map<Integer,List<String>> newCountStringMap=countStringMap.entrySet().stream().filter(e->! e.getValue().isEmpty()).collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));
        if(newCountStringMap.size()==1) return "YES";
        if(newCountStringMap.size()>2) return "NO";
        TreeMap<Integer,List<String>> treeMap=new TreeMap<>(newCountStringMap);
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
