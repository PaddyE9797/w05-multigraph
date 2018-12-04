package pakage1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class MultiGraph implements MultiGraphADT {

    private Map<Node, ArrayList<Edge>> graph;

    public MultiGraph() {
        graph = new HashMap<>();
    }

    /**
     * ADDS A NODE WITH AN EMPTY LIST TO THE MULTIGRAPH
     * */
    public boolean addNode(int id, Object value) {
        if(isNode(id)) {
            return false;
        }
        Node newNode = new Node(id, value);
        ArrayList<Edge> edgeList = new ArrayList();
        //assert (edgeList.isEmpty()) : edgeList;

        graph.put(newNode, edgeList);
        return true;
    }

    /**
     * ADDS EDGE TO THE EDGE ARRAY OF IT'S MATCHING NODE
     * */
    public boolean addEdge(Object label, int idA, int idB) {
        if(!isNode(idA) || !isNode(idB)) {
            return false;
        }

        //assert(findNode(idA) != null);
        //assert(findNode(idB) != null);

        Edge newEdge = new Edge(label, (Node)findNode(idA), (Node)findNode(idB));

        graph.get(findNode(idA)).add(newEdge);
        graph.get(findNode(idB)).add(newEdge);

        return true;
    }

    /**
     * RETURNS SET CONTAINING ALL THE NODES
     * */
    public Set<Node> allNodes() {
        Set<Node> nodeSet = new HashSet<>();

        for (Node n: graph.keySet()) {
            nodeSet.add(n);
        }
        return nodeSet;
    }

    /**
     *  RETURNS SET CONTAINING ALL THE EDGES
     * */
    public Set<Edge> allEdges() {
        Set<Edge> edgeSet = new HashSet<>();

        for (ArrayList<Edge> ae : graph.values()) {
            for (Edge e :  ae) {
                if (!edgeSet.contains(e)) {
                    edgeSet.add(e);
                }
            }
        }
        return edgeSet;
    }

    /**
     *  RETURNS ALL EDGES COMING OUT OF A GIVEN NODE
     **/
    public Set<Edge> getSuccessors(Node source){
        Set<Edge> results = new HashSet<>();

        for (Edge e : graph.get(source)) {
            results.add(e);
        }
        return results;
    }

    /**
     *  RETURNS LIST OF EDGES TAKEN TO GET FROM ONE NODE TO ANOTHER
     * */
    public List<Edge> findPath(Node source, Node destination) {
        Set<Node> visitedNodes = new HashSet<>();
        Queue<Node> nodesToVisit = new LinkedList<>();
        Map<Node, Edge> edgesUsedByNode = new HashMap<>();

        //assert(source != null);
        nodesToVisit.add(source);

        while (!nodesToVisit.isEmpty()) {
            Node node = nodesToVisit.poll();
            //assert(node != null);
            if (node == destination) {
                // We've reached the node we want, so we're done scanning
                // the graph
                LinkedList<Edge> results = new LinkedList<>();

                // We now know which edges to use to arrive at each of the
                // nodes we've seen -- so, we can scan this in reverse order
                // to build our results list
                while (node != source) {
                    Edge edge = edgesUsedByNode.get(node);
                    //assert(edge != null);
                    results.addFirst(edge);

                    if (node == edge.getNodeB()) {
                        node = edge.getNodeA();
                    }
                    else {
                        //assert(node == edge.getNodeA()) : node;
                        node = edge.getNodeB();
                    }
                }
                // We're done now!
                return results;
            }


            // What can we visit from this node?
            for (Edge edge : getSuccessors(node)) {
                // Find out what's on the other side of this edge
                assert(getSuccessors(node).size() > 0);
                Node otherNode;

                if (edge.getNodeA() == node) {
                    otherNode = edge.getNodeB();
                }
                else {
                    //assert(node == edge.getNodeB()) : node;
                    otherNode = edge.getNodeA();
                }

                if (!visitedNodes.contains(otherNode) && !nodesToVisit.contains(otherNode)) {
                    // Remember what edge we used to arrive at this node, and
                    // queue the node for further scanning
                    edgesUsedByNode.put(otherNode, edge);
                    nodesToVisit.add(otherNode);
                }
            }
            visitedNodes.add(node);
        }
        // We never got to our desired node, so something must have gone wrong
        return null;
    }

    /**
     * RETURNS A NODE IF ID EXISTS
     * */
    public Node findNode(int id) {
        for (Node n : graph.keySet()) {
            if (n.getId() == id)
                return n;
        }
        return null;
    }

    /**
     * RETURNS AN EDGE IF VALUE EXISTS
     * */
    public Edge findEdge(Object label) {
        for (Edge e : allEdges()) {
            if(label == e.getLabel()) {
                return e;
            }
        }
        return null;
    }

    /**
     * CHECK IF A NODE ID EXISTS
     * */
    private boolean isNode(int id) {
        for (Node n : allNodes()){
            if (id == n.getId()){
                return true;
            }
        }
        return false;
    }

    /**
     * CHECK IF A LABEL HAS AN EDGE
     * */
   /* private boolean isEdge(Object label) {
        for (EdgeADT e : allEdges()){
            if (label == e.getLabel()) {
                return true;
            }
        }
        return false;
    } */
}
