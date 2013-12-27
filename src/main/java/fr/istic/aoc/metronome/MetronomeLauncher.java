package fr.istic.aoc.metronome;

import javax.swing.JFrame;

import fr.istic.aoc.metronome.controller.MetronomeController;
import fr.istic.aoc.metronome.moteur.MoteurMetronome;
import fr.istic.aoc.metronome.view.Metronome;

/**
 * @author Jimmy
 */
public class MetronomeLauncher extends JFrame {

	private static final long serialVersionUID = -7836121271558471730L;

	private static final int width = 320;
	private static final int height = 200;

	private Metronome view;
	private MoteurMetronome engine;
	private MetronomeController controller;

	public MetronomeLauncher() {
		super("Métronomium V1");
		
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
		controller = new MetronomeController();

		controller.setMoteur(engine);
		controller.setView(view);
		controller.addControllerListener(view);
		engine.setEngineListener(controller);

		view.setController(controller);
	
		view.init();
		controller.init();
	}
	
	public static void main(String[] args) {
		new MetronomeLauncher();
	}
}
