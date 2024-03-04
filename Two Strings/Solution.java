import java.io.*;
import java.util.*;

/* Code implemented By Siavash Khalaj (contactsiavash@gmail.com) */

class Result {

    /*
     * Complete the 'twoStrings' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING s1
     *  2. STRING s2
     */


    public static String twoStrings(String s1, String s2) {
        List<String> s1List=Arrays.asList(s1.split(""));
        HashSet<String>s1Hash=new HashSet<>(s1List);
        List<String> s2List=Arrays.asList(s2.split(""));
        HashSet<String>s2Hash=new HashSet<>(s2List);

        for(String s:s2Hash){
            if(! s1Hash.add(s)){
                return "YES";
            }
        }
        return "NO";
    }
}



public class Solution2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        while(reader.ready()){
            String s1=reader.readLine().trim();
            String s2=reader.readLine().trim();
            System.out.println(Result.twoStrings(s1,s2));
        }
        reader.close();
    }
}
