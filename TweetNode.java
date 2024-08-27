//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (descriptive title of the program making use of this file)
// Course:   CS 300 Spring 2023
//
// Author:   Joel Schargorodsky
// Email:    jschargorods@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    None
// Partner Email:   None
// Partner Lecturer's Name: None
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////

public class TweetNode {
    // The next TweetNode in this linked list
    private TweetNode nextTweet;

    // The tweet contained in this node
    private Tweet tweet;

    /**
    * Constructs a singly-linked node containing a tweet
    * @param tweet - the tweet to put in this node
    * @param next - the next tweet in the linked list
    */
    public TweetNode(Tweet tweet, TweetNode next){
        this.tweet = tweet;
        this.nextTweet = next;
    }

    /**
    * Constructs a singly-linked node containing a tweet, with no successor tweet
    * @param tweet - the tweet to put in this node
    */
    public TweetNode(Tweet tweet){
        this.tweet = tweet;
    }

    /**
    * Accesses the tweet in this node
    * @returns the tweet in this node
    */
    public Tweet getTweet(){
        return this.tweet;
    }

    /**
    * Accesses the next TweetNode in the list
    * @returns the successor TweetNode
    */
    public TweetNode getNext(){
        return this.nextTweet;
    }

    /**
    * Links this node to another node
    * @param next - the successor TweetNode (can be null)
    */
    public void setNext(TweetNode next){
        this.nextTweet = next;
    }

}
