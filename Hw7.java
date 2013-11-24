package hw7.QueryCost;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
Map<Integer, Integer> map = new HashMap<Integer, Integer>(); for (Map.Entry<Integer, Integer> entry : map.entrySet()) { System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); }
*/

/**
 *
 * @author Libby Rodriguez AND Scott Brown
 */
public class Hw7 {
      
      
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String a = null;
        File file = new File(args[0]);
        Scanner fr = null;
        String[] split;
        int M, cost, pages, files, pages2, stacks;
        String operation, relation, relation2;
        try {
            //Read contents of file into ArrayList
            fr = new Scanner(file);            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Hw7.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fr != null){
            while(fr.hasNext()){
                a = fr.next();
                Op o = new Op(a);
                //do something with o?
                
               System.out.println("Processing [" + a + "]\n Cost: " + o.run() + "\n \n");
            }
        }
    }
}


