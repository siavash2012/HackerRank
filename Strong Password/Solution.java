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
     * Complete the 'minimumNumber' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING password
     */

    public static int minimumNumber(int n, String password) {
    // Return the minimum number of characters to make the password strong
    int required=0;
    List<String> numbers = Arrays.stream("0123456789".split("")).collect(Collectors.toList());
    List<String> lower_case = Arrays.stream("abcdefghijklmnopqrstuvwxyz".split("")).collect(Collectors.toList());
    List<String> upper_case = Arrays.stream("ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("")).collect(Collectors.toList());
    List<String> special_characters = Arrays.stream("!@#$%^&*()-+".split("")).collect(Collectors.toList());
    List<String>passwordList=Arrays.stream(password.split("")).collect(Collectors.toList());
    numbers.removeAll(passwordList);
    lower_case.removeAll(passwordList);
    upper_case.removeAll(passwordList);
    special_characters.removeAll(passwordList);
    if(numbers.size()==10)++required;
    if(lower_case.size()==26)++required;
    if(upper_case.size()==26)++required;
    if(special_characters.size()==12)++required;
    int size=passwordList.size()+required;
    if(size<6)required+=(6-size);
    return required;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String password = bufferedReader.readLine();

        int answer = Result.minimumNumber(n, password);

        bufferedWriter.write(String.valueOf(answer));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
