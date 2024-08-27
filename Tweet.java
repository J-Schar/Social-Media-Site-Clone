import java.util.Calendar;
import java.util.Date;

/**
 * This data type models a tweet - a short text post made on the social media service Twitter.
 */
public class Tweet {
  // A shared Calendar object for this class to use to generate consistent dates.
  // Initialized using the setCalendar() method below.
  // For tests which do not require a consistent date,
  // you can use Calendar.getInstance() to get a Calendar set to the current time.
  // If your tests require a consistent date, see the writeup for examples of how to set the time.
  private static Calendar dateGenerator;
  
  // the User associated with this tweet
  private User user;
  
  // the text of this tweet
  private String text;
  
  // the number of likes this tweet has
  private int numLikes;
  
  //the number of retweets this tweet has
  private int numRetweets;
  
  
  // The date and time this tweet was posted,
  // a Date object created by calling dateGenerator.getTime() at construction time
  private Date timestamp;
  
  // A value determining the maximum length of a tweet. Set to 280 by default.
  private static final int MAX_LENGTH = 280;

  /**
   * Creates a fresh, new tweet by the given user. This tweet has no likes or retweets yet, and its timestamp should be set to the current time.
   */
  public Tweet(User user, String text) throws IllegalArgumentException, NullPointerException, IllegalStateException {
    if (text.length() > MAX_LENGTH) {
      throw new IllegalArgumentException("length of text exceeds character limit");
    } else if (text == null || user == null) {
      throw new NullPointerException("text or user is null");
    } else if (dateGenerator == null) {
      throw new IllegalStateException("dataGenerator has not been initialized");
    }

    this.user = user;
    this.text = text;
    this.timestamp = dateGenerator.getTime();
  }

  /**
   * Initializes the dateGenerator static field to the provided Calendar object. 
   * For tests which do not require a consistent date, you can use Calendar.getInstance() 
   * to get a Calendar set to the current time. If your tests require a consistent date, see the writeup for examples of how to set the time.
   * @param c - the Calendar to use for date generation for this run of the program
   */
  public static void setCalendar(Calendar c) {
    dateGenerator = c;
  }


  /**
   * Accesses the text of this tweet
   * @return the text of this tweet
   */
  public String getText() {
    return text;
  }

  /**
   * Gets the total engagement numbers (likes + retweets) of this tweet
   * @return the total engagement of this tweet
   */
  public int getTotalEngagement() {
    return (numLikes + numRetweets);
  }

  /**
   * Checks whether the author of this tweet is a verified user
   * @return true if this tweet's User is verified, false otherwise
   */
  public boolean isUserVerified() {
    if (user.isVerified() == true) {
      return true;
    }
    return false;
  }

  /**
   * Returns the proportion of the total engagement that is composed of "likes". We only do positive, uplifting ratios around here.
   * the ratio of likes to total engagement , as a value between 0.0 and 1.0, or -1 if the total engagement is 0.
   */
  public double getLikesRatio() {
    return (double) numLikes / getTotalEngagement();
  }

  /**
   * Add one (1) to the number of likes for this tweet
   */
  public void like() {
    numLikes++;
  }

  /**
   * Add one (1) to the number of retweets for this tweet
   */
  public void retweet() {
    numRetweets++;
  }

  /**
   * determines whether two Tweets are the same by comparing user, text, and timestamp.
   * @param o - the object to compare this Tweet to
   * @returns true if this Tweet is equivalent to the provided object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Tweet)) {
      return false;
    } 
    Tweet p = (Tweet) o;
    
    if (!(user.toString().equals(p.user.toString()))) {
      return false;
    } else if (!(text.equals(p.text))) {
      return false;
    } else if (!(timestamp.equals(p.timestamp))) {
      return false;
    }

    return true;
  }

  @Override
  /**
   * A string representation of this tweet.
   * @returns a formatted string representation of this tweet
   */
  public String toString() {
    String strUserAndTimestamp = "tweet from " + user.toString() + " at " + timestamp + ":";
    String strText = "-- " + text;
    String strLikes = "-- likes: " + numLikes;
    String strRetweets = "-- retweets: " + numRetweets; 
    String str = strUserAndTimestamp + "\n" + strText + "\n" + strLikes + "\n" + strRetweets;
    return str;
  }
}
