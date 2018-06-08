/*
 * athera-assignment3.c
 *
 * Armoni Atherton
 * TCSS 333 - Winter 2018
 * Instructor: David Schuessler
 * Assignment 3
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/* The max names allowed. */
#define MAX_NAMES 370

/* The max name lenght allowed. */
#define NAME_LENGTH 40

/* The max amount of names to read . */
#define MAX_NAMES_TO_READ 100

/* The amount of rows. */
#define ROW 100

/* The amount of columns. */
#define COLUMN 10

/********************--Function Prototypes--********************/

/* This function will get the first 100 names and rank them. */
void getNames(FILE *theFile, char *theNames, int theNameRank[][COLUMN]);

/* This will get new names add to master list/no duplicates and rank. */
int getNewNames(FILE *f, char *theNames, int theNameRank[][COLUMN], 
                int dynamicLSize, int theRow);

/* This function will find end of list and add name to the end. */
void addToEndOfList(char *newName, char *names, int dynamicLSize);

/* This function will sort by breaking up names and ranks in parallel. */
void mergeStrSort2(char a[][NAME_LENGTH], int nameCount, 
                   int theNameRank[][COLUMN]);

/* This function will sort the names and ranks in parallel. */
void merge2(char a[][NAME_LENGTH], char left[][NAME_LENGTH], 
            char right[][NAME_LENGTH], int aLen, int leftLen, int rightLen, 
            int theNameRank[][COLUMN], int leftRank[][COLUMN], 
            int rightRank[][COLUMN]);

/* This function will output to the file the names, rank and year. */
void displayNames(char *names, int nameCount, FILE *summaryFile, 
                  int theRanks[][COLUMN]); 

/*
 * This function will open and close files intialize array to store
 * the names and ranks into. Will call other functions to get the names, 
 * ranks, and sort alphabetically in parallel to the rank. Will call other 
 * function to output to the file.
 */
int main(void) {
  FILE *f1 = fopen("yob1920.txt", "r");
  FILE *f2 = fopen("yob1930.txt", "r");
  FILE *f3 = fopen("yob1940.txt", "r");
  FILE *f4 = fopen("yob1950.txt", "r");
  FILE *f5 = fopen("yob1960.txt", "r");
  FILE *f6 = fopen("yob1970.txt", "r");
  FILE *f7 = fopen("yob1980.txt", "r");
  FILE *f8 = fopen("yob1990.txt", "r");
  FILE *f9 = fopen("yob2000.txt", "r");
  FILE *f10 = fopen("yob2010.txt", "r");
  FILE *summaryFile = fopen("summary.csv", "w");

  char names[MAX_NAMES][NAME_LENGTH];
  int nameRank[MAX_NAMES][COLUMN] = {{0, 0},{0, 0}};
  int dynamicLSize = MAX_NAMES_TO_READ;
  getNames(f1, (char *)names, nameRank);
  //This will add new unique names to list and change the size of the list.
  dynamicLSize = getNewNames(f2, (char *)names, nameRank, dynamicLSize, 1);
  dynamicLSize = getNewNames(f3, (char *)names, nameRank, dynamicLSize, 2);
  dynamicLSize = getNewNames(f4, (char *)names, nameRank, dynamicLSize, 3);
  dynamicLSize = getNewNames(f5, (char *)names, nameRank, dynamicLSize, 4);
  dynamicLSize = getNewNames(f6, (char *)names, nameRank, dynamicLSize, 5);
  dynamicLSize = getNewNames(f7, (char *)names, nameRank, dynamicLSize, 6);
  dynamicLSize = getNewNames(f8, (char *)names, nameRank, dynamicLSize, 7);
  dynamicLSize = getNewNames(f9, (char *)names, nameRank, dynamicLSize, 8);
  dynamicLSize = getNewNames(f10, (char *)names, nameRank, dynamicLSize, 9);
  mergeStrSort2(names, dynamicLSize, nameRank);
  displayNames((char *)names, dynamicLSize, summaryFile, nameRank);
  fclose(f1);
  fclose(f2);
  fclose(f3);
  fclose(f4);
  fclose(f5);
  fclose(f6);
  fclose(f7);
  fclose(f8);
  fclose(f9);
  fclose(f10);
  return 0;
}

/*
 * This will get the first 100 names and store them into a array.
 * Will also rank the names from most popular to least poular
 * in a parallel list to the names list.
 *  - The input file to read the names from.
 *  - The names array to store the names into.
 *  - The name rank array to store popularity from 1 - 100.
 */
void getNames(FILE *theFile, char *theNames, int theNameRank[][COLUMN]) {
  int r, c;
  // Read in the names from a csv file:
  for (r = 0; r < MAX_NAMES_TO_READ; r++) {
    fscanf(theFile, " %[^,], %*c, %*d", theNames + r * NAME_LENGTH);
  }
  int rank = 1;
  for (r = 0; r < ROW; r++) {
    for (c = 0; c < 1; c++) {
      theNameRank[r][c] = rank; 
      rank++;
    }
  } 
}

/*
 * Will added to the names list and will add names to the orignial
 * list if name is not found in the list already. Will also keep 
 * track of the rank of each name.
 *  - The File to read new names from.
 *  - The Master List of names.
 *  - The list to hold the popularity ranking.
 *  - The dynamic size of the list.
 *  - The row to add the ranking to.
 */
