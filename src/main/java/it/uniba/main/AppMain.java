package it.uniba.main;

/**
 * The main class for the project. It must be customized to meet the project
 * assignment specifications.
 *
 * <b>DO NOT RENAME</b>
 */

/** La classe AppMain si occupa di far partire l'esecuzione del programma.
 * Essendo una classe di tipo CONTROL si interfaccia con Damiera (ENTITY),
 * Tempo (ENTITY), GestioneCasi (BOUNDARY) e GestioneOutput (BOUNDARY).
 * AppMain alloca memoria degli oggetti di classi ENTITY e BOUNDARY.
 */

/** CONTROL */
public final class AppMain {
	/**
	 * Private constructor. Change if needed.
	 */
	private AppMain() {
	}

	/**
	 * * This is the main entry of the application.
	 *
	 * @param args The command-line arguments.
	 */
	public static void main(final String[] args) {
		Damiera damiera = new Damiera();  /** Costruttori utili all'impostazione del gioco. */
		GestioneCasi gestioneCasi = new GestioneCasi();
		GestioneOutput gestioneOutput = new GestioneOutput();
		Tempo tempo = new Tempo();

		/**
		 * Permette all'utente di poter vedere i comandi disponibili invocando l'applicazione e
		 * digitando successivamente "-h" o "--help".
		 */
		if (args.length > 0) {
			switch (args[0]) {
				case "-h":
					gestioneOutput.help();
					break;
				case "--help":
					gestioneOutput.help();
					break;
				default:

			}
		}
		gestioneCasi.menu(damiera, tempo);
	}
}
