import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
   
    private Item[] a;         // array of items
    private int n;            // number of elements on stack
   
    public RandomizedQueue()                 // construct an empty randomized queue
    {
        a = (Item[]) new Object[2];
        n = 0;
    }
    
    public boolean isEmpty()                 // is the queue empty?
    { return n == 0; }
    
    public int size()                        // return the number of items on the queue
    {
        return n;
    }
    
        // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;

        // textbook implementation
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;

       // alternative implementation
       // a = java.util.Arrays.copyOf(a, capacity);
    }

    
    public void enqueue(Item item)           // add the item
    {
        if (item == null)
           throw new NullPointerException();
               
        if (n == a.length) resize(2*a.length);    // double size of array if necessary
        a[n++] = item;                            // add item
    }
    
   public Item dequeue()                    // remove and return a random item
   {
       if (isEmpty()) throw new NoSuchElementException("Stack underflow");
       int index = StdRandom.uniform(n);
       
       Item item = a[index];
       
       a[index] = a[n-1];
       a[n-1] = null;
       n--;
       
       if (n > 0 && n == a.length/4) resize(a.length/2);
       
       return item;
   }
   public Item sample()                     // return (but do not remove) a random item
   {
       if (isEmpty()) throw new NoSuchElementException("Stack underflow");
       
       return a[StdRandom.uniform(n)];
   }
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   {
       return new RandomArrayIterator();
   }
   
    // an iterator, doesn't implement remove() since it's optional
    private class RandomArrayIterator implements Iterator<Item> {
        private int i;
        
        public RandomArrayIterator() {
            StdRandom.shuffle(a, 0, n-1);
            i = n-1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
               
            return a[i--];
        }
    }
   public static void main(String[] args)   // unit testing
   {
       RandomizedQueue<String> stack = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                stack.enqueue(item);
            else 
                StdOut.print(stack.dequeue() + " ");
        }
        StdOut.println("(" + stack.size() + " left on stack)");
   }
}