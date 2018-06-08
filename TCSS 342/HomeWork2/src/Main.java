/*
 * Main.java
 * 
 * TCSS 342 - Spring 2018
 * Armoni Atherton
 * Instructor: Paulo Barreto
 * Assignment-2
 * 
 */
import java.util.concurrent.TimeUnit;

public class Main {
	
	/**
	 * Static main method used to run the program and test the 
	 * program elements.
	 * 
	 * @param args Command line arguments.
	 */

	public static void main(String[] args) {
		//This will start the timer.
		long startTime = System.nanoTime();
		Population currentPopulation = new Population(100, 0.05);
		int cnt = 0;
		while(currentPopulation.myMostFit.fitness() != 0) {
			currentPopulation.day();
			System.out.println("Generation: "+ cnt + ": " +currentPopulation.myMostFit.toString() + ", "+ currentPopulation.myMostFit.fitness());
			cnt++;
		}
		long totalTime = System.nanoTime() - startTime;
		double seconds =TimeUnit.MILLISECONDS.convert(totalTime, TimeUnit.NANOSECONDS);
		System.out.println();
		System.out.println("Total Number of Generations: " + --cnt + ", Total Time in Milliseconds: " + seconds);
//		testGenome();
//		testPopulation();
	}
	
	/**
	 * This method will test my genome class functionality.  Will test again all 
	 * the different methods and will see if it is working correctly.
	 */
	public static void testGenome() {
		//This checks constructors
		System.out.println("TESTING GENOME CLASS\n");
		System.out.println("If Expected Value and Actual Value are same the test Passed!!");
		Genome test = new Genome(0.05);
		System.out.println("Test Constructor - Expected is  'A', Actually: " + test.toString());
		
		System.out.println("\nMaking a clone using constuctor using current genome.....");
		Genome clone = new Genome(test);
		System.out.println("Test Clone Constructor To Check both are New Objects - Expectded Equality is false, Acutally: " + test.equals(clone));
		
		//This checks mutate method.
		System.out.println("\nTesting Mutate with 100 iterations...");
		System.out.println("Genomes Original state should mutate by either adding or changing or removing Characters.");
		for(int i = 0; i < 100; i++) {
			test.mutate();
		}
		System.out.println("If String has mutated at all test have passed: " + test.toString());
		
		//This checks crossover.
		System.out.println("\nFirst Genome: " + test.toString() + ", Second Genome: " + clone.toString());
		test.crossover(clone);
		System.out.println("Will Crossover the two Genomes should have a high change to cross letters: " + test.toString());
		
		//This checks Fitness.
		System.out.println("\nTest Fitness - Expected Fitness is 77, Actually: " + clone.fitness());
		System.out.println("Testing fitness to check if fitness value changes when mutated: ");
		for(int i = 0; i < 100; i++) {
			clone.mutate();
		}
		System.out.println("Value should be something besides 77: Actually: " + clone.fitness());
		System.out.println("\nEND OF TESTING GENOME CLASS");
		
		//This checks toString
		Genome test1 = new Genome(0.05);
		System.out.println("\nTest toString - Expected String is 'A', Actually: " + test1.toString());
	}
	
	/**
	 * This method will test my population class functianilty.  Will test again all 
	 * the different methods and will see if it is working correctly.
	 */
	public static void testPopulation() {
		System.out.println("TESTING POPULATION CLASS\n");
		
		//This will test the constructor.
		Population currentPopulation = new Population(100, 0.5);
		System.out.println("Testing Constructor - Most Fit expected 'A', Actually: " + currentPopulation.myMostFit);
		System.out.println("Testing Constructor - Most Fit expected 77 Actually: " + currentPopulation.myMostFit.fitness());
		System.out.println("Testing Constructor - Array Size expected 100, Actually: " + currentPopulation.myPopulation.size());
		
		System.out.println("\nNow testing Day method with 200 iterations...");
		System.out.println("Intially set to Most fit: " + currentPopulation.myMostFit + ", Fitness: " + currentPopulation.myMostFit.fitness());
		for(int i = 0; i < 200; i++) {
			if (i == 99) {
				System.out.println("\nHalf Way through the Most fit should be getting more fit.");
				System.out.println(currentPopulation.myMostFit + ", fitness: " + currentPopulation.myMostFit.fitness());
				currentPopulation.day();
			} else {
				currentPopulation.day();
			}
		}
		System.out.println("\nEnd, Most fit should be even closer when compared to the half way point.");
		System.out.println(currentPopulation.myMostFit + ", fitness: " + currentPopulation.myMostFit.fitness() + "\n");
		
		System.out.println("END OF TESTING POPULATION CLASS");

	}
}
