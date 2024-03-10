import java.io.*;
import java.util.concurrent.atomic.*;

*/ Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */

class Result {

    /*
     * Complete the 'countingValleys' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER steps
     *  2. STRING path
     */

    public static int countingValleys(int steps, String path) {
    // Write your code here
    AtomicInteger seaLevel=new AtomicInteger();
    AtomicInteger valleys=new AtomicInteger();
    path.chars().map(e->(char)e).forEach(
        e->{
            if (e=='D'){
                seaLevel.getAndDecrement();
            }
            else if(e=='U'){
                if(seaLevel.get()==-1) valleys.getAndIncrement();
                seaLevel.getAndIncrement();
            }
        }
    );
    return valleys.get();
    }
}

public class Solution2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int steps = Integer.parseInt(bufferedReader.readLine().trim());

        String path = bufferedReader.readLine();

        int result = Result.countingValleys(steps, path);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
