import edu.princeton.cs.algs4.Draw;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.lang.*;
import java.util.*;
/*
 * A 2d-tree is a generalization of a BST to two-dimensional keys.
 * The idea is to build a BST with points in the nodes,
 * using the x- and y-coordinates of the points as keys in strictly alternating sequence.
 */

public class KdTree {
	private Node root;
	private int size;
	private enum Separator {HORIZONTAL, VERTICAL};

	private static class Node {
		private Point2D point;
		private Node leftBottom;
		private Node rightTop;
		private RectHV rect;
		private Separator sepr;
		Node (Point2D p, RectHV r, Separator s) {
			point = p;
			rect = r;
			sepr = s;
		}
		public Separator nextSepr() {
			return sepr == Separator.HORIZONTAL? Separator.VERTICAL: Separator.HORIZONTAL;
		}
		public RectHV splitLB() {//向左或向下划分平面
			return sepr == Separator.VERTICAL?
					new RectHV(rect.xmin(), rect.ymin(), point.x(), rect.ymax()):
					new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), point.y());
		}
		public RectHV splitRT() {//向右或向上划分平面
			return sepr == Separator.HORIZONTAL?
					new RectHV(point.x(), rect.ymin(), rect.xmax(), rect.ymax()):
					new RectHV(rect.xmin(), point.y(), rect.xmax(), rect.ymax());
		}
		public boolean judgeLeftorRight(Point2D q) {//判断当前插入的节点应该插入做子树还是右子树
			return (sepr == Separator.HORIZONTAL && point.y() > q.y())
					|| (sepr == Separator.VERTICAL && point.x() > q.x());
		}
	}

    public KdTree() {// construct an empty set of points
    	root = null;
    	size = 0;
    }
    public boolean isEmpty() {// is the set empty?
    	return size == 0;
    }
    public int size() {// number of points in the set
    	return size;
    }

    public void insert(Point2D p) {// add the point to the set (if it is not already in the set)
    	if (p == null)
    		return;
    	if (root == null) {
    		root = new Node(p, new RectHV(0,0,1,1), Separator.VERTICAL);
    		size++;
    		return;
    	}
    	Node cur = root;
    	Node prev = cur;
    	while (cur != null) {
    		if (cur.point.equals(p))
    			return;
    		prev = cur;
    		cur = cur.judgeLeftorRight(p)? cur.leftBottom : cur.rightTop;
    	}
    	//插入新节点,当前prev指向需待插入节点的父节点
    	if (prev.judgeLeftorRight(p)) {
    		prev.leftBottom = new Node(p, prev.splitLB(), prev.nextSepr());
    	} else {
    		prev.rightTop = new Node(p, prev.splitRT(), prev.nextSepr());
    	}
    	size++;
    	return;
    }
    public boolean contains(Point2D p) {// does the set contain point p?
    	Node cur = root;
    	if (isEmpty()) return false;
    	while (cur != null) {
    		if (cur.point.equals(p)) return true;
    		cur = cur.judgeLeftorRight(p)? cur.leftBottom : cur.rightTop;
    	}
    	return false;
    }
    public void draw() {// draw all points to standard draw

    }
    public Iterable<Point2D> range(RectHV rect) {// all points that are inside the rectangle
    	List<Point2D> res = new ArrayList<Point2D>();
    	dfsHelper(root, rect, res);
    	return res;
    }
    private void dfsHelper(Node p, RectHV rect, List<Point2D> result) {
    	if (p == null)
    		return;
    	if (rect.contains(p.point)) {
    		result.add(p.point);
    		dfsHelper(p.leftBottom, rect, result);
    		dfsHelper(p.rightTop, rect, result);
    		return;
    	}
    	//如果矩形没有包含当前点，则检查矩形是否可能包含当前点的左子树或右子树
    	//矩形左下角比当前点的x值大或y值大，则有可能包含左子树的点
    	if (p.judgeLeftorRight(new Point2D(rect.xmin(), rect.ymin()))) {
    		dfsHelper(p.leftBottom, rect, result);
    	}
    	//如果矩形的最大点都没有包含做子树，则只能在右子树范围内找
    	if (!p.judgeLeftorRight(new Point2D(rect.xmax(), rect.ymax()))) {
    		dfsHelper(p.rightTop, rect, result);
    	}
    }
    public Point2D nearest(Point2D p) {// a nearest neighbor in the set to point p; null if the set is empty
			if (p == null) return null;
			return isEmpty()? null : nearest(p, root.point, root);
    }
		private Point2D nearest(Point2D target, Point2D closest, Node node) {
			if (node == null)
			  return closest;
			//递归查找左子树和右子树，对于可能包含最近邻的点
			double closDist = closest.distanceTo(target);
			if (node.rect.distanceTo(target) < closDist) {
				double nodeDist = node.point.distanceTo(target);
				if (nodeDist < closDist) {
					closest = node.point;
				}
			}
			if (node.judgeLeftorRight(target)) {
				closest = nearest(target, closest, node.leftBottom);
				closest = nearest(target, closest, node.rightTop);
			} else {
				closest = nearest(target, closest, node.rightTop);
				closest = nearest(target, closest, node.leftBottom);
			}
			return closest;
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
