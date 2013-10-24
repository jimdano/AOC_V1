package aoc.abstractions;

import java.rmi.activation.ActivationGroupDesc.CommandEnvironment;

import aoc.interfaces.patterncommand.ICommand;

public abstract class Metronome {

	private long tempo;
	private int tempoParMesure;
	private boolean etatMarche;
	
	public Metronome() {
		
	}
	
	public long getTempo() {
		return tempo;
	}
	
	public int getTempoParMesure() {
		return tempoParMesure;
	}
	
	public void setTempo(long t) {
		tempo = t;
	}
	
	public void setTempsParMesure(int tps){
		tempoParMesure = tps;
	}
	
	public void setCmd(String str, ICommand c) {
		
	}
	
	public void setEtatMarche(boolean b){
		etatMarche = true;
	}
	
	public void setEtatStop(boolean b){
		 setEtatMarche = false;
	}
}
