import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/* Code implemented By Siavash Khalaj (contactsiavash@gmail.com) */
public class Solution {

    public static boolean canWin(int[] game, int leap) {

        if (leap >= game.length) return true;
        var zeroIndices = new ArrayList<Integer>();
        for (var i = 0; i < game.length; ++i) {
            if (game[i] == 0) {
                zeroIndices.add(i);
            }
        }
        
        var map = new HashMap<Integer, HashSet<Integer>>();
        for (var i : zeroIndices) {
            var newSet = new HashSet<Integer>();
            if (i >= game.length - leap) newSet.add(game.length);
            if (zeroIndices.contains(i + 1)) newSet.add(i + 1);
            if (zeroIndices.contains(i + leap)) newSet.add(i + leap);
            if (zeroIndices.contains(i - 1) && (i - 1) > 0) newSet.add(i - 1);
            map.put(i, newSet);
        }

        while (true) {
            var newSet = new HashSet<Integer>();
            for (var index : map.get(0)) {
                var set = map.get(index);
                for (var setIndex : set) {
                    if (setIndex >= game.length - leap || setIndex == game.length - 1) {
                        return true;
                    } else {
                        newSet.add(setIndex);
                    }
                }
            }
            if (!map.get(0).addAll(newSet)) break;
        }
        return false;
    }


    public static void main(String[] args) throws Exception {
        var casesReader = new BufferedReader(new InputStreamReader(System.in));
        casesReader.readLine();
        while (casesReader.ready()) {
            var leap = Integer.valueOf(casesReader.readLine().split(" ")[1]);
            var game = Arrays.stream(casesReader.readLine().split(" ")).map(String::strip).mapToInt(Integer::valueOf).toArray();
            var result = (canWin(game, leap) ? "YES" : "NO");
            System.out.println(result);
        }
    }
}
