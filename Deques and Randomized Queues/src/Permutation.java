import edu.princeton.cs.algs4.StdIn;

import java.util.NoSuchElementException;

public class Permutation {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        try {
            for (int i = n; i > 0; i--) {
                String input = StdIn.readString();
                rq.enqueue(input);
            }

            for (int i = n; i > 0; i--) {
                System.out.println(rq.dequeue());

            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchElementException nsee) {
            nsee.printStackTrace();
        }

    }
}