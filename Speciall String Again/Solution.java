import java.io.*;
import java.util.*;

/* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */

public class Solution{

    public static int computeCombinations(int n){
        int count=0;
        for(int i=1;i<=n;++i){
            count+=(n-i)+1;
        }
        return count;
    }

    static long substrCount(int n, String s) {
        String[] str=s.split("");
        long count=0;
        Set<Integer> visited=new HashSet<>();
        LinkedList<String> same=new LinkedList<>();
        LinkedList<String> different=new LinkedList<>();

        for(int i=0;i<str.length;++i){
            same.addLast(str[i]);
            int j=i+1;
            while(true){
                if(j<str.length && str[j].equals(same.peekLast())){
                    same.push(str[j++]);
                }else{
                    break;
                }
            }
            if(visited.add(j)){
                count+=computeCombinations(same.size());
            }
            if(j<str.length) different.push(str[j++]);
            while(true){
                if(j<str.length && str[j++].equals(same.peekLast())){
                    same.pollLast();
                    if(same.isEmpty()){
                        ++count;
                        break;
                    }
                }else{
                    break;
                }
            }
            same.clear();
            different.clear();
        }
        return count;
    }



    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        String s = scanner.nextLine();
        long result = substrCount(n,s);
        System.out.println(String.valueOf(result));
        scanner.close();
    }
}
