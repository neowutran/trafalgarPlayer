package controllers;

import java.awt.Point;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import library.ObjectString;
import models.MapModel;
import models.NavireModel;
import models.TerritoireModel;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.jdom2.Element;

import views.MapView;
import config.GenererConfig;
import demonstrateurs.Demonstrateur;
import demonstrateurs.TrafalgarPlayer;

/**
 * Cette classe est le controller de trafalgarPlayer, il gerera les interaction
 * necessaires entre vues et models
 * 
 * @author Cibert Gauthier
 * @author Martini Didier
 * @author Responsable: Martini Didier
 * @version 3.0
 */
public class TrafalgarPlayerController {

	private static Element		configuration;
	private final Logger		logger;
	private final MapModel		model;
	private final MapView		view;
	private boolean				requiredDir;
	public static final String	NOM_JOUEUR	= "joueur";
	private NavireModel			selectedNavire;
	private final String		pathConfig;

	/**
	 * @param config
	 * @param getBackground
	 * @param pathConfig
	 */
	public TrafalgarPlayerController(final Element config,
					final String getBackground, final String pathConfig) {

		TrafalgarPlayerController.configuration = config;
		DOMConfigurator.configure(config.getChild("logger").getChildText(
						"config"));
		this.logger = Logger.getLogger(this.getClass().getName());
		try {

			this.logger.debug("[constructor]Element:"
							+ ObjectString.toString(config));

		} catch (final IOException e) {

			this.logger.error("[" + e.getClass().getName() + "]"
							+ e.getMessage());

		}
		this.model = new MapModel(TrafalgarPlayerController.configuration);

		String background;
		if (getBackground != null) {

			background = getBackground;

		} else {

			background = TrafalgarPlayerController.configuration.getChild(
							this.model.getClass().getName()).getChildText(
							"backgroundImage");

		}

		this.pathConfig = pathConfig;
		this.model.setPathImage(background);
		this.model.load(Demonstrateur.FOLDER + Demonstrateur.CARTE);
		(new TrafalgarPlayer(this, TrafalgarPlayerController.configuration))
						.etat1();
		this.view = new MapView(TrafalgarPlayerController.configuration, this);
		this.model.addObserver(this.view);

	}

	/**
	 * @param traceDirection
	 */
	public void changeDirection(final Point traceDirection) {

		// action a faire ici

	}

	/**
	 * @param x
	 *            Valeur des coordonnées X du curseur de la souris
	 * @param y
	 *            Valeur des coordonnées Y du curseur de la souris
	 */
	public void click(final int x, final int y) {

		// Si on est censés attendre le prochain clic pour déterminer une
		// direction
		if (this.requiredDir) {

			// action a faire ici

			this.requiredDir = false;
		}

	}

	/**
	 * @param x
	 *            Valeur des coordonnées X du curseur de la souris
	 * @param y
	 *            Valeur des coordonnées Y du curseur de la souris
	 */
	public void clickReleased(final int x, final int y) {

		final String configMainPanel = "views.MainPanel";
		final String configNavire = "navire";
		final String configTailleDetection = "taille_detection";
		final Iterator<NavireModel> itNavires = this.model.getNavires()
						.values().iterator();
		while (itNavires.hasNext()) {
			final NavireModel navire = itNavires.next();
			if (((x > (navire.x - Integer
							.valueOf(TrafalgarPlayerController.configuration
											.getChild(configMainPanel)
											.getChild(configNavire)
											.getChildText(configTailleDetection)))) && (x < (navire.x + Integer
							.valueOf(TrafalgarPlayerController.configuration
											.getChild(configMainPanel)
											.getChild(configNavire)
											.getChildText(configTailleDetection)))))
							&& ((y > (navire.y - Integer
											.valueOf(TrafalgarPlayerController.configuration
															.getChild(configMainPanel)
															.getChild(configNavire)
															.getChildText(configTailleDetection)))) && (y < (navire.y + Integer
											.valueOf(TrafalgarPlayerController.configuration
															.getChild(configMainPanel)
															.getChild(configNavire)
															.getChildText(configTailleDetection)))))) {
				this.selectedNavire = navire;
				this.view.printNavire(navire);
				return;
			}
		}

		final List<TerritoireModel> territoires = this.model.getTerritoire();
		for (final TerritoireModel entry : territoires) {

			if (entry.contains(x, y)) {

				this.selectedNavire = null;
				this.view.printTroupe(entry);

			}
		}

	}

	/**
	 * 
	 */
	public void debarquer() {

		// action a faire ici

	}

	/**
	 * 
	 */
	public void embarquer() {

		// action a faire ici

	}

	/**
	 * @return String
	 */
	public String getCurrentSavePath() {

		return this.model.getCurrentSavePath();

	}

	/**
	 * @return String
	 */
	public String getImagePath() {

		return this.model.getPathImage();

	}

	/**
	 * @return int
	 */
	public int getMeteo() {

		return this.model.getMeteo();

	}

	/**
	 * @param index
	 * @param indexPoint
	 * @return String
	 */
	public String getNamePort(final int index, final int indexPoint) {

		return this.model.getNamePort(index, indexPoint);

	}

	/**
	 * @return NavireModel
	 */
	public NavireModel getNavireJoueur() {

		return this.model.getNavireJoueur();

	}

	/**
	 * @return Map
	 */
	public Map<String, NavireModel> getNavires() {

		// TODO Auto-generated method stub
		return this.model.getNavires();
	}

	/**
	 * @return NavireModel
	 */
	public NavireModel getSelectedNavire() {

		return this.selectedNavire;
	}

	/**
	 * @return Map
	 */
	public List<TerritoireModel> getTerritoire() {

		this.logger.debug("[return][Map<String, TerritoireModel>]"
						+ this.model.getTerritoire());
		return this.model.getTerritoire();

	}

	/**
	 * @param index
	 * @return TerritoireModel
	 */
	public TerritoireModel getTerritoire(final int index) {

		return this.model.getTerritoire(index);

	}

	/**
	 * 
	 */
	public void requireDir() {

		this.requiredDir = true;
	}

	private void saveConfig(final Element config) {

		final GenererConfig saveConfig = new GenererConfig(config);
		saveConfig.setPathFile(this.pathConfig);
		saveConfig.genererXML();

	}

	/**
	 * @param config
	 */
	public void setConfig(final Element config) {

		TrafalgarPlayerController.configuration = config;
		this.saveConfig(config);
		this.model.updateConfiguration(config);

	}

	/**
	 * @param x
	 * @param y
	 */
	public void setDirection(final int x, final int y) {

		this.model.setDirection(TrafalgarPlayerController.NOM_JOUEUR, x, y);

	}

	/**
	 * @param x
	 * @param y
	 */
	public void setDirectionVoulu(final int x, final int y) {

		this.model.setDirectionVoulu(TrafalgarPlayerController.NOM_JOUEUR, x, y);

	}

	/**
	 * @param imagePath
	 */
	public void setImagePath(final String imagePath) {

		this.model.setPathImage(imagePath);

	}

	/**
	 * @param speed
	 */
	public void setSpeed(final int speed) {

		this.model.setSpeed(speed);

	}

	/**
	 * @param speed
	 */
	public void setSpeedVoulue(final int speed) {

		this.model.setSpeedVoulue(speed);

	}

	/**
	 * 
	 */
	public void tirer() {

		// action a faire ici

	}

	/**
	 * @param vecteur
	 */
	public void update(final Map<?, ?> vecteur) {

		this.model.update(vecteur);

	}

}