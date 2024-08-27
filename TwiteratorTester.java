import java.util.Calendar;
import java.util.Date;

public class TwiteratorTester {

    public static boolean testUser() {
        User test = new User("John");
        User testTwo = new User("Rowan");

        // tests that unverified user's name is returned correctly
        {
            String expected = "@John";
            String actual = test.toString();
            System.out.println(actual.toString());
            if (!(actual.equals(expected))) {
                return false;
            }
        }

        // tests that unverified user's name is returned correctly
        {
            String expected = "@John";
            String actual = test.toString();
            System.out.println(actual.toString());
            if (!(actual.equals(expected))) {
                return false;
            }
        }

        // tests that isVerified is false for unverified user
        {
            boolean expected = false;
            boolean actual = test.isVerified();
            if (actual != expected) {
                return false;
            }
        }


        // tests that verified user's name is returned correctly
        {
            String expected = "@Rowan*";
            testTwo.verify();
            System.out.println(testTwo.getUsername());
            String actual = testTwo.toString();
            System.out.println(actual.toString());
            if (!(actual.equals(expected))) {
                return false;
            }
        }

        // tests that isVerified is true for verified user
        {
            testTwo.verify();
            boolean expected = true;
            boolean actual = testTwo.isVerified();
            System.out.println(actual);
            if (actual != expected) {
                return false;
            }
        }

        // tests that user whose verification has been revoked has name returned correctly
        {
            testTwo.revokeVerification();
            String expected = "@Rowan";
            String actual = testTwo.toString();
            System.out.println(actual.toString());
            if (!(actual.equals(expected))) {
                return false;
            }
        }

        return true;
    }

    public static boolean testTweet() {
        User userJacques = new User("Jacques");
        Tweet.setCalendar(Calendar.getInstance());
        Tweet testTweet = new Tweet(userJacques, "Hi, I am Jacques");
        testTweet.like();
        testTweet.retweet();
        testTweet.retweet();

        // tests that the tweet's text is returned correctly
        {
            String expected = "Hi, I am Jacques";
            String actual = testTweet.getText();
            if (!(expected.equals(actual))) {
                return false;
            }
        }

        // tests that isUserVerified returns false for an unverified user
        {
            boolean expected = false;
            boolean actual = testTweet.isUserVerified();
            if (actual != expected) {
                return false;
            }
        }

        // tests that isUserVerified returns true for a verified user
        {
            userJacques.verify();
            boolean expected = true;
            boolean actual = testTweet.isUserVerified();
            if (actual != expected) {
                return false;
            }
        }

        // tests that getTotalEngagement() returns number of likes + number of retweets
        {
            int expected = 3;
            int actual = testTweet.getTotalEngagement();
            if (actual != expected) {
                return false;
            }
        }

        // tests that getLikesRatio() returns number of likes divided by sum of number of numLikes and numRetweets
        {
            double expected = (double) 1/3;
            System.out.println(expected);
            double actual = testTweet.getLikesRatio();
            System.out.println(actual);
            if (actual != expected) {
                return false;
            }
        }


        // tests that equals() returns true for two tweets from same user with same text and timestamp
        User bob = new User("Bob");
        Tweet testBob = new Tweet(bob, "Hi, I am Bob.");

        User bobCopy = new User("Bob");
        Tweet testBobCopy = new Tweet(bob, "Hi, I am Bob.");

        Calendar test = Calendar.getInstance();
        test.set(2023, Calendar.APRIL, 11, 3, 51, 05);
        Tweet.setCalendar(test);

        {
            boolean expected = true;
            boolean actual = testBob.equals(testBobCopy);
            if (actual != expected) {
                return false;
            }
        }

        User bobCopyTwo = new User("Bob");
        Tweet testBobCopyTwo= new Tweet(bob, "Hi, I am not Bob.");

        // tests that equals() returns false for two tweets from same user with different message and same timestamp
        {
            boolean expected = false;
            boolean actual = testBob.equals(testBobCopyTwo);
            if (actual != expected) {
                return false;
            }
        }

        User joe = new User("Joe");
        Tweet testJoe = new Tweet(joe, "Hi, I am Bob.");

        // tests that equals() returns false for two tweets from same different users with same message and timestamp
        {
            boolean expected = false;
            boolean actual = testBob.equals(testJoe);
            if (actual != expected) {
                return false;
            }
        }

        User bobCopyFour = new User("Bob");
        Tweet testBobCopyFour= new Tweet(bob, "Hi, I am Bob.");

        Tweet.setCalendar(Calendar.getInstance());

        // tests that equals() returns false for two tweets from same user with same text and different timestamp
        {
            boolean expected = false;
            boolean actual = testBob.equals(testBobCopyTwo);
            if (actual != expected) {
                return false;
            }
        }


        return true;
    }

