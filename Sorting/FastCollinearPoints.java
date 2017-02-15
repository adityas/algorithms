import java.util.*;
import edu.princeton.cs.algs4.*;

public class FastCollinearPoints {
    
    ArrayList<LineSegment> lineSegments;
    
   public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
   {
       if(points == null)
           throw new java.lang.NullPointerException();
       
       lineSegments = new ArrayList<LineSegment>();
       
       for(int p = 0; p < points.length; p++)
       {
           for(int q = 0; q < points.length; q++)
           {
               if(p == q)
                   continue;
               
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
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
}
}