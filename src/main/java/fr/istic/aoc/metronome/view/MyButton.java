package fr.istic.aoc.metronome.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;


/**
 * @author Jimmy & Anthony
 * Classe qui permet d'implanter la méthode touchepressée
 */
public class MyButton extends JButton implements MouseListener {

	private static final long serialVersionUID = 1L;
	/**
	 * valeur de pression du bouton
	 */
	private boolean pressed = false;

	/**
	 * Constructeur
	 * @param s le nom du bouton
	 */
	public MyButton(String s) {
		super(s);
	}

	/**
	 *
	 * @return la valeur de pression du bouton (true/false)
	 */
	public boolean isPressed() {
		return pressed;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	/**
	 * change la valeur de pression du bouton
	 */
	public void mousePressed(MouseEvent e) {
		pressed = true;
	}
	
	/**
	 * change la valeur de pression du bouton
	 */
	public void mouseReleased(MouseEvent e) {
		pressed = false;
	}
}
