package pakage1;

public class Node implements NodeADT {

    private int id;
    private Object value;

    public Node() {
        this(1, null);
    }

    public Node(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() { return id; }

    public Object getValue() { return value; }

    public void setId(int id) { this.id = id; }

    public void setValue(Object value) { this.value = value; }
}
