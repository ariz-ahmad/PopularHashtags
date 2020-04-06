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
        FibonacciHeap<Node> fh = new FibonacciHeap<Node>();
        Hashtable<String, Node> h =
                new Hashtable<String, Node>();
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
            String a[];
            while ((l = inputStream.readLine()) != null) {
                if(l.charAt(0)=='#'){
                    a = l.split(" ");
                    key = a[0].substring(1);
                    value = Integer.valueOf(a[1]);
                    if()
                }
                else if(l.equals("stop") || l.equals("STOP")){
                    System.exit(0);
                }
                else{
                    freq = Integer.valueOf(l);
                    //System.out.println(freq);
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
