package fr.istic.aoc.metronome;

import javax.swing.JFrame;

import fr.istic.aoc.metronome.controller.MetronomeController;
import fr.istic.aoc.metronome.moteur.MoteurMetronome;
import fr.istic.aoc.metronome.view.Metronome;

/**
 * @author Jimmy
 */
public class MetronomeLauncher extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int width = 280;
	private static final int height = 220;

	private Metronome view;
	private MoteurMetronome engine;

	public MetronomeLauncher() {
		super("M�tronomium V1");
		
		this.initComponents();
		this.setSize(width, height);
		this.setLocationRelativeTo(this.getParent());
		this.add(this.view);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void initComponents() {
		view = new Metronome();
		engine = new MoteurMetronome(60, 4);
		
		MetronomeController.getInstance().setMoteur(engine);
		MetronomeController.getInstance().setView(view);
		MetronomeController.getInstance().addControllerListener(view);
		engine.setEngineListener(MetronomeController.getInstance());

		view.setController(MetronomeController.getInstance());
		view.init();
		MetronomeController.getInstance().init();
	}
	
	public static void main(String[] args) {
		new MetronomeLauncher();
	}
}