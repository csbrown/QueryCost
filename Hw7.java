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

public class Op {
  public static Op(String op){
    split = op.split("\\s+");
    int this.M = Integer.parseInt(split[0]);
    String this.operation = split[1].toLowerCase();
    String this.r1 = split[2].toLowerCase();
    int this.p1 = Integer.parseInt(split[3]);
    if(split.length > 4){
      String this.r2 = split[4].toLowerCase();
      int this.p2 = Integer.parseInt(split[5]);
    } 
  }
 
  private int hashSet(Relation rel1, Relation rel2){
    int perbucket;
    int cost = 0;
    if(rel1.pages >= this.M && rel2.pages >= this.M){
        perbucket = rel1.pages + rel2.pages;
        while(perbucket > M){
          perbucket = perbucket/M;
          cost += 2*rel1.pages + 2*rel2.pages;
        }
    }
    cost += rel1.pages + rel2.pages
    return cost;
    
  private int sortSet(Relation rel1, Relation rel2){ 
    int cost = 0;
    if(rel1.pages >= this.M && rel2.pages >= this.M){
      while(rel1.stacks >= this.M) cost += rel1.sort(this.M);
      while(rel2.stacks >= this.M) cost += rel2.sort(this.M);
      if(rel1.stacks + rel2.stacks > M) cost += rel1.pages < rel2.pages ? rel1.sort(this.M) : rel2.sort(this.M);
    }
    cost += rel1.pages + rel2.pages;
    return cost; 

  public int run(HashMap<String, Relation> relationmap){
    int cost = 0;
    Relation rel1 = relationmap.get(this.r1);
    //Unary operators
    switch(operation){

      case "select":
        return rel1.pages;

      case "sort":
        while(rel1.stacks > 1) cost += rel1.sort(this.M);
        return cost;
    }


    Relation rel2 = relationmap.get(this.r2);         
    //Binary operators
    switch(operation){
      case "block-nested-join":
        if(rel1.pages < this.M || rel2.pages < this.M) cost = rel1.pages + rel2.pages;
        else{
            files = rel1.pages/(this.M-1);
            cost = rel1.pages + files*rel2.pages;
        }
        return cost;

      case "hash-union":
      case "hash-intersection":
      case "hash-difference":
        return hashSet(rel1, rel2);

      case "sort-merge-join":
      case "sort-difference":
      case "sort-intersection":
      case "sort-union":
        return sortSet(rel1, rel2);

      default:
    }
  }
}

      
    
    

//Put these in a hashmap, pointed to by their name
public class Relation {
  public static Relation(int pages){ 
    int this.pages = pages;
    int this.stacks = pages;
  }
  //returns the cost of the sort
  public void sort(int M){
    this.stacks = Math.ceil(this.stacks*1.0/M);
    return this.pages > M ? this.pages*2 : this.pages;
  }
}

/**
 *
 * @author Libby Rodriguez AND Scott Brown
 */
public class Hw7 {
    
    //returns the cost of a series of operations
    private int performOps(Ops[] ops){
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
                Ops[] oplist= new Ops[ops.length];
                for(int i=0; i < oplist.length; i++)
                  oplist[i] = Op(ops[i].trim());
                

   
        //TODO split over the colon!


                
                //deal with chaining with :
                
                System.out.println("Processing [" + a + "]\n Cost: " + cost + "\n \n");
            }
        
        
        }
        
        
        
    }
    }


}
