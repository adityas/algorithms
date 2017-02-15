import java.util.*;
import edu.princeton.cs.algs4.*;

public class BruteCollinearPoints {
    
    ArrayList<LineSegment> lineSegments;
    
    //To check whether the 4 points p, q, r, and s are collinear, check whether the three slopes between p and q, 
    //between p and r, and between p and s are all equal.
   public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
   {
       if(points == null)
           throw new java.lang.NullPointerException();
       
       lineSegments = new ArrayList<LineSegment>();
       
       for(int p = 0; p < points.length; p++)
       {
           for(int q = 0; q < points.length; q++)
           {
               for(int r = 0; r < points.length; r++)
               {
                   for(int s = 0; s < points.length; s++)
                   {
                       if(!(points[p].compareTo(points[q]) < 0 && points[q].compareTo(points[r]) < 0 && points[r].compareTo(points[s]) < 0))
                           continue;
                       
                       if(points[p] == null || points[q] == null || points[r] == null || points[s] == null)
                           throw new java.lang.NullPointerException();
                       
                       double slope_pq = points[p].slopeTo(points[q]);
                       double slope_pr = points[p].slopeTo(points[r]);
                       double slope_ps = points[p].slopeTo(points[s]);
                       
                       if(slope_pq == Double.NEGATIVE_INFINITY || slope_pr == Double.NEGATIVE_INFINITY || 
                          slope_ps == Double.NEGATIVE_INFINITY)
                           throw new java.lang.IllegalArgumentException();

                       if(slope_pq == slope_pr)
                       {
                           if(slope_pq == slope_ps)
                           {
                               LineSegment l = new LineSegment(points[p], points[s]);
                               lineSegments.add(l);
                           }
                       }
                   }
               }
           }
       }
   }
   
   public int numberOfSegments()        // the number of line segments
   {
       return lineSegments.size();
   }
   
   public LineSegment[] segments()                // the line segments
   {
       return lineSegments.toArray(new LineSegment[0]);
   }
   
   public static void main(String[] args) {

    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
        int x = in.readInt();
        int y = in.readInt();
        points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
        p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
}
}