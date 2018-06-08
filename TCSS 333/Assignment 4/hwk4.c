/*
 * athera-assignment4.c
 *
 * Armoni Atherton
 * TCSS 333 - Winter 2018
 * Instructor: David Schuessler
 * Assignment 4
 */

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h> 

/********************--Function Prototypes--********************/

/* This will get the size of the file the user inputed.. */
unsigned long get_file_size(char *s);

/* Will read in the files into pointers for manipulation,/check BMP. */
int readInFile(FILE*, FILE*, unsigned long, unsigned long, char**, char**, 
               int *p1Height, int *p1Width, int *p2Height, int *p2Width);

/* Blends the two orginal images together. */
void blendImages(char *firstImage, char *secondImage,
                 char *combinedImage, int fileSize1);

/* Checker boards the two orginal images together. */
void checkerImages(char *firstImage, char *secondImage,
                   char *combinedImage, int fileSize1, int p1Height, 
                   int p1Width);

/*
 * This function will open the files and store them into pointers and will
 * output the image to a new file. Will call other functions to apply
 * the image manipulation for the file to be written to.
 */
int main(int argc, char **argv) {
  FILE *firstInfile, *secondInfile, *blendFile, *checkerFile;
  unsigned long fileSize1, fileSize2;
  char *firstImage, *secondImage, *changedImage; 
  int p1Height, p1Width, p2Height, p2Width;
  int bmFormat = 1;
  //check to make sure only 2 files are entered.
  if (argc == 3) {
    firstInfile = fopen(*(argv + 1), "rb");
    secondInfile = fopen(*(argv + 2), "rb");
    //Check if file is null.
    if (!(firstInfile == NULL || secondInfile == NULL)) {
      fileSize1 = get_file_size(*(argv + 1));
      fileSize2 = get_file_size(*(argv + 2));
      //This will make sure both files have information.
      if (fileSize1 > 0 && fileSize2 > 0) {
        bmFormat = readInFile(firstInfile, secondInfile, fileSize1,
                              fileSize2, &firstImage, &secondImage, 
                              &p1Height, &p1Width, &p2Height, &p2Width);
        //This will check both images are vaild.
        if (bmFormat && p1Width == p2Width && p1Height == p2Height) {
          blendFile = fopen("blend.bmp", "wb");
          checkerFile = fopen("checker.bmp", "wb");
          changedImage = (char*) malloc(fileSize1);
          blendImages(firstImage, secondImage, changedImage, fileSize1);
          fwrite(changedImage, sizeof(char), fileSize1, blendFile);
          checkerImages(firstImage, secondImage, changedImage, fileSize1, 
                        p1Height, p1Width);
          fwrite(changedImage, sizeof(char), fileSize1, checkerFile);
          free(changedImage);
          fclose(blendFile);
          fclose(checkerFile);
        }
      } 
    }
  } 
  return 0;
}

/*
 * This will get the incoming file and will store the result/ size 
 * of the file into a variable.
 *  - This will return the file size of the incoming file.
 */
unsigned long get_file_size(char *s) {
  long result = -1;
  struct stat file_info;
  if (stat(s, &file_info) > -1)
  {
    result = (unsigned long)(file_info.st_size);
  }
  return (unsigned long)result; 
} 

int readInFile(FILE * theFirstInfile, FILE * theSecondInfile, 
               unsigned long fileSize1, unsigned long fileSize2, 
               char** firstImage, char** secondImage, int *p1Height, 
               int *p1Width, int *p2Height, int *p2Width) {
  int boolean = 1;
  *firstImage = (char*) malloc(fileSize1);
  *secondImage = (char*) malloc(fileSize2);

  //This will read in the file
  fread(*firstImage, sizeof(char), fileSize1, theFirstInfile);
  fread(*secondImage, sizeof(char), fileSize2, theSecondInfile);
  //This is a char pointer to the file.
  char * p1 = *firstImage;
  char * p2 = *secondImage;
  //Check if both files are in bm format.
  if (!(*p1 == 'B' && *(p1 + 1) == 'M' && *p2 == 'B' && *(p2 + 1) == 'M')) {
    printf("ERROR! Files are not in BM format!\n");
    free(*firstImage);
    free(*secondImage);
    boolean = 0;
  } else {
      * p1Width = *(int*) (p1 + 18);
      * p1Height = *(int*) (p1 + 22);
      * p2Width = *(int*) (p2 + 18);
      * p2Height = *(int*) (p2 + 22);
  }
  return boolean;
}

/*
 * This will take the two images and will take the average of each one
 * and will will store it into a new image pointer of the combined image.
 */
void blendImages(char *firstImage, char *secondImage,
                 char *changedImage, int fileSize1) {
  int r;
  for (r = 0; r < 55; r++) {
    *(changedImage + r) = *(firstImage + r);
  }
  for(r = 55; r < fileSize1; r++) { 
     *(changedImage + r) = (unsigned char)*(firstImage + r) / 2 
                         + (unsigned char)*(secondImage + r) / 2;
  }
}

/*
 * This will take the two images and will checkerboard each one
 * and will will store it into a new image pointer of the combined image
 * Which should be a checkerboard of the two images.
 */
void checkerImages(char *firstImage, char *secondImage,
                 char *changedImage, int fileSize1, int p1Height, 
                 int p1Width) {
  int flag = 1;
  int counter = 1;
  int hCnt = 0;
  int r;
  for(r = 55; r < fileSize1; r++) {
    if (counter == p1Width * 3 / 8) {
      flag = !flag;
      counter = 0;
      hCnt++;
    }
    if (hCnt == p1Height ) {
      flag = !flag;
      hCnt = 0;
    }
    if (flag) {
      //george
      *(changedImage + r) = *(secondImage + r);
    } else {
      //lincoln
      *(changedImage + r) = *(firstImage + r);
    } 
    counter++;
  }
}