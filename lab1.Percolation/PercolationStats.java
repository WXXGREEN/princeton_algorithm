import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class PercolationStats {

    private double mean;
    private double stddev;
    private int trials;
    private double[] threshold;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Given n <= 0 || trials <= 0");
        }
        this.trials = trials;
        threshold = new double[trials];
        int i = 0;
        Percolation percolate;
        while (trials > 0) {
            percolate = new Percolation(n);
            int row = 0;
            int col = 0;
            while (!percolate.percolates()) {
                row = StdRandom.uniform(1, n + 1);
                col = StdRandom.uniform(1, n + 1);
                if (!percolate.isOpen(row, col)) {
                    percolate.open(row, col);
                }
            }
            threshold[i++] = (double) percolate.numberOfOpenSites() / (n * n);
            trials--;
        }

    }

    public double mean() {
        mean = StdStats.mean(threshold);
        return mean;
    }

    public double stddev() {
        stddev = StdStats.stddev(threshold);
        return stddev;
    }

    public double confidenceLo() {
        return this.mean - ((1.96 * this.stddev) / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return this.mean + ((1.96 * this.stddev) / Math.sqrt(trials));
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] array = StdIn.readAllInts();
        PercolationStats stats = new PercolationStats(array[0], array[1]);
        // PercolationStats stats=new PercolationStats(200,100);
        StdOut.println("mean = " + stats.mean());
        StdOut.println("stddev = " + stats.stddev());
        StdOut.println("95% confidence interval = " + stats.confidenceLo() + "," + stats.confidenceHi());
    }

}
