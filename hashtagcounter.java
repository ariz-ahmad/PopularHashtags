import java.io.*;
import java.util.Hashtable;


public class hashtagcounter {


    public static void main(String[] args) throws IOException {

        String line, hashtag;
        String[] arrs;
        int fre, outnum;

        boolean check = true;
         FibonacciHeap<String> fh = new FibonacciHeap<String>();
         Hashtable hashtags = new Hashtable();


        BufferedReader f = new BufferedReader(new FileReader("input_1000000.txt"));
        File of = new File("output_1000000.txt");
        FileOutputStream fop = new FileOutputStream(of);
        OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");

        int abtToEnd = 0;
        for(;;){
            fre=0;
            outnum=0;
            line = f.readLine();
            if (line.equals("STOP")||line.equals("stop")){
                abtToEnd = 1;
                break;
                //System.exit(0);
            }
            if (line.substring(0,1).equals("#")){
                arrs = line.split(" ");
                hashtag = arrs[0].substring(1);
                fre = Integer.parseInt(arrs[1]);

                if (hashtags.containsKey(hashtag)){
                    Node<String> renode = (Node<String>) hashtags.get(hashtag);
                    fh.increaseKey(renode, fre);

                }else {
                    Node<String> node = new Node<String>(hashtag,fre);
                    hashtags.put(hashtag, node);
                    fh.insert(node, fre);
                }

            }else if(line.contains("S")){
                break;
            }else {
                outnum = Integer.parseInt(line);
                Node<String>[] outNodeList = new Node[outnum];

                for (int j = 0; j < outnum; j++){
                    if(check) {
                        String outNodeData = fh.max().getData();
                        Double outNodeKey = fh.max().getKey();
                        Node<String> outNode = new Node<String>(outNodeData, outNodeKey);
                        outNodeList[j] = outNode;
                    }
                    if(check){
                        writer.append(fh.max().getData());
                    }

                    if (j<outnum-1){
                        writer.append(",");
                    }
                    if(check) {
                        hashtags.remove(fh.max().getData());
                        fh.removeMax();
                    }
                }
                writer.append("\n");
                for (int j = 0; j < outnum; j++){
                    if(check) {
                        hashtags.put(outNodeList[j].data, outNodeList[j]);
                    }
                    fh.insert(outNodeList[j], outNodeList[j].getKey());
                }
            }
        }
        if(abtToEnd == 1) {
            writer.close();
            fop.close();
            f.close();
            fh.clear();
        }
    }
}
