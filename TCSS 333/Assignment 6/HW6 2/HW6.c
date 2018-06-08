/*
 * HW6.c
 *
 * Armoni Atherton
 * TCSS 333 - Winter 2018
 * Instructor: David Schuessler
 * Assignment 6
 */

#include <stdio.h>
#include "linkedlist.h"
#include "sort.h"
#include <ctype.h>

/********************--Function Prototypes--********************/

/* Will read in the input files and store it in a string. */
void readFile(FILE *inFile, List **list, int theValue, int whichFile);

/* Will find the vaild parts of the string. */
void parseString(int *firstIndex, int *lastIndex, char name[]);

/* Will insert the vaild string into the linked list. */
void insertString(int *firstIndex, int *lastIndex, char name[], 
                  char vaildName[]);

/*
 * This is the main driver of the program that will run the program. Will 
 * open files and call methods to read in the data correctly. Call 
 * other c files to place the names into a linked list.
 */
int main(void) {
  List *list = createList();
  char file1 [50] = "LittleRegiment.txt";
  char file2 [50] = "RedBadge.txt";
  FILE *inFile2 = fopen(file1, "r");
  FILE *inFile1 = fopen(file2, "r");
  // Will read in the file.
  readFile(inFile1, &list, 1, 1);
  readFile(inFile2, &list, 1, 0);
  //Closes the files.
  fclose(inFile1);
  fclose(inFile2);

  //Print in alpahbetical order.
  printTopList(list, file1, file2);
  //Will sort the list.
  bubble_sort_list(&list, compare);
  //will print the list when sorted.
  printTopListSorted(list, file1, file2, getListSize(list));
  freeList(list);
  return 0;
}

/*
 * This will read in the incoming file and will store the names
 * into a char array. Will call other functions to correctly read 
 * it in.
 */
void readFile(FILE *inFile, List **list, int theValue, int whichFile) {
  char name[50] = ""; //Will read a name of 50 but only store 49 characters.
  int i;
  int value = theValue;
  int boolean = 1;  
  while (fscanf(inFile, "%s", name) != EOF) {
    //Lower case the the incoming name.
    for(i = 0; name[i]; i++) {
      name[i] = tolower(name[i]);
    }
    int firstIndex = 0;
    int lastIndex = 0;
    //Find first and last index parts of string.
    parseString(&firstIndex, &lastIndex, name);
    
    //Make a vaild string based off the first and last index of the string.
    char vaildName[50] = "";
    insertString(&firstIndex, &lastIndex, name, vaildName);

    if (!(firstIndex == 0 && lastIndex == 0)) {
      if (whichFile) {
        insert(list, vaildName, theValue, whichFile);
      } else {
        insert(list, vaildName, theValue, whichFile);
      }
    }
    //Clear the name array.
    for(i = 0; name[i]; i++) {
      name[i] = 0;
    }
  }
}

/*
 * This will parse the string correctly and find the first and last 
 * index of the string that is correct.
 */
void parseString(int *firstIndex, int *lastIndex, char name[]) {
  int i, isSet = 1;
  int isSet2 = 1;
  for (i = 0; i < 50; i++) {
    //Between a-z or ' - or if not to go through string anymore.
    if ((name[i] >= 97 && name[i] <= 122) || name[i] == '-' 
        || name[i] == '\'') {
      if (isSet) {
        *firstIndex = i;
        isSet = 0;
      }
    } else if (isSet2 && !isSet) {
        *lastIndex = i;
        isSet2 = 0;
    } 
  }
}

/*
 * This will take the first index up to all the characters up to the last 
 * index of the string in which should all be vaild and store it into a 
 * new string or only correct characters.
 */
void insertString(int *firstIndex, int *lastIndex, char name[], 
                  char vaildName[]) {
  int i, cnt = 0;
    //Wont insert if no vaild chars were found.
    for (i = *firstIndex; i < *lastIndex; ++i) {
      vaildName[cnt] = name[i];
      if (name[i] == *lastIndex - 1) {
        name[i] = '\0';
        vaildName[cnt] = name[i];
      }
      cnt++;
    }
}
