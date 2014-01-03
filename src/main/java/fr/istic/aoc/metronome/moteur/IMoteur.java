package fr.istic.aoc.metronome.moteur;

import fr.istic.aoc.components.command.ICommand;

/**
 * 
 * @author jimmy & Anthony
 * interface d�finissant les m�thodes du moteur m�tronome
 */
public interface IMoteur {

	/**
	 * @return true => moteur d�marr�, false sinon
	 */
	boolean isStarted();
	
	/**
	 * d�marre le moteur
	 */
	void start();
	
	/**
	 * Stop le moteur
	 */
	void stop();
	
	/**
	 * @return bpm courant
	 */
	int getBpm();
	
	/**
	 * setter du bpm
	 * @param value nouvelle valeur pour le bpm
	 */
	void setBpm(int value);
	
	/**
	 * @return le tempo courant
	 */
	int getTempo();
	
	/**
	 * setter du tempo
	 * @param value la nouvelle valeur de tempo
	 */
	void setTempo(int value);
	
	/**
	 * la commande utilis�e pour les battements est d�finie ici
	 * @param cmd la commande
	 */
	void setBipCommand(ICommand beatCmd);
	
	/**
	 * @return la commande utilis�e pour les battements
	 */
	ICommand getBipCommand();
	
	/**
	 * effectue le bip
	 */
	void bip();
}
