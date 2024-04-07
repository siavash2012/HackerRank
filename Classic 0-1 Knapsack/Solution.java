import java.io.*;
import java.util.*;

/* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */

class Item {
    int weight;
    int value;
    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
    public String toString() {
        return this.weight + " " + this.value;
    }
}



public class Solution {

    /* Function for findidng the maximum value of items which can be included in the knapsack */
    public static int[][] solve(int weightLimit, List < Item > itemList) {
        int[][] dp = new int[itemList.size() + 1][weightLimit + 1];
        for (int i = 1; i < dp.length; ++i) {
            for (int j = 1; j < dp[i].length; ++j) {
                int withoutItemValue = dp[i - 1][j];
                if (itemList.get(i - 1).weight <= j) {
                    int withItemRemainingWeight = j - itemList.get(i - 1).weight;
                    int withItemValue = itemList.get(i - 1).value + dp[i - 1][withItemRemainingWeight];
                    if (withItemValue > withoutItemValue) {
                        dp[i][j] = withItemValue;
                    } else {
                        dp[i][j] = withoutItemValue;
                    }
                } else {
                    dp[i][j] = withoutItemValue;
                }
            }
        }
        return dp;
    }

    /* Function for finding the actual items which can be included in the knapsack (not required for this challenge) */
    public static List < Item > findItems(int[][] dp, List < Item > itemList) {
        List < Item > includedItemList = new ArrayList < > ();
        findItemsHelper(dp, itemList, includedItemList);
        return includedItemList;
    }

    public static void findItemsHelper(int[][] dp, List < Item > itemList, List < Item > includedItemList) {
        int row = dp.length - 1;
        int column = dp[row].length - 1;
        while (row >= 1) {
            if (dp[row][column] > dp[row - 1][column]) {
                includedItemList.add(itemList.get(row - 1));
                column -= itemList.get(row - 1).weight;
            }
            --row;
        }
    }

    public static void main(String[] args) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var numCases = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numCases; ++i) {
            var itemList = new ArrayList < Item > ();
            var arr = reader.readLine().split(" ");
            var weightLimit = Integer.parseInt(arr[0]);
            var numItems = Integer.parseInt(arr[1]);
            for (int j = 0; j < numItems; ++j) {
                var itemArr = reader.readLine().split(" ");
                var itemWeight = Integer.parseInt(itemArr[0]);
                var itemValue = Integer.parseInt(itemArr[1]);
                itemList.add(new Item(itemWeight, itemValue));
            }
            int[][] dp = solve(weightLimit, itemList);
            System.out.println(dp[itemList.size()][weightLimit]);
            // System.out.println(findItems(dp,itemList));
        }
    }
}
