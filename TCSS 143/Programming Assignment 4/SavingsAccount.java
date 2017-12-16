/*
* SavingsAccount.java
*
* TCSS 143 - Spring 2017
* Instructor: David Schuessler
* Assignment 4
*/

/**
* This class inherits from the BankAccount and then
* sets up a savings account that tracks withdraws or deposits
* and finally stores the information so it can be used later.
*
* @author Armoni Atherton athera@uw.edu
* @version 20 April 2017
*/

public class SavingsAccount extends BankAccount implements NamedAccount {
  /**
  * This makes it to where if you want you can change the amount to
  * have a active savings account to greater or less money, more
  * convenient.
  */
  public static final double SAVINGS_ACCOUNT = 25.0;
  /**
  * This makes it to where if you want you can change the amount of
  * withdraws a account owner can make before charging.
  */
  public static final int WITHDRAW_ALLOWANCE = 5;
  /**
  * Stores whether the savings account is active or not.
  */
  private boolean myStatusIsActive;
  /**
   * This method sets up the constructors correctly for the variables
   * to be used throughout the class. Inherits from the parent class
   * BankAccount to set up constructors.
   *
   * @param theNameOfOwner The incoming (String) name of account owner.
   * @param theIntrestRate The incoming (double) interest rate.
   */
  public SavingsAccount(final String theNameOfOwner,
                        final double theIntrestRate) {
    //Runs it the parameters through the parent class.
    super(theNameOfOwner, theIntrestRate);
    //Sets the account active or not depending on amount of money in it.
    myStatusIsActive = false;
  }
  /**
   * Uses the parent class to determin if deposit was sucesfull or
   * not. Next determins the status of the of the savings account.
   *
   * @param theAmount The incoming (double) deposit amount.
   * @return flag true/false if deposit was vaild or not.
   */
  public boolean processDeposit(final double theAmount) {
    //Determins if the the deposit was sucesfull or not.
    boolean flag = super.processDeposit(theAmount);
    //Determins if savings account is active or not.
    if (getBalance() >= SAVINGS_ACCOUNT) {
      myStatusIsActive = true;
    }
    return flag; //Returns if deoposit was sucesfull or not.
  }
  /**
   * Uses the parent class to determin if withdrawal was sucesfull or
   * not. Next determins the status of the of the savings account.
   *
   * @param theAmount The incoming (double) withdrawal amount.
   * @return flag true/false if withdrawal was vaild or not.
   */
  public boolean processWithdrawal(final double theAmount) {
    //Starts the proccessWithdrawal at false.
    boolean flag = false;
    //If savings account is active process withdrawl.
    if (myStatusIsActive) {
      flag = super.processWithdrawal(theAmount);
    }
    //If balance is less than 25 mark savings account as false.
    if (getBalance() < SAVINGS_ACCOUNT) {
      myStatusIsActive = false;
    }
    //Checks the total amount of withdrawls.
    if (super.myMonthlyWithdrawCount > WITHDRAW_ALLOWANCE) {
      super.myMonthlyServiceCharges += 1;
    }
    return flag; //Return true or false if withdraw was vaild or not.
  }
  /**
   * Uses the parent class to perform mothyly processes to calculate
   * the myMonthlyServiceCharges and calculated intrest. Checks if
   * savings account is active or inactive.
   */
  public void performMonthlyProcess() {
    //Class the parent to perform the methods process
    super.performMonthlyProcess();
    //Determins if the account is still active or not.
    if (getBalance() >= SAVINGS_ACCOUNT) {
      myStatusIsActive = true;
    } else {
      myStatusIsActive = false;
    }
  }
  /**
   * This gives back the savings account information of customer name,
   * account balance, and ect.. as a string so it can be seen visually
   * of current information.
   *
   * @return Formated string of all current banking information
   *         over a month period.
   */
  public String toString() {
    //Returns formated string and overides parent class to add if
    //myStatusIsActive to the end of the string.
    return "SavingsAccount" +
            super.toString().substring(11, super.toString().length() - 1) +
            ", myStatusIsActive: " + myStatusIsActive + "]";
  }
}
