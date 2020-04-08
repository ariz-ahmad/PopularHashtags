class Node<String>
{

    String data;
    Node<String> child;
    Node<String> prev;
    Node<String> parent;

    Node<String> next;
    double key;
    int degree;

    boolean childCut;

    public Node(String data, double key)
    {
        next = prev = this;
        this.data = data;
        this.key = key;
    }

    public final String getData()
    {
        return data;
    }

    public final double getKey()
    {
        return key;
    }

}

