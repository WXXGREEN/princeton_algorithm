import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;
import java.util.List;
import java.util.LinkedList;
import java.util.Deque;


public class Solver {
    private boolean isSolvable;
    private SearchNode solutionNode;
    private MinPQ<SearchNode> pq;

    public Solver(Board initial) {// find a solution to the initial board (using the A* algorithm)
      isSolvable = false;
      pq = new MinPQ<SearchNode>();
      pq.insert(new SearchNode(initial, 0, null));
      int cnt = 0;
      while (!pq.isEmpty()) {
        solutionNode = pq.delMin();
        if (solutionNode.getBoard().isGoal()) {
          isSolvable = true;
          break;
        }
        cnt++;
        for (Board b : solutionNode.getBoard().neighbors()) {
          if (b.equals(solutionNode))
            continue;
          pq.insert(new SearchNode(b, cnt, solutionNode));
        }
      }
    }
    private class SearchNode implements Comparable<SearchNode> {
      private Board board;
      private SearchNode prev;
      private int moves;
      SearchNode(Board board, int moves, SearchNode prev) {
        this.board = board;
        this.moves = moves;
        this.prev = prev;
      }
      @Override
      public int compareTo(SearchNode node) {
        return this.priority() - node.priority();
      }
      public int priority() {
        return board.manhattan() + moves;
      }
      public Board getBoard() {
        return board;
      }
      public int getMoves() {
        return moves;
      }
      public SearchNode getPrev() {
        return prev;
      }
    }
    public boolean isSolvable() { // is the initial board solvable?
      return isSolvable;
    }
    public int moves(){ // min number of moves to solve initial board; -1 if unsolvable
      return isSolvable()? solutionNode.getMoves() : -1;
    }
    public Iterable<Board> solution() {// sequence of boards in a shortest solution; null if unsolvable
      if (!isSolvable)
        return null;
      Deque<Board> solution = new LinkedList<Board>();
      SearchNode node = solutionNode;
      while (node != null) {
        solution.addFirst(node.getBoard());
        node = node.getPrev();
      }
      return solution;
    }
    public static void main(String[] args) {
      // create initial board from file
      In in = new In(args[0]);
      int n = in.readInt();
      int[][] blocks = new int[n][n];
      for (int i = 0; i < n; i++)
          for (int j = 0; j < n; j++)
              blocks[i][j] = in.readInt();
      Board initial = new Board(blocks);
      // solve the puzzle
      Solver solver = new Solver(initial);
      // print solution to standard output
      if (!solver.isSolvable())
          StdOut.println("No solution possible");
      else {
          StdOut.println("Minimum number of moves = " + solver.moves());
          for (Board board : solver.solution())
              StdOut.println(board);
   }
    }
}