    public static boolean testNode() {
        User shao = new User("shao");
        Tweet tweetOne = new Tweet(shao, "hi, I am Shao");
        Tweet tweetTwo = new Tweet(shao, "hi again, I am Shao. please send your regards");
        TweetNode testNodeTwo = new TweetNode(tweetTwo);
        TweetNode testNodeOne = new TweetNode(tweetOne, testNodeTwo);

        // tests if getTweet() in TweetNode works properly
        {
            Tweet expected = tweetOne;
            Tweet actual = testNodeOne.getTweet();
            if (!(actual.equals(expected))) {
                return false;
            }
        }

        // tests if getNext() in TweetNode works properly
        {
            TweetNode expected = testNodeTwo;
            TweetNode actual = testNodeOne.getNext();
            if (!(actual.equals(expected))) {
                return false;
            }
        }
        
        return true;
    }

    public static boolean testAddTweet() {
        User shao = new User("shao");
        Tweet tweetOne = new Tweet(shao, "hi, I am Shao");
        Tweet tweetTwo = new Tweet(shao, "hi again, I am Shao. please send your regards");
        TweetNode testNodeTwo = new TweetNode(tweetTwo);
        TweetNode testNodeOne = new TweetNode(tweetOne, testNodeTwo);
        TwitterFeed testFeed = new TwitterFeed();

        {
            boolean expected = true;
            boolean actual = testFeed.isEmpty();
            if (expected != actual) {
                return false;
            }
        }

        // verifies that TwitterFeed object has size 1 after adding Tweet object to empty TwitterFeed
        testFeed.addFirst(tweetTwo);
        {
            int expected = 1;
            int actual = testFeed.size();
            if (expected != actual) {
                return false;
            }
        }

        // verifies that TwitterFeed object contains added Tweet object
        {
            boolean expected = true;
            boolean actual = testFeed.contains(tweetTwo);
            if (actual != expected) {
                return false;
            }
        }

        // // verifies that get(0) returns Tweet object stored in first node of TwitterFeed
        {
            boolean expected = true;
            Tweet aux = testFeed.get(0);
            boolean actual = tweetTwo.equals(aux);
            if (actual != expected) {
                return false;
            }
        }

        // verifies that TwitterFeed object has size 2 after adding Tweet object to TwitterFeed of size 1
        testFeed.addFirst(tweetOne);
        {
            int expected = 2;
            int actual = testFeed.size();
            if (expected != actual) {
                return false;
            }
        }

        
        return true;
    }

    public static boolean testInsertTweet() {
        return true;
    }
    
    public static boolean testDeleteTweet() {
        return true;
    }

    public static boolean testChronoTwiterator(){
        return true;
    }

    public static boolean testVerifiedTwiterator(){
        return true;
    }

    public static boolean testRatioTwiterator(){
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println("testUser: " + testUser());
        System.out.println("testTweet: " + testTweet());
        System.out.println("testNode: " + testNode());
        System.out.println("testAddTweet: " + testAddTweet());
        System.out.println("testInsertTweet: " + testInsertTweet());
        System.out.println("testDeleteTweet: " + testDeleteTweet());
    }

}
