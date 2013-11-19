package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import library.ObjectString;
import models.Ligne;
import models.NavireModel;
import models.PointModel;
import models.TerritoireModel;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.jdom2.Element;

import controllers.TrafalgarPlayerController;

/**
 * Il s'agit de la classe qui va gerer le panneau de dessin où sont afficher la
 * carte et navire
 * 
 * @author Cibert Gauthier
 * @author Martini Didier
 * @author Responsable: Martini Didier
 * @version 3.0
 */
public class MainPanel extends JPanel {

	private static final long				serialVersionUID					= 1L;
	private static Element					configuration;
	private final TrafalgarPlayerController	controllerHamecon;
	private final MapView					view;
	private final Logger					logger;

	private int								angle								= 0;
	private int								translateX							= 0;
	private int								translateY							= 0;
	private float							scaleFactor							= 1;
	private static final int				GRIDSTEP							= 1;
	private Point							intersect;
	private List<Point>						pointsTraces;
	private TerritoireModel					selectedTerritoire;
	private Ligne							ligneEnCours;
	private static final String				REDNAME								= "red";
	private static final String				BLUENAME							= "blue";
	private static final String				GREENNAME							= "green";
	private static final String				ALPHANAME							= "alpha";
	private List<Ligne>						lignes								= new LinkedList<Ligne>();
	private final List<Ligne>				lignesDeleted						= new LinkedList<Ligne>();
	private Point							traceDirection;
	private List<Point>						intersections						= new LinkedList<Point>();
	private final MouseList					ml;
	private static final String				CONFIG_NAVIRE						= "navire";
	private static final String				CONFIG_COULEUR_NAVIRE_JOUEUR		= "couleur_navire_joueur";
	private static final String				CONFIG_COULEUR_NAVIRE_SELECTIONNER	= "couleur_navire_selectionner";
	private static final String				CONFIG_COULEUR_NAVIRE				= "couleur_navire";
	private static final String				CONFIG_COULEUR_DIRECTION			= "couleur_direction";

	/**
	 * @param config
	 *            Element de configuration
	 * @param controllerHamecon
	 *            Controller
	 * @param view
	 *            Vue "mere"
	 */
	public MainPanel(final Element config,
					final TrafalgarPlayerController controllerHamecon,
					final MapView view) {

		MainPanel.configuration = config;
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

		this.view = view;
		this.controllerHamecon = controllerHamecon;

		// Ajoute le listener de la souris

		this.ml = new MouseList(this, MainPanel.configuration);
		this.addMouseListener(this.ml);
		this.addMouseMotionListener(this.ml);
		this.addMouseWheelListener(this.ml);

	}

	/**
	 * Cette methode ajoute a l'affichage la derniere ligne supprimée
	 */
	public void addLastRemovedLigne() {

		if (this.lignesDeleted.size() > 0) {
			this.lignes.add(((LinkedList<Ligne>) this.lignesDeleted).getLast());
			((LinkedList<Ligne>) this.lignesDeleted).removeLast();
			this.recalculIntersections();
			this.repaint();
		}

	}

	/**
	 * Methode appelé lors d'un clic, elle notifie le controller de cela
	 * 
	 * @param x
	 *            valeur du coordonnée X du pointeur de la souris
	 * @param y
	 *            valeur du coordonnée Y du pointeur de la souris
	 */
	public void click(final int x, final int y) {

		this.requestFocus();
		this.controllerHamecon.click(x, y);

	}

	/**
	 * Methode appelé lors d'un relachement de clic, elle notifie le controller
	 * de cela
	 * 
	 * @param x
	 *            valeur du coordonnée X du pointeur de la souris
	 * @param y
	 *            valeur du coordonnée Y du pointeur de la souris
	 */
	public void clickReleased(final int x, final int y) {

		this.requestFocus();
		this.controllerHamecon.clickReleased(x, y);

	}

