/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hw7.QueryCost;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Libby
 */
public final class Op {
    
    String operation;
    int M;
    Rel rel1, rel2;
    int cost = 0;
    HashMap<String, Rel> relationmap = new HashMap<>();
    //Op takes an entire line and we need to split over the colon here
    public Op(String op) {
        
        String[] allRelations = op.split(":");
        
        if(allRelations.length > 1){/*A colon was found*/}
        else{/*A colon was not found*/
            String[] split = allRelations[0].split("\\s+");

            this.M = Integer.parseInt(split[0]);
            this.operation = split[1].toLowerCase();

            String r1 = split[2].toLowerCase();
            int p1 = Integer.parseInt(split[3]);
            this.rel1 = relationmap.get(r1);
            if(this.rel1 == null){
              this.rel1 = new Rel(p1);
              relationmap.put(r1, this.rel1); 
            }

            if(split.length > 4){
              String r2 = split[4].toLowerCase();
              int p2 = Integer.parseInt(split[5]);
              this.rel2 = relationmap.get(r2);
              if(this.rel2 == null){
                this.rel2 = new Rel(p2);
                relationmap.put(r2, this.rel2); 
              }
            }
        }

        this.cost = run();

    }
    
    //returns the cost of a series of operations
    private int performOps(Op[] ops){
      Map<String, Rel> relations = new HashMap<>();
      return 0;
    }
    
    private int hashSet(){
        int perbucket;
        if(rel1.pages >= this.M && rel2.pages >= this.M){
            perbucket = rel1.pages + rel2.pages;
            while(perbucket > M){
              perbucket = perbucket/M;
              cost += 2*rel1.pages + 2*rel2.pages;
            }
        }
        cost += rel1.pages + rel2.pages;
        return cost;
    }
    
  private int sortSet(){ 
        if(rel1.pages >= this.M && rel2.pages >= this.M){
          while(rel1.stacks >= this.M) cost += rel1.sort(this.M);
          while(rel2.stacks >= this.M) cost += rel2.sort(this.M);
          if(rel1.stacks + rel2.stacks > M) cost += rel1.pages < rel2.pages ? rel1.sort(this.M) : rel2.sort(this.M);
        }
        cost += rel1.pages + rel2.pages;
        return cost; 
  }

  public int run(){
    //Unary operators
    switch(operation){

      case "select":
        return rel1.pages;

      case "sort":
        while(rel1.stacks > 1) cost += rel1.sort(M);
        return cost;
    
      default:
    }

       
    //Binary operators
    switch(operation){
      case "block-nested-join":
        if(rel1.pages < M || rel2.pages < M) cost = rel1.pages + rel2.pages;
        else{
            cost = rel1.pages + (rel1.pages/(M-1))*rel2.pages;
        }
        return cost;

      case "hash-union":
      case "hash-intersection":
      case "hash-difference":
        return hashSet();

      case "sort-merge-join":
      case "sort-difference":
      case "sort-intersection":
      case "sort-union":
        return sortSet();

      default:
        return 0;
    }
  }

}
