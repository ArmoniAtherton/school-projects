/*
 * sort.c
 *
 * Armoni Atherton
 * TCSS 333 - Winter 2018
 * Instructor: David Schuessler
 * Assignment 6
 */

#include "sort.h"

/*
 * This will sort the linked list using bubble sort. Will go through
 * each element and check each one and will move nodes if not in correct
 * order.
 */
void bubble_sort_list(List **list, int (*compare)(List *one, List *two))
{
  int i, size, sorted = 0;
  List *back, *cur, *front;
  // Count the number of nodes in the list and store in size:
  for (size = 0, cur = *list; cur; size++, cur = cur->next);

  size--;                         // reduce the size by one...  Remember,
                                  // we will only possibly swap the 2nd
                                  // from the last node with the last node.
                                  // Or, think of it this way, if there is
                                  // only 1 node in the list, its sorted!
  while (size && !sorted) {
    back = NULL;             // Set up pointers to start one bubble process.
    cur = *list;
    front = (*list)->next;
    sorted = 1;
    if (compare(*list, front) > 0) {  // Need to update the list pointer
      sorted = 0;                // by swapping pointers.  This is a special
      cur->next = front->next;        // case only done at the start of each
      *list = front;                  // bubble process.
      front->next = cur;
      front = cur->next;
      back = *list;
    } else {                         // No swap.  Move to next set of nodes.
      back = cur;
      cur = front;
      front = front->next;
    }
    // Now we are looking at (or starting with) the second node in the list
    for (i = 1; front && i < size; i++) {
      if (compare(cur, front) >= 0) { // Need to make a swap
        sorted = 0;
        back->next = front;
        cur->next = front->next;
        front->next = cur;
        back = front;
        front = cur->next;
      } else {                       // No swap.  Move to next set of nodes.
        back = cur;
        cur = front;
        front = front->next;
      }
    }
    size--;                        // One more node is in the correct sorted
                                    // position.
  }
}