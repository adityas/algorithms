import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    
    
    
   public static void main(String[] args)
   {
       int k = Integer.parseInt(args[0]);
       
       RandomizedQueue<String> a = new RandomizedQueue<String>();
       
       while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            a.enqueue(item);
       }
       
       int ctr = 0;
       
       if(k > 0 && k <= a.size())
       {
           for (String s : a)
           {
               StdOut.println(s);
               ctr++;
               
               if (ctr == k)
                   break;
           }
       }
   }
}