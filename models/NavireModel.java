/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.jdom2.Element;

/**
 * Cette classe est le model correspondant aux navires
 * 
 * @author Cibert Gauthier
 * @author Martini Didier
 * @author Responsable: Martini Didier
 * @version 3.0
 */
public class NavireModel extends Point implements Observer {

	private static Element		configuration;
	private static final long	serialVersionUID	= 1L;

	private final String		identifiant;
	private final Point			positionNavire;
	private final String		type;
	private final String		flotte;
	private final String		joueurEnControle;
	private Dimension			deplacement;
	private Dimension			deplacementVoulu;
	private int					vitesseNavire;
	private int					vitesseNavireVoulue;
	private final int			etat;
	private final int			vitesseMaxNavire;
	private final int			nombreSoldat;
	private final int			munitionsRestantes;

	private static final int	CERCLE_TRIGO		= 360;
	private final Logger		logger;

	/**
	 * @param identifiant
	 * @param positionNavire
	 * @param type
	 * @param flotte
	 * @param joueurEnControle
	 * @param deplacement
	 * @param vitesseNavire
	 * @param etat
	 * @param vitesseMaxNavire
	 * @param nombreSoldat
	 * @param munitionsRestantes
	 * @param config
	 */
	public NavireModel(final String identifiant, final Point positionNavire,
					final String type, final String flotte,
					final String joueurEnControle, final Dimension deplacement,
					final int vitesseNavire, final int etat,
					final int vitesseMaxNavire, final int nombreSoldat,
					final int munitionsRestantes, final Element config) {

		super(positionNavire.x, positionNavire.y);

		NavireModel.configuration = config;
		DOMConfigurator.configure(NavireModel.configuration.getChild("logger")
						.getChildText("config"));
		this.logger = Logger.getLogger(this.getClass().getName());

		this.identifiant = identifiant;
		this.positionNavire = positionNavire;
		this.type = type;
		this.flotte = flotte;
		this.joueurEnControle = joueurEnControle;
		this.deplacement = deplacement;
		this.deplacementVoulu = deplacement;
		this.vitesseNavire = vitesseNavire;
		this.vitesseNavireVoulue = vitesseNavire;
		this.etat = etat;
		this.vitesseMaxNavire = vitesseMaxNavire;
		this.nombreSoldat = nombreSoldat;
		this.munitionsRestantes = munitionsRestantes;

	}

	/**
	 * @return int
	 */
	public float getAngle() {

		float a = ((float) ((Math.atan2(this.positionNavire.y
						- this.deplacement.getHeight(),
						this.deplacement.getWidth() - this.positionNavire.x) * (NavireModel.CERCLE_TRIGO / 2)) / Math.PI));

		if (a < 0) {

			a = NavireModel.CERCLE_TRIGO + a;
		}

		return a;

	}

	/**
	 * @return int
	 */
	public float getAngleVoulu() {

		float a = ((float) ((Math.atan2(this.positionNavire.y
						- this.deplacementVoulu.getHeight(),
						this.deplacementVoulu.getWidth()
										- this.positionNavire.x) * (NavireModel.CERCLE_TRIGO / 2)) / Math.PI));

		if (a < 0) {

			a = NavireModel.CERCLE_TRIGO + a;
		}

		return a;

	}

	/**
	 * @return double
	 */
	public Dimension getDirection() {

		return this.deplacement;
	}

	/**
	 * @return double
	 */
	public Dimension getDirectionVoulu() {

		return this.deplacementVoulu;
	}

	/**
	 * @return int
	 */
	public int getEtat() {

		return this.etat;
	}

	/**
	 * @return String
	 */
	public String getFlotte() {

		return this.flotte;
	}

	/**
	 * @return String
	 */
	public String getIdentifiant() {

		return this.identifiant;
	}

	/**
	 * @return String
	 */
	public String getJoueurEnControle() {

		return this.joueurEnControle;
	}

	/**
	 * @return the logger
	 */
	public Logger getLogger() {

		return this.logger;
	}

	/**
	 * @return int
	 */
	public int getMunitionsRestantes() {

		return this.munitionsRestantes;
	}

	/**
	 * @return int
	 */
	public int getNombreSoldat() {

		return this.nombreSoldat;
	}

	/**
	 * @return Point
	 */
	public Point getPointTrigo() {

		final float a = this.getAngleVoulu();
		return new Point((int) Math.cos(a), (int) Math.sin(a));

	}

	/**
	 * @return Point
	 */
	public Point getPointTrigoVoulu() {

		final float a = this.getAngleVoulu();
		return new Point((int) Math.cos(a), (int) Math.sin(a));

	}

	/**
	 * @return Point
	 */
	public Point getPositionNavire() {

		return this.positionNavire;
	}

	/**
	 * @return String
	 */
	public String getType() {

		return this.type;
	}

	/**
	 * @return int
	 */
	public int getVitesseMaxNavire() {

		return this.vitesseMaxNavire;
	}

	/**
	 * @return int
	 */
	public int getVitesseNavire() {

		return this.vitesseNavire;
	}

	/**
	 * @return int
	 */
	public int getVitesseVoulueNavire() {

		return this.vitesseNavireVoulue;

	}

	/**
	 * @param direction
	 */
	public void setDirection(final Dimension direction) {

		this.deplacement = direction;

	}

	/**
	 * @param direction
	 */
	public void setDirectionVoulu(final Dimension direction) {

		this.deplacementVoulu = direction;

	}

	/**
	 * @param speed
	 */
	public void setSpeed(final int speed) {

		this.vitesseNavire = speed;

	}

	/**
	 * @param speed
	 */
	public void setSpeedVoulue(final int speed) {

		this.vitesseNavireVoulue = speed;

	}

	public void update(final Observable o, final Object arg) {

		if (arg instanceof Element) {

			NavireModel.configuration = (Element) arg;

		}

	}

}
