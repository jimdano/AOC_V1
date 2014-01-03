package fr.istic.aoc.metronome.launcher;

import javax.swing.JFrame;

import fr.istic.aoc.metronome.controller.MetronomeController;
import fr.istic.aoc.metronome.moteur.MoteurMetronome;
import fr.istic.aoc.metronome.view.Metronome;

/**
 * @author Jimmy & anthony
 * le lanceur de l'application
 */
public class MetronomeLauncher extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int width = 280;
	private static final int height = 220;
	/**
	 * vue associée
	 */
	private Metronome view;
	/**
	 * moteur de métronome utilisé
	 */
	private MoteurMetronome moteur;

	/**
	 * initialisation de la vue et des composants nécessaire
	 */
	public MetronomeLauncher() {
		super("Métronomium V1");
		
		view = new Metronome();
		MetronomeController.getInstance();
		moteur = new MoteurMetronome(MetronomeController.INIT_TEMPO, MetronomeController.INIT_BPM);
		MetronomeController.getInstance().setMoteur(moteur);
		MetronomeController.getInstance().setView(view);
		MetronomeController.getInstance().addController(view);
		view.init();
		MetronomeController.getInstance().init();
		setSize(width, height);
		setLocationRelativeTo(this.getParent());
		add(this.view);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * lanceur de l'application
	 * @param args
	 */
	public static void main(String[] args) {
		new MetronomeLauncher();
	}
}
