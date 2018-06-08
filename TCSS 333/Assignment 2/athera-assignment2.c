/*
 * athera-assignment2.c
 *
 * Armoni Atherton
 * TCSS 333 - Winter 2018
 * Instructor: David Schuessler
 * Assignment 2
 */

#include <stdio.h>

/* The header size of the file. */
#define HEADER (54)

/* The size of the file excluding the header. */
#define FILE_SIZE (499392)

/* The row size of the image. */
#define ROW (408)

/* The column size of the image. */
#define COLUMN (408 * 3)

/* The pixel height to make a 8 * 8 Checker board. */
#define BOX_HEIGHT (51)

/* The pixel width to make a 8 * 8 Checker board. */
#define BOX_WIDTH (153)

/*****Function Prototypes*****/

/* Blends the two orginal images together. */
void blendImages(char[][COLUMN], char[][COLUMN],
                 char[][COLUMN]);

/* Checker boards the two orginal images together. */
void checkerImages(char[][COLUMN], char[][COLUMN],
                   char[][COLUMN]);

/*
 * This function will open the files and store them into arrays and will
 * output the image to a new file. Will call other functions to apply
 * the image manipulation for the file to be written to.
 */
int main(void) {
  FILE *firstInfile = fopen("in1.bmp", "rb"); //Open the file for reading.
  FILE *secondInfile = fopen("in2.bmp", "rb"); //Open the file for reading.
  FILE *blendOutfile = fopen("blend.bmp", "wb"); //Open the file for writing.
  //Store the header.
  char header[HEADER];
  //These will store images
  char firstImage[ROW][COLUMN];
  char secondImage[ROW][COLUMN];
  char combinedImage[ROW][COLUMN];
  //This will go through the first file and store image.
  fread(header, 1, HEADER, firstInfile);
  fread(firstImage, 1, FILE_SIZE, firstInfile);

  //This will go through the second file and store image.
  fread(header, 1, HEADER, secondInfile);
  fread(secondImage, 1, FILE_SIZE, secondInfile);
  //Will blend the images.
  blendImages(firstImage, secondImage, combinedImage);

  fwrite(header, sizeof(char), HEADER, blendOutfile);
  fwrite(combinedImage, sizeof(char), FILE_SIZE, blendOutfile);

  fclose(firstInfile);
  fclose(secondInfile);
  fclose(blendOutfile);
  /********************SECOND PART CHECKERBOARD*************************/
  FILE *CheckerOutfile = fopen("checker.bmp", "wb"); //Open the file for writing.
  //This will call function to checker the two images.
  checkerImages(firstImage, secondImage, combinedImage);

  fwrite(header, sizeof(char), HEADER, CheckerOutfile);
  fwrite(combinedImage, sizeof(char), FILE_SIZE, CheckerOutfile);
  fclose(CheckerOutfile);
}

/*
 * This will take the two images and will take the average of each one
 * and will will store it into a new image array of the combined image.
 */
void blendImages(char firstImage[][COLUMN], char secondImage[][COLUMN],
                 char combinedImage[][COLUMN]) {
  int r,c;
  //Will go through and calculate the average of the two pictures.
  for (r = 0; r < ROW; r++) {
    for ( c = 0; c < COLUMN; c++) {
      unsigned char temp1 = firstImage[r][c];
      unsigned char temp2 = secondImage[r][c];
      combinedImage[r][c] = (temp1 / 2) + (temp2 / 2); //Will average.
    }
  }
}

/*
 * This will take the two images and will checkerboard each one
 * and will will store it into a new image array of the combined image
 * Which should be a checkerboard of the two images.
 */
void checkerImages(char firstImage[][COLUMN], char secondImage[][COLUMN],
                   char combinedImage[][COLUMN]) {
  int r;
  int c = 0;
  int cnt = 0;
  //Will go through and calculate the average of the two pictures.
  for (r = 0; r < ROW; r++) {
    if (r % BOX_HEIGHT == 0) { //Amount of rows for one box.
      cnt++;
    }
    if (cnt % 2 == 0) {
        combinedImage[r][c] = firstImage[r][c]; //George
    } else {
        combinedImage[r][c] = secondImage[r][c]; //Lincoln
    }
    for (c = 0; c < COLUMN; c++) {
      if (c % BOX_WIDTH == 0) { //Amount of columns for one box.
        cnt++;
      }
      if (cnt % 2 == 0) {
        combinedImage[r][c] = firstImage[r][c]; //George
      } else {
          combinedImage[r][c] = secondImage[r][c]; //Lincoln
      }
    }
  }
}
