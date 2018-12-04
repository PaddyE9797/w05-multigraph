package pakage1;

public class Edge implements EdgeADT {

    private Object label;
    private Node nodeA, nodeB;

    public Edge() {
        this (null, null, null);
    }

    public Edge(Object label, Node nodeA, Node nodeB) {
        this.label = label;
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    public Object getLabel() {
        return label;
    }

    public Node getNodeA() {
        return nodeA;
    }

    public Node getNodeB() {
        return nodeB;
    }

    public void setLabel(Object label) {
        this.label = label;
    }

    public void setNodeA(Node nodeA) {
        this.nodeA = nodeA;
    }

    public void setNodeB(Node nodeB) {
        this.nodeB = nodeB;
    }
}
