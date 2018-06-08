/*
 * mallok.c
 *
 * Armoni Atherton
 * TCSS 333 - Winter 2018
 * Instructor: David Schuessler
 * Assignment 7
 */

#include "mallok.h"

/********************--Global Variables--********************/

static void *myHeap = NULL;
static List *heap_list = NULL;

/*
 * Checks the heap_list pointer and heap and if still active should do 
 * nothing, otherwise. Allocate memory for the heap (using malloc), 
 * checks that the heap space has been allocated 
 * (heap pointer is not NULL) and if so, creates the first node of 
 * the heap_list and sets its fields to the appropriate values.
 */
void create_pool(int size) {
  //checks the heap_list pointer/heap and if still active should do nothing,
  if (myHeap == NULL && heap_list == NULL) {
    myHeap = malloc(size);
    heap_list = createList();
    //This will create the first node in the linked list.
    Node *newNode = malloc(sizeof(Node));
    newNode->memory = myHeap;
    newNode->size = size;
    newNode->available = 1;
    newNode->next = NULL;
    //This will add the node that is just created to the linked list.
    heap_list = newNode;
  } 
  // printList(heap_list);
} 

/*
 * Search the block list for the FIRST free block that is big enough to 
 * satisfy the requested size. If the first free block is more than big 
 * enough, then my_malloc should use the first part of the block to satisfy 
 * the request and keep the latter part in the private heap.
 */
void *my_malloc(int size) {
  // printf("This is the size: %d\n", size);
  Node *scan = heap_list;
  void *Memory = NULL;
  int boolean = 1;
    while (scan != NULL && boolean) {
      if (scan->size >= size && scan->available == 1) {
        boolean = 0;
      } else {
          scan = scan->next;
      }
    }

    if (scan) {
      Node *newNode = malloc(sizeof(Node));
      Node *Temp = scan->next;
      //This will set the new node and orgininal node.
      newNode->size = scan->size - size;
      newNode->available = 1;
      newNode->memory = scan->memory + size;
      scan->size = size;
      scan->available = 0;
      //Check if the new node size is 0 and dont add to end.
      if (newNode->size != 0) {
        scan->next = newNode;
        newNode->next = Temp;
        //This will be the value to be returned.
        Memory = scan->memory;
    } else {
        //Send back the last node that was used and get ride of newNode.
        Memory = scan->memory;
        free(newNode);
    }
      // printf("The Heap_List: %p == The Pointer to Memory: ", newNode->memory);
    }
  // printList(heap_list);
  return Memory;
}

/*
 * Return a block to the private heap, making that storage 
 * once again available. In the process you must recombine the block 
 * with any neighboring blocks that are also free.
 */
void my_free(void *block) {
  if (block != NULL) {
    Node *currentNode = heap_list;
    Node *backNode = NULL;
    Node *nextNode = NULL;
    int boolean = 1;
    while (currentNode != NULL && block != currentNode->memory) {
        backNode = currentNode;
        currentNode = currentNode->next;
    }
    int check1 = 1;
    int check2 = 1;
    if (currentNode->next != NULL) {
      nextNode = currentNode->next;

      if (nextNode->available == 1 && currentNode != NULL) {
        currentNode->size = currentNode->size + nextNode->size;
        currentNode->available = 1;
        currentNode->next = nextNode->next;
        free(nextNode);
        check1 = 0;
      }
    }

    if (backNode != NULL && backNode->available == 1) {
      backNode->size = backNode->size + currentNode->size;
      backNode->next = currentNode->next;
      free(currentNode);
      check2 = 0;
    }

    if (check1 && check2 && currentNode != NULL) {
      currentNode->available = 1;
    
    }
  }
}

/*
 * Completely frees the private heap. Will go through the linked list and 
 * free all the values and set them to null. Will free the global heap 
 * and will free the heap and set it to null.
 */
void free_pool() {
  Node *currentNode = heap_list;
  while (currentNode != NULL) {
    Node *temp = currentNode;
    currentNode = currentNode->next;
    free(temp);
    temp = NULL;
  }
  free(myHeap);
  myHeap = NULL;
  heap_list = NULL;
}


/********************--Linked List Functions--********************/

/*
 * This will intialize the linked or also known as the heap. 
 */
Node *createList(void) {
  return NULL;
}

/*
 * This will go through the whole heap list and will print all the values 
 * and all the fields that are currently stored in them.
 */
void printList(List *list) {
  if (list) {
    printf("The Memory: %p, The list size: %d The list available: %d.\n", 
            list->memory, list->size, list->available);
    printList(list->next);
  }
}