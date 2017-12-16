/*
* SafeDepositBoxAccount.java
*
* TCSS 143 - Spring 2017
* Instructor: David Schuessler
* Assignment 4
*/

/**
* This class implements from the NamedAccount and then
* sets up a SafeDepositBoxAccount account that tracks account holders name
* and finally replaces the old account holder name with a new one.
*
* @author Armoni Atherton athera@uw.edu
* @version 20 April 2017
*/
public class SafeDepositBoxAccount implements NamedAccount {
  /**
  * Stores the Account Name.
  */
  private String myAccountName;
  /**
  * This sets up the constructor to be able to access the
  * account holders name.
  *
  * @param theNameOfOwner the incoming (String) of account owners name.
  */
  public SafeDepositBoxAccount(final String theNameOfOwner) {
    myAccountName = theNameOfOwner;
  }
  /**
   * Gets the account holders name to be used for later
   * refrences.
   *
   * @return getAccountHolderName gives the account holders name.
   */
  public String getAccountHolderName() {
    return myAccountName;
  }
  /**
   * Changes the account holder name for later refrences.
   *
   * @param theNewName holds the name the user wants to change to.
   */
  public void setAccountHolderName(final String theNewName) {
    //Changes the old name to the new one.
    myAccountName = theNewName;
  }
  /**
   * This gives back the information of customer SafeDepositBoxAccount
   * name in a string.
   *
   * @return (String) Formated string with the owners name of the
   *         safe deposit box.
   */
  public String toString() {
    //Returns formated string of owner of the SafeDepositBoxAccount name.
    return "SafeDepositBoxAccount[owner: " + myAccountName + "]";
  }
}
