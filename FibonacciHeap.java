import java.io.*;
import java.util.*;


public class FibonacciHeap {
    public int size;
    public Node head;
    public Node max;
    public Node prev, next;
    public Hashtable<String, Node> h;
    //don't forget to change all occurrences of < with > and vice versa

        public FibonacciHeap(){

        }

        public boolean isEmpty() {
        return size == 0;
    }

        public int size() {
        return size;
    }
        /*
        *
        * FIB-HEAP-INSERT(H, x)


                 7 concatenate the root list containing x with root list H


        */
        public void insert(FibonacciHeap H, Node x){
            x.degree = 0;
            x.parent = null;
            x.child = null;
            x.mark = false;
            if(H.max == null){
                //x.next = x;
                //x.prev = x;
                H.max = x;
            }
            else{
                //head.prev.next = x;
                //x.next = head;
                //x.prev = head.prev;
                //head.prev = x;
                x.prev = max;
                x.next = max.next;
                max.next = x;
                x.next.prev = x;
                if(x.value > H.max.value){
                    H.max = x;
                }
            }
            H.size++;
        }

        public void link(FibonacciHeap H, Node y, Node x){
            y.prev.next = y.next;
            y.next.prev = y.prev;
            //make y a child of x
            y.parent = x;
            if(x.child == null){
                x.child = y;
                y.prev = y;
                y.next = y;
            }else{
                y.prev = x.child;
                y.next = x.child.next;

                x.child.next = y;
                y.next.prev = y;
            }
            x.degree++;
            y.mark = false;
        }
        /*
        * FIB-HEAP-UNION(H1, H2)

                3 concatenate the root list of H2 with the root list of H
                4 if (min[H1] = NIL) or (min[H2] ≠ NIL and min[H2] < min[H1])
                5 then min[H] ← min[H2]
                6 n[H] ← n[H1] + n[H2]
                7 free the objects H1 and H2
                8 return H
        */

        public FibonacciHeap union(FibonacciHeap H1, FibonacciHeap H2){
            FibonacciHeap H = new FibonacciHeap();
            H.max = combine(H1.max, H2.max);

            //concatenate the root list of H2 with the root list of H

            /*
            *
            * public FibonacciMinPQ<Key> union(FibonacciMinPQ<Key> that) {
                this.head = meld(head, that.head);
                this.min = (greater(this.min.key, that.min.key)) ? that.min : this.min;
                this.size = this.size+that.size;
                return this;
            }
            *

            * */
            if(H1.max == null || (H2.max != null && H2.max.value < H1.max.value)){
                H.max = H2.max;
            }
            H.size = H1.size + H2.size;
            return H;
        }

        //combine() to help with union - supraba - partially used code for meld
        public static Node combine(Node x, Node y){
            if(x == null && y == null)
                return null;
            else if(x == null)
                return y;
            else if(y == null)
                return x;
            else{
                x.prev.next = y.next;
                y.next.prev = x.prev;
                x.prev = y;
                y.next = x;
            }
            if(isgreaterthan(x.value,y.value))
                return x;
            else
                return y;
        }

        //remove if not necessary - had to make it static below
        public static boolean isgreaterthan(int a, int b){
            if(a > b)
                return true;
            return false;
        }

        /*
        * FIB-HEAP-EXTRACT-MIN(H)
                 1
                 3 then for each child x of z
                 4 do add x to the root list of H
                 5 p[x] ← NIL
                 6 remove z from the root list of H
                 7 if z = right[z]
                 8 then min[H] ← NIL
                 9 else min[H] ← right[z]



        */
        public Node extractMax(FibonacciHeap H){
            if (isEmpty()) throw new NoSuchElementException("Priority queue is empty");

            /*

             Node z = H.max;
                if(x != null){
                    head = meld(head, x);
                    max.child = null;
                    x.parent = null;
                    head = cut(max, head);
                    if(z.next == z)
                        H.max = null;
                    else
                        H.max = null;

                }
                consolidate(H);
                H.size--;
                return H.max;
            }

            return z;
            */
            Node z = H.max;
            Node temp;
            int numChildren;
            if(z != null) {

                Node x = z.child;
                numChildren = z.degree;
                while (numChildren > 0) {
                    temp = x.next;

                    x.prev.next = x.next;
                    x.next.prev = x.prev;

                    x.prev = z;
                    x.next = z.next;
                    z.next = x;
                    x.next.prev = x;

                    x.parent = null;
                    x = temp;
                    numChildren--;
                }
                z.prev.next = z.next;
                z.next.prev = z.prev;

                if(z == z.next)
                    H.max = null;
                else {
                    H.max = z.next;
                    consolidate(H);
                }
                H.size--;
            }
                 return z;
        }
        //meld added to help with above
        private Node meld(Node x, Node y) {
            if (x == null) return y;
            if (y == null) return x;
            x.prev.next = y.next;
            y.next.prev = x.prev;
            x.prev = y;
            y.next = x;
            return x;
        }
        /*
        //cut added to help with extractMax
        private Node cut(Node x, Node head) {
            if (x.next == x) {
                x.next = null;
                x.prev = null;
                return null;
            } else {
                x.next.prev = x.prev;
                x.prev.next = x.next;
                Node res = x.next;
                x.next = null;
                x.prev = null;
                if (head == x)  return res;
                else 			return head;
            }
        }
        */
        //Removes a tree from the list defined by the head pointer
        private Node cut(Node x, Node head) {
            if (x.next == x) {
                x.next = null;
                x.prev = null;
                return null;
            } else {
                x.next.prev = x.prev;
                x.prev.next = x.next;
                Node res = x.next;
                x.next = null;
                x.prev = null;
                if (head == x)  return res;
                else 			return head;
            }
        }

