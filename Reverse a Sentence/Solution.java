import java.util.stream.*;
import java.util.*;

/*Code implemented by Siavash Khalaj (contactsiavash@gmail.com)*/
/*This code reverses the words in a sentence.*/
public class Solution{
    public String reverseString(String input){
        var array=input.split(" ");
        array[0]=array[0].substring(0,1).toLowerCase().concat(array[0].substring(1));
        array[array.length-1]=array[array.length-1].substring(0,1).toUpperCase().concat(array[array.length-1].substring(1));
        StringBuilder result=new StringBuilder();
        Arrays.stream(array).map(a->a.concat(" ")).forEach(a->{
            result.insert(0,a);
        });
        return result.toString().strip();
    }
    public static void main(String...args){
        var test=new A().reverseString("One two three");
        System.out.println(test);
    }
}