	/**
	 * Cette methode va dessiner la lignes represantant la direction
	 */
	private void dessinerDirection(final Graphics2D g2) {

		if (this.traceDirection != null) {
			final NavireModel navireJoueur = this.controllerHamecon
							.getNavireJoueur();

			g2.setColor(new Color(
							Integer.parseInt(MainPanel.configuration
											.getChild(this.getClass().getName())
											.getChild(MainPanel.CONFIG_NAVIRE)
											.getChild(MainPanel.CONFIG_COULEUR_DIRECTION)
											.getChildText(MainPanel.REDNAME)),
							Integer.parseInt(MainPanel.configuration
											.getChild(this.getClass().getName())
											.getChild(MainPanel.CONFIG_NAVIRE)
											.getChild(MainPanel.CONFIG_COULEUR_DIRECTION)
											.getChildText(MainPanel.GREENNAME)),
							Integer.parseInt(MainPanel.configuration
											.getChild(this.getClass().getName())
											.getChild(MainPanel.CONFIG_NAVIRE)
											.getChild(MainPanel.CONFIG_COULEUR_DIRECTION)
											.getChildText(MainPanel.BLUENAME)),
							Integer.parseInt(MainPanel.configuration
											.getChild(this.getClass().getName())
											.getChild(MainPanel.CONFIG_NAVIRE)
											.getChild(MainPanel.CONFIG_COULEUR_DIRECTION)
											.getChildText(MainPanel.ALPHANAME))

			));
			g2.drawLine(navireJoueur.x, navireJoueur.y, this.traceDirection.x,
							this.traceDirection.y);

		}
	}

	/**
	 * Cette methode va dessiner les intersections entre des lignes
	 */
	private void dessinerIntersection(final Graphics2D g2) {

		final String configColorIntersection = "colorIntersection";
		final String configTailleIntersection = "taille_intersection";
		g2.setColor(new Color(Integer.parseInt(MainPanel.configuration
						.getChild(this.getClass().getName())
						.getChild(configColorIntersection)
						.getChildText(MainPanel.REDNAME)), Integer
						.valueOf(MainPanel.configuration
										.getChild(this.getClass().getName())
										.getChild(configColorIntersection)
										.getChildText(MainPanel.GREENNAME)),
						Integer.valueOf(MainPanel.configuration
										.getChild(this.getClass().getName())
										.getChild(configColorIntersection)
										.getChildText(MainPanel.BLUENAME)),
						Integer.valueOf(MainPanel.configuration
										.getChild(this.getClass().getName())
										.getChild(configColorIntersection)
										.getChildText(MainPanel.ALPHANAME))));
		for (final Point intersection : this.intersections) {
			if (intersection != null) {
				g2.fillOval(intersection.x
								- (Integer.valueOf(MainPanel.configuration
												.getChild(this.getClass()
																.getName())
												.getChildText(configTailleIntersection)) / 2),
								intersection.y
												- (Integer.valueOf(MainPanel.configuration
																.getChild(this.getClass()
																				.getName())
																.getChildText(configTailleIntersection)) / 2),
								Integer.valueOf(MainPanel.configuration
												.getChild(this.getClass()
																.getName())
												.getChildText(configTailleIntersection)),
								Integer.valueOf(MainPanel.configuration
												.getChild(this.getClass()
																.getName())
												.getChildText(configTailleIntersection)));
			}
		}

	}

	/**
	 * Cette methode va dessiner le point qui suis le curseur de la souris
	 */
	private void dessinerIntersectionSouris(final Graphics2D g2) {

		final String pointName = "point";
		final String taillePointName = "taillePoint";

		g2.setColor(new Color(Integer.parseInt(MainPanel.configuration
						.getChild(this.getClass().getName())
						.getChild(pointName).getChildText(MainPanel.REDNAME)),
						Integer.parseInt(MainPanel.configuration
										.getChild(this.getClass().getName())
										.getChild(pointName)
										.getChildText(MainPanel.GREENNAME)),
						Integer.parseInt(MainPanel.configuration
										.getChild(this.getClass().getName())
										.getChild(pointName)
										.getChildText(MainPanel.BLUENAME)),
						Integer.parseInt(MainPanel.configuration
										.getChild(this.getClass().getName())
										.getChild(pointName)
										.getChildText(MainPanel.ALPHANAME))

		));

		if (this.intersect != null) {
			g2.fillOval(this.intersect.x
							- Integer.parseInt(MainPanel.configuration
											.getChild(this.getClass().getName())
											.getChild(taillePointName)
											.getChildText("positionX")),
							this.intersect.y
											- Integer.parseInt(MainPanel.configuration
															.getChild(this.getClass()
																			.getName())
															.getChild(taillePointName)
															.getChildText("positionY")),
							Integer.parseInt(MainPanel.configuration
											.getChild(this.getClass().getName())
											.getChild(taillePointName)
											.getChildText("tailleX")),
							Integer.parseInt(MainPanel.configuration
											.getChild(this.getClass().getName())
											.getChild(taillePointName)
											.getChildText("tailleY")));
		}

	}

