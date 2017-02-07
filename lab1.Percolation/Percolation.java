import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private boolean[][] gridOpened;
    private int top = 0;
    private int bottom;
    private WeightedQuickUnionUF uf;

    public Percolation(int n) {
        this.n = n;
        gridOpened = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2);
        bottom = n * n + 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                gridOpened[i][j] = false;
            }
        }
    }

    private int xyTo1D(int row, int col) {
        return (row - 1) * n + col;
    }

    public void open(int row, int col) {
        gridOpened[row - 1][col - 1] = true;
        if (row == 1) {
            uf.union(xyTo1D(row, col), top);
        }
        if (row == n) {
            uf.union(xyTo1D(row, col), bottom);
        }
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }
        if (row < this.n && isOpen(row + 1, col)) {
            uf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }
        if (col < this.n && isOpen(row, col + 1)) {
            uf.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        if (row >= 1 && row <= n && col >= 1 && row <= n) {
            return gridOpened[row - 1][col - 1];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean isFull(int row, int col) {
        if (row >= 1 && row <= n && col >= 1 && row <= n) {
                return this.isOpen(row, col) && uf.connected(xyTo1D(row, col), top);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int numberOfOpenSites() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (gridOpened[i][j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public boolean percolates() {
        if (n == 1) {
            return this.isOpen(1, 1);
        }
        return uf.connected(top, bottom);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }

}
