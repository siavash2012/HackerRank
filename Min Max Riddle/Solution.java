import java.io.*;
import java.util.*;

/* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */


public class Solution {

    static long[] riddle(long[] arr) {
        // complete this function
        Map<Long,Long> minMap=new TreeMap<>();
        LinkedList<Long>left=new LinkedList<>();
        LinkedList<Long>right=new LinkedList<>();
        long min=Long.MAX_VALUE;
        for(int i=0;i<arr.length;++i){
            long current=arr[i];
            if(current<min)min=current;
            int l=i-1;
            int r=i+1;
            while(l>=0 && arr[l]>=current){
                left.addLast(arr[l--]);
            }
            while(r<arr.length && arr[r]>=current){
                right.addLast(arr[r++]);
            }
            long count=right.size()+left.size()+1;
            for(long c=1;c<=count;++c){
                minMap.computeIfPresent(c,(k,v)->{
                    return current>v?current:v;
                });
                minMap.putIfAbsent(c,current);
            }
            right.clear();
            left.clear();
        }
        minMap.putIfAbsent((long)arr.length,min);
        return minMap.values().stream().mapToLong(a->a).toArray();
    }
  

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long[] arr = new long[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long arrItem = Long.parseLong(arrItems[i]);
            arr[i] = arrItem;
        }

        long[] res = riddle(arr);

        for (int i = 0; i < res.length; i++) {
            bufferedWriter.write(String.valueOf(res[i]));

            if (i != res.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
