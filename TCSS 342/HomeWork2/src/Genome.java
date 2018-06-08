/*
 * Genome.java
 * 
 * TCSS 342 - Spring 2018
 * Armoni Atherton
 * Instructor: Paulo Barreto
 * Assignment-2
 * 
 */
import java.util.Random;

/**
 * This class will set up a Single Genome that will allow for various 
 * mutations to a single genome. Will allow for crossingover of two genomes
 * and calculating the fitness.
 * 
 * @author Armoni
 * @version April 16, 2018 
 */
public class Genome {
	
	/** This is the Size of the total characters in the string world. */
	public final static int  SIZE = 29;
	
	/** This is all the total chars that are possible. */
	private char myCharacters[]  = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 
														'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 
														'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '_',
														' ', '\''};
	
	/** Contains the string that you will be your target to recreate. */
	private String myTarget = "PAULO SERGIO LICCIARDI MESSEDER BARRETO";
	
	/** This is will hold the mutation rate. */
	private int myMuationRate;
	
	/** This is the fitness level of the genome. */
	public int myFitness;
	
	/** This will be what the genome letters will be stored in. */ 
	private StringBuilder myGenome;
	
	/** This will allow for the selecting at random. */
	private Random myRandom = new Random();

	/**
	 * This is the constructor that will create a new genome with a default 
	 * char and the mutation rate passed in by the user.
	 * 
	 * @param mutationRate This is the incoming decimal number used as the mutation rate.
	 */
	public Genome(double mutationRate) {
		myMuationRate = ((int)(1 / mutationRate));
		myGenome = new StringBuilder();
		myGenome.append('A');

	}
	
	/**
	 * This is the copy constructor that will create a copy of a incoming genome 
	 * allowing  for making a new genome without corrupting the data
	 * of the last genome.
	 * 
	 * @param gene This is the incoming genome to copy.
	 */
	public Genome(Genome gene) {
		myMuationRate = gene.myMuationRate;
		myGenome = new StringBuilder();
		myGenome.append(gene.myGenome.toString());
	}
	
	/**
	 * This method will mutate the genome by possibly adding a new character in the genome,
	 * possibly deleting a randomly selected character in a string and possible changing a 
	 * character in the genome to a different value.
	 */
	public void mutate() {
		//This will get a randomly secected character.
		int randomCharacter = myRandom.nextInt(SIZE);
		//This will do a 1/20 chance..
		boolean randomChance = myRandom.nextInt(myMuationRate) == 0;
//		mutationRate chance add a randomly selected character to a randomly selected position in the string.
		if (randomChance) {
			//This will go up to the length plus one since exclusive.
			int index = myRandom.nextInt(myGenome.length() + 1);
			//This will check if it is the length of the string append to the end.
			if (index == myGenome.length()) {
				//Add to end
				myGenome.append(myCharacters[randomCharacter]);
			} else {
				//Add to Beginning or Middle.
				myGenome.insert(index, myCharacters[randomCharacter]);
			}
		}
		//This will randomly delete a character in a string.
		randomChance = myRandom.nextInt(myMuationRate) == 0;
//		with mutationRate chance delete a single character from a randomly selected position of the string but do this only if the string has length at least 2.
		if (randomChance && myGenome.length() >= 2) {
			int index = myRandom.nextInt(myGenome.length());
			myGenome.deleteCharAt(index);
		}
		
		//This will randomly change character in a string.
		for (int i = 0; i < myGenome.length(); i++) {
			randomChance = myRandom.nextInt(myMuationRate) == 0;
			if (randomChance) {
				randomCharacter = myRandom.nextInt(SIZE);
				int temp = i;
				myGenome.replace(i, temp + 1, String.valueOf(myCharacters[randomCharacter]));
			}
		}
	}
	
	/**
	 * This will allow for crossing over of two Genomes. When given two Genomes create
	 *  a third that is a combination of the two.
	 *  
	 * @param other The incoming Genome to crossover.
	 */
	void crossover(Genome other) {
		int index = 0, flag = 1;
		StringBuilder crossedName = new StringBuilder();
		StringBuilder parent = new StringBuilder();
		parent.append(other.toString());
		
		int pickParent = myRandom.nextInt(2);
		while (flag == 1) {
			//This will pick parent 1.
			if (pickParent == 0) {
				//Check if there are still characters to grab from.
				if (index < myGenome.length() ) {
					crossedName.append(myGenome.charAt(index));
					index++;
				} else {
					flag = 0;
				}
			//This will pick parent 2.
			} else {
				//Check if there are still characters to grab from.
				if (index < parent.length()) {
					crossedName.append(parent.charAt(index));
					index++;
				} else {
					flag = 0;
				}
			}
			pickParent = myRandom.nextInt(2);
		}
		myGenome = crossedName;

	}
	
	/**
	 * This allows for measuring a Genome fitness. Calculate how close the string in the Genome is from
	 *  your target name using the simple calculation below.
	 *  
	 * @return the fitness level of the current genome.
	 */
	public Integer fitness() {
		//This will calculate fitness using Levenshtein edit distance. Uncomment for testing extra credit.
//		 myFitness = extraCredit();
		int n = myGenome.length();
		int m = myTarget.length();
		int L = Math.max(n,m);
		int d = Math.min(m, n);
		int f = Math.abs(m-n);
		for (int i = 0; i < L; i++) {
			//If one string is empty will not thought exception
			if (i < d) {
				char temp = myGenome.charAt(i);
				if (temp != myTarget.charAt(i)) {
					f++;
				} 
			} else {
				f++;
			}
		}
		myFitness = f;
		return myFitness;
	}
	
	/**
	 * This is the extra credit using the Levenshtein edit distance algorithm.
	 * 
	 * @return the fitness level of the current genome.
	 */
	private Integer extraCredit() {
		int n = myGenome.length(); //1
		int m = myTarget.length(); //39
		int [][] D = new int[n + 1][m + 1];
		
		//Will be the indiceise
		int c = 0;
		
		//This will set the indicises of first row and columns.
		for (int r = 0; r <= n; r++) {
			//Set the first column.
				D[r][0] = r;
			} 
			for (c = 0; c <= m; c++ ) {
				//This will set row. 
				D[0][c] = c;
		}
		//This is the algorithm.
		for (int r = 1; r <= n; r++) {
			for (c = 1; c <= m; c++) {
				if (myGenome.charAt(r - 1) == myTarget.charAt(c - 1)) {
					D[r][c] = D[r - 1][c - 1];
				} else {
					D[r][c] = Math.min(D[r - 1][c] + 1, Math.min(D[r][c - 1] + 1, D[r - 1][c - 1] + 1));
				}
			}
		}
		return D[n][m]+(Math.abs(n - m) + 1) / 2;
	}
	
	/**
	 * This will allow for a visual string representation of the current Genome.
	 */
	public String toString() {
		return myGenome.toString(); 
		
	}
}