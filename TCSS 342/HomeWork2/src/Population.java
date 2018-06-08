/*
 * Population.java
 * 
 * TCSS 342 - Spring 2018
 * Armoni Atherton
 * Instructor: Paulo Barreto
 * Assignment-2
 * 
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class will make a population of genomes and will 
 * simulate a evolution. Using the principles survival of the 
 * most fit.
 * 
 * @author Armoni
 * @version April 16, 2018 
 */
public class Population {
	
	/** This will hold a list of Genomes simulating a population. */
	protected List<Genome> myPopulation;
	
	/**This will keep track of the most fit genome in a population. */
	protected Genome myMostFit;
	
	/** This will allow for the selecting at random. */
	private Random myRandom = new Random();
	
	/** The current number of genomes in the population. */
	private Integer myNumGenomes;
	
	/** The mutation rate of the population. */
	private Double myMutationRate;
	
	/**
	 * This is the constructor to make a population. Will put in all genomes into 
	 * the population with requested size.
	 * 
	 * @param numGenomes The size of the population.
	 * @param mutationRate The rate at which the genomes should mutate.
	 */
	public Population(Integer numGenomes, Double mutationRate) {
		myNumGenomes = numGenomes;
		myMutationRate = mutationRate;
		myPopulation = new ArrayList<>();
		
		for (int i = 0; i < numGenomes; i++) {
			myPopulation.add(new Genome(myMutationRate));
		}	
		myMostFit = myPopulation.get(0);
	}
	
	/**
	 * This function is called every breeding cycle. Will update the most fit in the population, 
	 * delete half the of the least fit and populate the size of the list again to original size by 
	 * crossing over the top fit genomes.
	 */
	public void day() {
		//This will sort if lowest to highest.
		Collections.sort(myPopulation, (o1, o2) -> (o1.fitness() - o2.fitness()));
		//Will set the most Fit.
		myMostFit = myPopulation.get(0);
		int halfPop = myPopulation.size() / 2;
		//This will half the population.
		for (int i = myNumGenomes -1; i >= halfPop; i--) {
			myPopulation.remove(i);
		}
		
		//This will choose the parent.
		int ranGenerate = myRandom.nextInt(2);
		//This will pick from the strongest half of the population.
		int ranGenome = myRandom.nextInt(halfPop);
		//Will go through until you populate the population.
		for (int i = halfPop; i < myNumGenomes; i++) { 
			//Will mutate it
			if (ranGenerate == 0) {
				Genome copy = new Genome(myPopulation.get(ranGenome));
				copy.mutate();
				myPopulation.add(copy);
				//This will crossover the two.
			} else {
				Genome copy1 = new Genome(myPopulation.get(ranGenome));
				ranGenome = myRandom.nextInt(halfPop);
//				Genome copy2 = new Genome(myPopulation.get(ranGenome));
				ranGenome = myRandom.nextInt(halfPop);
				copy1.crossover(myPopulation.get(ranGenome));
				copy1.mutate();
				myPopulation.add(copy1);
			}
			ranGenerate = myRandom.nextInt(2);
		}	
	}
}
