import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwitterFeed implements ListADT<Tweet>, Iterable<Tweet>{

// The node containing the most recent tweet
private TweetNode head;

// The iteration mode for the timeline display
private TimelineMode mode;

// The ratio of likes to total engagement that we want to see; set to .5 by default
private static double ratio = .5;

// The number of tweets in this linked list
private int size;

// The node containing the oldest tweet
private TweetNode tail;


/**
* Default constructor; creates an empty TwitterFeed by
* setting all data fields to their default values,
* and the timeline mode to CHRONOLOGICAL.
*/
public TwitterFeed(){
    this.head = null;
    this.mode = TimelineMode.CHRONOLOGICAL;
    this.size = 0;
    this.tail = null;
}

/**
* Adds the given Tweet to a specified position in the linked list
* @param index - the position at which to add the new Tweet
* @param newObject - the Tweet to add
* @throws IndexOutOfBoundsException - if the index is negative or greater than the size of the linked list
*/
public void add(int index, Tweet newObject){
    if (index < 0 || index > this.size) {
        throw new IndexOutOfBoundsException("index provided is negative or greater than size of linked list");
    }
    if (index == 0) {
        this.addFirst(newObject);
    } else if (index == this.size) {
        this.addLast(newObject);
    } else {
        this.size++;
    }
    
}

/**
* Adds the given Tweet to the head of the linked list
* @param newObject - the Tweet to add
*/
public void addFirst(Tweet newObject){
    if (this.size == 0) {
        this.head = new TweetNode(newObject);
        this.tail = this.head;
        this.size++;
    }
    this.head = new TweetNode(newObject);
    this.size++;
}

/**
* Adds the given Tweet to the tail of the linked list
* @param newObject - the Tweet to add
*/
public void addLast(Tweet newObject){
    if (this.size == 0) {
        this.tail = new TweetNode(newObject);
        this.head = this.tail;
    }
    this.tail = new TweetNode(newObject);
    this.size++;
}

/**
* Determines whether a given Tweet is present in the TwitterFeed.
* @param findObject - the Tweet to search for
* @returns true if the Tweet is present, false otherwise
*/
public boolean contains(Tweet findObject){
    for (Tweet t : this) {
        if (t .equals(findObject)) {
            return true;
        }
    }
    return false;
}

/**
* Removes and returns the Tweet at the given index
* @param index - the position of the Tweet to remove
* @returns the Tweet object that was removed from the list
* @throws IndexOutOfBoundsException - if the index is negative or greater than the largest index currently present in the linked list
*/
public Tweet delete(int index){
    if (index < 0 || index > this.size) {
        throw new IndexOutOfBoundsException("index is negative or greater than largest index in list");
    } else if (this.size == 1) {
        this.head = null;
        this.tail = this.head;
        this.size--;
    }  else if (index == 0) {
        TweetNode aux = this.head;
        this.head = head.getNext();
        this.size--;
        return aux.getTweet();

    } else if (index == this.size) {

        int temp = 0;
        TweetNode aux = this.head;
        while (temp < this.size - 2) {
            aux = aux.getNext();
        }
        this.tail = aux.getNext();
        this.size--;
    }
    
    return null;
    
}

/** 
* Accessor method for the Tweet at a given index
* @param index - the index of the Tweet in question
* @returns the Tweet object at that index (NOT its TweetNode!)
* @throws IndexOutOfBoundsException - if the index is negative or greater than the largest index of the TwitterFeed
*/
public Tweet get(int index){
    if (index < 0 || index > this.size()) {
        throw new IndexOutOfBoundsException("index is negative or bigger than largest index");
    }
    Tweet aux = null;
    int temp = -1;
    for (Tweet t : this) {
        temp++;
        if (temp == index) {
            aux = t;
        }
    }
    return aux;
}

/**
* Accessor method for the first Tweet in the TwitterFeed
* @returns the Tweet object at the head of the linked list
* @throws NoSuchElementException - if the TwitterFeed is empty
*/
public Tweet getHead(){
    if (this.size == 0) {
        throw new NoSuchElementException("TwitterFeed is empty");
    }
    return head.getTweet();
}

/**
* Accessor method for the last Tweet in the TwitterFeed
* @returns the Tweet object at the tail of the linked list
* @throws NoSuchElementException - if the TwitterFeed is empty
*/
public Tweet getTail(){
    if (this.size == 0) {
        throw new NoSuchElementException("TwitterFeed is empty");
    }
    return tail.getTweet();
}

/**
* Accessor method for the index of a given Tweet in the TwitterFeed.
* @param findObject - the Tweet to search for
* @returns the index of the Tweet in the TwitterFeed if present, -1 if not
*/
public int indexOf(Tweet findObject){
    int temp = -1;
    for (Tweet t : this) {
        temp++;
        if (t.equals(findObject)) {
            return temp;
        }
    }
    return -1;
}

/**
* Determines whether this feed is empty.
* @returns true if there are NO TweetNodes in this TwitterFeed, false otherwise
*/
public boolean isEmpty(){
    if (this.size == 0) {
        return true;
    }
    return false;
}

/**
* Creates and returns the correct type of iterator based on the current mode of this TwitterFeed
* @returns
*/
@Override
public Iterator<Tweet> iterator(){
    if (this.mode == TimelineMode.CHRONOLOGICAL) {
        return new ChronoTwiterator(this.head);
    } else if (this.mode == TimelineMode.VERIFIED_ONLY) {
        return new VerifiedTwiterator(this.head);
    } else if (this.mode == TimelineMode.LIKE_RATIO) {
        return new RatioTwiterator(head, ratio);
    }
    return null;
}

/**
* Sets the iteration mode of this TwitterFeed
* @param m - the iteration mode; any value from the TimelineMode enum
*/
public void setMode(TimelineMode m){
    this.mode = m;
}

/**
* Accessor for the size of the feed
* @returns the number of TweetNodes in this TwitterFeed
*/
public int size(){
    return this.size;
}



}
