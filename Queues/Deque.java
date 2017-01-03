import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }
    
    private int size;
    
    private Node first;
    private Node last;
    
   public Deque() // construct an empty deque
   {
       size = 0;
       first = null;
       last = null;
   }
       
   public boolean isEmpty()                 // is the deque empty?
   {
       return size == 0;
   }
   public int size()                        // return the number of items on the deque
   {
       return size;
   }
   public void addFirst(Item item)          // add the item to the front
   {
       if (item == null)
           throw new NullPointerException();
       
       Node oldfirst = first;
       first = new Node();
       first.item = item;
       
       if (oldfirst != null)
       {
           first.next = oldfirst;
           first.previous = null;
           oldfirst.previous = first;
       }
       else
       {
           first.previous = null;
           first.next = null;
           last = first;
       }

       size++;

   }
   public void addLast(Item item)           // add the item to the end
   {
       if (item == null)
           throw new NullPointerException();
       
       Node oldlast = last;
       last = new Node();
       last.item = item;
       
       if (oldlast != null)
       {
           last.previous = oldlast;
           last.next = null;
           oldlast.next = last;
       }
       else
       {
           last.previous = null;
           last.next = null;
           first = last;
       }
       size++;
   }   
   public Item removeFirst()                // remove and return the item from the front
   {
       if (size == 0)
           throw new NoSuchElementException();
       
       if (first == null)
           throw new UnsupportedOperationException();
       
       Node oldfirst = first;
       
       Item item = oldfirst.item;
       first = oldfirst.next;
       
       if (first == null)
           last = null;
       
       //oldfirst = null;
       
       size--;
       
       return item;
   }
   public Item removeLast()                 // remove and return the item from the end
   {
       if (size == 0)
           throw new NoSuchElementException();
       
       if (last == null)
           throw new UnsupportedOperationException();
       
       Node oldlast = last;
       
       Item item = oldlast.item;
       last = oldlast.previous;
       if(last == null)
       {
           first = null;
       }
       //oldlast = null;
       
       size--;
       
       return item;
   }
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   {
       return new ListIterator();
   }
   
   // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    
   public static void main(String[] args) // unit testing
   {
       Deque<String> deque = new Deque<String>();
         deque.addFirst("2");
         StdOut.println(deque.removeLast());
         deque.addFirst("4");
         StdOut.println(deque.removeLast());
         deque.addLast("3");
         
         for (String s : deque)
         {
           StdOut.println(s);
         }
         
         StdOut.println(deque.removeLast());
         deque.addFirst("5");
         StdOut.println(deque.removeLast());
         StdOut.println(deque.removeLast());
         
         
       /*Deque<String> stack = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                stack.addFirst(item);
            else 
                StdOut.print(stack.removeLast() + " ");
        }
        StdOut.println("(" + stack.size() + " left on stack)");*/
   }
}