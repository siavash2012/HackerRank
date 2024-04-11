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
     * Complete the 'dayOfProgrammer' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts INTEGER year as parameter.
     */

    public static boolean isLeap(int year, boolean gregorian) {
        if (gregorian) {
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (year % 4 == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    /* The function below converts day of the year to date in gregorian and Juilan calender systems, including the year 1918 when the calender was transitioned in Russia */

    public static String dayOfProgrammer(int year, int day) {

        List < Integer > months = Arrays.asList(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        int index = 0;
        boolean transitionMonth = false;
        if (year == 1918 && 32 <= day && day <= 46) {
            transitionMonth = true;
        }
        boolean gregorian = year <= 1917 ? false : true;
        boolean isLeap = isLeap(year, gregorian);
        if (year == 1918) {
            months.set(1, months.get(1) - 13);
        } else if (isLeap) {
            months.set(1, months.get(1) + 1);
        }
        while (day > months.get(index)) {
            day -= months.get(index);
            ++index;
        }

        if (transitionMonth) {
            day += 13;
        }

        ++index;
        String month = index < 10 ? "0" + String.valueOf(index) : String.valueOf(index);
        return day + "." + month + "." + year;

    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int year = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.dayOfProgrammer(year, 256);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
