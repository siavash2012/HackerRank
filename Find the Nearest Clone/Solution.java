import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* Code implemented by Siavash Khalaj (contactsiavash@gmail.com) */
class Node{
    boolean visited;
    List<Node>neighbors;
    int id;
    long color;
    int count;
    public Node(int id,long color){
        visited=false;
        this.id=id;
        this.color=color;
        this.count=0;
        neighbors=new ArrayList<Node>();
    }
    public String toString(){
        return this.id+" "+this.color+" "+this.neighbors.size();
    }
}

public class Solution{

    static int BFS(Node source,int val){
        LinkedList<Node> queue = new LinkedList<>();
        // Mark the current node as visited and enqueue it
        source.visited = true;
        queue.add(source);

        // Iterate over the queue
        while (!queue.isEmpty()) {
            // Dequeue a vertex from queue and print it
            Node currentNode = queue.poll();

            if((!currentNode.equals(source)) && currentNode.color==val){
                return currentNode.count;
            }

            // Get all adjacent vertices of the dequeued
            // vertex currentNode If an adjacent has not
            // been visited, then mark it visited and enqueue it
            for (Node neighbor : currentNode.neighbors) {
                if (!neighbor.visited) {
                    neighbor.visited = true;
                    neighbor.count=currentNode.count+1;
                    queue.add(neighbor);
                }
            }
        }
        return 0;
    }

    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
        
        if(Arrays.binarySearch(ids,val)<0){
            return -1;
        }
        int min=Integer.MAX_VALUE;
        Set<Node> sourceSet=new HashSet<>();
        Map<Integer,Node> nodeIdMap=Stream.concat(Arrays.stream(graphFrom).boxed(),Arrays.stream(graphTo).boxed()).collect(Collectors.toSet()).stream().map(i->new Node(i,ids[i-1])).collect(Collectors.toMap(a->a.id,a->a));

        for(int i=0;i<graphFrom.length;++i){
           nodeIdMap.get(graphFrom[i]).neighbors.add(nodeIdMap.get(graphTo[i]));
           nodeIdMap.get(graphTo[i]).neighbors.add(nodeIdMap.get(graphFrom[i]));
        }

        Collection<Node>nodes=nodeIdMap.values();

        for(Node node:nodes){
            if(node.color==val){
                sourceSet.add(node);
            }
        }

        for(Node source:sourceSet){
            int count=BFS(source,val);
            if(count<min){
                min=count;
            }
            nodes.forEach(a->a.visited=false);
        }

        return min==0 ?-1:min;

    }



    public static void main(String[] args) throws IOException {
        final Scanner scanner = new Scanner(System.in);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] graphNodesEdges = scanner.nextLine().split(" ");
        int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
        int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

        int[] graphFrom = new int[graphEdges];
        int[] graphTo = new int[graphEdges];

        for (int i = 0; i < graphEdges; i++) {
            String[] graphFromTo = scanner.nextLine().split(" ");
            graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
            graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
        }

        long[] ids = new long[graphNodes];

        String[] idsItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < graphNodes; i++) {
            long idsItem = Long.parseLong(idsItems[i]);
            ids[i] = idsItem;
        }

        int val = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
