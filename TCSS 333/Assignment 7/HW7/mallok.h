/*
 * mallok.h
 *
 * Armoni Atherton
 * TCSS 333 - Winter 2018
 * Instructor: David Schuessler
 * Assignment 7
 */

/********************--Constants--********************/

#ifndef MALLOK_H
#define MALLOK_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/********************--Data Type Definitions--********************/

typedef struct node {
  void *memory;
  int size;
  int available;
  struct node *next;
} Node, List;

/********************--Linked List Prototypes--********************/

/* This will create the linked list. */
Node *createList(void);

/* This will pring out all the elements in the linked list. */
void printList(List *);

/********************--Mallok Prototypes--********************/

/* Will request a size for a heap. */
void create_pool(int size); 

/* This will allocate memory to if there is space available in the heap. */
void *my_malloc(int size); 

/* This will go through the heap and will free the space.*/
void my_free(void *block); 

/* This will clear the entire heap. */
void free_pool();
#endif 