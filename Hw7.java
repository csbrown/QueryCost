/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hw7.QueryCost;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
Map<Integer, Integer> map = new HashMap<Integer, Integer>(); for (Map.Entry<Integer, Integer> entry : map.entrySet()) { System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); }
*/


//Put these in a hashmap, pointed to by their name
public class Relation {
  public static Relation(int pages){ 
    int this.pages = pages;
    int this.stacks = pages;
  }
  //returns the cost of the sort
  public void sort(int M, boolean write){
    this.stacks = Math.ceil(this.stacks*1.0/M);
    return write ? this.pages*2 : this.pages;
  }
}

/**
 *
 * @author Libby Rodriguez AND Scott Brown
 */
public class Hw7 {
    
    //returns the cost of a series of operations
    private int performOps(String[] ops){
      Map<String, Relation> relations = new HashMap<String, Relation>();
      
      
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
                String[] ops = a.split(":");
                cost = getCostMany(ops);

                split = a.split(" ");
                M = split[0].charAt(0) + '0';
                operation = split[1].toLowerCase();
                relation = split[2].toLowerCase();
                pages = Integer.parseInt(split[3]);
                if(split.length > 4){
                  relation2 = split[4].toLowerCase();
                  pages2 = Integer.parseInt(split[5]);
                

        
        //TODO split over the colon!

                switch(operation){
                    case "select":
                        //cost = ;
                        break;
                    case "sort":
                        cost = 0;
                        stacks = pages;
                        while(stacks > 1){
                          cost += sortCost(pages, stacks, M);
                          stacks = sortStacks(stacks, M);
                        }
                        break;
                    case "block-nested-join":
                        relation2 = split[4];
                        pages2 = Integer.parseInt(split[5]);
                        if(pages < M-1 || pages2 < M-1) cost = pages + pages2;
                        else{
                            files = pages/M-1;
                            cost = pages + files*pages2;
                        }
                        break;
                    case "sort-merge-join":
                        relation2 = split[4];
                        pages2 = Integer.parseInt(split[5]);
                        if(pages + pages2 <= M) cost = pages + pages2;
                        else{
                            cost = 2*pages + 2*pages2;
                            if((pages/M + pages2/M) <= M){
                                files = pages/M + pages2/M;
                                cost += files*M;
                            }
                        }
                        break;
                    case "hash-union":
                        relation2 = split[4];
                        pages2 = Integer.parseInt(split[5]);
                        if(pages < M){
                            cost = pages + pages2;
                        }else{
                            files = pages/M;
                            cost = pages + files*pages2;
                        }
                        break;
                    case "hash-intersection":
                        relation2 = split[4];
                        pages2 = Integer.parseInt(split[5]);
                        if(pages <= M){
                            cost = pages + pages2;
                        }else{
                            files = pages/M;
                            cost = pages + files*pages2;
                        }
                        break;
                    case "hash-difference":
                        relation2 = split[4];
                        pages2 = Integer.parseInt(split[5]);
                        if(pages <= M){
                            cost = pages + pages2;
                        }else{
                           files = pages/M;
                           cost = pages + files*pages2;
                        }
                        break;
                    case "sort-difference":
                    case "sort-intersection":
                    case "sort-union":
                        //cost = sortSetCost(pages, pages2, M);
                        break;
                    default:
                }
                
                //deal with chaining with :
                
                System.out.println("Processing [" + a + "]\n Cost: " + cost + "\n \n");
            }
        
        
        }
        
        
        
    }
    }

    private static int getCostMany(String[] ops){
        return 0;
    }
    private static int getCostOne(String op, boolean is_piped){
        
        return 0;
    }




    private static int selectCost(int pages, int M){return pages;}
    private static int sortCost(int pages, int stacks, int M){return stacks<=M ? pages : pages*2;}
    private static int sortStacks(int pages, int M){return (int) Math.ceil(pages*1.0/M);}
    private static int[] sortSetCost(int p1, int p2, int stacks1, int stacks2, int M){
      int cost = p1+p2;
      if(p1 > M && p2 > M){
        while(stacks1>M){
          cost += sortCost(p1, stacks1, M);
          stacks1 = sortStacks(stacks1, M);
        }
        while(stacks2>M){
          cost += sortCost(p2, stacks2, M);
          stacks2 = sortStacks(stacks2,M);
        }
        if(stacks1 + stacks2 > M){
          if(p1 < p2) cost += sortCost(p1, stacks1, M);
          else cost += sortCost(p2, stacks2, M);
        }
      }
      return new int[] {cost, stacks1, stacks2};
    }
   
    public static int sort(int pages, int M){
        int files, cost;
        if(pages <= M) cost = pages;
        else{
            cost = 2*pages;
            files = pages/M;
            if(files <= M) cost += files*M;
            while(files > M){
                cost += 2*pages;
                files = files/M;
            }
            cost += files*M;
        }
        return cost;
    }
}
