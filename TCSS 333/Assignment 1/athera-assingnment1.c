/*
 * athera-assignment1.c
 *
 * Armoni Atherton
 * TCSS 333 - Winter 2018
 * Instructor: David Schuessler
 * Assignment 1
 */

#include <stdio.h>
#include <math.h>

/*****Function Prototypes*****/

/* Prints all the values, calls other functions. */
void applyBinaryMath(char [], int, char,  char [], int);

/* Converts from binary to float. */
double binaryToFloat(char [], int);

/* Converts the binary decmial part to a double value. */
double getDecimal(char [], int);

/* Applies the operation to the values specified by the user. */
double applyOperation(double, double, char);

/* Converts from float to binary. */
void floatToBinary(double, int);

/* Converts the integer part to a double value. */
void doCalculations(double, int);

/* Converts the decimal part to a binary value. */
void dofloatCalculations(double);

/* Clears the array that is sent into it. */
void clearBinaryString(char [], int);

/*
 * This will set up all the variables and will take input from the user.
 * Will check for if the user wants to continue or exit the program.
 * Call other functions to do the conversions.
 */
int main() {
  char firstBinaryNum[50] = {'\0'};
  char secondBinaryNum[50] = {'\0'};
  char operator;
  int boolean = 1;
  //Executes continously until user enters 'Q' or 'q'
  do {
    printf("Enter an expression using binary numbers or Q to quit: ");
    scanf("%s ", firstBinaryNum);
    //Checks to continue program
    if (firstBinaryNum[0] != 'q' && firstBinaryNum[0] != 'Q') {
      scanf("%c %s", &operator, secondBinaryNum);
      applyBinaryMath(firstBinaryNum, 50, operator, secondBinaryNum, 50);
      clearBinaryString(firstBinaryNum, 50);
      clearBinaryString(secondBinaryNum, 50);
    } else {
        //Will end the loop/program.
        boolean = 0;
    }
  } while (boolean);
  return 0;
}

/*
 * Will call other functions to convert binary to decimal and will
 * display the values one converted to float and back to binary.
 *
 */
void applyBinaryMath(char theNum1[], int theNum1Size, char theOperator,
                   char theNum2[], int theNum2Size) {
  double n1 = binaryToFloat(theNum1, theNum1Size);
  double n2 = binaryToFloat(theNum2, theNum2Size);
  double total = applyOperation(n1, n2, theOperator);
  printf("%.10f %c %.10f = %.10f\n", n1, theOperator, n2, total);
  printf("%s %c %s = ", theNum1, theOperator, theNum2);
  floatToBinary(total, theNum1Size);
  printf("\n");
}

/*
 * This will do the integer conversion for the binary numbers
 * and call another function to do the decimal conversion.
 * Will return the converted number.
 */
double binaryToFloat(char theNumber[], int size) {
  double total = 0;
  int boolean = 0;
  int cnt = 0;
  int i;
  for(i = size; i >= 0; i--) {
    if (theNumber[i] == '.' || boolean) {
      boolean = 1;
      if (theNumber[i] == '1') {
        total += pow(2, cnt);
        cnt++;
      } else if (theNumber[i] == '0') {
        cnt++;
      }
    }
  }
  //Adds to the integer value the decimal value.
  total += getDecimal(theNumber, size);
  return total;
}

/*
 * This will do the decimal conversion for the binary number and
 * return the converted value.
 */
double getDecimal(char theNumber[], int size) {
  double total = 0;
  int boolean = 0;
  int cnt = 0;
  int i;
  for(i = 0; i < size; i++) {
    if (theNumber[i] == '.' || boolean) {
      boolean = 1;
      if (theNumber[i] == '1') {
        cnt--;
        total += pow(2, cnt);
      } else if (theNumber[i] == '0') {
        cnt--;
      }
    }
  }
  return total;
}

/*
 * This will take the two numbers and operator the user entered. Will
 * apply the correct operation and return the total.
 */
double applyOperation(double theFirstNumber,
                      double theSecondNumber,
                      char theOperator) {
  double total;
  if (theOperator == '*') {
    total = theFirstNumber * theSecondNumber;
  } else if (theOperator == '/') {
      total = theFirstNumber / theSecondNumber;
  } else if (theOperator == '-') {
      total = theFirstNumber - theSecondNumber;
  } else {
      total = theFirstNumber + theSecondNumber;
  }
  return total;
}

/*
 * This will take the number and find the power the number can go into.
 * Will call other methods to to do the conversion from float to binary.
 */
void floatToBinary(double theNumber, int theSize) {
  int power = 0;
  int cnt = 0;
  int boolean = 1;
  while (boolean) {
    power = pow(2, cnt);
    cnt++;
    if (theNumber < power) {
      boolean = 0;
      doCalculations(theNumber, power);
    }
  }
}

/*
 * This will do the calculations for the integer part of converting from
 * float to binary. Will print out integer binary numbers. Finally Will
 * call other functions to do the calulations for the decimal conversion.
 */
void doCalculations(double theNumber, int thePower) {
  double currentNumber = theNumber;
  int currentPower = thePower;
  int boolean = 1;
  //Adds zero to front if no integer part.
  if (currentPower == 1) {
    printf("0.");
    //adds zero to end if no decimal part.
    if (currentNumber == 0.0) {
      printf("0");
    }
    boolean = 0;
  }
  while (boolean) {
    currentPower = currentPower / 2;
    if (currentPower == 0) {
      printf(".");
      boolean = 0;
      //After integer adds zero to end if no decimal part.
      if (currentNumber == 0.0) {
        printf("0");
      }
    } else if (currentNumber - currentPower >= 0.0) {
        currentNumber = currentNumber - currentPower;
        printf("1");
    } else {
        printf("0");
    }
  }
  dofloatCalculations(currentNumber);
}

/*
 * This will do the conversion of the decimal and will print the value of
 * it into binary.
 */
void dofloatCalculations(double theIncomingNumber) {
  double currentNumber = theIncomingNumber;
  double currentPower;
  int cnt = 0;
  int exponent = -1;
  int boolean = 1;
  while (boolean && cnt <= 20) {
    cnt++;
    currentPower = pow(2, exponent);
    exponent--;
    if (currentNumber == 0) {
      boolean = 0;
    } else if (currentNumber - currentPower >= 0.0) {
        currentNumber = currentNumber - currentPower;
        printf("1");
    } else {
        printf("0");
    }
  }
}

/*
 * This will clear the incoming char array therefore allow the array to
 * be reusable.
 */
void clearBinaryString(char theString[], int theSize) {
  int i;
  for (i = 0; i < theSize - 1; i++)
    theString[i] = ' ';
    theString[theSize - 1] = '\0';
}
