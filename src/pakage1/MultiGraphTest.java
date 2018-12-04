package pakage1;
import org.junit.*;
import static org.junit.Assert.*;

public class MultiGraphTest {

    @Test
    public void getNodeIdTest() {
        NodeADT n = new Node(1, "v1");
        assertEquals(1, n.getId());
    }

    @Test
    public void getNodeValueTest() {
        NodeADT n = new Node(1, "v1");
        assertEquals("v1", n.getValue());
    }

    @Test
    public void setNodeIdTest() {
        NodeADT n = new Node();
        n.setId(1);
        assertEquals(1, n.getId());
    }

    @Test
    public void setNodeValueTest() {
        NodeADT n = new Node();
        n.setValue("v1");
        assertEquals("v1", n.getValue());
    }

    @Test
    public void setEdgeLabelTest() {
        EdgeADT e = new Edge();
        e.setLabel("l1");
        assertEquals("l1", e.getLabel());
    }

    @Test
    public void setNodeATest() {
        Edge e = new Edge();
        Node node1 = new Node(1, "v1");
        e.setNodeA(node1);
        assertEquals(1, e.getNodeA().getId());
        assertEquals("v1", e.getNodeA().getValue().toString());
    }

    @Test
    public void setNodeBTest() {
        Edge e = new Edge();
        Node node2 = new Node(2, "v2");
        e.setNodeB(node2);
        assertEquals(2, e.getNodeB().getId());
        assertEquals("v2", e.getNodeB().getValue().toString());
    }

    @Test
    public void addNodeTest() {
        //Node is added to the multi-graph successfully
        MultiGraphADT mg = new MultiGraph();

        assertTrue(mg.addNode(1, "v1"));
        assertTrue(mg.allNodes().size() == 1);

        assertTrue(mg.addNode(2, "v2"));
        assertTrue(mg.allNodes().size() == 2);

        assertTrue(mg.addNode(3, "v3"));
        assertTrue(mg.allNodes().size() == 3);

        assertTrue(mg.addNode(4, "v4"));
        assertTrue(mg.allNodes().size() == 4);
    }

    @Test
    public void nodeAlreadyExistsTest() {
        //Node with same Id already exists within the multi-graph so node is not added
        MultiGraphADT mg = new MultiGraph();

        assertTrue(mg.addNode(1, "v1"));
        assertFalse(mg.addNode(1, "v1"));

        assertTrue(mg.allNodes().size() == 1);
    }

    @Test
    public void addEdgeTest() {
        //Edge is added to the multi-graph successfully
        MultiGraphADT mg = new MultiGraph();
        mg.addNode(1, "v1");
        mg.addNode(2, "v2");

        assertTrue(mg.addEdge("l1", 1, 2));
        assertTrue(mg.allEdges().size() == 1);
    }

    @Test
    public void addMultipleEdgesTest() {
        //Multiple edges are added to the multi-graph successfully
        MultiGraphADT mg = new MultiGraph();
        mg.addNode(1, "v1");
        mg.addNode(2, "v2");
        mg.addNode(3, "v3");
        mg.addNode(4, "v4");

        assertTrue(mg.addEdge("l1", 1, 2));
        assertTrue(mg.allEdges().size() == 1);

        assertTrue(mg.addEdge("l2", 2, 3));
        assertTrue(mg.allEdges().size() == 2);

        assertTrue(mg.addEdge("l3", 3, 4));
        assertTrue(mg.allEdges().size() == 3);

        assertTrue(mg.addEdge("l4", 1, 3));
        assertTrue(mg.allEdges().size() == 4);
    }

    @Test
    public void addMultipleEdgesBetweenTwoNodesTest() {
        //Multiple edges are added between the same two nodes
        MultiGraphADT mg = new MultiGraph();
        mg.addNode(1, "v1");
        mg.addNode(2, "v2");

        assertTrue(mg.addEdge("l1", 1, 2));
        assertTrue(mg.allEdges().size() == 1);

        assertTrue(mg.addEdge("l2", 1, 2));
        assertTrue(mg.allEdges().size() == 2);
    }

    @Test
    public void nodesDoNotExistTest() {
        //Nodes specified do not exist within the multi-graph
        MultiGraphADT mg = new MultiGraph();
        mg.addNode(1, "v1");

        assertFalse(mg.addEdge("l1", 1, 2));
        assertFalse(mg.addEdge("l2", 2, 3));
    }

    @Test
    public void returnPath() {
        //If a path between two nodes exist then a path is returned
        MultiGraphADT mg = new MultiGraph();
        mg.addNode(1, "v1");
        mg.addNode(2, "v1");
        mg.addNode(3, "v3");
        mg.addNode(4, "v4");
        mg.addEdge("l1", 1, 2);
        mg.addEdge("l1", 2, 3);
        mg.addEdge("l1", 3, 4);
        mg.addEdge("l1", 1, 3);

        assertTrue(!mg.findPath(mg.findNode(1), mg.findNode(2)).isEmpty());
        assertTrue(!mg.findPath(mg.findNode(2), mg.findNode(1)).isEmpty());
        assertTrue(!mg.findPath(mg.findNode(2), mg.findNode(3)).isEmpty());
        assertTrue(!mg.findPath(mg.findNode(3), mg.findNode(2)).isEmpty());
        assertTrue(!mg.findPath(mg.findNode(3), mg.findNode(4)).isEmpty());
        assertTrue(!mg.findPath(mg.findNode(4), mg.findNode(3)).isEmpty());
        assertTrue(!mg.findPath(mg.findNode(1), mg.findNode(3)).isEmpty());
        assertTrue(!mg.findPath(mg.findNode(3), mg.findNode(1)).isEmpty());


    }

    @Test
    public void returnEmptyPath() {
        //If a path between two nodes does not exist then a null value is returned
        MultiGraphADT mg = new MultiGraph();
        mg.addNode(1, "v1");
        mg.addNode(2, "v2");

        assertEquals(null, mg.findPath(mg.findNode(1), mg.findNode(2)));
        assertEquals(null, mg.findPath(mg.findNode(2), mg.findNode(1)));
    }
}
