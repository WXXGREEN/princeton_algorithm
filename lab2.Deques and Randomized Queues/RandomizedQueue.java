import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
   private Item[] a = (Item[]) new Object[1];
   private int N;
   public RandomizedQueue() {
     N = 0;
   }                // construct an empty randomized queue
   public boolean isEmpty() {
     return N == 0;
   }                // is the queue empty?
   public int size() {
     return N;
   }                       // return the number of items on the queue
   private void resize(int max) {
     Item[] temp = (Item[]) new Object[max];
     for(int i = 0; i < a.length; i++) {
       temp[i] = a[i];
     }
     a = temp;
   }
   public void enqueue(Item item) {
     if(item == null) {
       throw new java.lang.NullPointerException();
     }
     if(N == a.length) {
       resize(a.length * 2);
     }
     a[N++] = item;
   }          // add the item
   public Item dequeue() {
     if(isEmpty()) {
       throw new java.util.NoSuchElementException();
     }
     int index = StdRandom.uniform(a.length);
     for(int i = index; i < a.length - 1; i++) {
       a[i] = a[i + 1];
     }
     a[a.length-1] = null;
     N--;
     if(N > 0 && N == a.length / 4) {
       resize(a.length / 2);
     }
     return a[index];
   }                   // remove and return a random item
   public Item sample() {
     if(isEmpty()) {
       throw new java.util.NoSuchElementException();
     }
     int index = StdRandom.uniform(a.length);
     return a[index];
   }                    // return (but do not remove) a random item
   public Iterator<Item> iterator() {
     return new RandomizedQueueIterator();
   }        // return an independent iterator over items in random order
   private class RandomizedQueueIterator implements Iterator<Item> {
     private int copiedN;
     private Item[] copiedArray;
     RandomizedQueueIterator() {
       copiedArray = (Item[]) new Object[N];
       for (int i = 0; i < N; i++) {
           copiedArray[i] = a[i];
       }
       copiedN = N;
     }
     public boolean hasNext() {
       return copiedN > 0;
     }
     public Item next() {
       if(!hasNext()) {
         throw new java.util.NoSuchElementException();
       }
       int index = StdRandom.uniform(copiedN);
       Item item = copiedArray[index];
       copiedArray[index] = copiedArray[copiedN-1];
       copiedArray[copiedN-1] = null;
       copiedN--;
       return item;
     }
     public void remove() {
       throw new java.lang.UnsupportedOperationException();
     }
   }
   public static void main(String[] args) {

   }  // unit testing (optional)
}
