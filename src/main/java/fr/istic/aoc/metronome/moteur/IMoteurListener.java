package fr.istic.aoc.metronome.moteur;

/**
 * 
 * @author jimmy & Anthony
 * d�finie les m�thodes utilis�es par le controller
 * 
 */
public interface IMoteurListener {
	
	/**
	 * @param bpm la nouvelle valeur du bpm
	 */
	void onBPMChanged(int bpm);
	
	/**
	 * @param tempo le nouveau tempo
	 */
	void onTempoChanged(int tempo);
	
	/**
	 * notifie le listener que le moteur est en marche
	 * @param tempo courant
	 * @param bpm courant
 	 */
	void onStart(int tempo, int bpm);
	
	/**
	 * notifie le listener que le moteur est stopp�
	 */
	void onStop();
	
	/**
	 * notifie le listener pour bipper
	 */
	void onBip();
	
	/**
	 * notifie le listener pour marquer une mesure
	 */
	void onMesure();
}
