/**
 * 
 */
package fr.isitc.aoc.metronome.command;

import fr.istic.aoc.components.command.ICommand;
import fr.istic.aoc.metronome.moteur.IMoteur;

/**
 * @author jimmy
 *
 */
public class BipCommand implements ICommand{

	private IMoteur m;
	
	public BipCommand(IMoteur moteur) {
		this.m = moteur;
	}
	
	public void execute() {
		if(m != null){
			m.bip();	
		}
		else System.out.println("Pas de MoteurMetronome");
	}
}
