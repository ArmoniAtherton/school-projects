/*
* NamedAccount.java
*
* TCSS 143 - Spring 2017
* Instructor: David Schuessler
* Assignment 4
*/

/**
* This program is the interface for all the programs written
* that implements the interface and gives them a skeleton blueprint
* of what methods the programs should have.
*
* @author Armoni Atherton athera@uw.edu
* @version 20 April 2017
*/
public interface NamedAccount {
  /**
   * Every program the implements interface must have this method
   * in which gets the users name.
   *
   * @return the account holders name.
   */
  String getAccountHolderName();
  /**
   * Every program the implements interface must have this method
   * in which sets the account holders name and assign it the new name.
   *
   * @param theNewName Contains the new name of the account holder.
   */
  void setAccountHolderName(final String theNewName);
}
