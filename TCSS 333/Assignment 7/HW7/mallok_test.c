/*
 * mallok_test.c
 *
 * Armoni Atherton
 * TCSS 333 - Winter 2018
 * Instructor: David Schuessler
 * Assignment 7
 */

#include <stdio.h>
#include "mallok.h"

/********************--Prototypes--********************/

/* Create a pool of 1000 bytes, Check how many times you can request. */
int testMallokFunction();

/* Create a pool of 1000 bytes. Make 5 requests and free all request.*/
void testFreeFunction();

/* Create a pool of 1000 bytes. Check allocation is working correctly. */
void testFreeFunction2();

/* Create a pool of 1000 bytes. Fill memory and check if getting filled. */
void testFreeFunction3();

/* Create pool of 1000 bytes. Fill/free blocks of various sizes. */
void testFreeFunction4();


/*
 * This is the main driver of the program that will run the program. Will 
 * test program on many test. Will call functions from main to check 
 * many possible cases that might break the program.
 */
int main(void) { 
  int cnt = 0;
  cnt = testMallokFunction();
  printf("1). Testing Create Pool Function: \n");
  if (cnt == 100) {
    printf("Test Passed: Create pool of 1000 bytes. Successfully request a block of size 10 a total of 100 times.\n");
  } else {
      printf("Test Failed: Should be a total of 100 successfully requested blocks. You requested %d\n", cnt);
  }
  printf("\n2). Now Testing the Free Function:\n");

  free_pool(); //This will clear the heap to for next function testing.

  testFreeFunction();

  free_pool(); //This will clear the heap to for next function testing.

  testFreeFunction2();

  free_pool(); //This will clear the heap to for next function testing.

  testFreeFunction3();

  free_pool(); //This will clear the heap to for next function testing.
  
  testFreeFunction4();

  return 0;
}

/*
 * Create a pool of 1000 bytes. Count how times you can successfully 
 * request a block of size 10.
 */
int testMallokFunction() {
  create_pool(1000);
  int i, boolean = 1;
  int cnt = 0;
  char *ptr;
  for (i = 0; i < 150 && boolean; i++) {
    ptr = my_malloc(10);
    if (ptr == NULL) {
    boolean = 0;
  } else {
      cnt++;
    }
  }
  return cnt;
}

/*
 * create a pool of 1000 bytes. Make 5 requests for blocks of 200 bytes.
 * Free all 5 blocks. Repeat this request/free pattern several times to 
 * make sure you can reuse blocks after they are returned.
 */
void testFreeFunction() {
  create_pool(1000);
  void *ptr[5];
  int r, c;
  int checkAllocating = 1, checkFree = 1;
  for (r = 0; r < 25; r++) {
    for (c = 0; c < 5; c++) {
      ptr[c] = my_malloc(200);
      //Check if it is not null;
      if (ptr[c] == NULL) {
        checkAllocating = 0;
      }
    }
    for (c = 0; c < 5; c++) {
      my_free(ptr[c]);
      ptr[c] = NULL;
      if (ptr[c] != NULL) {
        checkFree = 0;
      }
    }
  }
  if (checkAllocating) {
    printf("Test passed: allocated memory correctly.\n");
  } else {
      printf("Test Failed: allocated memory should not equal null.\n");
  }
  if (checkFree) {
    printf("Test Passed: All memory was successfully freed.\n");
  } else {
    printf("Test Failed: one of the memory was not totally cleared.\n");
  }
}
/*
 *  Create a pool of 1000 bytes. Make 5 requests for blocks of 200 bytes.
 *  Free the middle block. Request a block of 210 bytes (it should fail) 
 *  Request a block of 150 bytes (it should succeed) Request a block of 60 
 *  bytes (it should fail) Request a block of 50 bytes (it should succeed).
 */
