import java.io.*;
import java.util.*;

public class Solution {
    
    Set<Integer>set=new HashSet<>();
    
    public static void inOrder(Node node,List<Integer>list)
    {
        if (node == null) return;
        inOrder(node.left,list);
        list.add(node.data);
        inOrder(node.right,list);
    }
    
    boolean checkBST(Node root) {
       List<Integer> inOrderList=new ArrayList<>();
        inOrder(root,inOrderList);
        List<Integer> inOrderListClone=new ArrayList(inOrderList);
        Collections.sort(inOrderListClone);
        return inOrderListClone.equals(inOrderList) && inOrderList.size()==new HashSet<>(inOrderList).size();
    }

    class Node {
        int data;
        Node left;
        Node right;
    }
    
    public Node buildTree(String [] arr, int from, int to) {
        int middle = from + (to - from) / 2;
        Node node = new Node();
        node.data = Integer.valueOf(arr[middle]);
        if (middle > from) {
            node.left = buildTree(arr, from, middle - 1);
            node.right = buildTree(arr, middle + 1, to);
        }
        return node;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)))) {
            int maxHeight = Integer.valueOf(in.readLine());
            String [] line = in.readLine().split(" ");
            Solution solution = new Solution();
            Node root = solution.buildTree(line, 0, line.length);
            if (solution.checkBST(root)) {
                out.println("Yes");
            } else {
                out.println("No");
            }
        }
    }
}
