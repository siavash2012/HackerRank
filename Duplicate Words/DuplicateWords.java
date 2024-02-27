import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;
import java.io.*;

public class DuplicateWords{
    public static String removeConsecutiveDuplicates(String str){
        var array=str.split(" ");
        var result=new StringBuilder(array[0].concat(" "));
        var index=new AtomicInteger();
        return result.append(Arrays.stream(array).skip(1).filter(a->! a.toLowerCase().equals(array[index.getAndIncrement()].toLowerCase())).collect(Collectors.joining(" "))).toString();
    }
    
    public static void main(String...args) throws IOException{
        var reader=new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        while(reader.ready()){
            System.out.println(removeConsecutiveDuplicates(reader.readLine().strip()));
        }
        reader.close();
    }
}
