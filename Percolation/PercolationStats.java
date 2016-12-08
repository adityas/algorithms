/*---------------------------------------------------------
 *  PercolationStats.java
 *  Author: Aditya Sankar
 *  Course: Introduction to Algorithms, Coursera
 *---------------------------------------------------------*/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
   private double stats[];
   private int numTrials;
   
   // perform trials independent experiments on an n-by-n grid
   public PercolationStats(int n, int trials){
       if (n <= 0 || trials <= 0)
           throw new java.lang.IllegalArgumentException();
       
       numTrials = trials;
       
       stats = new double[trials];
       
       for(int i = 0; i < trials; i++)
       {
           Percolation p = new Percolation(n);
       
           int num_open = 0;
           while(!p.percolates())
           {
               int row = StdRandom.uniform(n)+1;
               int col = StdRandom.uniform(n)+1;
               
               if(p.isOpen(row, col))
                   continue;
               
               p.open(row, col);
               num_open++;
           }
           
           stats[i] = (double) num_open / n / n;
       }
   }
   
   // sample mean of percolation threshold
   public double mean() {
       return StdStats.mean(stats);
   }
   
   // sample standard deviation of percolation threshold
   public double stddev(){
       return StdStats.stddev(stats);
   }
   
   // low  endpoint of 95% confidence interval
   public double confidenceLo(){
       return mean() - 1.96 * stddev() / Math.sqrt(numTrials);
   }
   
   // high endpoint of 95% confidence interval
   public double confidenceHi(){
       return mean() + 1.96 * stddev() / Math.sqrt(numTrials);
   }

   // test client (described below)
   public static void main(String[] args){
       int n = Integer.parseInt(args[0]);
       int trials = Integer.parseInt(args[1]);
       
       PercolationStats ps = new PercolationStats(n, trials);
       StdOut.println("mean                    = " + ps.mean());
       StdOut.println("stddev                  = " + ps.stddev());
       StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
   }
}
