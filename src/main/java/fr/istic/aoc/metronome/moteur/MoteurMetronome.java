package fr.istic.aoc.metronome.moteur;

import fr.isitc.aoc.metronome.state.IMoteurState;
import fr.isitc.aoc.metronome.state.StartState;
import fr.isitc.aoc.metronome.state.StopState;
import fr.istic.aoc.components.api.IHorloge;
import fr.istic.aoc.components.command.ICommand;
import fr.istic.aoc.metronome.controller.MetronomeController;
import fr.istic.aoc.metronome.horloge.Horloge;

/**
 * @author jimmy & Anthony
 * la classe représentant le moteur de notre métronome.
 */
public class MoteurMetronome implements IMoteur {

	private int tempo;
	private int bpm;
	private int bipCount;

	private IHorloge horloge;
	private ICommand bipCmd;
	private IMoteurState state = StopState.stop;

	/**
	 * constructeur du moteur métronome
	 * @param tempo le tempo voulu
	 * @param bpm le bpm voulu au départ
	 */
	public MoteurMetronome(int tempo, int bpm) {
		this.tempo = tempo;
		this.bpm = bpm;
		this.bipCount = 0;
		this.bipCmd = new ICommand() {
			public void execute() {
				System.err.println("Pas de Bip command");
			}
		};
		this.horloge = new Horloge();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isStarted() {
		return state.isStarted();
	}

	/**
	 * {@inheritDoc}
	 */
	public void start() {
		state.start(this, horloge);
		state = StartState.start;
		MetronomeController.getInstance().onStart(tempo, bpm);
	}

	/**
	 * {@inheritDoc}
	 */
	public void stop() {
		state.stop(this, horloge);
		state = StopState.stop;
		MetronomeController.getInstance().onStop();
	}

	/**
	 * {@inheritDoc}
	 */
	public int getBpm() {
		return bpm;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setBpm(int value) {
		bpm = value;
		MetronomeController.getInstance().onBPMChanged(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public int getTempo() {
		return tempo;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setTempo(int value) {
		tempo = value;
		state.setTempo(this, horloge);
		MetronomeController.getInstance().onTempoChanged(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setBipCommand(ICommand cmd) {
		bipCmd = cmd;
	}

	/**
	 * {@inheritDoc}
	 */
	public ICommand getBipCommand() {
		return bipCmd;
	}

	/**
	 * {@inheritDoc}
	 */
	public void bip() {
		MetronomeController.getInstance().onBip();
		bipCount++;
		if (bipCount % bpm == 0) {
			MetronomeController.getInstance().onMesure();
			bipCount = 0;
		}
	}
	
	/**
	 * @return the bipCount
	 */
	public int getBipCount() {
		return bipCount;
	}

	/**
	 * @param bipCount the bipCount to set
	 */
	public void setBipCount(int bipCount) {
		this.bipCount = bipCount;
	}
}