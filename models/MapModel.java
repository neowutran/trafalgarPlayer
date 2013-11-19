package models;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import library.ObjectString;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import controllers.TrafalgarPlayerController;
import demonstrateurs.Demonstrateur;

/**
 * Cette classe est le model principal qui a co elaboré pour trafalgarMap et
 * trafalgarPlayer (etant donnée que les besoins etaient dans l'ensemble les
 * memes)
 * Ce model "contient" tous les autres. Elle contient entre autres les
 * territoires et les
 * navires
 * 
 * @author Cibert Gauthier
 * @author Martini Didier
 * @author Responsable: Martini Didier
 * @version 3.0
 */
public final class MapModel extends Observable implements Serializable,
				Cloneable {

	/**
	 * 
	 */
	private static final long			serialVersionUID		= 1L;
	private final List<TerritoireModel>	territoire				= new ArrayList<TerritoireModel>();
	private Map<String, NavireModel>	navires					= new HashMap<String, NavireModel>();
	private String						pathImage;

	private final Logger				logger;

	private static org.jdom2.Document	document;
	private static Element				racine;

	private static Element				racineenregistrer		= new Element(
																				"Map");

	private Color						couleurtmp;
	private String						nomtmp;

	private static Element				configuration;
	private String						currentSavePath			= "";
	private NavireModel					navireJoueur;
	private int							meteo;

	private static final String			POINTCONFIGNAME			= "models.PointModel";
	private static final String			TERRITOIRECONFIGNAME	= "models.TerritoireModel";

	/**
	 * @param config
	 */
	public MapModel(final Element config) {

		MapModel.configuration = config;
		DOMConfigurator.configure(config.getChild("logger").getChildText(
						"config"));
		this.logger = Logger.getLogger(this.getClass().getName());

	}

	/**
	 * @param points
	 * @param c
	 * @param name
	 */
	public void addTerritoire(final List<PointModel> points, final Color c,
					final String name) {

		final TerritoireModel ajoutTerritoire = new TerritoireModel(points, c,
						name, MapModel.configuration, this);

		this.territoire.add(ajoutTerritoire);
		this.setChanged();
		this.notifyObservers();

	}

	/**
	 * @param index
	 * @param c
	 */
	public void colorTerritoire(final int index, final Color c) {

		this.territoire.get(index).setColor(c);
		this.setChanged();
		this.notifyObservers();

	}

	/**
	 * @return String
	 */
	public String getCurrentSavePath() {

		return this.currentSavePath;

	}

	/**
	 * @return int
	 */
	public int getMeteo() {

		return this.meteo;

	}

	/**
	 * @param index
	 * @param indexPoint
	 * @return String
	 */
	public String getNamePort(final int index, final int indexPoint) {

		return this.territoire.get(index).getPoint().get(indexPoint).getName();

	}

	/**
	 * @return NavireModel
	 */
	public NavireModel getNavireJoueur() {

		return this.navireJoueur;
	}

	/**
	 * @return Map
	 */
	public Map<String, NavireModel> getNavires() {

		return this.navires;
	}

	/**
	 * @return String
	 */
	public String getPathImage() {

		return this.pathImage;

	}

	/**
	 * @return Map
	 */
	public List<TerritoireModel> getTerritoire() {

		return this.territoire;

	}

	/**
	 * @param index
	 * @return TerritoireModel
	 */
	public TerritoireModel getTerritoire(final int index) {

		return this.territoire.get(index);

	}

	/**
	 * @param xmlPath
	 */
	public void load(final String xmlPath) {

		this.currentSavePath = xmlPath;
		final SAXBuilder sxb = new SAXBuilder();

		try {

			MapModel.document = sxb.build(new File(xmlPath));

		} catch (final Exception e) {
			this.logger.error("[" + e.getClass().getName() + "]"
							+ e.getMessage());

		}

		try {

			MapModel.racine = MapModel.document.getRootElement();

		} catch (final Exception e) {
			this.logger.error("[" + e.getClass().getName() + "]"
							+ e.getMessage());

			MapModel.racine = null;

		}

		if (MapModel.racine != null) {

			this.pathImage = (MapModel.racine)
							.getChildText(MapModel.configuration.getChild(
											this.getClass().getName())
											.getChildText("textImagefond"));

			if (((this.pathImage != null) && !(new File(this.pathImage)
							.exists()))
							|| this.pathImage.equals("SystemDefault")) {

				this.pathImage = Demonstrateur.FOLDER
								+ Demonstrateur.BACKGROUND;

			}

			this.loadTerritoire();
			this.setChanged();
			this.notifyObservers();

		}

	}

	private void loadColor(final Element color) {

		final String couleur = "couleur";

		final int red = Integer
						.parseInt(color.getChildText(MapModel.configuration
										.getChild(MapModel.TERRITOIRECONFIGNAME)
										.getChild(couleur)
										.getChildText("textRed")));
		final int green = Integer
						.parseInt(color.getChildText(MapModel.configuration
										.getChild(MapModel.TERRITOIRECONFIGNAME)
										.getChild(couleur)
										.getChildText("textGreen")));
		final int blue = Integer
						.parseInt(color.getChildText(MapModel.configuration
										.getChild(MapModel.TERRITOIRECONFIGNAME)
										.getChild(couleur)
										.getChildText("textBlue")));
		final int alpha = Integer
						.parseInt(color.getChildText(MapModel.configuration
										.getChild(MapModel.TERRITOIRECONFIGNAME)
										.getChild(couleur)
										.getChildText("textAlpha")));
		this.couleurtmp = new Color(red, green, blue, alpha);

	}

	private void loadPoint(final Element t) {

		boolean port;
		final LinkedList<PointModel> points = new LinkedList<PointModel>();

		for (final Element point : t.getChildren(MapModel.configuration
						.getChild(MapModel.POINTCONFIGNAME).getChildText(
										"textPoint"))) {

			if (point.getChildText(
							MapModel.configuration.getChild(
											MapModel.POINTCONFIGNAME)
											.getChildText("textPort")).equals(
							"true")) {

				port = true;

			} else {

				port = false;
			}

			final PointModel ajoutPoint = new PointModel(
							Integer.parseInt(point
											.getChildText(MapModel.configuration
															.getChild(MapModel.POINTCONFIGNAME)
															.getChildText("textX"))),
							Integer.parseInt(point
											.getChildText(MapModel.configuration
															.getChild(MapModel.POINTCONFIGNAME)
															.getChildText("textY"))),
							port, point.getChildText(MapModel.configuration
											.getChild(MapModel.POINTCONFIGNAME)
											.getChildText("textPoint")),
							MapModel.configuration);

			points.add(ajoutPoint);
		}

		this.addTerritoire(points, this.couleurtmp, this.nomtmp);

	}

	private void loadTerritoire() {

		for (final Element e : MapModel.racine
						.getChildren(MapModel.configuration.getChild(
										MapModel.TERRITOIRECONFIGNAME)
										.getChildText("textTerritoire"))) {

			this.loadColor(e.getChild(MapModel.configuration.getChild(
							MapModel.TERRITOIRECONFIGNAME).getChildText(
							"textCouleur")));
			this.nomtmp = new String(e.getChildText(MapModel.configuration
							.getChild(MapModel.TERRITOIRECONFIGNAME)
							.getChildText("textNom")));
			this.loadPoint(e);

		}

	}

	/**
	 * @param index
	 */
	public void removeTerritoire(final int index) {

		this.territoire.remove(index);
		this.setChanged();
		this.notifyObservers();

	}

	/**
	 * @param navire
	 * @param x
	 * @param y
	 */
	public void setDirection(final String navire, final int x, final int y) {

		final Dimension direction = new Dimension(x, y);
		this.navireJoueur.setDirection(direction);
		this.updateNavireJoueur();
		this.setChanged();
		this.notifyObservers();

	}

	/**
	 * @param navire
	 * @param x
	 * @param y
	 */
	public void setDirectionVoulu(final String navire, final int x, final int y) {

		final Dimension direction = new Dimension(x, y);
		this.navireJoueur.setDirectionVoulu(direction);
		this.updateNavireJoueur();
		this.setChanged();
		this.notifyObservers();

	}

	/**
	 * @param index
	 * @param newName
	 */
	public void setName(final int index, final String newName) {

		this.territoire.get(index).setName(newName);
		this.setChanged();
		this.notifyObservers();

	}

	/**
	 * @param index
	 * @param indexPoint
	 * @param newName
	 */
	public void setNamePort(final int index, final int indexPoint,
					final String newName) {

		(((this.territoire.get(index)).getPoint()).get(indexPoint))
						.setName(newName);
		this.setChanged();
		this.notifyObservers();

	}

	/**
	 * @param path
	 */
	public void setPathImage(final String path) {

		this.pathImage = path;

		this.setChanged();
		this.notifyObservers();

	}

	/**
	 * @param index
	 * @param indexPoint
	 */
	public void setPort(final int index, final int indexPoint) {

		this.territoire.get(index).getPoint().get(indexPoint).setPort();
		this.setChanged();
		this.notifyObservers();

	}

	/**
	 * @param speed
	 */
	public void setSpeed(final int speed) {

		this.navireJoueur.setSpeed(speed);
		this.updateNavireJoueur();
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * @param speed
	 */
	public void setSpeedVoulue(final int speed) {

		this.navireJoueur.setSpeedVoulue(speed);
		this.updateNavireJoueur();
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * @param xmlPath
	 */
	public void store(final String xmlPath) {

		this.logger.debug("[method]String:" + xmlPath);
		this.currentSavePath = xmlPath;
		this.toXML();
		MapModel.racineenregistrer = this.toXML();

		try {

			final XMLOutputter sortie = new XMLOutputter(
							org.jdom2.output.Format.getPrettyFormat());
			sortie.output(MapModel.racineenregistrer, new FileOutputStream(
							xmlPath));

		} catch (final IOException e) {

			this.logger.error("[" + e.getClass().getName() + "]"
							+ e.getMessage());

		}

	}

	/**
	 * @return Element
	 */
	public Element toXML() {

		final Element xml = new Element("Map");
		final Element imagefond = new Element(MapModel.configuration.getChild(
						this.getClass().getName())
						.getChildText("textImagefond"));
		imagefond.addContent(this.pathImage);
		xml.addContent(imagefond);

		for (final TerritoireModel entry : this.territoire) {

			xml.addContent((entry).toXML());

		}

		try {
			this.logger.debug("[return][Element]" + ObjectString.toString(xml));
		} catch (final IOException e) {
			this.logger.error("[" + e.getClass().getName() + "]"
							+ e.getMessage());
		}
		return xml;

	}

	/**
	 * @param index
	 * @param x
	 * @param y
	 */
	public void translateTerritoire(final int index, final int x, final int y) {

		this.logger.debug("[method]int:" + index + ";int:" + x + ";int:" + y);
		this.territoire.get(index).translate(x, y);

	}

	/**
	 * @param vecteur
	 */
	public void update(final Map<?, ?> vecteur) {

		this.navires = (Map<String, NavireModel>) vecteur.get("navires");

		for (final NavireModel navire : this.navires.values()) {
			if (navire.getJoueurEnControle().equals(
							TrafalgarPlayerController.NOM_JOUEUR)) {
				this.navireJoueur = navire;
			}
		}

		final Map<?, ?> troupes = (Map<?, ?>) vecteur.get("troupe");
		for (final TerritoireModel appartenanceTerritoire : this.territoire) {

			appartenanceTerritoire.setAppartenance((String) troupes
							.get(this.territoire
											.indexOf(appartenanceTerritoire)));
		}

		this.meteo = Integer.valueOf((String) vecteur.get("meteo"));

		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * @param config
	 */
	public void updateConfiguration(final Element config) {

		try {
			this.logger.debug("[metohd]Element:"
							+ ObjectString.toString(config));
		} catch (final IOException e) {
			this.logger.error("[" + e.getClass().getName() + "]"
							+ e.getMessage());

		}

		MapModel.configuration = config;

		this.setChanged();
		this.notifyObservers(config);
	}

	/**
	 * 
	 */
	public void updateNavireJoueur() {

		this.navires.remove(TrafalgarPlayerController.NOM_JOUEUR);
		this.navires.put(TrafalgarPlayerController.NOM_JOUEUR,
						this.navireJoueur);

	}
}
