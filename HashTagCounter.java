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



        BufferedReader inputStream = null;
        PrintWriter outputStream = null;
        try {
            inputStream =
                    new BufferedReader(new FileReader("sampleInput-test.txt"));
            outputStream =
                    new PrintWriter(new FileWriter("sampleOutput-test.txt"));

            String l;

            String key;
            int value;
            int freq;
            String a[] = new String[200];
            while ((l = inputStream.readLine()) != null) {
                if(l.charAt(0)=='#'){
                    a = l.split(" ");
                    key = a[0].substring(1);
                    value = Integer.valueOf(a[1]);
                    if(h.containsKey(key)) {
                        Node x = new Node(key, value);
                        F.increaseKey(F, x , value);
                    }else{
                        Node x = new Node(key, value);
                        h.put(key, x);
                        F.insert(F, x);
                    }
                }
                else if(l.equals("stop") || l.equals("STOP")){
                    System.exit(0);
                }
                else{
                    freq = Integer.valueOf(l);
                    //System.out.println(freq);
                    //output n hashtags
                    Node[] outNodeList = new Node[freq];
                    String outNodeKey;
                    int outNodeValue;
                    for(int j = 0; j < freq; j++){
                        outNodeKey = F.max.key;
                        outNodeValue = F.max.value;
                        Node outNode =  new Node(outNodeKey, outNodeValue);
                        outNodeList[j] = outNode;
                        outputStream.append(outNodeList[j].key);
                        if(j < freq - 1)
                            outputStream.append(",");
                        F.extractMax(F);
                        h.remove(outNodeKey);
                    }
                    outputStream.append("\n");
                    //re insert n hashtags
                    for(int j = 0; j < freq; j++){
                        Node x = new Node(outNodeList[j].key, outNodeList[j].value);
                        h.put(outNodeList[j].key, outNodeList[j]);
                        F.insert(F, outNodeList[j]);
                    }
                }
                //outputStream.println(l);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }



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
