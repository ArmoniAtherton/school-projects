/*
* BankAccount.java
*
* TCSS 143 - Spring 2017
* Instructor: David Schuessler
* Assignment 4
*/

/**
* This class takes information from the driver class and then
* sets up a bank account that tracks withdraws or deposits
* and finally stores the information so it can be used later.
*
* @author Armoni Atherton athera@uw.edu
* @version 20 April 2017
*/
public class BankAccount implements NamedAccount {
  /**
  * This makes it to where if you want you can change it
  * instead of years but by monthly if desired.
  */
  public static final double MONTHS = 12.0;
  /**
  * Stores the Customers name.
  */
  private String myCustomerName;
  /**
  * Stores the Account balance.
  */
  private double myAccountBalance;
  /**
  * Stores the Intrest rate.
  */
  private double myIntrestRate;
  /**
  * Stores the Monthly Withdraw count(Since protected
  * it is accessible within the class and subclases).
  */
  protected int myMonthlyWithdrawCount;
  /**
  * Stores the Monthly Service charges(Since protected
  * it is accessible within the class and subclases).
  */
  protected double myMonthlyServiceCharges;
  /**
   * This method sets up the constructors
   * correctly for the variables to be used throughout the
   * class and some subclasses.
   *
   * @param theNameOfOwner The incoming (String) name of account owner.
   * @param theIntrestRate The incoming (double) interest rate.
   */
  public BankAccount(final String theNameOfOwner,
                     final double theIntrestRate) {
    //Sets up the users name.
    myCustomerName = theNameOfOwner;
    //Makes sure that the intrest is a vaild value.
    if (theIntrestRate >= 0.0) {
      myIntrestRate = theIntrestRate;
    } else {
      myIntrestRate = 0.0;
    }
    myAccountBalance = 0.0; //Sets up the account balance.
    myMonthlyWithdrawCount = 0; //Sets up the Withdraw count.
    myMonthlyServiceCharges = 0.0; // Sets up the service charges.
  }
  /**
   * This method gets the account balance as a double.
   *
   * @return myAccountBalance sends back the current account balance.
   */
  public double getBalance() {
    return myAccountBalance;
  }
  /**
   * This method deposits money and only allows
   * vaild values.
   *
   * @param theAmount The incoming (double) amount of money to deposit.
   * @return flag true/false if deposit was vaild or not.
   */
  public boolean processDeposit(final double theAmount) {
    boolean flag = false; //Starts not vaild
    //Adds to Account Balance if not negative.
    if (theAmount >= 0.0) {
      myAccountBalance += theAmount;
      flag = true; //Sets true if vaild amount entered.
    }
    return flag; //Returns true or false if deposit was sucesfull.
  }
  /**
   * This method withdraws money and only allows
   * vaild values to be withdrawed depending on your
   * account balance.
   *
   * @param theAmount The incoming (double) amount of money to withdraw.
   * @return flag true/false if withdraw was vaild or not.
   */
  public boolean processWithdrawal(final double theAmount) {
    boolean flag = true;
    //Doesnt withdraw if negative number or greater than account bal.
    if (theAmount < 0.0 || theAmount > myAccountBalance ) {
      flag = false;
    //Does withdraw if positive number or less than account balance.
    } else {
        myAccountBalance -= theAmount;
        myMonthlyWithdrawCount++; //Keeps count of sucesfull withdraws.
    }
    return flag; //Returns true or false if withdrawl was sucesfull.
  }
  /**
   * This method calculates the intrest of account owner.
   *
   * @return the interest rate of the account.
   */
  public double calculateInterest() {
    //Returns monthly intrest.
    return myAccountBalance * (myIntrestRate / MONTHS);
  }
  /**
   * This Subtracts all monthly service charges from the balance,
   * adds the monthly interest rate to the balance through a call
   * to calculateInterest. Resets the withdraw count and service
   * charges.
   */
  public void performMonthlyProcess() {
    //Takes balance minuses the monthyly service charges.
    myAccountBalance -= myMonthlyServiceCharges;
    //Takes new account balance and calculates interest.
    myAccountBalance += calculateInterest();
    //Resets the values bellow to zero.
    if (myAccountBalance < 0.0) {
      myAccountBalance = 0.0;
    }
    //Resets vaules to zero
    myMonthlyWithdrawCount = 0;
    myMonthlyServiceCharges = 0.0;
  }
  /**
   * Gets the account holders name to be used for later
   * refrences.
   *
   * @return myCustomerName gives the account holders name.
   */
  public String getAccountHolderName() {
    //returns the customer name.
    return myCustomerName;
  }
  /**
   * Changes the account holder name for later refrences.
   *
   * @param theNewName holds the name the user wants to change to.
   */
  public void setAccountHolderName(final String theNewName) {
    //Sets the customer name to the new name to be used.
    myCustomerName = theNewName;
  }
  /**
   * This gives back the information of customer name, account balance,
   * and ect.. as a string so it can be seen visually of current
   * information.
   *
   * @return String formated string of all current banking information
   *         over a month period.
   */
  public String toString() {
    //returns a fully formated banking information in a organized fashion.
    return String.format("BankAccount[owner: %1$s, balance: " +
            "%2$,.2f, interest rate: %3$,.2f, \n\tnumber of withdrawals" +
            "this month: %4$d, service charges for this month: %5$,.2f]",
            myCustomerName, myAccountBalance, myIntrestRate,
            myMonthlyWithdrawCount, myMonthlyServiceCharges);
  }
}
