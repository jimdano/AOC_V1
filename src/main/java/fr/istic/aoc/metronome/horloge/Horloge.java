package fr.istic.aoc.metronome.horloge;

import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

import fr.istic.aoc.components.api.IHorloge;
import fr.istic.aoc.components.command.ICommand;

/**
 * 
 * @author jimmy & Anthony
 * l'implémentation de la classe qui contient les différentes tâches à activer periodiquement ou après délai.
 */
public class Horloge implements IHorloge {

	/**
	 * la liste des taches
	 */
	private Hashtable<ICommand, Timer> taches;
	
	/**
	 * @return les taches asociées
	 */
	public Hashtable<ICommand, Timer> getTaches() {
		return taches;
	}

	/**
	 * @param taches les taches à affecter
	 */
	public void setTaches(Hashtable<ICommand, Timer> taches) {
		this.taches = taches;
	}

	/**
	 * constructeur, initialise les taches à une table de taches vide
	 */
	public Horloge() {
		taches = new Hashtable<ICommand, Timer>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void activerPeriodiquement(final ICommand cmd, int delay, int period) {		
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				cmd.execute();
			}
		};
		Timer t = new Timer();
		t.scheduleAtFixedRate(task, delay, period);
		taches.put(cmd, t);
	}

	/**
	 * {@inheritDoc}
	 */
	public void activerApresDelai(final ICommand cmd, int delay) {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				cmd.execute();
			}
		};
		Timer t = new Timer();
		t.schedule(task, delay);
		taches.put(cmd, t);
	}

	/**
	 * {@inheritDoc}
	 */
	public void desactiver(ICommand cmd) {
		Timer timer = taches.get(cmd);
		if (timer != null) {
			timer.cancel();
			taches.remove(cmd);
		}
	}

}