        public void consolidate(FibonacciHeap H){
            double phi = (1 + Math.sqrt(5))/2;
            int sizeD = (int)Math.floor(Math.log(size)/Math.log(phi)) + 1;
            List<Node> A = new ArrayList<Node>(sizeD);
            for(int i = 0; i < sizeD; i++)
                A.add(i, null);

            /*
            Node w, x, y;
            int d;
            w = H.max;
            do{
                x = w;
                d = x.degree;
                while(A.get(d) != null){
                    y = A.get(d);
                    if(x.value < y.value){
                        Node temp = x;
                        x = y;
                        y  = temp;
                    }
                    link(H, y, x);
                    A.set(d, null);
                    d = d + 1;
                }
                A.set(d, x);
                w = w.next;
            }while(w != head);
            H.max = null;
            for(int i = 0; i < sizeD; i++){
                y = A.get(i);
                if(y != null){
                    if(H.max == null){
                        //
                        y.prev = y;
                        y.next = y;
                        H.max = y;
                    }
                    else{
                        //
                        insert(H, y);
                        if(y.value > H.max.value)
                            H.max = y;
                    }
                }
            }
            */
            Node w, x, y, z;
            int numRoots = 0;
            x = H.max;
            if(x != null){
                do{
                    numRoots++;
                    x = x.next;
                }while(x!=head);
            }

            int d;

            while(numRoots > 0){
                d = x.degree;
                w = x.next;

                while(true){
                    y = A.get(d);
                    if(y == null)
                        break;
                    if(x.value < y.value){
                        z = x;
                        x = y;
                        y = z;
                    }
                    link(H, y, x);
                    A.set(d, null);
                    d++;
                }
                A.set(d, x);

                x = w;
                numRoots--;
            }
            H.max = null;
            for(int i = 0; i < sizeD; i++){
                y = A.get(i);
                //if(y == null)
                //    continue;
                if(H.max != null){
                    y.prev.next = y.next;
                    y.next.prev = y.prev;

                    y.prev = H.max;
                    y.next = H.max.next;
                    H.max.next = y;
                    y.next.prev = y;

                    if(y.value > H.max.value)
                        H.max = y;
                }
                else
                    H.max = y;
            }
        }

        public void increaseKey(FibonacciHeap H, Node x, int k){
            x.value  = x.value + k;
            Node y = x.parent;
            if(y != null && x.value > y.value){
                cut(H, x, y);
                cascadingCut(H, y);
            }
            if(x.value > H.max.value)
                H.max = x;
        }

        public void cascadingCut(FibonacciHeap H, Node y){
        Node z = y.parent;
        if(z != null){
            if(y.mark == false)
                y.mark = true;
            else{
                cut(H, y, z);
                cascadingCut(H, z);
            }
        }
    }

        public void cut(FibonacciHeap H, Node x, Node y){
            x.prev.next = x.next;
            x.next.prev = x.prev;
            y.degree--;

            if(y.child == x)
                y.child = x.next;

            if(y.degree == 0)
                y.child = null;

            x.prev = H.max;
            x.next = H.max.next;
            H.max.next = x;
            x.next.prev = x;

            x.parent = null;
            x.mark = false;
            /*
            if (x.next == x) {
            x.next = null;
            x.prev = null;
            } else{
                x.next.prev = x.prev;
                x.prev.next = x.next;
            }
            x.parent = null;
            x.mark = false;
            */
        }

}
