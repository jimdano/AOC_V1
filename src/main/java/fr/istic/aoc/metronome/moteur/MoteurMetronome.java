package fr.istic.aoc.metronome.moteur;

import fr.isitc.aoc.metronome.state.IMoteurState;
import fr.isitc.aoc.metronome.state.StartState;
import fr.isitc.aoc.metronome.state.StopState;
import fr.istic.aoc.components.api.IHorloge;
import fr.istic.aoc.components.command.ICommand;
import fr.istic.aoc.metronome.horloge.Horloge;

public class MoteurMetronome implements IMoteur {

	private int tempo;
	private int bpm;
	private int bipCount;
	private IHorloge horloge;
	private ICommand bipCmd;
	private IMoteurState state = StopState.stop;
	private IMoteurListener listener;

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

	public boolean isStarted() {
		return state.isStarted();
	}

	public void start() {
		state.start(this, horloge);
		state = StartState.start;
		if (listener != null) {
			listener.onStart(tempo, bpm);
		}
	}

	public void stop() {
		state.stop(this, horloge);
		state = StopState.stop;
		if (listener != null) {
			listener.onStop();
		}
	}

	public int getBpm() {
		return bpm;
	}

	public void setBpm(int value) {
		bpm = value;
		if (this.listener != null) {
			listener.onBPMChanged(value);
		}
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int value) {
		tempo = value;
		state.setTempo(this, horloge);
		if (this.listener != null) {
			listener.onTempoChanged(value);
		}
	}

	public void setBipCommand(ICommand cmd) {
		bipCmd = cmd;
	}

	public ICommand getBipCommand() {
		return bipCmd;
	}

	public void setEngineListener(IMoteurListener listener) {
		this.listener = listener;
	}

	public void bip() {
		if (listener != null) {
			listener.onBip();
			bipCount++;
			if (bipCount % bpm == 0) {
				listener.onMesure();
				bipCount = 0;
			}
		}
	}
}
