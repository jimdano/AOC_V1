package fr.istic.aoc.metronome.controller;

import java.util.Timer;
import java.util.TimerTask;

import fr.isitc.aoc.metronome.command.BipCommand;
import fr.istic.aoc.metronome.moteur.IMoteur;
import fr.istic.aoc.metronome.moteur.IMoteurListener;
import fr.istic.aoc.metronome.moteur.MoteurMetronome;
import fr.istic.aoc.metronome.view.IControllerListener;
import fr.istic.aoc.metronome.view.IView;


public class MetronomeController implements  IController, IMoteurListener{

	private static final int LED_FLASH_DELAY = 120;
	private static final int MIN_TEMPO = 0;
	private static final int MIN_BPM = 2;
	private static final int MAX_TEMPO = 200;
	private static final int MAX_BPM = 7;
	private static final int INIT_TEMPO = 120;
	private static final int INIT_BPM = 4;
	
	private IView view;
	private IControllerListener listener;
	private IMoteur moteur;

	/** Constructeur priv� */	
	private MetronomeController() {
	}
 
	/** Holder */
	private static class SingletonHolder
	{		
		/** Instance unique non pr�initialis�e */
		private final static MetronomeController instance = new MetronomeController();
	}
 
	/** Point d'acc�s pour l'instance unique du singleton */
	public static MetronomeController getInstance()
	{
		return SingletonHolder.instance;
	}
	
	public void init() {
		this.view.setTempoConstants(MIN_TEMPO, MAX_TEMPO, INIT_TEMPO);
		this.view.setBPMConstants(MIN_BPM, MAX_BPM, INIT_BPM);
		this.moteur.setBipCommand(new BipCommand(moteur));

		if (this.moteur.isStarted()){
			if (listener != null){
				this.listener.onStart(moteur.getTempo(), moteur.getBpm());
			}
		} else if (listener != null) {
			this.listener.onStop();
		}
	}
	
	public void start() {
		if(!moteur.isStarted()){
			moteur.start();
		}
	}

	public void stop() {
		if(moteur.isStarted()){
			moteur.stop();
		}
	}

	public void inc() {
		if(moteur.getBpm()+1 <= MAX_BPM) {
			moteur.setBpm(moteur.getBpm()+1);
		}
	}

	public void dec() {
		if(moteur.getBpm()-1 >= MIN_BPM) {
			moteur.setBpm(moteur.getBpm()-1);
		}
	}

	public void tempo() {
		moteur.setTempo((int) view.getMolette().position());
	}

	public void onBPMChanged(int bpm) {
		if (listener != null) {
			this.listener.onBPMChanged(bpm);
		}
	}

	public void onTempoChanged(int tempo) {
		if (listener != null) {
			this.listener.onTempoChanged(tempo);
		}
	}

	public void onStart(int tempo, int bpm) {
		if (listener != null) {
			this.listener.onStart(tempo, bpm);
		}
	}

	public void onStop() {
		if (listener != null) {
			this.listener.onStop();
		}
	}

	public void onBip() {
		view.getAfficheur().allumerLed(0);
		Timer timer = new Timer();
		TimerTask t = new TimerTask() {
			@Override
			public void run() {
				view.getAfficheur().eteindreLed(0);
			}
		};
		timer.schedule(t, LED_FLASH_DELAY);
	}

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
		timer.schedule(t, LED_FLASH_DELAY);
	}

	public void setMoteur(IMoteur moteur) {
		this.moteur = moteur;
	}
	
	public IMoteur getMoteur() {
		return moteur;
	}

	public void setView(IView view) {
		this.view = view;
	}
	
	public IView getView(){
		return view;
	}

	public void addControllerListener(IControllerListener listener) {
		this.listener = listener;
	}
}
