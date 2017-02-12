/*
solution:Think of p as the origin.
For each other point q, determine the slope it makes with p.
Sort the points according to the slopes they makes with p.
Check if any 3 (or more) adjacent points in the sorted order
have equal slopes with respect to p. If so, these points,
together with p, are collinear.*/

import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;


public class FastCollinearPoints {
  private Point[] points;
  private LineSegment[] lineSegments;
  private int length = 0;

   public FastCollinearPoints(Point[] points) {
     if (points == null || points.length == 0) {
       throw new java.lang.NullPointerException();
     }

     Arrays.sort(points);

     this.points = new Point[points.length];

     for (int i = 0; i < points.length; i++) {
       this.points[i] = points[i];
     }
   }    // finds all line segments containing 4 or more points
   public int numberOfSegments() {
     return length;
   }       // the number of line segments
   public LineSegment[] segments() {
     for (int i = 0; i < points.length; i++) {
       Point[] tmp = new Point[points.length - 1];
       for (int j = 0, k = 0; j < tmp.length; j++, k++) {
         if (k == i) {
           continue;
         }
         tmp[j++] = points[k++];
       }
       Arrays.sort(tmp, points[i].slopeOrder());

       for (int j = 0; j < tmp.length - 2; j++) {
         for (int k = j + 1; k < tmp.length - 1; k++) {
           double slope1 = tmp[j].slopeTo(tmp[k]);
           for (int l = k + 1; l < tmp.length; l++) {
             double slope2 = tmp[k].slopeTo(tmp[l]);
             if (slope2 != slope1) {
               continue;
             } else {
               LineSegment line = new LineSegment(tmp[i], tmp[l]);
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
     int n = StdIn.readInt();
     Point[] points = new Point[n];
     for (int i = 0; i < n; i++) {
         int x = StdIn.readInt();
         int y = StdIn.readInt();
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
