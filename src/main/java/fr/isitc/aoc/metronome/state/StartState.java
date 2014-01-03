package fr.isitc.aoc.metronome.state;

import fr.istic.aoc.components.api.IHorloge;
import fr.istic.aoc.components.command.ICommand;
import fr.istic.aoc.metronome.moteur.IMoteur;

/**
 * 
 * @author jimmy & Anthony
 * Repr�sente l'�tat de mise en marche du moteur m�tronome
 */
public class StartState implements IMoteurState {

	/**
	 * instance de l'�tat marche
	 */
	public static IMoteurState start = new StartState();
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isStarted() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public void start(IMoteur moteur, IHorloge horloge) {
	}

	/**
	 * {@inheritDoc}
	 */
	public void stop(IMoteur moteur, IHorloge horloge) {
		horloge.desactiver(moteur.getBipCommand());
	}

	/**
	 * {@inheritDoc}
	 */
	public void setTempo(IMoteur moteur, IHorloge horloge) {
		ICommand bip = moteur.getBipCommand();
		horloge.desactiver(bip);
		float delay = (60 /(float) moteur.getTempo()) * 1000;
		horloge.activerPeriodiquement(bip, (int) delay, (int) delay);
	}
}
