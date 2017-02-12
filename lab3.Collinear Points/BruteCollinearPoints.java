import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
   private Point[] points;
   private LineSegment[] lineSegments;
   private int length = 0;

   public BruteCollinearPoints(Point[] points) {
     if (points == null || points.length == 0) {
       throw new java.lang.NullPointerException();
     }

     Arrays.sort(points);

     this.points = new Point[points.length];

     for (int i = 0; i < points.length; i++) {
       this.points[i] = points[i];
     }
   }    // finds all line segments containing 4 points
   public int numberOfSegments() {
     return this.length;
   }        // the number of line segments
   public LineSegment[] segments() {
     for (int i = 0; i < points.length - 3; i++) {
       for (int j = i + 1; j < points.length - 2; j++) {
         double slope1 = points[i].slopeTo(points[j]);
         for (int k = j + 1; k < points.length - 1; k++) {
           double slope2 = points[j].slopeTo(points[j]);
           if (slope2 != slope1) {
             continue;
           }
           for (int l = k + 1; l < points.length ; l++) {
             double slope3 = points[k].slopeTo(points[l]);
             if (slope3 != slope2) {
               continue;
             } else {
               LineSegment line = new LineSegment(points[i], points[l]);
               lineSegments[length++] = line;
             }
           }
         }
       }
     }
     return lineSegments;
   }               // the line segments
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
