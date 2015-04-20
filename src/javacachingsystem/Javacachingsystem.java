/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacachingsystem;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author nomercy
 */
public class Javacachingsystem {

    private ConcurrentHashMap<Integer, Listmap> cmap ;
	private Listmap start,end;
	private int cmapcapacity,cmaplength;
        public Javacachingsystem(){
            this.cmap = new ConcurrentHashMap<Integer, Listmap>();
        }
        
        public String getvalue(int key) {
		if (cmap.containsKey(key)) {
			Listmap latest = cmap.get(key);
			deleteNode(latest);
			setHead(latest);
			return latest.value;
		}
                if(!cmap.containsKey(key)) {
			return null;
		}
                return null;
	}
        public void deleteNode(Listmap node) {
		Listmap node1 = node.previous;
		Listmap node2 = node.next;
		if (node1 != null) {
			node1.next = node2;
		} else {
			start = node2;
		}
		if (node2 != null) {
			node2.previous = node1;
		} else {
			end = node1;
		}
	}
        
        public void setHead(Listmap node) {
		node.next = start;
		node.previous = null;
		if (start != null) {
			start.previous = node;
		}
		start = node;
		if (end == null) {
			end = node;
		}
	}
        
        public void setvalue(int key, String value) {
		if (cmap.containsKey(key)) {
			Listmap prevNode = cmap.get(key);
			prevNode.value = value;
			deleteNode(prevNode);
			setHead(prevNode);
		} else {
			Listmap newNode = new Listmap(key, value);
			if (cmaplength < cmapcapacity) {
				setHead(newNode);
				cmap.put(key, newNode);
				cmaplength++;
			} else {
				cmap.remove(end.key);
				end = end.previous;
				if (end != null) {
					end.next = null;
				}
				setHead(newNode);
				cmap.put(key, newNode);
			}
		}
        }
	public void printdata(){
            int inc=0;
            Set keyset = cmap.keySet();
            Iterator it =keyset.iterator();
            while(it.hasNext()){
                Listmap pcmap = cmap.get(it.next());
                System.out.println("key is "+pcmap.key+" value is "+pcmap.value);
                inc++;
            }
            System.out.println("head is "+start.key+" tail is "+end.key);
        }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        System.out.println("Enter the size of the frame you want");
        Scanner sc = new Scanner(System.in);
        int fsize = sc.nextInt();
        Javacachingsystem jcs=new Javacachingsystem();
        jcs.cmapcapacity=fsize;
        int key = 0,flag=0;
        char c;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("test2.txt"));
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String key1="",value="";
                int inc=0;
                while(inc!=sCurrentLine.length()){
                    if(flag==1)
                        value +=sCurrentLine.charAt(inc);
                    if(sCurrentLine.charAt(inc) ==' '){
                        key=Integer.parseInt(key1);
                        //System.out.println("how "+key1);
                        flag=1;
                            }
                    if(inc+1==sCurrentLine.length()){
                        flag=0;
                        }
                    if(flag==0)
                        key1 +=sCurrentLine.charAt(inc);
                    inc++;
                    }
                jcs.setvalue(key, value);
            }
        } catch (FileNotFoundException ex) {
               Logger.getLogger(Javacachingsystem.class.getName()).log(Level.SEVERE, null, ex);
        }
	/*String val=jcs.getvalue(0);
	System.out.println(""+val);
	String val2=jcs.getvalue(385);
	System.out.println(""+val2);
        jcs.printdata();*/
    }
}

class Listmap {
	public int key;
        public String value;
	public Listmap previous,next;
	public Listmap(int key, String value) {
		this.value = value;
		this.key = key;
	}
}