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

class Result {

    /*
     * Complete the 'caesarCipher' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER k
     */

    public static String caesarCipher(String s, int k) {
    // Write your code here
    StringBuilder cipher=new StringBuilder();
    var list=Arrays.stream(s.split("")).collect(Collectors.toList());
    for(var str:list){
        char c=str.charAt(0);
        if(Character.isUpperCase(c)){
            int rotated=c+(k%26);
            if(rotated>90)rotated-=26;
            cipher.append((char)rotated);
        }else if(Character.isLowerCase(c)){
            int rotated=c+(k%26);
            if(rotated>122)rotated-=26;
            cipher.append((char)rotated);
        }else{
            cipher.append(c);
        }
    }
    return cipher.toString();

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.caesarCipher(s, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
