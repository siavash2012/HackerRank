import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */

class Node{
    Node left;
    Node right;
    int index;
    public Node(int index){
        this.index=index;
    }
    public void addChildren(List<Node> children){
        this.left=children.get(0);
        this.right=children.get(1);
    }

    public String toString(){
        return "Node "+index;
    }

    public void swapNodes(){
        Node temp=this.left;
        this.left=this.right;
        this.right=temp;
    }

}

class Result {

    static List<Integer> traversalList=new ArrayList<>();

    public static void inOrderTraversal(Node node){
        if(node==null)return;
        inOrderTraversal(node.left);
        traversalList.add(node.index);
        inOrderTraversal(node.right);
    }

    public static void inOrderTraversalHelper(Node node){
        traversalList.clear();
        inOrderTraversal(node);
    }

    public static Map<Integer,List<List<Integer>>> depthMap(List<List<Integer>> indexes){

        Map<Integer,List<List<Integer>>> depthMap=new HashMap<>();
        depthMap.put(1,List.of(List.of(1,-1)));
        int depth=2;
        int start=0;
        long nonNullCount=1;
        while(true){
            if(start+nonNullCount>indexes.size())break;
            List<List<Integer>> subList=indexes.subList(start,start+(int)nonNullCount);
            depthMap.put(depth,subList);
            start+=nonNullCount;
            nonNullCount=subList.stream().flatMap(List::stream).filter(a->a!=-1).count();
            if(nonNullCount==0){
                ++nonNullCount;
            }
            ++depth;
        }
        return depthMap;
    }



    public static Map<Integer,List<List<Node>>> constructTree(Map<Integer,List<List<Integer>>> depthMap,int...swapArray){

        List<Integer>depthList=new ArrayList<>(depthMap.keySet());
        depthList.sort(Comparator.naturalOrder());
        Map<Integer,List<List<Node>>> depthNodeMap=new HashMap<>();
        for(int i=1;i<depthList.size()+1;++i){
            List<List<Integer>> list=depthMap.get(i);
            List<List<Node>> temp=new ArrayList<>();
            for(List<Integer> nodeList:list){
                Node zero=nodeList.get(0)==-1?null:new Node(nodeList.get(0));
                Node one=nodeList.get(1)==-1?null:new Node(nodeList.get(1));
                temp.add(Arrays.asList(zero,one));
            }
            depthNodeMap.put(i,temp);
        }
        for(int i=0;i<depthList.size()-1;++i){
            int parentDepth=depthList.get(i);
            int childDepth=depthList.get(i+1);
            List<List<Node>>parentNodes=depthNodeMap.get(parentDepth);
            List<List<Node>> childNodes=depthNodeMap.get(childDepth);
            List<Node> flatparentNodesWithoutNulls=parentNodes.stream().flatMap(List::stream).filter(Objects::nonNull).collect(Collectors.toList());
            for(int j=0;j<flatparentNodesWithoutNulls.size();++j){
                flatparentNodesWithoutNulls.get(j).addChildren(childNodes.get(j));
            }
        }
        if(swapArray.length!=0){
            for(int swap:swapArray){
                List<List<Node>> toBeSwapped=depthNodeMap.get(swap);
                toBeSwapped.stream().flatMap(List::stream).filter(Objects::nonNull).forEach(Node::swapNodes);
            }

        }
        return depthNodeMap;
    }

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        List<List<Integer>>result=new ArrayList<>();
        Map<Integer,List<List<Integer>>> depthMap=depthMap(indexes);
        Map<Integer,List<List<Node>>> depthNodeMap=constructTree(depthMap);
        int maxDepth=new TreeSet<>(depthMap.keySet()).last();
        Node root;
        List<List<Integer>> swapList=new ArrayList<>();
        for(int query:queries){
            List<Integer>temp=new ArrayList<>(swapList.size()>0?swapList.get(swapList.size()-1):new ArrayList<>());
            int multiplier=1;
            while(multiplier*query<maxDepth){
                temp.add(multiplier*query);
                ++multiplier;
            }
            swapList.add(temp);
        }

        for(List<Integer>list:swapList){
            depthNodeMap=constructTree(depthMap,list.stream().mapToInt(a->a).toArray());
            root=depthNodeMap.get(1).get(0).get(0);
            inOrderTraversalHelper(root);
            result.add(new ArrayList<>(traversalList));
        }

        return result;
    }

}

public class Solution{
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> indexes = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                indexes.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<List<Integer>> result = Result.swapNodes(indexes, queries);

        result.stream()
                .map(
                        r -> r.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                )
                .map(r -> r + "\n")
                .collect(toList())
                .forEach(e -> {
                    try {
                        bufferedWriter.write(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
