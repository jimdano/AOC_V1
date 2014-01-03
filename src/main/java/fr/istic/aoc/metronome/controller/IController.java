package fr.istic.aoc.metronome.controller;

/**
 * @author jimmy & Anthony
 * interface regroupant les m�thodes utilis�es par le controller
 */
public interface IController {

	/**
	 * D�marre le moteur
	 */
	void start();
	
	/**
	 * stop le moteur
	 */
	void stop();
	
	/**
	 * augmente le nombre de battements par mesure
	 */
	void inc();

	/**
	 * diminue le nombre de battements par mesure
	 */
	void dec();

	/**
	 * mise � jour du tempo
	 */
	void tempo();
}
