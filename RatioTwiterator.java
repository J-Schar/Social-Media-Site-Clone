import java.util.NoSuchElementException;
import java.util.Iterator;
public class RatioTwiterator implements Iterator<Tweet> {
    private TweetNode next; // the next linked node in the list
    private final double THRESHOLD; // The minimum threshold value for the ratio of likes to total engagement

    //Constructs a new twiterator at the given starting node
    public RatioTwiterator(TweetNode startNode, double threshold) {
        THRESHOLD = threshold;
        if (startNode.getTweet().getLikesRatio() < threshold) {
            startNode = startNode.getNext();
        } else if (startNode.getTweet().getLikesRatio() >= threshold) {
            this.next = startNode;
        }
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
            throw new NoSuchElementException("there is no next tweet with sufficient getLikesRatio");
        }
        return this.next.getTweet();
    }



}
