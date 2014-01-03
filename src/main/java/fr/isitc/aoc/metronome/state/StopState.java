package fr.isitc.aoc.metronome.state;

import fr.istic.aoc.components.api.IHorloge;
import fr.istic.aoc.metronome.moteur.IMoteur;

/**
 * 
 * @author jimmy & Anthony
 * Repr�sente l'�tat d'arr�t du moteur m�tronome
 */
public class StopState implements IMoteurState {

	/**
	 * instance de l'�tat d'arret
	 */
	public static IMoteurState stop = new StopState();

	/**
	 * {@inheritDoc}
	 */
	public boolean isStarted() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void start(IMoteur moteur, IHorloge horloge) {
		float delay = (60 /(float) moteur.getTempo()) * 1000;
		horloge.activerPeriodiquement(moteur.getBipCommand(), 0, (int) delay);
	}

	/**
	 * {@inheritDoc}
	 */
	public void stop(IMoteur moteur, IHorloge horloge) {
	}

	/**
	 * {@inheritDoc}
	 */
	public void setTempo(IMoteur moteur, IHorloge horloge) {
	}
}
