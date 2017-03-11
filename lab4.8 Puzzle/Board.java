import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private int[][] board;
    private int n;
    public Board(int[][] blocks) {
      int n = block.size();
      for (int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
          board[i][j] = blocks[i][j];
        }
      }
      n = board.size();
    }           // construct a board from an n-by-n array of blocks
                                           // (where blocks[i][j] = block in row i, column j)
    public int dimension() {
      return board.size();
    }                // board dimension n
    public int hamming() {
      int cnt = 0;
      int num = 1;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (board[i][j] != num) cnt++;
          num++;
        }
      }
      return cnt;
    }                 // number of blocks out of place
    public int manhattan() {
      int cnt = 0;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (board[i][j] == 0) continue;
          if (board[i][j] == 1) {
            cnt += abs(0 - i) + abs(0 - j);
          } else if (board[i][j] == 2) {
            cnt += abs(0 - i) + abs(1 - j);
          } else if (board[i][j] == 3) {
            cnt += abs(0 - i) + abs(2 - j);
          } else if (board[i][j] == 4) {
            cnt += abs(1 - i) + abs(0 - j);
          } else if (board[i][j] == 5) {
            cnt += abs(1 - i) + abs(1 - j);
          } else if (board[i][j] == 6) {
            cnt += abs(1 - i) + ans(2 - j);
          } else if (board[i][j] == 7) {
            cnt += abs(2 - i) + abs(0 - j);
          } else if (board[i][j] == 8) {
            cnt += abs(2 - i) + abs(1 - j);
          }
        }
      }
      return cnt;
    }               // sum of Manhattan distances between blocks and goal
    public boolean isGoal() {
      Board goal;
      int arr = {{1,2,3},{4,5,6},{7,8}};
      goal.Board(arr);
      return goal.equals(board);
    }               // is this board the goal board?
    public Board twin() {
      while (true) {
        int x1 = Random(0, 2);
        int y1 = Random(0, 2);
        int x2 = Random(0, 2);
        int y2 = Random(0, 2);
        if (x1 == x2 && y1 == y2) continue;
        else break;
      }
      int tmp;
      tmp = board[x1][y1];
      board[x1][y1] = board[x2][y2];
      board[x2][y2] = tmp;
      return board;
    }                   // a board that is obtained by exchanging any pair of blocks
    public boolean equals(Object y) {
      for (int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
          if (y[i][j] != board[i][j]) {
            return false;
          }
        }
      }
      return true;
    }       // does this board equal y?
    public Iterable<Board> neighbors() {

    }    // all neighboring boards
    public String toString()               // string representation of this board (in the output format specified below)

    public static void main(String[] args) // unit tests (not graded)
}
