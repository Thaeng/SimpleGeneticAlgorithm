package algorithm;

import inhabitants.Individual;

public class FitnessCalculator {

	private static byte[] solution;

	public static void setSolution(byte[] solution) {
		FitnessCalculator.solution = solution;
	}

	public static void setSolution(String newSolution) {
		FitnessCalculator.solution = new byte[newSolution.length()];
		for (int i = 0; i < newSolution.length(); i++) {
			String character = newSolution.substring(i, i + 1);
			if (character.contains("0") || character.contains("1")) {
				FitnessCalculator.solution[i] = Byte.parseByte(character);
			} else {
				FitnessCalculator.solution[i] = 0;
			}
		}
	}
	
	public static int getFitness(Individual individual) {
		int fitness = 0;
		for(int i = 0; i < individual.getSize() && i < FitnessCalculator.solution.length; i++) {
			if(individual.getGene(i) == solution[i]) {
				fitness++;
			}
		}
		return fitness;
	}
	
	public static int getMaxFitness() {
		return solution.length;
	}

}
