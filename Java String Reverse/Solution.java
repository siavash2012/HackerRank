import java.io.*;
import java.util.*;

/*Code implemented by Siavash Khalaj (contactsiavash@gmail.com)*/
/*This code checks if a String is a palindrome*/

public class Solution {
    
    boolean isPalindrome(String str){
        var left=0;
        var right=str.length()-1;
        str=str.toLowerCase();
        while(right>left){
            if(!(str.charAt(left++)==str.charAt(right--))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        var str=sc.nextLine();
        System.out.println(new Solution().isPalindrome(str)?"Yes":"No");
        
    }
}
