package algorithm;

import inhabitants.Individual;
import inhabitants.Population;

public class SimpleGeneticAlgorithm {

    private double uniformRate;
    private double mutationRate;
    private int tournamentSize;
    private boolean elitism;
    
	public SimpleGeneticAlgorithm(double uniformRate, double mutationRate, int tournamentSize, boolean elitism) {
		super();
		this.uniformRate = uniformRate;
		this.mutationRate = mutationRate;
		this.tournamentSize = tournamentSize;
		this.elitism = elitism;
	}
	
	public Population evolvePopulation(Population pop) {
		Population newPopulation = new Population(pop.getPopulationSize(), false);
		
		
		int elitismOffset = 0;
		if(elitism) {
			newPopulation.saveIndividual(0, pop.getFittest());
			elitismOffset = 1;
		}
		
		for(int i = elitismOffset; i < pop.getPopulationSize(); i++) {
			Individual mother = tournamentSelection(pop);
			Individual father = tournamentSelection(pop);
			Individual child = mutate(crossover(mother, father));
			newPopulation.saveIndividual(i, child);
		}
		
		return newPopulation;
		
	}
	
	
	private Individual crossover(Individual i1, Individual i2) {
		Individual newInd = new Individual(false);
		
		for(int i = 0; i < i1.getSize(); i++) {
			if(Math.random() <= uniformRate) {
				newInd.setGene(i, i1.getGene(i));
			}else {
				newInd.setGene(i, i1.getGene(i));
			}
		}
		
		return newInd;		
	}
	
	private Individual mutate(Individual indiv) {
		for(int i = 0; i < indiv.getSize(); i++) {
			if(Math.random() <= mutationRate) {
				indiv.setGene(i, (byte) Math.round(Math.random()));
			}
		}
		return indiv;
	}
	
	private Individual tournamentSelection(Population pop) {
		Population tournament = new Population(tournamentSize, false);
		
		for(int i = 0; i < tournamentSize; i++) {
			int rndInt = (int) (Math.random() * pop.getPopulationSize());
			tournament.saveIndividual(i, pop.getIndividual(rndInt));
		}
		return tournament.getFittest();
	}

}
