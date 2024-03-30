import java.io.*;
import java.util.*;

public class Solution {

    // Complete the minTime function below.
    static long minTime(long[] machines, long goal) {
        Arrays.sort(machines);
        long min= (goal>machines.length?goal/machines.length:1)*machines[0];
        long max= (goal>machines.length?goal/machines.length:1)*machines[machines.length-1];
        long mid=(min+max)/2;
        
        while(min<max){
            long fmid=mid;
            long total=Arrays.stream(machines).map(a->fmid/a).sum();
            if(total>=goal) {
                max=mid;
            }
            else if(total<goal){
                min=mid+1;
            }
            mid=(max+min)/2;
        }
        
        return min;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nGoal = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nGoal[0]);

        long goal = Long.parseLong(nGoal[1]);

        long[] machines = new long[n];

        String[] machinesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long machinesItem = Long.parseLong(machinesItems[i]);
            machines[i] = machinesItem;
        }

        long ans = minTime(machines, goal);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
