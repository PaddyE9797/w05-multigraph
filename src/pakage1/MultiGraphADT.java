package pakage1;

import java.util.Set;
import java.util.List;

public interface MultiGraphADT {

    boolean addNode(int id, Object value);

    boolean addEdge(Object label, int idA, int idB);

    Set<Node> allNodes();

    Set<Edge> allEdges();

    List<Edge> findPath(Node nodeA, Node nodeB);

    Node findNode(int id);

    Edge findEdge(Object label);

    Set<Edge> getSuccessors(Node node);
}