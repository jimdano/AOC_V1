package fr.istic.aoc.metronome.view;

import fr.istic.aoc.metronome.simulation.ISimulation;

/**
 * @author Jimmy & Anthony
 * Interface qui contient les différentes méthodes utilisées par la vue, pour changer le tempo, le bpm..
 */
public interface IView extends ISimulation {

	/**
	 * initialise les valeurs possibles du tempo
	 * @param minTempo valeur minimale
	 * @param maxTempo valeur maximale
	 * @param defaultTempo valeur à l'initialisation
	 */
	void setTempoValues(int minTempo, int maxTempo, int defaultTempo);

	/**
	 * initialise les valeurs possibles du bpm
	 * @param minBpm valeur minimale
	 * @param maxBpm valeur maximale
	 * @param defaultBpm valeur à l'initialisation
	 */
	void setBPMValues(int minBpm, int maxBpm, int defaultBpm);
}
