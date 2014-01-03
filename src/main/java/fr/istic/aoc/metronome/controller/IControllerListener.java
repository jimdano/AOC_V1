package fr.istic.aoc.metronome.controller;

/**
 * @author jimmy & Anthony
 * interface regroupant les m�thodes utilis�es par le controller et la vue
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
	 * au lancement du m�tronome
	 * @param tempo
	 * @param bpm
	 */
	void onStart(int tempo, int bpm);

	/**
	 * � l'arret du m�tronome
	 */
	void onStop();
}
