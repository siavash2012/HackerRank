import java.io.*;
import java.util.*;
import java.util.stream.*;

/* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */

class Result {

    /*
     * Complete the 'makeAnagram' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING a
     *  2. STRING b
     */
     

    public static int makeAnagram(String a, String b) {
        int count=0;
        List<String>aList=Arrays.stream(a.split("")).collect(Collectors.toList());
        List<String> bList=Arrays.stream(b.split("")).collect(Collectors.toList());
        Collections.sort(aList);
        Collections.sort(bList);
         for(int i=0;i<aList.size() && i<bList.size();++i){
             if(aList.get(i).compareTo(bList.get(i))<0){
                 aList.remove(i);
                 ++count;
                 --i;
             }else if(aList.get(i).compareTo(bList.get(i))>0){
                 bList.remove(i);
                 ++count;
                 --i;
             }
         }
         count+=Math.abs(aList.size()-bList.size());
         return count;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String a = bufferedReader.readLine();

        String b = bufferedReader.readLine();

        int res = Result.makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
