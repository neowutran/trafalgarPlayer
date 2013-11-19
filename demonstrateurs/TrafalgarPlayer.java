package demonstrateurs;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import models.NavireModel;

import org.jdom2.Element;

import controllers.TrafalgarPlayerController;

/**
 * Cette classe va donner l'etat a charger (navires, meteo, appartenance des
 * territoires)
 * 
 * @author Cibert Gauthier
 * @author Martini Didier
 * @author Responsable: Martini Didier
 * @version 3.0
 */
public class TrafalgarPlayer extends Observable {

	private final TrafalgarPlayerController	controller;
	private static Element					config;

	/**
	 * @param controller
	 */
	public TrafalgarPlayer(final TrafalgarPlayerController controller,
					final Element config) {

		this.controller = controller;
		TrafalgarPlayer.config = config;
	}

	/**
	 * Genere et envoie le vecteur d'etat qui permet de tester
	 * trafalgarPlayer
	 */
	public void etat1() {

		final Map vecteur = new HashMap();

		final Map<String, NavireModel> navires = new HashMap<String, NavireModel>();
		navires.put("navire1", new NavireModel("Orage", new Point(150, 850),
						"Orage", "Flotte geniale", "Ennemi",
						new Dimension(1, 1), 100, 200, 200, 150, 3,
						TrafalgarPlayer.config));
		navires.put("navire2", new NavireModel("Charle de gaule", new Point(
						880, 300), "Charle de gaule", "Flotte geniale",
						"Ennemi", new Dimension(1, 1), 100, 200, 200, 150, 3,
						TrafalgarPlayer.config));
		navires.put("navire3", new NavireModel("joueur", new Point(425, 285),
						"Joueur", "Flotte des PGM", "joueur", new Dimension(1,
										1), 100, 200, 200, 150, 3,
						TrafalgarPlayer.config));

		vecteur.put("navires", navires);

		vecteur.put("meteo", "6");

		final Map<Integer, String> troupes = new HashMap<Integer, String>();
		troupes.put(3, "Flotte geniale");
		troupes.put(1, "Flotte geniale");
		troupes.put(2, "Flotte geniale");
		troupes.put(4, "Flotte geniale");
		troupes.put(5, "Flotte des PGM");
		troupes.put(6, "Flotte des PGM");

		vecteur.put("troupe", troupes);

		this.update(vecteur);

	}

	/**
	 * @param vecteur
	 */
	public void update(final Map<?, ?> vecteur) {

		this.controller.update(vecteur);
	}

}
