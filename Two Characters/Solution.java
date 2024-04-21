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
     * Complete the 'alternate' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */
     
    static boolean isAlternate(List<String> s){
       Set<String> set=new HashSet<>(s);
       if(set.size()!=2)return false;
       List<String> setList=new ArrayList<>(set);
       String first=s.get(0);
       setList.remove(first);
       String second=setList.get(0);
       for(int i=0;i<s.size();i+=2)if(! s.get(i).equals(first))return false;
       for(int i=1;i<s.size();i+=2)if(! s.get(i).equals(second))return false;
       return true;   
    }

    public static int alternate(String s) {
    // Write your code here
    int max=Integer.MIN_VALUE;
    Set<String> set=Arrays.stream(s.split("")).collect(Collectors.toSet());
    List<String>sList=Arrays.stream(s.split("")).collect(Collectors.toList());
    List<String>setList=new ArrayList<>(set);
    for(int i=0;i<setList.size()-1;++i){
        for(int j=i+1;j<setList.size();++j){
            List<String>tempSList=new ArrayList<>(sList);
            List<String>tempSetList=new ArrayList<>(setList);
            tempSetList.remove(setList.get(i));
            tempSetList.remove(setList.get(j));
            tempSList.removeAll(tempSetList);
            if(isAlternate(tempSList)){
                max=Math.max(max,tempSList.size());
            }
        }
    }
    return max==Integer.MIN_VALUE?0:max;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int l = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int result = Result.alternate(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
