package views;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import models.NavireModel;
import models.TerritoireModel;

import org.jdom2.Element;

/**
 * Cette classe gere la barre d'information contenant toutes les informations
 * sur un navire ou un territoire
 * 
 * @author Cibert Gauthier
 * @author Martini Didier
 * @author Responsable: Martini Didier
 * @version 3.0
 */
public class ToolBarInfo extends JToolBar {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private final JLabel		identifiant			= new JLabel();
	private final JLabel		type				= new JLabel();
	private final JLabel		flotte				= new JLabel();
	private final JLabel		joueurEnControle	= new JLabel();
	private final JLabel		vitesseNavire		= new JLabel();
	private final JLabel		vitesseNavireVoulue	= new JLabel();
	private final JLabel		etat				= new JLabel();
	private final JLabel		nombreSoldat		= new JLabel();
	private final JLabel		munitionsRestantes	= new JLabel();
	private final JLabel		appartenance		= new JLabel();
	private final JLabel		direction			= new JLabel();
	private final JLabel		directionVoulu		= new JLabel();
	private final JLabel		angleVoulu			= new JLabel();
	private final JLabel		angle				= new JLabel();
	private static Element		config;

	private NavireModel			navireCourant;

	/**
	 * @param config
	 */
	public ToolBarInfo(final Element config) {

		super(SwingConstants.VERTICAL);
		ToolBarInfo.config = config;

	}

	/**
	 * @return NavireModel
	 */
	public NavireModel getNavireCourant() {

		return this.navireCourant;

	}

	@Override
	public void paint(final Graphics g) {

		this.setOrientation(SwingConstants.VERTICAL);
		super.paint(g);

	}

	/**
	 * Cette methode affiche les informations d'un navire
	 * 
	 * @param navire
	 */
	public void printNavire(final NavireModel navire) {

		this.navireCourant = navire;
		this.setOrientation(SwingConstants.VERTICAL);
		this.removeAll();
		this.add(this.identifiant);
		this.addSeparator();
		this.add(this.type);
		this.addSeparator();
		this.add(this.flotte);
		this.addSeparator();
		this.add(this.joueurEnControle);
		this.addSeparator();
		this.add(this.vitesseNavire);
		this.addSeparator();
		this.add(this.vitesseNavireVoulue);
		this.addSeparator();
		this.add(this.etat);
		this.addSeparator();
		this.add(this.nombreSoldat);
		this.addSeparator();
		this.add(this.munitionsRestantes);
		this.addSeparator();
		this.add(this.direction);
		this.addSeparator();
		this.add(this.directionVoulu);
		this.addSeparator();
		this.add(this.angle);
		this.addSeparator();
		this.add(this.angleVoulu);

		// TODO Chercher les textes � mettre devant dans un fichier de config

		this.identifiant.setText(ToolBarInfo.config.getChild(
						this.getClass().getName()).getChildText("identifiant")
						+ navire.getIdentifiant());
		this.type.setText(ToolBarInfo.config
						.getChild(this.getClass().getName()).getChildText(
										"type")
						+ navire.getType());
		this.flotte.setText(ToolBarInfo.config.getChild(
						this.getClass().getName()).getChildText("flotte")
						+ navire.getFlotte());
		this.joueurEnControle.setText(ToolBarInfo.config.getChild(
						this.getClass().getName()).getChildText("joueur")
						+ navire.getJoueurEnControle());
		this.vitesseNavire.setText(ToolBarInfo.config.getChild(
						this.getClass().getName()).getChildText("vitesse")
						+ navire.getVitesseNavire());
		this.vitesseNavireVoulue.setText(ToolBarInfo.config.getChild(
						this.getClass().getName())
						.getChildText("vitesseVoulue")
						+ navire.getVitesseVoulueNavire());
		this.etat.setText(ToolBarInfo.config
						.getChild(this.getClass().getName()).getChildText(
										"etat")
						+ navire.getEtat());
		this.nombreSoldat.setText(ToolBarInfo.config.getChild(
						this.getClass().getName()).getChildText("soldat")
						+ navire.getNombreSoldat());
		this.munitionsRestantes.setText(ToolBarInfo.config.getChild(
						this.getClass().getName()).getChildText("munition")
						+ navire.getMunitionsRestantes());
		this.direction.setText(ToolBarInfo.config.getChild(
						this.getClass().getName()).getChildText("direction")
						+ "("
						+ navire.getDirection().width
						+ ","
						+ navire.getDirection().height + ")");
		this.directionVoulu.setText(ToolBarInfo.config.getChild(
						this.getClass().getName()).getChildText(
						"directionVoulue")
						+ "("
						+ navire.getDirectionVoulu().width
						+ ","
						+ navire.getDirectionVoulu().height + ")");
		this.angleVoulu.setText(ToolBarInfo.config.getChild(
						this.getClass().getName()).getChildText("angleVoulu")
						+ Math.round(navire.getAngleVoulu()));
		this.angle.setText(ToolBarInfo.config.getChild(
						this.getClass().getName()).getChildText("angle")
						+ Math.round(navire.getAngle()));

		this.repaint();
	}

	/**
	 * Cette methode affiche le nom du detenteur du territoire
	 * 
	 * @param territoire
	 */
	public void printTroupe(final TerritoireModel territoire) {

		this.setOrientation(SwingConstants.VERTICAL);
		this.removeAll();
		this.add(this.appartenance);
		String flotteName = territoire.getAppartenance();
		if (flotteName == null) {
			flotteName = ToolBarInfo.config.getChild(this.getClass().getName())
							.getChildText("noName");
		}
		this.appartenance.setText(ToolBarInfo.config.getChild(
						this.getClass().getName()).getChildText("appartenance")
						+ flotteName);
		this.repaint();
	}

}
