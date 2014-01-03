package fr.istic.aoc.metronome.controller;

import java.util.Timer;
import java.util.TimerTask;

import fr.isitc.aoc.metronome.command.BipCommand;
import fr.istic.aoc.metronome.moteur.IMoteur;
import fr.istic.aoc.metronome.moteur.IMoteurListener;
import fr.istic.aoc.metronome.view.IView;

/**
 * 
 * @author jimmy & Anthony
 * Classe qui représente le controller unique de l'application
 */
public class MetronomeController implements  IController, IMoteurListener{

	/**
	 * variables de bases, initialise les données maximales minimales et d'initialisation du métronome
	 */
	private static final int LED_FLASH = 150;
	public static final int MIN_TEMPO = 0;
	public static final int MIN_BPM = 2;
	public static final int MAX_TEMPO = 200;
	public static final int MAX_BPM = 7;
	public static final int INIT_TEMPO = 100;
	public static final int INIT_BPM = 4;
	
	/**
	 * la vue associé ou on effectuera les changements graphiques
	 */
	private IView view;
	/**
	 * le controller associé pour effectuer des changements visuels
	 */
	private IControllerListener listener;
	/**
	 * le moteur du métronome
	 */
	private IMoteur moteur;

	/** Constructeur privé */	
	private MetronomeController() {
	}
 
	/** Holder */
	private static class SingletonHolder
	{		
		/** Instance unique non préinitialisée */
		private final static MetronomeController instance = new MetronomeController();
	}
 
	/** Point d'accès pour l'instance unique du singleton */
	public static MetronomeController getInstance()
	{
		return SingletonHolder.instance;
	}
	
	/**
	 * initialisation du métronome
	 */
	public void init() {
		this.view.setTempoValues(MIN_TEMPO, MAX_TEMPO, INIT_TEMPO);
		this.view.setBPMValues(MIN_BPM, MAX_BPM, INIT_BPM);
		this.moteur.setBipCommand(new BipCommand(moteur));

		if (this.moteur.isStarted()){
			if (listener != null){
				this.listener.onStart(moteur.getTempo(), moteur.getBpm());
			}
		} else if (listener != null) {
			this.listener.onStop();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void start() {
		if(!moteur.isStarted()){
			moteur.start();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void stop() {
		if(moteur.isStarted()){
			moteur.stop();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void inc() {
		if(moteur.getBpm()+1 <= MAX_BPM) {
			moteur.setBpm(moteur.getBpm()+1);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void dec() {
		if(moteur.getBpm()-1 >= MIN_BPM) {
			moteur.setBpm(moteur.getBpm()-1);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void tempo() {
		moteur.setTempo((int) view.getMolette().position());
	}

	/**
	 * {@inheritDoc}
	 */
	public void onBPMChanged(int bpm) {
		if (listener != null) {
			this.listener.onBPMChanged(bpm);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void onTempoChanged(int tempo) {
		if (listener != null) {
			this.listener.onTempoChanged(tempo);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void onStart(int tempo, int bpm) {
		if (listener != null) {
			this.listener.onStart(tempo, bpm);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void onStop() {
		if (listener != null) {
			this.listener.onStop();
		}
	}

	/**
	 * bip pour le tempo ou la mesure si nécessaire
	 */
	public void onBip() {
		view.getAfficheur().allumerLed(0);
		Timer timer = new Timer();
		TimerTask t = new TimerTask() {
			@Override
			public void run() {
				view.getAfficheur().eteindreLed(0);
			}
		};
		timer.schedule(t, LED_FLASH);
	}

	/**
	 * bip pour la mesure
	 */
	public void onMesure() {
		view.getEmetteur().emettreClick();
		view.getAfficheur().allumerLed(1);
		Timer timer = new Timer();
		TimerTask t = new TimerTask() {
			@Override
			public void run() {
				view.getAfficheur().eteindreLed(1);
			}
		};
		timer.schedule(t, LED_FLASH);
	}

	/**
	 * setter du moteur
	 * @param moteur
	 */
	public void setMoteur(IMoteur moteur) {
		this.moteur = moteur;
	}
	
	/**
	 * getter du moteur
	 * @return
	 */
	public IMoteur getMoteur() {
		return moteur;
	}

	/**
	 * setter de la vue
	 * @param view
	 */
	public void setView(IView view) {
		this.view = view;
	}
	
	/**
	 * getter de la vue
	 * @return la vue attachée
	 */
	public IView getView(){
		return view;
	}

	/**
	 * affecte le listener
	 * @param listener
	 */
	public void addController(IControllerListener listener) {
		this.listener = listener;
	}
}
