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

/**
 * This class models a Twitter user.
 */
public class User {
  private boolean isVerified; // The verified status of this User (whether they have a blue checkmark or not)

  private String username; // The username this User tweets under
  
  
  /**
   * Constructs a new user with a given username.
   * @param username a String
   */
  public User(String username) {
    if (username == null) {
      throw new IllegalArgumentException("username cannot be null");
    } else if (username.equals("")) {
      throw new IllegalArgumentException("username cannot be blank");
    } else if (username.contains("*")) {
      throw new IllegalArgumentException("username cannot contain *");
    }
    this.username = username;
    this.isVerified = false; // all Users are unverified by default
  }
  
  /**
   * Accesses the username of this User
   * @return the username this User tweets under
   */
  public String getUsername() {
    return username;
  }
  
  /**
   * Determines whether the User is verified or not
   * @return true if the User is verified
   */
  public boolean isVerified() {
    if (username.contains("*")) {
      isVerified = true;
    }
    return isVerified;
  }
   
  /**
   * Gives this User an important-looking asterisk
   */
  public void verify() {
    if (!(username.contains("*"))) {
      username = username + "*";
    }
  }
  
  /**
   * Takes this User's important-looking asterisk away
   */
  public void revokeVerification() {
    if (username.contains("*")) {
      username = username.replace("*", "");
    }
  }
  
  /**
   * Creates a String representation of this User for display. 
   * For example, if this User's username is "uwmadison" and they are verified, 
   * this method will return "@uwmadison*"; if this User's username is "dril" 
   * and they are not verified, this method will return "@dril" (with no asterisk).
   */
  @Override
  public String toString() {
    return ("@" + username);
  }
  
}
