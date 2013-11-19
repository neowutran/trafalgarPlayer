package views;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import library.ObjectString;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.jdom2.Element;


/**
 * Cette classe va gerer tout ce qui concerne la souris et les traitement a
 * faire dessus
 * 
 * @author Cibert Gauthier
 * @author Martini Didier
 * @author Responsable: Martini Didier
 * @version 3.0
 */

public class MouseList extends MouseAdapter implements MouseMotionListener,
				MouseWheelListener {

	private final MainPanel		parent;
	private int					drag;
	private int					dragFromX					= 0;
	private int					dragFromY					= 0;
	private static final int	EPSILON						= 10;
	private static final int	DRAGGEDBUTTON1				= 1;
	private static final int	DRAGGEDBUTTON3				= 3;
	private final Logger		logger;
	private static Element		config;
	private static final String	CONFIG_STEP_SLIDER			= "valeurChangement";
	private static final int	INDEX_X						= 0;
	private static final int	INDEX_Y						= 1;
	private static final int	TRAITEMENT_SOURIS_POINTEUR	= 2;
	private static final int	TRAITEMENT_SOURIS_NORMAL	= 1;
	private static final int	CERCLE_TRIGO				= 360;
	private static final int	MAX_COLOR					= 255;

	// -------------------------------------------------------- *** Constructeur
	//

	/**
	 * @param pan
	 * @param config
	 */
	public MouseList(final MainPanel pan, final Element config) {

		super();
		MouseList.config = config;
		DOMConfigurator.configure(MouseList.config.getChild("logger")
						.getChildText("config"));
		this.logger = Logger.getLogger(this.getClass().getName());

		try {

			this.logger.debug("[constructor]Element:"
							+ ObjectString.toString(config));

		} catch (final IOException e) {

			this.logger.error("[" + e.getClass().getName() + "]"
							+ e.getMessage());

		}

		this.parent = pan;

	}

	/**
	 * @param e
	 */
	public void curseur(final Point e) {

		final int gridStep = this.parent.getGridStep();
		List<Integer> position = new LinkedList<Integer>();
		position = this.traitementSurSouris(e,
						MouseList.TRAITEMENT_SOURIS_POINTEUR);

		if (this.isOverIntersect(e, gridStep)) {

			this.parent.intersect(position.get(MouseList.INDEX_X),
							position.get(MouseList.INDEX_Y));

		} else {
			this.parent.resetIntersect();
		}

	}

	/**
	 * @param e
	 * @param gridStep
	 * @return int
	 */
	public int getXGap(final Point e, final int gridStep) {

		if ((e.getX() % gridStep) > MouseList.EPSILON) {
			return (int) (((e.getX() % gridStep) - gridStep));
		}

		return (int) (e.getX() % gridStep);
	}

	/**
	 * @param e
	 * @param gridStep
	 * @return int
	 */
	public int getYGap(final Point e, final int gridStep) {

		if ((e.getY() % gridStep) > MouseList.EPSILON) {
			return (int) (((e.getY() % gridStep) - gridStep));
		}

		return (int) (e.getY() % gridStep);
	}

	/**
	 * @param e
	 * @param gridStep
	 * @return boolean
	 */
	public boolean isOverIntersect(final Point e, final int gridStep) {

		// Sert Ã  dÃ©terminer si la souris est au dessus d'une intersection de
		// la
		// grille

		// TODO RÃ©cupÃ©rer un epsilon dans le fichier de config
		final int xGap = this.getXGap(e, gridStep);
		final int yGap = this.getYGap(e, gridStep);
		return ((xGap <= MouseList.EPSILON) && (yGap <= MouseList.EPSILON));

	}

	// -------------------------------------------------------- *** mÃ©thode
	// mouseDragged
	//
	public void mouseDragged(final MouseEvent e) {

		this.curseur(e.getPoint());
		List<Integer> position = new LinkedList<Integer>();
		position = this.traitementSurSouris(e.getPoint(),
						MouseList.TRAITEMENT_SOURIS_NORMAL);

		if (this.drag == MouseList.DRAGGEDBUTTON1) {
			this.parent.dragLine(position.get(MouseList.INDEX_X),
							position.get(MouseList.INDEX_Y), this.dragFromX,
							this.dragFromY);
		} else if (this.drag == MouseList.DRAGGEDBUTTON3) {
			this.parent.drawDirection(position.get(MouseList.INDEX_X),
							position.get(MouseList.INDEX_Y));
		}

	}

	// -------------------------------------------------------- *** mÃ©thode
	// mouseMoved
	//
	public void mouseMoved(final MouseEvent e) {

		this.curseur(e.getPoint());

	}

	// -------------------------------------------------------- *** mÃ©thode
	// mousePressed
	//
	@Override
	public void mousePressed(final MouseEvent e) {

		List<Integer> position = new LinkedList<Integer>();
		position = this.traitementSurSouris(e.getPoint(),
						MouseList.TRAITEMENT_SOURIS_NORMAL);

		// Si c'est un clic gauche, on déssine une ligne
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.parent.click(position.get(MouseList.INDEX_X),
							position.get(MouseList.INDEX_Y));
			this.drag = MouseList.DRAGGEDBUTTON1;
			this.dragFromX = position.get(MouseList.INDEX_X);
			this.dragFromY = position.get(MouseList.INDEX_Y);
		}
		// Si c'est un clic droit, on
		else if (e.getButton() == MouseEvent.BUTTON3) {
			this.parent.drawDirection(position.get(MouseList.INDEX_X),
							position.get(MouseList.INDEX_Y));

			this.drag = MouseList.DRAGGEDBUTTON3;

		}

	}

	// -------------------------------------------------------- *** mÃ©thode
	// mouseReleased
	//
	@Override
	public void mouseReleased(final MouseEvent e) {

		List<Integer> position = new LinkedList<Integer>();
		position = this.traitementSurSouris(e.getPoint(),
						MouseList.TRAITEMENT_SOURIS_NORMAL);

		if (e.getButton() == MouseEvent.BUTTON1) {
			this.parent.clickReleased(position.get(MouseList.INDEX_X),
							position.get(MouseList.INDEX_Y));

		}

		this.drag = 0;
		this.parent.mouseReleased(position.get(MouseList.INDEX_X),
						position.get(MouseList.INDEX_Y));
	}

	public void mouseWheelMoved(final MouseWheelEvent e) {

		int value = this.parent.getView().getToolBarCommande().getSpeedSlider()
						.getValue();

		if (e.getWheelRotation() > 0) {

			if ((value - Integer.valueOf(MouseList.config.getChild(
							this.getClass().getName()).getChildText(
							MouseList.CONFIG_STEP_SLIDER))) > 0) {
				value -= Integer.valueOf(MouseList.config.getChild(
								this.getClass().getName()).getChildText(
								MouseList.CONFIG_STEP_SLIDER));
			} else {
				value = 0;
			}

			this.parent.getView().getToolBarCommande().getSpeedSlider()
							.setValue(value);

		} else {

			if ((value + Integer.valueOf(MouseList.config.getChild(
							this.getClass().getName()).getChildText(
							MouseList.CONFIG_STEP_SLIDER))) < MouseList.MAX_COLOR) {
				value += Integer.valueOf(MouseList.config.getChild(
								this.getClass().getName()).getChildText(
								MouseList.CONFIG_STEP_SLIDER));
			} else {
				value = MouseList.MAX_COLOR;
			}
			this.parent.getView().getToolBarCommande().getSpeedSlider()
							.setValue(value);

		}

	}

	/**
	 * Calcul pour pouvoir gerer la rotation correctement
	 * 
	 * @param coordonnee
	 * @return List<Integer>
	 */
	private List<Integer> rotation(final List<Integer> coordonnee) {

		int x, y, x2, y2;
		x = coordonnee.get(0);
		y = coordonnee.get(1);
		x2 = (int) ((x * Math.cos(Math.toRadians(MouseList.CERCLE_TRIGO
						- this.parent.getAngle()))) - (y * Math.sin(Math
						.toRadians(MouseList.CERCLE_TRIGO
										- this.parent.getAngle()))));
		y2 = (int) ((x * Math.sin(Math.toRadians(MouseList.CERCLE_TRIGO
						- this.parent.getAngle()))) + (y * Math.cos(Math
						.toRadians(MouseList.CERCLE_TRIGO
										- this.parent.getAngle()))));
		coordonnee.removeAll(coordonnee);
		coordonnee.add(x2);
		coordonnee.add(y2);
		return coordonnee;
	}

	/**
	 * Methode qui va appeller toutes les methodes de calculs sur le zoom,
	 * rotation et translation pour obtenir des coordonnées curseur correct
	 */
	private List<Integer> traitementSurSouris(final Point e, final int type) {

		List<Integer> retour = new LinkedList<Integer>();

		int x, y;
		final int gridStep = this.parent.getGridStep();

		x = (int) (e.getX() - this.getXGap(e, gridStep));
		y = (int) (e.getY() - this.getYGap(e, gridStep));

		retour.add(x);
		retour.add(y);

		retour = this.translation(retour, MouseList.TRAITEMENT_SOURIS_NORMAL);
		retour = this.zoom(retour);
		retour = this.rotation(retour);
		if (type == MouseList.TRAITEMENT_SOURIS_POINTEUR) {
			retour = this.translation(retour,
							MouseList.TRAITEMENT_SOURIS_POINTEUR);
		}
		return retour;

	}

	/**
	 * Calcul pour pouvoir gerer la translation correctement
	 */
	private List<Integer> translation(final List<Integer> coordonnee,
					final int type) {

		int x, y;
		x = coordonnee.get(0);
		y = coordonnee.get(1);
		if (type == MouseList.TRAITEMENT_SOURIS_NORMAL) {
			x = x - this.parent.getTranslateX();
			y = y - this.parent.getTranslateY();
		} else {
			x = x + this.parent.getTranslateX();
			y = y + this.parent.getTranslateY();

		}
		coordonnee.removeAll(coordonnee);
		coordonnee.add(x);
		coordonnee.add(y);
		return coordonnee;
	}

	/**
	 * Calculs pour pouvoir gerer le zoom correctement
	 */
	private List<Integer> zoom(final List<Integer> coordonnee) {

		int x, y;
		x = coordonnee.get(0);
		y = coordonnee.get(1);
		x = (int) (x / this.parent.getScaleFactor());
		y = (int) (y / this.parent.getScaleFactor());
		coordonnee.removeAll(coordonnee);
		coordonnee.add(x);
		coordonnee.add(y);
		return coordonnee;

	}
}
