/*
 * athera-assignment5.c
 *
 * Armoni Atherton
 * TCSS 333 - Winter 2018
 * Instructor: David Schuessler
 * Assignment 5
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/********************--Constants--********************/

#define NAM_LEN 30
#define STUFF_NAM_LEN 30
#define MAX_CUSTOMERS 20
#define MAX_ITEMS 10

/********************--Data Structures--********************/

struct items {
  char name[STUFF_NAM_LEN];
  int totalItems;
  float price;
  float quantityPrice;
};

/* This will hold the specific customer items. */
typedef struct items myItems;

struct customer {
  char name[NAM_LEN];
  myItems *itemsPtrs[MAX_ITEMS];
  int totalAssets;
  float totalPrice;
};

/* This will hold the customer information. */
typedef struct customer Customer;

/********************--Function Prototypes--********************/

/* This will get customers total price and quantity of items. */
void totalCustomerItems(Customer *[], int);

/* This will display the Customers and items as read in. */
void displayCustomersItems(Customer *[], int);

/* This will output the customer in a sorted order. */
void displayCustomersOutput(Customer *[], int);

/* Will read in the file to get customer information. */
int getCustomerInformation(Customer *[], char *);

/* Will organized the customers in a specific order. */
void bubbleSort(void *[], int, int(*compare)(const void *, const void *));

/* Will orgainze customers by total price. */
int totalCompare(const void *, const void *);

/* Will orgainze customers by price. */
int priceCompare(const void *, const void *);

/* Will free all the memory used by the program. */
void deallocateAllPointers(Customer *[], int);


/***************************************************************************
 * Main function to process Customers' assets data. Will call other 
 * functions to organize the Customers data and will output it to different
 * files.
 */
int main(void) {
  int total, i;
  Customer *CustomerPtrs[MAX_CUSTOMERS];
  total = getCustomerInformation(CustomerPtrs, "hw5input.txt");
  totalCustomerItems(CustomerPtrs, total);
  displayCustomersItems(CustomerPtrs, total);
  bubbleSort((void *) CustomerPtrs, total, totalCompare);
  for (i = 0; i < total; i++)
    bubbleSort((void *) (CustomerPtrs[i]->itemsPtrs), 
                CustomerPtrs[i]->totalAssets, 
                priceCompare);
  displayCustomersOutput(CustomerPtrs, total);
  deallocateAllPointers(CustomerPtrs, total);
  return 0;
}

/***************************************************************************
 * Use Bubble Sort to sort arrays of Customer pointers (is passed a pointer 
 * to the appropriate comparison function):
 */
void bubbleSort(void *ptrs[], int size,
                           int (*compare)(const void *, const void *)) {
  void *s;
  int topBubble, bubbles, notSorted = 1;
  for (topBubble = 0; topBubble < size - 1 && notSorted; topBubble++)
    for (bubbles = size - 1, notSorted = 0; bubbles > topBubble; 
         bubbles--) {
      if (compare(ptrs[bubbles], ptrs[bubbles - 1]) > 0) {
        s = ptrs[bubbles];
        ptrs[bubbles] = ptrs[bubbles - 1];
        ptrs[bubbles - 1] = s;
        notSorted = 1;
      }
    }
}

/***************************************************************************
 * Comparison function using pointers to the customer to organize the 
 * total price.
 */
int totalCompare(const void *a1, const void *a2) {
  return (int)(((Customer *)a1)->totalPrice * 100 - 
               ((Customer *)a2)->totalPrice * 100);
}

/***************************************************************************
 * Comparison functions using pointers to Items quantity price to be 
 * organized.
 */
int priceCompare(const void *w1, const void *w2) {
  return (int)(((myItems *)w1)->quantityPrice * 100 - 
               ((myItems *)w2)->quantityPrice * 100);
}

/***************************************************************************
 * This function will get the total price of the customers items while 
 * also  getting the quantity price of the items bought by the specific
 * customer.
 */