int getNewNames(FILE *theFile, char *theNames, int theNameRank[][COLUMN], 
                int dynamicLSize, int theRow) {
  int i, j;
  int cntRank = 0;
  char tempString[NAME_LENGTH];
  //This first for loop will read in new 100 names
  for (i = 0; i < ROW; i++) {
    cntRank++;
    //This will store new age group of names to tempString.
    fscanf(theFile, " %[^,], %*c, %*d", tempString);
      //This will check all names in previous year to the new year.
      int isFound = 0;
      for (j = 0; j < dynamicLSize; j++) {
        if (strcmp(tempString, theNames + j * NAME_LENGTH) == 0) {
          theNameRank[j][theRow] = cntRank; 
          isFound = 1;
        } 
    }
    if (!(isFound)) {
      char *tempNames = tempString;
      //Add one spot to the list for adding the new name.
      dynamicLSize++;
      addToEndOfList((char *)tempNames, (char *)theNames, dynamicLSize);
      theNameRank[j][theRow] = cntRank; 
    }
  }
  return dynamicLSize;
}

/*
 * This function will find the end of the list and place
 * a name element at the last index of the names array.
 *  - passes in the name to be added
 *  - the original names list.
 *  - dynamic size of the list of names.
 */
void addToEndOfList(char *newName, char *names, int dynamicLSize) {
  char *nam;
  int maxIter = 0;
  int notEnd = 1;
  nam = (char *)names;
  // Goes to the end of the list..
  while (maxIter < dynamicLSize) {
    if(maxIter == dynamicLSize - 1) {
      //This will copy the name to the list.
      strcpy(nam, newName);
    } else {
        nam += NAME_LENGTH;
    }
    //Will go until finds the end of the list.
    maxIter++;
  }
}

/*
 * Sorts an array of strings, length of 13 each.
 * receives:
 *  -an name array to be sorted
 *  -the number of strings to sort
 *  -an rank array to be sorted
 */
void mergeStrSort2(char a[][NAME_LENGTH], int nameCount, 
                   int theNameRank[][COLUMN]) {
  int i, i1, i2, c;
  // create 2 arrays to contain the data in a
  char left[nameCount / 2][NAME_LENGTH];
  char right[nameCount / 2 + 1][NAME_LENGTH];
  // create 2 arrays to contain the data in theNameRank
  int leftRank[nameCount / 2][COLUMN];
  int rightRank[nameCount / 2 + 1][COLUMN];
  int leftCount = nameCount / 2;
  int rightCount =  nameCount - leftCount;
  if (nameCount >= 2) {
    // Put first half of a into left:
    for (i1 = 0; i1 < leftCount; i1++) {
      strcpy(left[i1], a[i1]);
      //For NameRank.
      for (c = 0; c < COLUMN; c++) {
        leftRank[i1][c] = theNameRank[i1][c];
      }
    }
    // Put the later half of a into right:
    for (i2 = 0; i2 < rightCount; i2++, i1++) {
      strcpy(right[i2], a[i1]);
      //For NameRank.
      for (c = 0; c < COLUMN; c++) {
        rightRank[i2][c] = theNameRank[i1][c];
      }
    }
    // sort the two halves
    mergeStrSort2(left, leftCount, leftRank);
    mergeStrSort2(right, rightCount, rightRank);

    // merge the sorted halves into a sorted whole
    merge2(a, left, right, nameCount, leftCount, rightCount,
           theNameRank, leftRank, rightRank);
  }
}

/*
 * Merges two sorted arrays (second and third parameters (left and right)) 
 * into the array to which is the first parameter. 
 * The parameters:
 *   -an array that will contain the results of the merging
 *   -the left array to merge (already sorted)
 *   -the right array to merge (already sorted)
 *   -the number of strings in the left array
 *   -the number of strings in the right array
 *   -the size of each element being sorted
 */
void merge2(char a[][NAME_LENGTH], char left[][NAME_LENGTH], 
            char right[][NAME_LENGTH], int aLen, int leftLen, int rightLen, 
            int theNameRank[][COLUMN],int leftRank[][COLUMN],
            int rightRank[][COLUMN]) {
  int i, c;
  int i1 = 0;
  int i2 = 0;
  for (i = 0; i < aLen; i++) {
    if (i2 >= rightLen || 
      (i1 < leftLen && strcmp(left[i1], right[i2]) <= 0)) {
        strcpy(a[i], left[i1]); // take from left
        for (c = 0; c < COLUMN; c++) {
          theNameRank[i][c] = leftRank[i1][c];
        }
        i1++;
    } else {
        strcpy(a[i], right[i2]);   // take from right
        for (c = 0; c < COLUMN; c++) {
          theNameRank[i][c] = rightRank[i2][c];
        }
        i2++;
    }
  }
}

/*
 * This will print out the the file the years and the names followed by
 * the the popularity of a given name from that from 0 to 
 * 100 in rank.
 *  - Passes in the original names after sorted.
 *  - The size of the original list.
 *  - The file to output the information to.
 *  - The list of ranks for each given year.
 */
void displayNames(char *names, int dynamicLSize, FILE *summaryFile, 
                  int theRanks[][COLUMN]) {
  fprintf(summaryFile, "Name, 1920, 1930, 1940, 1950,");
  fprintf(summaryFile, " 1960, 1970, 1980, 1990, 2000, 2010\n");
  char *nam;
  int i, c;
  for (i = 0, nam = (char *)names; i < dynamicLSize; i++, 
       nam += NAME_LENGTH) {
    fprintf(summaryFile,"%s, ", nam);
    for (c = 0; c < COLUMN; c++) {
      if (theRanks[i][c] == 0) {
        fprintf(summaryFile, ",");
      } else {
          fprintf(summaryFile, "%d,", theRanks[i][c]);
      }
    }
    fprintf(summaryFile, "\n");
  }
}
