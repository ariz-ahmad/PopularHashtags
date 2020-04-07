public class Node{
    int degree = 0;
    boolean mark = false;
    Node next, prev;
    Node parent, child;
    public String key;
    public int value;

    Node(String key, int value){
        this.key = key;
        this.value = value;
        prev = next = this;
        //System.out.println(this.key);
        //System.out.println(this.value);
        //System.out.println();

    }

    public String getKey(){
        return key;
    }

    public int getValue(){
        return value;
    }


}