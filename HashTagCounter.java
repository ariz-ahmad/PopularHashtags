import java.io.*;
import java.util.Hashtable;
import java.util.Scanner;

public class HashTagCounter {
    public static void main(String args[])throws IOException {
        /*
        FileWriter fw=new FileWriter("output.txt");
        for (int i = 0; i < str.length(); i++)
            fw.write(str.charAt(i));
        fw.close();

         */

        //BufferedReader Input
        FibonacciHeap F = new FibonacciHeap();
        Hashtable<String, Node> h = new Hashtable<String, Node>();


        BufferedReader inputStream = new BufferedReader(new FileReader("sampleInput-test.txt"));
        File of = new File("sampleOutput-test.txt");
        FileOutputStream fop = new FileOutputStream(of);
        OutputStreamWriter outputStream = new OutputStreamWriter(fop, "UTF-8");

        String l;

        String key;
        int value;
        int freq;
        String a[] = new String[200];
        while ((l = inputStream.readLine()) != null) {

            if (l.charAt(0) == '#') {
                a = l.split(" ");
                key = a[0].substring(1);
                value = Integer.valueOf(a[1]);
                if (h.containsKey(key)) {
                    Node x = (Node)h.get(key);
                    F.increaseKey(x, value);
                } else {
                    Node x = new Node(key, value);
                    h.put(key, x);
                    F.insert(x, value);
                }
            } else if (l.equals("stop") || l.equals("STOP")) {
                System.exit(0);
            } else {
                freq = Integer.valueOf(l);
                //System.out.println(freq);
                //output n hashtags
                Node[] outNodeList = new Node[freq];
                String outNodeKey;
                int outNodeValue;
                for (int j = 0; j < freq; j++) {
                    outNodeKey = F.max().getKey();
                    outNodeValue = F.max().getValue();
                    Node outNode = new Node(outNodeKey, outNodeValue);
                    outNodeList[j] = outNode;
                    outputStream.append(F.max().getKey());
                    if (j < freq - 1)
                        outputStream.append(",");
                    F.extractMax();
                    h.remove(F.max().getKey());
                }
                outputStream.append("\n");
                //re insert n hashtags
                for (int j = 0; j < freq; j++) {
                    // Node x = new Node(outNodeList[j].key, outNodeList[j].value);
                    h.put(outNodeList[j].key, outNodeList[j]);
                    F.insert(outNodeList[j], outNodeList[j].getValue());
                }
            }
            //outputStream.println(l);

        }

        outputStream.close();
        //close the output stream
        fop.close();


        inputStream.close();
        //close the read file
        //FibonacciHeap.clear();
        //clear the Fibonacci Heap


        //Scanner
        /*
        Scanner s = null;
        try {
            s = new Scanner(new BufferedReader(new FileReader("sampleInput.txt")));

            while (s.hasNext()) {
                System.out.println(s.next());
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
        */
    }
}

