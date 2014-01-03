/**
 * 
 */
package fr.isitc.aoc.metronome.command;

import fr.istic.aoc.components.command.ICommand;
import fr.istic.aoc.metronome.moteur.IMoteur;

/**
 * @author jimmy & Anthony
 * Classe qui représente la concrete command dans le patron de conception command
 */
public class BipCommand implements ICommand{

	private IMoteur m;
	
	/**
	 * constructeur
	 * @param moteur le moteurMetronome qui execute les bip
	 */
	public BipCommand(IMoteur moteur) {
		this.m = moteur;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		if(m != null){
			m.bip();	
		}
		else System.err.println("Pas de MoteurMetronome");
	}
}