	/**
	 * Cette methode va dessiner la ligne en cours de tracé
	 */
	private void dessinerLignesEnCourTracer(final Graphics2D g2) {

		if (this.ligneEnCours != null) {
			final Point p1 = this.ligneEnCours.getP1();
			final Point p2 = this.ligneEnCours.getP2();
			g2.drawLine(p1.x, p1.y, p2.x, p2.y);
		}

	}

	/**
	 * Cette methode va dessiner toutes les lignes tracées
	 */
	private void dessinerLignesTracer(final Graphics2D g2) {

		for (final Ligne ligne : this.lignes) {
			final Point p1 = ligne.getP1();
			final Point p2 = ligne.getP2();
			g2.drawLine(p1.x, p1.y, p2.x, p2.y);
		}

	}

	/**
	 * Cette methode va dessiner les navires
	 */
	private void dessinerNavire(final Graphics2D g2) {

		// Dessin des navires

		final Iterator<NavireModel> itNavires = this.controllerHamecon
						.getNavires().values().iterator();
		while (itNavires.hasNext()) {
			final NavireModel navire = itNavires.next();
			// Si le navire est celui du joueur
			if (navire.getJoueurEnControle() == TrafalgarPlayerController.NOM_JOUEUR) {
				g2.setColor(new Color(
								Integer.parseInt(MainPanel.configuration
												.getChild(this.getClass()
																.getName())
												.getChild(MainPanel.CONFIG_NAVIRE)
												.getChild(MainPanel.CONFIG_COULEUR_NAVIRE_JOUEUR)
												.getChildText(MainPanel.REDNAME)),
								Integer.parseInt(MainPanel.configuration
												.getChild(this.getClass()
																.getName())
												.getChild(MainPanel.CONFIG_NAVIRE)
												.getChild(MainPanel.CONFIG_COULEUR_NAVIRE_JOUEUR)
												.getChildText(MainPanel.GREENNAME)),
								Integer.parseInt(MainPanel.configuration
												.getChild(this.getClass()
																.getName())
												.getChild(MainPanel.CONFIG_NAVIRE)
												.getChild(MainPanel.CONFIG_COULEUR_NAVIRE_JOUEUR)
												.getChildText(MainPanel.BLUENAME)),
								Integer.parseInt(MainPanel.configuration
												.getChild(this.getClass()
																.getName())
												.getChild(MainPanel.CONFIG_NAVIRE)
												.getChild(MainPanel.CONFIG_COULEUR_NAVIRE_JOUEUR)
												.getChildText(MainPanel.ALPHANAME))

				));

			} else {
				final NavireModel selectedNavire = this.controllerHamecon
								.getSelectedNavire();

				// Si le navire est sélectionné
				if ((selectedNavire != null) && (navire.equals(selectedNavire))) {
					g2.setColor(new Color(
									Integer.parseInt(MainPanel.configuration
													.getChild(this.getClass()
																	.getName())
													.getChild(MainPanel.CONFIG_NAVIRE)
													.getChild(MainPanel.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
													.getChildText(MainPanel.REDNAME)),
									Integer.parseInt(MainPanel.configuration
													.getChild(this.getClass()
																	.getName())
													.getChild(MainPanel.CONFIG_NAVIRE)
													.getChild(MainPanel.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
													.getChildText(MainPanel.GREENNAME)),
									Integer.parseInt(MainPanel.configuration
													.getChild(this.getClass()
																	.getName())
													.getChild(MainPanel.CONFIG_NAVIRE)
													.getChild(MainPanel.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
													.getChildText(MainPanel.BLUENAME)),
									Integer.parseInt(MainPanel.configuration
													.getChild(this.getClass()
																	.getName())
													.getChild(MainPanel.CONFIG_NAVIRE)
													.getChild(MainPanel.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
													.getChildText(MainPanel.ALPHANAME))

					));
				} else {// Si c'est un navire normal
					g2.setColor(new Color(
									Integer.parseInt(MainPanel.configuration
													.getChild(this.getClass()
																	.getName())
													.getChild(MainPanel.CONFIG_NAVIRE)
													.getChild(MainPanel.CONFIG_COULEUR_NAVIRE)
													.getChildText(MainPanel.REDNAME)),
									Integer.parseInt(MainPanel.configuration
													.getChild(this.getClass()
																	.getName())
													.getChild(MainPanel.CONFIG_NAVIRE)
													.getChild(MainPanel.CONFIG_COULEUR_NAVIRE)
													.getChildText(MainPanel.GREENNAME)),
									Integer.parseInt(MainPanel.configuration
													.getChild(this.getClass()
																	.getName())
													.getChild(MainPanel.CONFIG_NAVIRE)
													.getChild(MainPanel.CONFIG_COULEUR_NAVIRE)
													.getChildText(MainPanel.BLUENAME)),
									Integer.parseInt(MainPanel.configuration
													.getChild(this.getClass()
																	.getName())
													.getChild(MainPanel.CONFIG_NAVIRE)
													.getChild(MainPanel.CONFIG_COULEUR_NAVIRE)
													.getChildText(MainPanel.ALPHANAME))

					));
				}
			}
			g2.fillOval(navire.x,
							navire.y,
							Integer.parseInt(MainPanel.configuration
											.getChild(this.getClass().getName())
											.getChild(MainPanel.CONFIG_NAVIRE)
											.getChildText("taille")),
							Integer.parseInt(MainPanel.configuration
											.getChild(this.getClass().getName())
											.getChild(MainPanel.CONFIG_NAVIRE)
											.getChildText("taille")));
			g2.drawString(navire.getIdentifiant(), navire.x, navire.y);

		}
	}

	/**
	 * Cette methode va dessiner les points
	 */
	private void dessinerPointTrace(final Graphics2D g2) {

		final String taillePointName = "taillePoint";

		if (this.pointsTraces != null) {

			final String traceName = "trace";
			g2.setColor(new Color(
							Integer.parseInt(MainPanel.configuration
											.getChild(this.getClass().getName())
											.getChild(traceName)
											.getChildText(MainPanel.REDNAME)),
							Integer.parseInt(MainPanel.configuration
											.getChild(this.getClass().getName())
											.getChild(traceName)
											.getChildText(MainPanel.GREENNAME)),
							Integer.parseInt(MainPanel.configuration
											.getChild(this.getClass().getName())
											.getChild(traceName)
											.getChildText(MainPanel.BLUENAME)),
							Integer.parseInt(MainPanel.configuration
											.getChild(this.getClass().getName())
											.getChild(traceName)
											.getChildText(MainPanel.ALPHANAME))

			));

			final int size = this.pointsTraces.size();
			for (int i = 0; i < size; i++) {
				Point previousPoint = null;
				if (i != 0) {
					previousPoint = this.pointsTraces.get(i - 1);
				}
				final Point point = this.pointsTraces.get(i);
				if (previousPoint != null) {
					g2.drawLine(previousPoint.x, previousPoint.y, point.x,
									point.y);
				}

				g2.fillOval(point.x
								- Integer.parseInt(MainPanel.configuration
												.getChild(this.getClass()
																.getName())
												.getChild(taillePointName)
												.getChildText("positionX")),
								point.y
												- Integer.parseInt(MainPanel.configuration
																.getChild(this.getClass()
																				.getName())
																.getChild(taillePointName)
																.getChildText("positionY")),
								Integer.parseInt(MainPanel.configuration
												.getChild(this.getClass()
																.getName())
												.getChild(taillePointName)
												.getChildText("tailleX")),
								Integer.parseInt(MainPanel.configuration
												.getChild(this.getClass()
																.getName())
												.getChild(taillePointName)
												.getChildText("tailleY")));
			}
		}

	}

	/**
	 * Cette methode va dessiner les territoires
	 */
	private void dessinerPolygone(final Graphics2D g2) {

		// Dessin des territoires
		final List<TerritoireModel> territoires = this.controllerHamecon
						.getTerritoire();
		for (final TerritoireModel territoire : territoires) {

			g2.setColor(territoire.getColor());
			g2.fillPolygon(territoire);

			final Point centre = new Point();

			// Parcourir les points pour trouver les ports et les déssiner
			final List<PointModel> points = territoire.getPoint();
			final Iterator<PointModel> itPoint = points.iterator();
			while (itPoint.hasNext()) {
				final PointModel point = itPoint.next();
				if (point.getPort())// Traitement différent si c'est un port
				{

					final String taillePortName = "taillePort";
					g2.fillOval(point.x
									- Integer.parseInt(MainPanel.configuration
													.getChild(this.getClass()
																	.getName())
													.getChild(taillePortName)
													.getChildText("positionX")),
									point.y
													- Integer.parseInt(MainPanel.configuration
																	.getChild(this.getClass()
																					.getName())
																	.getChild(taillePortName)
																	.getChildText("positionY")),
									Integer.parseInt(MainPanel.configuration
													.getChild(this.getClass()
																	.getName())
													.getChild(taillePortName)
													.getChildText("tailleX")),
									Integer.parseInt(MainPanel.configuration
													.getChild(this.getClass()
																	.getName())
													.getChild(taillePortName)
													.getChildText("tailleY")));
					g2.drawString(point.getName(), point.x, point.y);

				}
				centre.x += point.x;
				centre.y += point.y;
			}

			// Affichage du nom du polygone au centre de ce dernier
			centre.x /= points.size();
			centre.y /= points.size();
			final String nomTerritoire = "nomTerritoire";
			g2.setColor(new Color(
							Integer.parseInt(MainPanel.configuration
											.getChild(this.getClass().getName())
											.getChild(nomTerritoire)
											.getChildText(MainPanel.REDNAME)),
							Integer.parseInt(MainPanel.configuration
											.getChild(this.getClass().getName())
											.getChild(nomTerritoire)
											.getChildText(MainPanel.GREENNAME)),
							Integer.parseInt(MainPanel.configuration
											.getChild(this.getClass().getName())
											.getChild(nomTerritoire)
											.getChildText(MainPanel.BLUENAME)),
							Integer.parseInt(MainPanel.configuration
											.getChild(this.getClass().getName())
											.getChild(nomTerritoire)
											.getChildText(MainPanel.ALPHANAME))

			));
			g2.drawString(territoire.getName(), centre.x, centre.y);
		}
	}

	/**
	 * Cette methode va dessiner la ligne en cours de creation
	 * 
	 * @param dragX
	 *            Valeur du coordonnée X du pointeur de souris
	 * @param dragY
	 *            Valeur du coordonnée Y du pointeur de souris
	 * @param dragFromX
	 *            Valeur du coordonnée X d'origine de la ligne
	 * @param dragFromY
	 *            Valeur du coordonnée Y d'origine de la ligne
	 */
	public void dragLine(final int dragX, final int dragY, final int dragFromX,
					final int dragFromY) {

		this.ligneEnCours = new Ligne(new Point(dragX, dragY), new Point(
						dragFromX, dragFromY));
		this.requestFocus();
		this.repaint();

	}

	/**
	 * Cette methode va dessiner la direction voulu
	 * 
	 * @param x
	 *            Valeur du coordonnée X du pointeur de souris
	 * @param y
	 *            Valeur du coordonnée Y du pointeur de souris
	 */
	public void drawDirection(final int x, final int y) {

		this.controllerHamecon.setDirectionVoulu(x, y);
		this.traceDirection = new Point(x, y);

		this.repaint();
	}

	/**
	 * Cette methode efface toutes les lignes tracées
	 */
	public void echap() {

		this.lignesDeleted
						.addAll((Collection<? extends Ligne>) ((LinkedList<?>) this.lignes)
										.clone());
		this.lignes = new LinkedList();
		this.recalculIntersections();
		this.requestFocus();
		this.repaint();
	}

	/**
	 * @return int
	 */
	public int getAngle() {

		return this.angle;

	}

	/**
	 * @return int
	 */
	public int getGridStep() {

		return MainPanel.GRIDSTEP;
	}

	/**
	 * @return float
	 */
	public float getScaleFactor() {

		return this.scaleFactor;
	}

	/**
	 * @return TerritoireModel
	 */
	public TerritoireModel getSelectedTerritoire() {

		return this.selectedTerritoire;
	}

	/**
	 * @return List<TerritoireModel>
	 */
	public List<TerritoireModel> getTerritoire() {

		return this.controllerHamecon.getTerritoire();
	}

	/**
	 * @return int
	 */
	public int getTranslateX() {

		return this.translateX;

	}

	/**
	 * @return int
	 */
	public int getTranslateY() {

		return this.translateY;

	}

	/**
	 * @return MapView
	 */
	public MapView getView() {

		return this.view;

	}

	/**
	 * Cette methode va permettre de dessiner l'emplacement du pointeur de
	 * souris
	 * 
	 * @param xPos
	 *            Valeur du coordonnée X du pointeur de la souris
	 * @param yPos
	 *            Valeur du coordonnée Y du pointeur de la souris
	 */
	public void intersect(final int xPos, final int yPos) {

		this.intersect = new Point(xPos, yPos);
		this.repaint();
	}

	/**
	 * @param x
	 *            valeur du coordonnée X du pointeur de la souris
	 * @param y
	 *            valeur du coordonnée Y du pointeur de la souris
	 */
	public void mouseReleased(final int x, final int y) {

		this.requestFocus();

		if (this.ligneEnCours != null) {
			this.lignes.add(this.ligneEnCours);
			this.ligneEnCours = null;
			this.recalculIntersections();

		} else if (this.traceDirection != null) {

			this.drawDirection(x, y);
			this.traceDirection = null;
		}

		this.repaint();
	}

	@Override
	public void paint(final Graphics gr) {

		// DESSIN DE BASE
		final Graphics2D g2 = (Graphics2D) gr;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
		final int width = this.getWidth();
		final int height = this.getHeight();
		g2.fillRect(0, 0, width, height);

		// Dessin de l'image de fond
		final String backgroundPath = this.controllerHamecon.getImagePath();

		if (backgroundPath != null) {
			g2.drawImage((new ImageIcon(backgroundPath)).getImage(), 0, 0,
							width, height, null);
		}

		g2.translate(this.translateX, this.translateY);
		g2.scale(this.scaleFactor, this.scaleFactor);

		g2.rotate(Math.toRadians(this.angle));

		this.dessinerPolygone(g2);

		this.dessinerPointTrace(g2);
		this.dessinerLignesEnCourTracer(g2);
		this.dessinerLignesTracer(g2);
		this.dessinerNavire(g2);
		this.dessinerDirection(g2);
		this.dessinerIntersection(g2);

		g2.rotate(Math.toRadians(0));

		g2.translate(this.translateX * -1, this.translateY * -1);
		this.dessinerIntersectionSouris(g2);

	}

	/**
	 * Cette methode recalcule les intersection entre des lignes
	 */
	private void recalculIntersections() {

		this.intersections = new LinkedList<Point>();
		for (final Ligne ligne1 : this.lignes) {
			for (final Ligne ligne2 : this.lignes) {
				this.intersections.add(ligne1.intersection(ligne2));
			}
		}

	}

	/**
	 * Cette methode efface la derniere ligne tracé
	 */
	public void removeLastLigne() {

		if (this.lignes.size() > 0) {
			this.lignesDeleted.add(((LinkedList<Ligne>) this.lignes).getLast());
			((LinkedList<Ligne>) this.lignes).removeLast();
			this.recalculIntersections();
			this.repaint();
		}

	}

	/**
	 * 
	 */
	public void resetIntersect() {

		this.intersect = null;
		this.repaint();
	}

	/**
	 * Cette methode modifie la rotation
	 * 
	 * @param theta
	 *            Valeur de l'angle a ajouter a l'angle courant
	 */
	public void rotate(final int theta) {

		this.angle += theta;

	}

	/**
	 * Cette methode modifie le zoom
	 * 
	 * @param d
	 *            Valeur du zoom a multiplier avec le zoom courant
	 */
	public void scale(final double d) {

		this.scaleFactor *= d;

	}

	/**
	 * Cette methode selectionne un territoire
	 * 
	 * @param territoire
	 *            Territoire a selectionner
	 */
	public void selectTerritoire(final TerritoireModel territoire) {

		this.selectedTerritoire = territoire;

	}

	/**
	 * Cette methode defini l'Element de configuration a utiliser
	 * 
	 * @param config
	 *            Element representant le fichier de configuration
	 */

	public void setConfig(final Element config) {

		MainPanel.configuration = config;

	}

	/**
	 * Cette methode deplace la vue actuelle
	 * 
	 * @param x
	 *            valeur du coordonnée X du pointeur de la souris
	 * @param y
	 *            valeur du coordonnée Y du pointeur de la souris
	 */
	public void translate(final int x, final int y) {

		if (x != 0) {
			this.translateX += x;
		}
		if (y != 0) {
			this.translateY += y;
		}

	}

}
