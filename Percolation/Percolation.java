/*---------------------------------------------------------
 *  Percolation.java
 *  Author: Aditya Sankar
 *  Course: Introduction to Algorithms, Coursera
 *---------------------------------------------------------*/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
    private int gridSize;
    private boolean[] open;
    private WeightedQuickUnionUF UF;
    private WeightedQuickUnionUF UFBackwash;
    
    // create n-by-n grid, with all sites blocked    
    public Percolation(int n){
         if (n <= 0)
              throw new IllegalArgumentException("Invalid argument");
         
         gridSize = n;
         
         UF = new WeightedQuickUnionUF(n * n + 2);
         UFBackwash = new WeightedQuickUnionUF(n * n + 1);
         
         for(int i = 0; i < n; i++)
         {
              UF.union(0, i + 1); //connect top virtual node
              UF.union(n * n + 1, n * n - i); // connect bottom virtual node 
              
              UFBackwash.union(0, i + 1); //connect top virtual node
         }
         
         open = new boolean[n * n]; //initializes to false
    }
         
    public void open(int row, int col) // open site (row, col) if it is not open already
    {
         validateBounds(row, col);
         
         if(isOpen(row, col))
              return;
              
         setOpen(row, col);
         
         if(checkBounds(row-1, col) && isOpen(row-1, col))
         {
              connect(row, col, row-1, col);
         }
         
         if(checkBounds(row+1, col) && isOpen(row+1, col))
         {
              connect(row, col, row+1, col);
         }
         
         if(checkBounds(row, col+1) && isOpen(row, col+1))
         {
              connect(row, col, row, col+1);
         }
         
         if(checkBounds(row, col-1) && isOpen(row, col-1))
         {
              connect(row, col, row, col-1);
         }
              
    }
    
    private void connect(int row1, int col1, int row2, int col2)
    {
         int UFIndex1 = xyTo1D(row1, col1) + 1; //adding one for virtual site
         int UFIndex2 = xyTo1D(row2, col2) + 1; //adding one for virtual site
         
         UF.union(UFIndex1, UFIndex2);
         UFBackwash.union(UFIndex1, UFIndex2);
    }
    
    public boolean isOpen(int row, int col)    // is site (row, col) open?
    {
         validateBounds(row, col);
         return open[xyTo1D(row, col)];
    }
    
    private void setOpen(int row, int col)    // is site (row, col) open?
    {
         validateBounds(row, col);
         open[xyTo1D(row, col)] = true;
    }
    
    public boolean isFull(int row, int col)// is site (row, col) full?
    {
         return (isOpen(row, col) && UFBackwash.connected(0, xyTo1D(row, col) + 1));
    }  
    
    public boolean percolates() // does the system percolate?
    {
        if(gridSize == 1)
            return isOpen(1,1);
        else
            return UF.connected(0, gridSize * gridSize + 1); //are top and bottom virtual 
                                                        //nodes connected
    }
    
    private int xyTo1D(int row, int col)
    {
         return (row-1)*gridSize + (col-1);
    }
    
    private boolean checkBounds(int row, int col)
    {
         if (row <= 0 || row > gridSize || col <= 0 || col > gridSize) 
              return false;
         else
              return true;
    }
    
    private void validateBounds(int row, int col)
    {
         if (row <= 0 || row > gridSize || col <= 0 || col > gridSize) 
              throw new IndexOutOfBoundsException("row index i out of bounds");
    }
    
    
    public static void main(String[] args){
         Percolation p = new Percolation(3);
         
         p.open(1, 1);
         p.open(1, 2);
         p.open(2, 2);
         p.open(3, 3);
         p.open(2, 3);
         
         StdOut.println(p.isOpen(1,1));
         StdOut.println(p.isOpen(1,2));
         StdOut.println(p.isOpen(2,2));
         StdOut.println(p.isOpen(3,3));
         
         StdOut.println(p.percolates());
    }    // test client (optional)
}