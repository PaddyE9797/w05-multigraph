package pakage1;

public class Main {

    public static void main(String[] args) {
        MultiGraph newGraph = new MultiGraph();
        System.out.println(newGraph.addNode(1, "Node1"));
        System.out.println(newGraph.addNode(2, "Node2"));
        System.out.println(newGraph.addNode(3, "Node3"));
        System.out.println(newGraph.addNode(4, "Node4"));

        System.out.println(newGraph.addEdge("Edge1", 1,2 ));
        System.out.println(newGraph.addEdge("Edge2", 2,3 ));
        System.out.println(newGraph.addEdge("Edge3", 3,4 ));
        System.out.println(newGraph.addEdge("Edge4", 1,3 ));

        Node node1 = newGraph.findNode(1);
        Node node2 = newGraph.findNode(4);

        System.out.println(newGraph.findPath(node1, node2));
    }
}
