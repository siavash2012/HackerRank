import java.io.*;
import java.util.*;
import java.util.stream.*;

/* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */

class Result {

    /*
     * Complete the 'reverseShuffleMerge' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String reverseShuffleMerge(String s) {
        List < String > sList = Arrays.stream(s.split("")).collect(Collectors.toList());
        Map < String, Long > remainingCounts = Arrays.stream(s.split("")).collect(Collectors.groupingBy(a -> a, Collectors.counting()));
        Map < String, Long > requiredCounts = Arrays.stream(s.split("")).collect(Collectors.groupingBy(a -> a, Collectors.collectingAndThen(Collectors.counting(), a -> a / 2)));
        Map < String, Long > currentCounts = new HashMap <> ();
        LinkedList < String > result = new LinkedList <> ();
        for (int i = sList.size() - 1; i >= 0; --i) {
            String current = sList.get(i);
            remainingCounts.computeIfPresent(current, (k, v) -> --v);
            if (result.isEmpty()) {
                result.addLast(current);
                currentCounts.computeIfPresent(current, (k, v) -> ++v);
                currentCounts.putIfAbsent(current, 1L);

            } else if (result.peekLast().compareTo(current) <= 0 && currentCounts.getOrDefault(current, 0L) < requiredCounts.get(current)) {
                result.addLast(current);
                currentCounts.computeIfPresent(current, (k, v) -> ++v);
                currentCounts.putIfAbsent(current, 1L);
            } else if (result.peekLast().compareTo(current) > 0 && currentCounts.getOrDefault(current, 0L) < requiredCounts.get(current)) {
                String previous = result.peekLast();
                while (result.size() > 0 && previous.compareTo(current) > 0 && remainingCounts.get(previous) >= requiredCounts.get(previous) - currentCounts.get(previous) + 1) {
                    currentCounts.computeIfPresent(previous, (k, v) -> --v);
                    result.pollLast();
                    previous = result.peekLast();
                }
                result.addLast(current);
                currentCounts.computeIfPresent(current, (k, v) -> ++v);
                currentCounts.putIfAbsent(current, 1L);
            }
        }
        return result.stream().collect(Collectors.joining(""));
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = bufferedReader.readLine();

        String result = Result.reverseShuffleMerge(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
