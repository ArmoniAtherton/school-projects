/*
 * linkedlist.c
 *
 * Armoni Atherton
 * TCSS 333 - Winter 2018
 * Instructor: David Schuessler
 * Assignment 6
 */

#include "linkedlist.h"

/*
 * This will create a linked list allowing for elements to be 
 * added.
 */
Node *createList(void) {
  return NULL;
}

/*
 * This will insert a name and a value into a linked list. Will place
 * the value into different fields depending on what file is being 
 * handled. Will sort all names in the linked list by ascending order.
 */
void insert(List **list, char *name, int value, int boolean) {
  Node *scan, *back = NULL;
  Node *newNode = malloc(sizeof(Node));
  strcpy(newNode->name, name);
  if (boolean) {
    newNode->value1 = value;
  } else {
    newNode->value2 = value;
  }
  newNode->next = NULL;

  if (*list == NULL) // Insert the very first node to the list:
    *list = newNode;
  else {
    scan = *list;
    while (scan != NULL && strcmp(scan->name, name) < 0) {
      back = scan;
      scan = scan->next;
    }
    if (scan == *list) {
      if (strcmp(scan->name, name) == 0 && boolean) {
        scan->value1++;
      } else if (strcmp(scan->name, name) == 0 && !boolean) {
          scan->value2++;
      } else {
          insertAtBeginning(list, newNode);
      }
      // insert at the end:
    } else if (scan != NULL && strcmp(scan->name, name) == 0 && boolean) { 
        scan->value1++;
    } else if (scan != NULL && strcmp(scan->name, name) == 0 && !boolean) { 
        scan->value2++;
    } else {                       // insert in the middle:
      newNode->next = scan;
      back->next = newNode;
    }
  }
}

/*
 * Will sort by decending by the two values in the two files, if equal will 
 * next sort by lenght of words by decending order and if equal 
 * finally will sort in alphabetical order.
 */
int compare(Node *a, Node *b) {
  int result = abs((b->value1 - b->value2)) - abs((a->value1 - a->value2));
  if (!result)
    result = strlen(b->name) - strlen(a->name);
  if(!result)
    result = strcmp(a->name, b->name);
  return result;
}

/*
 * Will insert the node in the beggining of the file to be inserted.
 */
void insertAtBeginning(List **list, Node *n) {
  n->next = *list;
  *list = n;
}

/*
 * This will print the linked list showing all the names and 
 * values of each node in the linked list.
 */
void printList(List *list) {
  if (list) {
    printf("%50s has %-5d List Two has: %-5d\n", (*list).name, list->value1,
           list->value2);
    printList(list->next);
  }
}

/*
 * This will print the top 25 names in the linked list.
 */
void printTopList(List *list, char file1[], char file2[]) {
  int size = 1;
  Node *skippy = list;
  printf("\nSorted by name in ascending order:\n");
  while (skippy && !(size == 26)) {
    printf("%5d. %30s, %1s %s: %5d, %1s %s: %5d, %s Difference: %s %d\n", 
      size, skippy->name, "", file2, skippy->value1, "", 
      file1, skippy->value2, "", "", abs(skippy->value1 - skippy->value2));
    skippy = skippy->next;
    size++;
  }

}

/*
 * This will read in the the first 25 names, values and differences in 
 * linked list followed by the last 5 elements in the list.
 */
void printTopListSorted(List *list, char file1[], char file2[], 
                        int listSize) {
  int size = 1;
  Node *skippy = list;
  printf("\nSorted Linked List by assignment requirments:\n");
  // && !(size == 26)//
  while (skippy && !(size == 26)) {
    printf("%5d. %30s, %1s %s: %5d, %1s %s: %5d, %s Difference: %s %d\n", 
           size, skippy->name, "", file2, skippy->value1, "", file1, 
           skippy->value2, "", "", abs(skippy->value1 - skippy->value2));
    skippy = skippy->next;
    size++;
  }
  Node *skippy2 = list;
  size = 1;
  printf("\nLast Five Items:\n");
  while(skippy2) {
    if (size >= listSize - 4) {
      printf("%5d. %30s, %1s %s: %5d, %1s %s: %5d, %s Difference: %s %d\n", 
             size, skippy2->name, "", file2, skippy2->value1, "", file1, 
             skippy2->value2 , "", "",
             abs(skippy2->value1 - skippy2->value2));
    }
    skippy2 = skippy2->next;
    size++;
  }
}

/*
 * This will iterate throught the entire linked list and will 
 * get the size and return the size.
 */
int getListSize(List *list) {
  int size = 0;
  Node *skippy = list;
  while (skippy) {
    skippy = skippy->next;
    size++;
  }
  return size;
}

/*
 * This will free all the elements inside the linked list.
 */
void freeList(List *list) {
  if (list) {
    freeList(list->next);
    free(list);
  }
}