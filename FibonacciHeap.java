import java.util.ArrayList;
import java.util.List;


class FibonacciHeap<String>
{


    private Node<String> max;
    private int size;
    public FibonacciHeap(){}
    public void clear()
    {
        max = null;
        size = 0;
    }

    public void insertHelper(Node<String> node, double key){
        node.key = key;
        if (max != null) {
            node.prev = max;
            node.next = max.next;
            max.next = node;
            node.next.prev = node;

            if (key > max.key) {
                max = node;
            }
        } else {
            max = node;
        }
    }
    public void insert(Node<String> node, double key)
    {
        insertHelper(node, key);
        size++;
    }


    public Node<String> max()
    {
        return max;
    }

    public void removeMaxHelperSub1(){

    }

    public void removeMaxHelperSub2(){

    }

    public void removeMaxHelper(){
        Node<String> z = max;
        Node<String> x = z.child;
        Node<String> temp;
        int numChildren = z.degree;
        while (numChildren > 0) {

            temp = x.next;
            x.prev.next = x.next;
            x.next.prev = x.prev;

            x.prev = max;
            x.next = max.next;
            max.next = x;
            x.next.prev = x;

            x.parent = null;
            x = temp;
            numChildren--;
        }

        z.prev.next = z.next;
        z.next.prev = z.prev;
        if (z == z.next) {
            max = null;
        } else {
            max = z.next;
            consolidate();
        }
        size--;
    }
    public Node<String> removeMax()
    {
        Node<String> z = max;
        if (z != null) {
            removeMaxHelper();
        }
        return z;
    }


    public void consolidateHelper(Node<String> x, int numRoots){

    }

    protected double initializePhi(){
        return (1 + Math.sqrt(5))/ 2.0;
    }

    protected int initializeArraySize(double Phi){
        return ((int) Math.floor(Math.log(size) / Math.log(Phi))) + 1;
    }
    protected void consolidate()
    {
        double Phi = initializePhi();
        int arraySize = initializeArraySize(Phi);

        List<Node<String>> array = new ArrayList<Node<String>>(arraySize);
        Node<String> temp;

        for (int i = 0; i < arraySize; i++) {
            array.add(null);
        }

        int numRoots = 0;
        Node<String> x = max;

        //consolidateHelper1(x, numRoots);
        if (x != null) {
            numRoots++;
            x = x.next;

            while (x != max) {
                numRoots++;
                x = x.next;
            }
        }

        //consolidateHelper(x, numRoots)
        while (numRoots > 0) {
            int d = x.degree;
            Node<String> next = x.next;

            while(true) {
                Node<String> y = array.get(d);
                if (y == null) {
                    // Nope.
                    break;
                }

                if (x.key < y.key) {
                    temp = y;
                    y = x;
                    x = temp;
                }

                link(y, x);

                array.set(d, null);
                d++;
            }

            array.set(d, x);

            x = next;
            numRoots--;
        }


        max = null;

        for (int i = 0; i < arraySize; i++) {
            Node<String> y = array.get(i);
            if (y == null) {
                continue;
            }

            if (max != null) {
                y.prev.next = y.next;
                y.next.prev = y.prev;

                y.prev = max;
                y.next = max.next;
                max.next = y;
                y.next.prev = y;
                if (y.key > max.key) {
                    max = y;
                }
            } else {
                max = y;
            }
        }
    }

    protected void linkHelper1(Node<String> y, Node<String> x){
        y.prev.next = y.next;
        y.next.prev = y.prev;
        y.parent = x;
    }

    protected void linkHelper2(Node<String> y, Node<String> x){
        if (x.child == null) {
            x.child = y;
            y.next = y;
            y.prev = y;
        } else {
            y.prev = x.child;
            y.next = x.child.next;
            x.child.next = y;
            y.next.prev = y;
        }
    }

    protected void linkHelper3(Node<String> y, Node<String> x){
        x.degree++;
        y.childCut = false;
    }

    protected void link(Node<String> y, Node<String> x)
    {
        linkHelper1(y, x);
        linkHelper2(y, x);
        linkHelper3(y, x);

    }

    protected void increaseKeyHelper(Node<String> x, double k){
        Node<String> y = x.parent;
        if ((y != null) && (x.key > y.key)) {
            cut(x, y);
            cascadingCut(y);
        }
        if (x.key > max.key) {
            max = x;
        }
    }

    public void increaseKey(Node<String> x, double k)
    {
        x.key += k;
        increaseKeyHelper(x, k);

    }
    protected void cutHelper1(Node<String> x, Node<String> y){
        x.prev.next = x.next;
        x.next.prev = x.prev;
        y.degree--;
    }

    protected void cutHelper2(Node<String> x, Node<String> y){
        if (y.child == x) {
            y.child = x.next;
        }
    }

    protected void cutHelper3(Node<String> x, Node<String> y){
        if (y.degree == 0) {
            y.child = null;
        }
    }

    protected void cutHelper4(Node<String> x, Node<String> y){
        x.prev = max;
        x.next = max.next;
        max.next = x;
        x.next.prev = x;
        x.parent = null;
        x.childCut = false;
    }

    protected void cut(Node<String> x, Node<String> y)
    {
        cutHelper1(x, y);

        cutHelper2(x, y);


        cutHelper3(x, y);


        cutHelper4(x, y);

    }


    protected void cascadingCut(Node<String> y)
    {
        Node<String> z = y.parent;
        if (z != null) {
            cascadingCutHelper(y);
        }
    }

    protected void cascadingCutHelper(Node<String> y){
        Node<String> z = y.parent;
        if (!y.childCut) {
            y.childCut = true;
        } else {
            cut(y, z);
            cascadingCut(z);
        }
    }
}

