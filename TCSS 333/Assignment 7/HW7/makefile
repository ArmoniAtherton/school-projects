
# makefile
#
# Armoni Atherton
# TCSS 333 - Winter 2018
# Instructor: David Schuessler
# Assignment 7

myheap: mallok_test.o mallok.o 
	gcc -o myheap mallok_test.o mallok.o

mallok.o: mallok.c mallok.h
	gcc -c mallok.c

mallock_test.o: mallok_test.c mallok.c mallok.h
	gcc -c mallok_test.c
