package fr.isitc.aoc.metronome.state;

import fr.istic.aoc.components.api.IHorloge;
import fr.istic.aoc.metronome.moteur.IMoteur;

/**
 * 
 * @author jimmy & Anthony
 * Etat du moteur du métronome
 */
public interface IMoteurState {

	/**
	 * @return retourne une valeur dépendante de l'état courant
	 */
	boolean isStarted();
	
	/**
	 * Start le moteur 
	 * @param moteur
	 * @param horloge
	 */
	void start(IMoteur moteur, IHorloge horloge);
	
	/**
	 * stop le moteur
	 * @param moteur
	 * @param horloge
	 */
	void stop(IMoteur moteur, IHorloge horloge);
	
	/**
	 * valeur du tempo qui changent
	 * @param moteur
	 * @param horloge
	 */
	void setTempo(IMoteur moteur, IHorloge horloge);
}
