import java.util.Iterator;
import java.util.NoSuchElementException;

public class ChronoTwiterator implements Iterator<Tweet> {
    private TweetNode next; // the next linked node in the list

    /**
    * Constructs a new twiterator at the given starting node
    * @param startNode - the node to begin the iteration at
    */
    public ChronoTwiterator(TweetNode startNode) {
        this.next = startNode;
    }

    /**
    * Checks whether there is a next tweet to return
    * @returns true if there is a next tweet, false if the value of next is null
    */
    @Override
    public boolean hasNext(){
        if (this.next != null) {
            return true;
        }
        return false;
    }

    /**
    * Returns the next tweet in the iteration if one exists, and advances next to the next tweet
    * @throws NoSuchElementException - if there is not a next tweet to return
    */
    @Override
    public Tweet next() throws NoSuchElementException{
        if (this.next == null) {
            throw new NoSuchElementException("there is no next tweet");
        }
        return this.next.getTweet();
    }
}
