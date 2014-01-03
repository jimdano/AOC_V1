package fr.istic.aoc.metronome.controller;

/**
 * @author jimmy & Anthony
 * interface regroupant les méthodes utilisées par le controller et la vue
 */
public interface IControllerListener {
	
	/**
	 * changement de BPM
	 * @param bpm
	 */
	void onBPMChanged(int bpm);
	
	/**
	 * changement de tempo
	 * @param tempo
	 */
	void onTempoChanged(int tempo);
	
	/**
	 * au lancement du métronome
	 * @param tempo
	 * @param bpm
	 */
	void onStart(int tempo, int bpm);

	/**
	 * à l'arret du métronome
	 */
	void onStop();
}
