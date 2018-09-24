package inhabitants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Population {

	private List<Individual> individuals;

	public Population(int populationSize, boolean create) {
		individuals = new ArrayList<>();
		if (create) {
			createPopulation(populationSize);
		}
	}

	public Individual getFittest() {
		return Collections.max(individuals, Comparator.comparing(i -> i.getFitness()));
	}

	private void createPopulation(int populationSize) {
		for (int i = 0; i < populationSize; i++) {
			individuals.add(new Individual(true));
		}
	}

	public int getPopulationSize() {
		return individuals.size();
	}

	public Individual getIndividual(int index) {
		return individuals.get(index);
	}

	public void saveIndividual(int index, Individual individual) {
		if (index >= individuals.size()) {
			individuals.add(individual);
		} else {
			individuals.set(index, individual);
		}
	}

}