void totalCustomerItems(Customer *ptrs[], int size) {
  int i, j;
  float quantityPrice, totalPrice;
  for (i = 0; i < size; i++) {
    totalPrice = 0;
    for (j = 0; j < ptrs[i]->totalAssets; j++) {
        quantityPrice = ptrs[i]->itemsPtrs[j]->totalItems * 
                        ptrs[i]->itemsPtrs[j]->price;
        ptrs[i]->itemsPtrs[j]->quantityPrice = quantityPrice;
        totalPrice += quantityPrice;
        if (j == ptrs[i]->totalAssets - 1) {
            ptrs[i]->totalPrice = totalPrice;
        }
    }
  }
}

/***************************************************************************
 * This will ouput to a file the customers followed by the names, price and 
 * quantity of items, and the total price.
 */
void displayCustomersItems(Customer *ptrs[], int size) {
  FILE *outFile = fopen("hw5time.txt", "w");
  int i, j;
  for (i = 0; i < size; i++) {
    fprintf(outFile, "Customer: %s \n%15s\n", ptrs[i]->name, "Orders:");
    for (j = 0; j < ptrs[i]->totalAssets; j++) {
      fprintf(outFile, "%15s %-10s %8d %9.2f %10.2f\n", "", 
              ptrs[i]->itemsPtrs[j]->name, 
              ptrs[i]->itemsPtrs[j]->totalItems, 
              ptrs[i]->itemsPtrs[j]->price, 
              ptrs[i]->itemsPtrs[j]->quantityPrice);
        if (j == ptrs[i]->totalAssets - 1) {
         fprintf(outFile, "%47s %8.2f\n", "Total:", ptrs[i]->totalPrice);
        }
    }
  }
  fclose(outFile);
}

/***************************************************************************
 * This will go the the customer list and will output it to a file
 * in the correct format as required. Will show the total order of the 
 * customer and the item values. 
 */
void displayCustomersOutput(Customer *ptrs[], int size) {
  FILE *sortedOutFile = fopen("hw5money.txt", "w");
  int i, j = 0;
  for (i = 0; i < size; i++) {
    fprintf(sortedOutFile, "%s, Total Order = $%.2f\n", ptrs[i]->name, 
            ptrs[i]->totalPrice);
    for (j = 0; j < ptrs[i]->totalAssets; j++) {
      fprintf(sortedOutFile, "%-s%s %d $%2.2f, Item Value: $%.2f\n", 
              ptrs[i]->itemsPtrs[j]->name, "", 
              ptrs[i]->itemsPtrs[j]->totalItems,
              ptrs[i]->itemsPtrs[j]->price, 
              ptrs[i]->itemsPtrs[j]->quantityPrice);
    }
  }
  fclose(sortedOutFile);
}

/***************************************************************************
 * Read in the Customers' data while allocating memory to store the data. 
 * Will read in the Customer items from a file.
 */
int getCustomerInformation(Customer *p[], char *fn) {
  FILE *in = fopen(fn, "r");
  char stuName[NAM_LEN], stuffName[STUFF_NAM_LEN];
  int i, totalItems, count = 0;
  float w;
  while (count < MAX_CUSTOMERS && fscanf(in, "%s %d %s $%f", stuName, 
         &totalItems, stuffName, &w) != EOF) {
    for (i = 0; i < count && strcmp(stuName, p[i]->name); i++);
    if (i == count) {
      p[i] = (Customer *) malloc(sizeof(Customer));
      p[i]->totalAssets = 0;
      count++;
    }
    p[i]->itemsPtrs[p[i]->totalAssets] = (myItems *) 
                                          malloc(sizeof(myItems));
    strcpy(p[i]->name, stuName);
    strcpy(p[i]->itemsPtrs[p[i]->totalAssets]->name, stuffName);
    p[i]->itemsPtrs[p[i]->totalAssets]->price = w;
    p[i]->itemsPtrs[p[i]->totalAssets]->totalItems = totalItems;
    p[i]->totalAssets++;
  }
  fclose(in);
  return count;
} 

/***************************************************************************
 * Restores all memory used by this program.
 */
void deallocateAllPointers(Customer *ptrs[], int size) {
  int i, j;
  for (i = 0; i < size; i++) {
    for (j = 0; j < ptrs[i]->totalAssets; j++)
      free(ptrs[i]->itemsPtrs[j]);
    free(ptrs[i]);
  }
}    
 