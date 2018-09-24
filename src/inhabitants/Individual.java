package inhabitants;

import algorithm.FitnessCalculator;

public class Individual {

	private static int defaultGeneLength = 64;
	private byte[] genes = new byte[defaultGeneLength];
	private int fitness = 0;
	
	public static void setDefaultGeneLength(int defaultGeneLength) {
		Individual.defaultGeneLength = defaultGeneLength;
	}
	
	public Individual(boolean create) {
		if(create)generateIndividual();
	}
	
	private void generateIndividual() {
		for(int i = 0; i < genes.length; i++) {
			genes[i] = (byte) Math.round(Math.random());
		}
	}
	

	public int getFitness() {
		if(fitness == 0) {
			fitness = FitnessCalculator.getFitness(this);
		}
		return fitness;
	}
	
	public int getSize() {
		return genes.length;
	}
	
	public byte getGene(int index) {
		return genes[index];
	}
	
	public void setGene(int index, byte gene) {
		fitness = 0;
		genes[index] = gene;
	}
	
	@Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < getSize(); i++) {
            geneString += getGene(i);
        }
        return geneString;
    }
}
