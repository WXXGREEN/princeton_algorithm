import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class Deque<Item> implements Iterable<Item> {
  private int N;
  private Node<Item> first;
  private Node<Item> last;

  private class Node<Item> {
    Item item;
    Node<Item> previous;
    Node<Item> next;
  }

  public Deque() {
    first = null;
    last = null;
    N = 0;
  }
  public boolean isEmpty() {
    if(N == 0) {
      return true;
    } else {
      return false;
    }
  }
  public int size() {
    return N;
  }
  public void addFirst(Item item) {
    if(item == null) {
      throw new NullPointerException();
    }
    Node<Item> newNode = new Node<Item>();
    newNode.item = item;
    newNode.next = first;
    newNode.previous = null;
    if(isEmpty()) {
      last = newNode;
    } else {
      first.previous = newNode;
    }
    first = newNode;
    N++;
  }
  public void addLast(Item item) {
    if(item == null) {
      throw new java.lang.NullPointerException();
    }
    Node<Item> newNode = new Node<Item>();
    newNode.item = item;
    newNode.previous = last;
    newNode.next = null;
    if(isEmpty()) {
      first = newNode;
    } else {
      last.next = newNode;
    }
    last = newNode;
    N++;
  }
  public Item removeFirst() {
    if(isEmpty()) {
      throw new java.util.NoSuchElementException();
    }
    Item tmp = first.item;
    first = first.next;
    N--;
    if(isEmpty()) {
      last = null;
    } else {
      first.previous = null;
    }
    return tmp;
  }
  public Item removeLast() {
    if(isEmpty()) {
      throw new java.util.NoSuchElementException();
    }
    Item tmp = last.item;
    last = last.previous;
    N--;
    if(isEmpty()) {
      first = null;
    } else {
      last.next = null;
    }
    return tmp;
  }
  public Iterator<Item> iterator() {
    return new DequeIterator<Item>(first);
  }
  private class DequeIterator<Item> implements Iterator<Item> {
    Node<Item> current;
    DequeIterator(Node<Item> first) {
      current=first;
    }
    public boolean hasNext() {
      return current != null;
    }
    public void remove() {
      throw new java.lang.UnsupportedOperationException();
    }
    public Item next() {
      if(!hasNext()) {
        throw new java.util.NoSuchElementException();
      }
      Item item = current.item;
      current = current.next;
      return item;
    }
  }
  public static void main(String[] args) {

  }
}
