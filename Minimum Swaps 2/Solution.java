import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.*;


/* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */

public class Solution {
    
    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        int num=0;
        AtomicInteger index=new AtomicInteger();
        Map<Integer,Integer> elementIndexMap=new HashMap<>();
        Arrays.stream(arr).forEach(e-> {
            elementIndexMap.put(e, index.getAndIncrement());
        });
        Arrays.sort(arr);
        boolean moreSwaps=true;
        while (moreSwaps) {
            moreSwaps=false;
            for(int i=0;i<arr.length;++i){
                int idx=elementIndexMap.get(arr[i]);
                if(idx !=i){
                    int temp=arr[i];
                    arr[i]=arr[idx];
                    arr[idx]=temp;
                    moreSwaps=true;
                    ++num;
                }
            }
        }
        return num;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        int[] arr=Arrays.stream(reader.readLine().split(" ")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        int num=minimumSwaps(arr);
        System.out.println(num);
    }
}
