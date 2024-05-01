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
     * Complete the 'countLuck' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING_ARRAY matrix
     *  2. INTEGER k
     */
    static class Move{
    int x;
    int y;
    Move parent;
    int moveOptions;
    public Move(int x,int y, Move parent){
        this.x=x;
        this.y=y;
        this.parent=parent;
    }
    @Override
    public boolean equals(Object otherO){
        Move other=(Move) otherO;
        return other.x==this.x && other.y==this.y;
    }
    @Override
    public int hashCode(){
        return this.x+this.y;
    }
    public String toString(){
        return this.x+" "+this.y;
    }
}
     
     static int moveOptions(List<String> matrix,Move move){
         int count=0;
         int x=move.x;
         int y=move.y;
         if((x-1>=0) &&  matrix.get(x-1).charAt(y)!='X')++count;
         if((x+1<matrix.size()) &&  matrix.get(x+1).charAt(y)!='X')++count;
         if((y-1>=0) &&  matrix.get(x).charAt(y-1)!='X')++count;
         if((y+1<matrix.get(0).length()) &&  matrix.get(x).charAt(y+1)!='X')++count;
         return count;
     }
     
    static List<Move> possibleMoves(List<String> matrix,Move move){
        int x=move.x;
        int y=move.y;
        List<Move>result=new ArrayList<>();
        if(x-1>=0 &&(matrix.get(x-1).charAt(y)=='.' || (matrix.get(x-1).charAt(y)=='*' ))){
            Move newMove=(new Move(x-1,y,move));
            int moveOptions=moveOptions(matrix,newMove);
            newMove.moveOptions=moveOptions;
            result.add(newMove);
        }
        if(x+1<matrix.size() && (matrix.get(x+1).charAt(y)=='.' || (matrix.get(x+1).charAt(y)=='*'))){
            Move newMove=(new Move(x+1,y,move));
           int moveOptions=moveOptions(matrix,newMove);
            newMove.moveOptions=moveOptions;
            result.add(newMove);
        }
        if(y+1<matrix.get(0).length() &&(matrix.get(x).charAt(y+1)=='.' || (matrix.get(x).charAt(y+1))=='*')){
            Move newMove=(new Move(x,y+1,move));
            int moveOptions=moveOptions(matrix,newMove);
            newMove.moveOptions=moveOptions;
            result.add(newMove);
        }
        if(y-1>=0 && (matrix.get(x).charAt(y-1)=='.' || (matrix.get(x).charAt(y-1)=='*'))){
            Move newMove=(new Move(x,y-1,move));
            int moveOptions=moveOptions(matrix,newMove);
            newMove.moveOptions=moveOptions;
            result.add(newMove);
        }
        return result;
    }
    
    static Move findStart(List<String>matrix){
        for(int i=0;i<matrix.size();++i){
            for(int j=0;j<matrix.get(i).length();++j){
                if(matrix.get(i).charAt(j)=='M'){
                    Move start= new Move(i,j,null);
                    start.moveOptions=moveOptions(matrix,start)+1;
                    return start;
                }
            }
        }
        return new Move(-1,-1,null);
    }
    

    public static String countLuck(List<String> matrix, int k) {
    
    Move start=findStart(matrix);
    Set<Move>set=new HashSet<>();
    
        LinkedList<Move>queue=new LinkedList<>();
        queue.addLast(start);
        while(! queue.isEmpty()){
            Move poll=queue.pollFirst();
            set.add(poll);
            if(matrix.get(poll.x).charAt(poll.y)=='*'){
                List<Integer>list=new ArrayList<>();
                while(true){
                    Move parent=poll.parent;
                    if(parent==null)break;
                    list.add(parent.moveOptions-1);
                    poll=parent;
                }
                long count=list.stream().filter(a->a>1).count();
                return count==k?"Impressed":"Oops!";
                
                
            }
            List<Move> pollPossibleMoves=possibleMoves(matrix,poll);
            queue.addAll(pollPossibleMoves);
            queue.removeAll(set);
        }
    
    return "Not Found";

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                List<String> matrix = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                    .collect(toList());

                int k = Integer.parseInt(bufferedReader.readLine().trim());

                String result = Result.countLuck(matrix, k);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
