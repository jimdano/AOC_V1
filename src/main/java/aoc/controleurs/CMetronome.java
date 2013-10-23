package aoc.controleurs;

import aoc.abstractions.Metronome;
import aoc.interfaces.controle.ICMetronome;
import aoc.presentations.PMetronome;

public class CMetronome extends Metronome implements ICMetronome {

	private PMetronome p;
	
	public CMetronome() {
		super();
	}
	
}
