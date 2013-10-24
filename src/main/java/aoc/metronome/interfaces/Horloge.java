package aoc.metronome.interfaces;

import aoc.interfaces.patterncommand.ICommand;

public interface Horloge {
	/**
	 * appel de l'operation execute de cmd toute les periodeEnSeconde
	 * @param cmd la commande
	 * @param periodeEnSeconde la période d'activation de la commande
	 */
	void activerPeriodiquement(ICommand cmd, float periodeEnSeconde);
	/**
	 * appel de l'operation execute de cmd apres delaiEnSeconde
	 * @param cmd la commande
	 * @param delaiEnSeconde le delai avant l'activation de la commande
	 */
	void activerApresDela(ICommand cmd, float delaiEnSeconde);
	/**
	 * desactive la commande
	 * @param cmd la commande
	 */
	void desactiver(ICommand cmd);
}