void testFreeFunction2() {
  printf("\n3). Testing Memory Collection:\n");
  create_pool(1000);
  char *ptr1, *ptr2, *ptr3, *ptr4, *ptr5;

  ptr1 = my_malloc(200);
  ptr2 = my_malloc(200);
  ptr3 = my_malloc(200);
  ptr4 = my_malloc(200);
  ptr5 = my_malloc(200);

  //This test should faild
  my_free(ptr3);
  ptr3 = my_malloc(210);
  if (ptr3 != NULL) {
    printf("Test Failed: SHOULD EQUAL ZERO! %p\n\n", ptr3);
  }
  // printf("SHOULD EQUAL ZERO: %p\n\n", ptr3);

  ptr3 = my_malloc(150);
  if (ptr3 == NULL) {
    printf("Test Failed: SHOULD ALLOCATED MEMORY! %p\n\n", ptr3);
  }
  ptr3 = my_malloc(60);
  if (ptr3 != NULL) {
    printf("Test Failed: SHOULD EQUAL ZERO! %p\n\n", ptr3);
  }

  ptr3 = my_malloc(50);
  if (ptr3 == NULL) {
    printf("Test Failed: SHOULD ALLOCATED MEMORY! %p\n\n", ptr3);
  }
  printf("Passed! If any statements printed above in this section, ");
  printf("not correctly done!\n\n");
}

/*
 * Create a pool of 1000 bytes. Request 5 blocks of 200 bytes. Fill the 
 * first block with the letter 'A', the second block with 'B', etc. Verify 
 * that the values stored in each block are still there. (Is the first 
 * block full of A's, second block full of B's, etc.)
 */
void testFreeFunction3() {
  printf("4). Testing Storing Values at Blocks: \n");
  char *ptr1, *ptr2, *ptr3, *ptr4, *ptr5;
  int i;
  int block1 = 1, block2 = 1, block3 = 1, block4 = 1, block5 = 1;

  create_pool(1000);

  ptr1 = my_malloc(200);
  for (i = 0; i < 200; i++) {
    ptr1[i] = 'A';
    // printf("%d: %c\n", i, ptr1[i]);
    if (ptr1[i] != 'A') {
      block1 = 0;
      // printf("%d: %c\n", i, ptr1[i]);
    }
  }
  // printf("Block2: %d\n", block2);
  ptr2 = my_malloc(200);
  for (i = 0; i < 200; i++) {
    // printf("Block2: %d\n", block2);
    ptr2[i] = 'B';
    if (ptr2[i] != 'B') {
      block2 = 0;
    }
  }

  ptr3 = my_malloc(200);
  for (i = 0; i < 200; i++) {
    ptr3[i] = 'A';
    if (ptr3[i] != 'A') {
      block3 = 0;
    }
  }

  ptr4 = my_malloc(200);
  for (i = 0; i < 200; i++) {
    ptr4[i] = 'B';
    if (ptr4[i] != 'B') {
      block4 = 0;
    }
  }

  ptr5 = my_malloc(200);
  for (i = 0; i < 200; i++) {
    ptr5[i] = 'A';
    if (ptr5[i] != 'A') {
      block5 = 0;
    }
  }
  
  if (block1 && block2 && block3 && block4 && block5) {
    printf("All Test Passed: Successfully Filled Memory!\n\n");
  } else {
      printf("Test Failed: Did not successfully fill memory!\n\n");
  }
}
  

/*
 * create a pool of 1000 bytes. Request and return a block of 1000 
 * bytes Request and return four blocks of 250 bytes Request and return 
 * ten blocks of 100 bytes
 */
void testFreeFunction4() {//
  printf("5). Testing block of 1000 and inserting different sizes of memory: \n");
  create_pool(1000);

  char *ptr = my_malloc(1000);
  if (ptr == NULL) {
    printf("Failed Test: Should allocated memory: \n");
  }
  my_free(ptr);
  ptr = NULL;
  if (ptr != NULL) {
    printf("Failed Test: Should have freed memory: \n");
  }

  int i;
  void *ptrList[10];

  for (i = 0; i < 4; i++) {
    ptrList[i] = my_malloc(250);
    if (ptrList[i] == NULL) {
      printf("Failed Test: Should allocated memory for 250: \n");
    }

  }

  for (i = 0; i < 4; i++) {
    my_free(ptrList[i]);
    ptrList[i] = NULL;
    if (ptrList[i] != NULL && i != 0) {
      printf("Failed Test: Should have freed memory for 250: \n");
    }
  }

  for (i = 0; i < 10; i++) {
    ptrList[i] = my_malloc(100);
    if (ptrList[i] == NULL) {
      printf("Failed Test: Should allocated memory for 100: \n");
    }
  }

  for (i = 0; i < 10; i++) {
    my_free(ptrList[i]);
    ptrList[i] = NULL;
    if (ptrList[i] != NULL && i != 0) {
      printf("Failed Test: Should allocated memory for 100: \n");
    }
  }
  printf("Passed! If any statements printed above in this section, ");
  printf("not correctly done!\n\n");
}