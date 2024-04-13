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
     * Complete the 'dayOfProgrammer' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts INTEGER year as parameter.
     */
     
    

    public static String dayOfProgrammer(int year,int day) {
        var calendar=new GregorianCalendar();
        //calendar.setGregorianChange(new Date(1918-1900,0,31));
        calendar.setGregorianChange(new GregorianCalendar(1918, Calendar.JANUARY, 31).getTime());
        calendar.set(calendar.YEAR,year);
        calendar.set(calendar.DAY_OF_YEAR,day);
        return String.format("%d.%2d.%d",calendar.get(calendar.DAY_OF_MONTH),calendar.get(calendar.MONTH)+1,calendar.get(calendar.YEAR)).replaceAll(" ","0");
    }
}

public class Solution2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int year = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.dayOfProgrammer(year,256);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
 
