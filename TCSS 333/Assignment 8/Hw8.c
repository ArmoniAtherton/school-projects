/*
 * Hw8.c
 *
 * Armoni Atherton
 * TCSS 333 - Winter 2018
 * Instructor: David Schuessler
 * Assignment 8
 */

#include <stdio.h>
#include <math.h>

/********************--Function Prototypes--********************/

/* This will convert the incoming integer value into a binary number. */
void convert(unsigned bytes, int size);

/********************--Unions/Structures--********************/

/* This will hold the specific number and different sections of bytes. */
typedef union data_tag {
   float decimalNumber;
   struct {
        unsigned int mantissa : 23;
        unsigned int exponent : 8;
        unsigned int sign : 1;
      } Bytes;
} Data;  

/*
 * This will open a file and read in all the input and will convert the
 * integer numbers into a float number. Will call other functions to 
 * convert the float number to a binary number and print out to console.
 */
int main(void) {
  FILE *input = fopen("Hw8in.txt", "r");
  float firstNum = 0, secondNum = 0, thirdNum = 0, total = 0;
  int cnt = 0;

  while (fscanf(input, "%f %f %f", &firstNum, &secondNum, &thirdNum) 
         != EOF ) {
    int boolean = 1;
    //This will check if the first number is negative to do correct math.
    if (firstNum < 0) {
      total = -1 * (fabsf(firstNum) + (secondNum / thirdNum));
    } else if (!thirdNum) {
        total = 0.0;
        boolean = 0;
    } else {
        total = firstNum + (secondNum / thirdNum);
    }
    //Check if denominator is not zero procede with math.
    if (boolean) {
      Data number;
      number.decimalNumber = total;
      printf("Floating point format of %20.15f:\n", total);
      printf("       %d ", number.Bytes.sign);
      convert(number.Bytes.exponent, 8);
      printf(" ");
      convert(number.Bytes.mantissa, 23);
      printf("\n");
    } else {
        printf("ERROR! Denominator can not be 0!!\n");
    }
  }
  fclose(input);
  return 0;
}

/*
 * This function will convert the incoming numbers into binary numbers
 * and will print it out the console.
 */
void convert(unsigned bytes, int size) {
  unsigned i = 1 << --size;
  for (; i > 0; i /= 2) {
      (bytes & i) ? printf("1") : printf("0");
  }
}