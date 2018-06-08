/*
 * linkedlist.h
 *
 * Armoni Atherton
 * TCSS 333 - Winter 2018
 * Instructor: David Schuessler
 * Assignment 6
 */

/********************--Constants--********************/

#ifndef LINKEDLIST_H
#define LINKEDLIST_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/********************--Data Type Definitions--********************/

typedef struct node {
  char name[49];
  int value1;
  int value2;
  struct node *next;
} Node, List;

/********************--Linked List Prototypes--********************/

/* This will create the linked list. */
Node *createList(void);

/* This will insert elements into the linked list. */
void insert(List **, char *, int, int);

/* This will insert elements in the beggining of the linked list. */
void insertAtBeginning(List **, Node *);

/* This will pring out all the elements in the linked list. */
void printList(List *);

/* This will print out the top 25 elements in the linked list. */
void printTopList(List *, char[], char[]);

/* This will print out the top 25 elements and the last 5 elements. */
void printTopListSorted(List *, char[], char[], int);

/* This will get the size of the linked list. */
int getListSize(List *);

/* This will free all the elements inside the linked list. */
void freeList(List *);

/* This compare the elements in the linked list for sorting. */
int compare(Node *, Node *);
#endif 