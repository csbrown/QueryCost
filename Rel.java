/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hw7.QueryCost;

/**
 *
 * @author Libby
 */
public class Rel {
    //Put these in a hashmap, pointed to by their name
    int pages, stacks;
  public Rel(int pages){ 
    this.pages = pages;
    this.stacks = pages;
  }
  //returns the cost of the sort
  public int sort(int M){
    this.stacks = (int) Math.ceil(this.stacks*1.0/M);
    if(this.pages > M) return this.pages*2;
    else return this.pages;
  }
}
