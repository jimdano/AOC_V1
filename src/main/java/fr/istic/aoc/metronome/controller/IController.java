package fr.istic.aoc.metronome.controller;

/**
 * @author jimmy & Anthony
 * interface regroupant les méthodes utilisées par le controller
 */
public interface IController {

	/**
	 * Démarre le moteur
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
	 * mise à jour du tempo
	 */
	void tempo();
}
