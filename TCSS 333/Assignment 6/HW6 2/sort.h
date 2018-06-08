/*
 * sort.h
 *
 * Armoni Atherton
 * TCSS 333 - Winter 2018
 * Instructor: David Schuessler
 * Assignment 6
 */


/********************--Constants--********************/

#ifndef LLBUBBLESORT_H
#define LLBUBBLESORT_H

#include "linkedlist.h"


/********************--Prototypes--********************/

/* This will sort the linked list using bubble sort. */
void bubble_sort_list(List **list, int (*compare)(List *one, List *two));

#endif