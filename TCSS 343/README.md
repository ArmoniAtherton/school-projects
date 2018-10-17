# Algorthims
Projects for TCSS 343

### Project - Conoe Rental Problems
* There are n trading posts numbered 1 ton, as you travel downstream. At any trading posti,you can rent a canoe to be returned at any of the downstream trading posts j>i. You are given a cost array R(i,j) giving the cost of these rentals for 1≤i≤j≤n. We will have to assume that R(i,i)=0 and R(i,j)=∞ if i>j. For example, with n=4, the cost array may look as follows: The rows are the sources (i-s) and the columns are the destinations (j’s).


      1   2   3   4
    1  0   2   3   7
    
    2     0   2   4
    
    3         0   2

    4             0
    
    
* The problem is to find a solution that computes the cheapest sequence of rentals taking you from post 1 all the way down to post n. In this example, the cheapest sequence is to rent from post 1 to post 3 (cost 3), then from post 3 to post 4 (cost 2), with a total cost of 5 (less than the direct rental from post 1 to post 7, which would cost 7).

* Solve this using Brute Force, Divide and Conquer, and Dynamic Programming.

