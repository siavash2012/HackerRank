import java.io.*;
import java.util.*;

/* Code implemented by Siavash Khalaj */

class Result {

    /*
     * Complete the 'isBalanced' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isBalanced(String s) {
    // Write your code here
    LinkedList<Character>list=new LinkedList<>();
    for(int i=0;i<s.length();++i){
        Character c=s.charAt(i);
        if(c.equals('{')||c.equals('[')||c.equals('(')){
            list.addLast(c);
        }else if(c.equals(')')){
            if(list.isEmpty()){
                return "NO";
            }else if(! list.pollLast().equals('(')){
                return "NO";
            }
        }else if(c.equals(']')){
            if(list.isEmpty()){
                return "NO";
            }else if(! list.pollLast().equals('[')){
                return "NO";
            }
        }else if(c.equals('}')){
            if(list.isEmpty()){
                return "NO";
            }else if(! list.pollLast().equals('{')){
                return "NO";
            }
        }
    }
    return list.isEmpty()?"YES":"NO";

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            String s = bufferedReader.readLine();

            String result = Result.isBalanced(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
