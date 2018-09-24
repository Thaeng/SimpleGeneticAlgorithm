package algorithm;

import java.util.ArrayList;
import java.util.List;

import inhabitants.Population;

public class SGAMain {

	private static List<EvolutionResult> results = new ArrayList<>();
	
	public static void main(String[] args) {
		FitnessCalculator.setSolution("1111000000000000000000000000000000000000000000000000000000001111");
		SimpleGeneticAlgorithm alg = new SimpleGeneticAlgorithm(0.8, 0.01, 15, true);
		
		
		for(int i = 0; i < 1000; i++) {
			EvolutionResult result = startNewPopulation(alg);
			System.out.println("[" + i + "] " + result);
			results.add(result);
		}
		
		double averageDuration = results.stream().mapToLong(result -> result.getDuration()).average().getAsDouble();
		double minDuration = results.stream().mapToLong(result -> result.getDuration()).min().getAsLong();
		double maxDuration = results.stream().mapToLong(result -> result.getDuration()).max().getAsLong();
		
		double averageGenerations = results.stream().mapToInt(result -> result.getGenerations()).average().getAsDouble();
		int minGenerations = results.stream().mapToInt(result -> result.getGenerations()).min().getAsInt();
		int maxGenerations = results.stream().mapToInt(result -> result.getGenerations()).max().getAsInt();
		
		System.out.println("Minimum Duration: " + formatDuration((long)minDuration));
		System.out.println("Maximum Duration: " + formatDuration((long)maxDuration));
		System.out.println("Average Duration: " + formatDuration((long)averageDuration));
		System.out.println("Minimum Generations: " + minGenerations);
		System.out.println("Maximum Generations: " + maxGenerations);
		System.out.println("Average Generations: " + averageGenerations);
	}
	
	public static EvolutionResult startNewPopulation(SimpleGeneticAlgorithm alg) {
		Population pop = new Population(100, true);
		int generations = 0;

		int fittestValue = pop.getFittest().getFitness();
		long start = System.currentTimeMillis();
		while (fittestValue < FitnessCalculator.getMaxFitness()) {
			generations++;
			pop = alg.evolvePopulation(pop);
			fittestValue = pop.getFittest().getFitness();
		}		
		long end = System.currentTimeMillis();		
		return new EvolutionResult(start, end, generations, pop.getFittest());		
	}
	
	public static  String formatDuration(long duration) {
		long hours = (duration / (1000*60*60)) % 60;
		long minutes = (duration / (1000*60)) % 60;
		long seconds = (duration / 1000) % 60;
		long millis = duration - ((hours*1000*60*60) + (minutes*1000*60) + (seconds*1000));
		
		return format(hours) + ":" + format(minutes) + ":" + format(seconds) + " " + millis;
	}
	
	private static String format(long l1) {
		String val = "";
		if(l1 < 10) {
			val+="0";
		}
		val+=l1;
		return val;
	}

}
