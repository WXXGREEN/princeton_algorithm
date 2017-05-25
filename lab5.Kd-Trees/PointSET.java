import java.util.TreeSet;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Draw;
import java.util.*;
import java.lang.*;

/*Brute-force implementation
 */
public class PointSET {
	TreeSet<Point2D> treeSet;
    public PointSET() {// construct an empty set of points
    	treeSet = new TreeSet<Point2D>();
    }
    public boolean isEmpty() {// is the set empty? 
    	return size() == 0 ? true: false;
    }
    public int size() {// number of points in the set
    	return treeSet.size();
    }
    public void insert(Point2D p) {// add the point to the set (if it is not already in the set)
    	if (treeSet.contains(p)) return;
    	treeSet.add(p);
    }
    public boolean contains(Point2D p) {// does the set contain point p? 
    	return treeSet.contains(p);
    }
    public void draw() {// draw all points to standard draw 
    	
    }
    public Iterable<Point2D> range(RectHV rect) {// all points that are inside the rectangle
    	List<Point2D> ret = new LinkedList<Point2D>();
    	for (Point2D p : treeSet) {
    		if (rect.contains(p))
    			ret.add(p);
    	}
    	return ret;
    }
    public Point2D nearest(Point2D p) {// a nearest neighbor in the set to point p; null if the set is empty 
    	if (size() == 0)
    		return null;
    	double x = 0.0, y = 0.0;
    	double min = Double.MAX_VALUE;
    	for (Point2D tmp : treeSet) {
    		if (p.distanceTo(tmp) < min) {
    			x = tmp.x();
    			y = tmp.y();
    		}
    	}
    	return new Point2D(x, y);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
