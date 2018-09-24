package algorithm;

import inhabitants.Individual;

public class EvolutionResult {

	private long duration;
	private int generations;
	private Individual fittest;
	
	public EvolutionResult(long start, long end, int generations, Individual fittest) {
		super();
		this.duration = end - start;
		this.generations = generations;
		this.fittest = fittest;
	}
	
	public long getDuration() {
		return duration;
	}

	public int getGenerations() {
		return generations;
	}
	
	public Individual getFittest() {
		return fittest;
	}
	
	@Override
	public String toString() {
		return "Duration: " + SGAMain.formatDuration(duration) + " Generations: " + generations;
	}
	
}
