/*
* LostPuppy.java
*
* TCSS 143 - Spring 2017
* Instructor: David Schuessler
* Assignment 3
*/

import java.util.Random;

/**
* This program will simulate a lost puppy in which the puppy is
* randomly placed in a 2D array. In which the user will input
* numbers to try to find the puppy.
*
* @author Armoni Atherton athera@uw.edu
* @version 15 April 2017
*/

public class LostPuppy {
  /** Stores the 2D array demensions.  */
  private char[][] myHidingPlaces;
  /** Stores the Puppy location for the floor.  */
  private int myFloorLocation;
  /** Stores the Puppy location for the room.  */
  private int myRoomLocation;
  /** Stores the winning player. */
  private char myWinner;
  /** Stores if the puppy has been found.  */
  private boolean myFound;
  /**
   * This method sets up the constructors creates a
   * 2D array placing empty (char)' ', hides the puppy
   * and sets up all the variables correctly.
   *
   * @param theRows User inputed number of rows/floors.
   * @param theColumns User inputed number of Columns/rooms.
   */
  public LostPuppy(int theRows, int theColumns) {
    //Sets up correct demensions of the array inputed by user.
    myHidingPlaces = new char[theRows][theColumns];
    //This sets up 2D array assigning all spots to ' '.
    for (int r = 0; r < theRows; r++) {
      for (int c = 0; c < theColumns; c++) {
        myHidingPlaces[r][c] = ' ';
      }
    }
    Random random = new Random(); // Sets random to a variable.
    //Choses random floor location for the puppy.
    myFloorLocation = random.nextInt(theRows);
    //Choses random room location for the puppy.
    myRoomLocation = random.nextInt(theColumns);
    //Places the puppy into the array.
    myHidingPlaces[myFloorLocation][myRoomLocation] = 'P';
    myWinner = ' '; //Sets up to eventually place player.
    myFound = false; //Sets the puppy to not found.

  }
  /**
   * Checks if the rooms and floors have been entered by either
   * user.
   *
   * @param theGuessFloors User inputed number of guessed floor.
   * @param theGuessRooms User inputed number of guessed room.
   * @return flag is saying whether the floor/room has been searched.
   */
  public boolean roomSearchedAlready(int theGuessFloors,
                                     int theGuessRooms ) {
    boolean flag = true;
    //Checks if the room/floor has been check by either user.
    if (myHidingPlaces[theGuessFloors][theGuessRooms] != 1
        && myHidingPlaces[theGuessFloors][theGuessRooms] != 2) {
          flag = false;
        }
    return flag; //If it has been checked or not.
  }
  /**
   * Checks if the puppy is in the guessed location specfied
   * by the user.
   *
   * @param theGuessFloors User inputed number of guessed floor.
   * @param theGuessRooms User inputed number of guessed room.
   * @return flag is saying whether the floor/room is the puppys
   * location or not.
   */
  public boolean puppyLocation(int theGuessFloors, int theGuessRooms) {
    boolean flag = false;
    //Checks if gueesed numbers are the puppys location.
    if (myHidingPlaces[theGuessFloors][theGuessRooms] == 'P') {
      flag = true;
    }
    return flag; //If user guessed correct location or not.
  }
  /**
   * Checks if the inputed numbers are within the array
   * demensions or not.
   *
   * @param theGuessFloors User inputed number of guessed floor.
   * @param theGuessRooms User inputed number of guessed room.
   * @return flag true/false if within the correct demensions or not.
   */
  public boolean indicesOK(int theGuessFloors, int theGuessRooms) {
    boolean flag = false;
    //Checks if within correct demensions of the array.
    if (theGuessFloors < myHidingPlaces.length &&
        theGuessRooms < myHidingPlaces[0].length) {
        flag = true; //If correct demensions sets to true.
        }
    return flag; //If user inputed correct vaild numbers within
                 //demensions.
  }
  /**
   * Gets the number of floors in the building.
   *
   * @return myHidingPlaces.lenght the number of floors.
   */
  public int numberOfFloors() {
    return myHidingPlaces.length;
  }
  /**
   * Gets the number of rooms in the building.
   *
   * @return myHidingPlaces[0].lenght the number of rooms.
   */
  public int numberOfRooms() {
    return myHidingPlaces[0].length;
  }
  /**
   * Checks the rooms whether they are empyt or have the puppy
   * inside the room. Puts player number inside the room showing
   * it has been searched.
   *
   * @param theGuessFloors User inputed number of guessed floor.
   * @param theGuessRooms User inputed number of guessed room.
   * @param thePlayer Current player 1 or 2.
   * @return flag true if puppy is found, false otherwise.
   */
  public boolean searchRoom(int theGuessFloors, int theGuessRooms,
                            char thePlayer) {
    boolean flag = false;
    //Determins if the puppy is in the guessed room/floor.
    if (myHidingPlaces[theGuessFloors][theGuessRooms] == ' ') {
      myHidingPlaces[theGuessFloors][theGuessRooms] = thePlayer;
      //Determins if the player found the puppy.
    } else if (myHidingPlaces[theGuessFloors][theGuessRooms] == 'P') {
        flag = true; //Flag true means the puppy was found.
        myWinner = thePlayer; //Sets winner to current player.
        myFound = true; //Sets the dog as found.
    }
    return flag;
  }
  /**
   * Shows the building with current update information such as
   * player number within building and location of the puppy
   * when found with players number.
   *
   * @return frame the visual representation of the building.
   */
  public String toString() {
    String frame = "";
    //Builds the top of the frame/building with correct demensions.
    frame += " ___";
    //Builds top middle pieces depending on users demensions entered.
    for (int t = 0; t < myHidingPlaces[0].length - 2; t++) {
      frame += "____";
    }
    //Builds top end pieces depending on users demnsions entered.
    if ((myHidingPlaces[0].length > 1)) {
      frame += "____ ";
    }
    //loop creating visual frame/building with |,_  as rooms/floors.
    for (int r = myHidingPlaces.length - 1; r >= 0; r--) {
      frame += "\n" + "|"; //Puts a | on a new line for rows.
      for (int c = 0; c < myHidingPlaces[0].length; c++) {
        if (puppyLocation(r, c)) {
          if (myFound) {
            //If found puts the winning user with Puppy inside room
            frame += "" + myWinner + 'P' + " ";
          } else {
              frame += "   ";
          }
        } else {
            frame += " " + myHidingPlaces[r][c] + " ";
        }
        frame += "|";
      }
      frame += "\n" + "|";
      //Prints the last part of the column
      for (int j = 0; j <= (myHidingPlaces[0].length - 1); j++) {
        frame += "___|"; //Prints end piece.
      }
    }
    return frame; //Correct visual representaion of building.
  }
}
