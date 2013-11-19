package views;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

import org.jdom2.Element;

import controllers.TrafalgarPlayerController;

/**
 * Cette classe va permettre la modification du fichier de configuration et
 * l'application immediate de ses changement
 * Chaque methode propre a cette classe represente un certain nombre de champs
 * permettant la modification.
 * 
 * @author Cibert Gauthier
 * @author Martini Didier
 * @author Responsable: Martini Didier
 * @version 3.0
 */
public class ChangeConfig extends JFrame {

	private static final long				serialVersionUID					= 1L;
	private final JPanel					panneau								= new JPanel();
	private final JTabbedPane				tabbedPane							= new JTabbedPane();
	private final JComponent				panel1;
	private final JComponent				panel2;
	private final Element					config;
	private final TrafalgarPlayerController	hamecon;

	private JSpinner						spinner1;
	private JSpinner						spinner2;
	private JSpinner						spinner3;
	private JSpinner						spinner4;
	private JSpinner						spinner5;
	private JSpinner						spinner6;
	private JSpinner						spinner7;
	private JSpinner						spinner8;
	private JSpinner						spinner9;
	private JSpinner						spinner10;
	private JSpinner						spinner11;
	private JSpinner						spinner12;
	private JSpinner						spinner13;
	private JSpinner						spinner14;
	private JSpinner						spinner15;
	private JSpinner						spinner16;
	private JSpinner						spinner17;
	private JSpinner						spinner18;
	private JSpinner						spinner19;
	private JSpinner						spinner20;
	private JSpinner						spinner21;
	private JSpinner						spinner22;
	private JSpinner						spinner23;
	private JSpinner						spinner24;
	private JSpinner						spinner25;
	private JSpinner						spinner26;
	private JSpinner						spinner27;
	private JSpinner						spinner28;

	private static final int				MAX_COLOR							= 255;
	private static final int				MIN_COLOR							= 0;
	private static final int				PAS									= 1;
	private static final String				REDNAME								= "red";
	private static final String				BLUENAME							= "blue";
	private static final String				GREENNAME							= "green";
	private static final String				ALPHANAME							= "alpha";
	private static final String				CONFIG_MAIN_PANEL					= "views.MainPanel";
	private static final String				CONFIG_NAVIRE						= "navire";
	private static final String				CONFIG_COULEUR_NAVIRE_JOUEUR		= "couleur_navire_joueur";
	private static final String				CONFIG_COULEUR_NAVIRE_SELECTIONNER	= "couleur_navire_selectionner";
	private static final String				CONFIG_COULEUR_NAVIRE				= "couleur_navire";
	private static final String				CONFIG_COULEUR_DIRECTION			= "couleur_direction";
	private static final String				CONFIG_NOM_TERRITOIRE				= "nomTerritoire";
	private static final String				CONFIG_POINT						= "point";
	private static final String				CONFIG_FENETRE						= "fenetre";
	private static final String				CONFIG_COLOR_INTERSECTION			= "colorIntersection";
	private static final String				CONFIG_TAB							= "tab";

	/**
	 * @param hamecon
	 *            controller
	 * @param config
	 *            Element de configuration
	 */
	public ChangeConfig(final TrafalgarPlayerController hamecon,
					final Element config) {

		this.hamecon = hamecon;
		this.config = config;

		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setBounds(Integer.valueOf(config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_FENETRE)
						.getChildText("positionX")),
						Integer.valueOf(config
										.getChild(this.getClass().getName())
										.getChild(ChangeConfig.CONFIG_FENETRE)
										.getChildText("positionY")),
						Integer.valueOf(config
										.getChild(this.getClass().getName())
										.getChild(ChangeConfig.CONFIG_FENETRE)
										.getChildText("longueur")),
						Integer.valueOf(config
										.getChild(this.getClass().getName())
										.getChild(ChangeConfig.CONFIG_FENETRE)
										.getChildText("largeur")));

		this.panel1 = new JPanel();
		this.tabbedPane.addTab(config.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_TAB).getChild("tab1")
						.getChildText("nom"), null, this.panel1);
		this.tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		this.panel2 = new JPanel();
		this.tabbedPane.addTab(config.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_TAB).getChild("tab2")
						.getChildText("nom"), null, this.panel2);
		this.tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		this.panneau.add(this.tabbedPane);
		this.panneau.setLayout(new GridLayout(Integer.valueOf(config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_TAB)
						.getChildText("ligne")), Integer.valueOf(config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_TAB)
						.getChildText("colonne"))));
		this.add(this.panneau);
		this.panel1.setLayout(new GridLayout(Integer.valueOf(config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_TAB).getChild("tab1")
						.getChildText("ligne")), Integer.valueOf(config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_TAB).getChild("tab1")
						.getChildText("colonne"))));
		this.panel2.setLayout(new GridLayout(Integer.valueOf(config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_TAB).getChild("tab2")
						.getChildText("ligne")), Integer.valueOf(config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_TAB).getChild("tab2")
						.getChildText("colonne"))));

		this.colorNavire();
		this.colorNavireSelectionner();
		this.colorNavireJoueur();
		this.colorNomTerritoire();
		this.colorPoint();
		this.colorDirection();
		this.colorIntersection();

	}

	/**
	 * Couleur des lignes de direction
	 */
	private void colorDirection() {

		final SpinnerNumberModel spinnerModel21 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NAVIRE)

										.getChild(ChangeConfig.CONFIG_COULEUR_DIRECTION)
										.getChildText(ChangeConfig.REDNAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner21 = new JSpinner(spinnerModel21);
		final JLabel label21 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_COULEUR_DIRECTION)
						.getChildText("R"));
		this.panel2.add(label21);
		this.panel2.add(this.spinner21);

		final SpinnerNumberModel spinnerModel22 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NAVIRE)
										.getChild(ChangeConfig.CONFIG_COULEUR_DIRECTION)

										.getChildText(ChangeConfig.GREENNAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner22 = new JSpinner(spinnerModel22);
		final JLabel label22 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_COULEUR_DIRECTION)
						.getChildText("G"));
		this.panel2.add(label22);
		this.panel2.add(this.spinner22);

		final SpinnerNumberModel spinnerModel23 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NAVIRE)
										.getChild(ChangeConfig.CONFIG_COULEUR_DIRECTION)

										.getChildText(ChangeConfig.BLUENAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner23 = new JSpinner(spinnerModel23);
		final JLabel label23 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_COULEUR_DIRECTION)
						.getChildText("B"));
		this.panel2.add(label23);
		this.panel2.add(this.spinner23);

		final SpinnerNumberModel spinnerModel24 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NAVIRE)
										.getChild(ChangeConfig.CONFIG_COULEUR_DIRECTION)

										.getChildText(ChangeConfig.ALPHANAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner24 = new JSpinner(spinnerModel24);
		final JLabel label24 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_COULEUR_DIRECTION)
						.getChildText("A"));
		this.panel2.add(label24);
		this.panel2.add(this.spinner24);

	}

	/**
	 * Couleur des points d'intersections entre des lignes
	 */
	private void colorIntersection() {

		final SpinnerNumberModel spinnerModel25 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_COLOR_INTERSECTION)
										.getChildText(ChangeConfig.REDNAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner25 = new JSpinner(spinnerModel25);
		final JLabel label25 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_COLOR_INTERSECTION)
						.getChildText("R"));
		this.panel2.add(label25);
		this.panel2.add(this.spinner25);

		final SpinnerNumberModel spinnerModel26 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_COLOR_INTERSECTION)

										.getChildText(ChangeConfig.GREENNAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner26 = new JSpinner(spinnerModel26);
		final JLabel label26 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_COLOR_INTERSECTION)
						.getChildText("G"));
		this.panel2.add(label26);
		this.panel2.add(this.spinner26);

		final SpinnerNumberModel spinnerModel27 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_COLOR_INTERSECTION)

										.getChildText(ChangeConfig.BLUENAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner27 = new JSpinner(spinnerModel27);
		final JLabel label27 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_COLOR_INTERSECTION)
						.getChildText("B"));
		this.panel2.add(label27);
		this.panel2.add(this.spinner27);

		final SpinnerNumberModel spinnerModel28 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_COLOR_INTERSECTION)

										.getChildText(ChangeConfig.ALPHANAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner28 = new JSpinner(spinnerModel28);
		final JLabel label28 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_COLOR_INTERSECTION)
						.getChildText("A"));
		this.panel2.add(label28);
		this.panel2.add(this.spinner28);

	}

	/**
	 * Couleur des navires
	 */
	private void colorNavire() {

		final SpinnerNumberModel spinnerModel1 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NAVIRE)
										.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE)
										.getChildText(ChangeConfig.REDNAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner1 = new JSpinner(spinnerModel1);
		final JLabel label1 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE)
						.getChildText("R"));
		this.panel1.add(label1);
		this.panel1.add(this.spinner1);

		final SpinnerNumberModel spinnerModel2 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NAVIRE)
										.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE)
										.getChildText(ChangeConfig.GREENNAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner2 = new JSpinner(spinnerModel2);
		final JLabel label2 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE)
						.getChildText("G"));
		this.panel1.add(label2);
		this.panel1.add(this.spinner2);

		final SpinnerNumberModel spinnerModel3 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NAVIRE)
										.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE)
										.getChildText(ChangeConfig.BLUENAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner3 = new JSpinner(spinnerModel3);
		final JLabel label3 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE)
						.getChildText("B"));
		this.panel1.add(label3);
		this.panel1.add(this.spinner3);

		final SpinnerNumberModel spinnerModel4 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NAVIRE)
										.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE)
										.getChildText(ChangeConfig.ALPHANAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner4 = new JSpinner(spinnerModel4);
		final JLabel label4 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE)
						.getChildText("A"));
		this.panel1.add(label4);
		this.panel1.add(this.spinner4);

	}

	/**
	 * Couleur du navire du joueur
	 */
	private void colorNavireJoueur() {

		final SpinnerNumberModel spinnerModel9 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NAVIRE)
										.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_JOUEUR)
										.getChildText(ChangeConfig.REDNAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner9 = new JSpinner(spinnerModel9);
		final JLabel label9 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_JOUEUR)
						.getChildText("R"));
		this.panel1.add(label9);
		this.panel1.add(this.spinner9);

		final SpinnerNumberModel spinnerModel10 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NAVIRE)
										.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_JOUEUR)
										.getChildText(ChangeConfig.GREENNAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner10 = new JSpinner(spinnerModel10);
		final JLabel label10 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_JOUEUR)
						.getChildText("G"));
		this.panel1.add(label10);
		this.panel1.add(this.spinner10);

		final SpinnerNumberModel spinnerModel11 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NAVIRE)
										.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_JOUEUR)
										.getChildText(ChangeConfig.BLUENAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner11 = new JSpinner(spinnerModel11);
		final JLabel label11 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_JOUEUR)
						.getChildText("B"));
		this.panel1.add(label11);
		this.panel1.add(this.spinner11);

		final SpinnerNumberModel spinnerModel12 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NAVIRE)
										.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_JOUEUR)
										.getChildText(ChangeConfig.ALPHANAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner12 = new JSpinner(spinnerModel12);
		final JLabel label12 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_JOUEUR)
						.getChildText("A"));
		this.panel1.add(label12);
		this.panel1.add(this.spinner12);

	}

	/**
	 * Couleur du navire selectionner
	 */
	private void colorNavireSelectionner() {

		final SpinnerNumberModel spinnerModel5 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NAVIRE)
										.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
										.getChildText(ChangeConfig.REDNAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner5 = new JSpinner(spinnerModel5);
		final JLabel label5 = new JLabel(
						this.config.getChild(this.getClass().getName())
										.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
										.getChildText("R"));
		this.panel1.add(label5);
		this.panel1.add(this.spinner5);

		final SpinnerNumberModel spinnerModel6 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NAVIRE)
										.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
										.getChildText(ChangeConfig.GREENNAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner6 = new JSpinner(spinnerModel6);
		final JLabel label6 = new JLabel(
						this.config.getChild(this.getClass().getName())
										.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
										.getChildText("G"));
		this.panel1.add(label6);
		this.panel1.add(this.spinner6);

		final SpinnerNumberModel spinnerModel7 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NAVIRE)
										.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
										.getChildText(ChangeConfig.BLUENAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner7 = new JSpinner(spinnerModel7);
		final JLabel label7 = new JLabel(
						this.config.getChild(this.getClass().getName())
										.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
										.getChildText("B"));
		this.panel1.add(label7);
		this.panel1.add(this.spinner7);

		final SpinnerNumberModel spinnerModel8 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NAVIRE)
										.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
										.getChildText(ChangeConfig.ALPHANAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner8 = new JSpinner(spinnerModel8);
		final JLabel label8 = new JLabel(
						this.config.getChild(this.getClass().getName())
										.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
										.getChildText("A"));
		this.panel1.add(label8);
		this.panel1.add(this.spinner8);

	}

	/**
	 * Couleur des noms de territoires
	 */
	private void colorNomTerritoire() {

		final SpinnerNumberModel spinnerModel13 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NOM_TERRITOIRE)
										.getChildText(ChangeConfig.REDNAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner13 = new JSpinner(spinnerModel13);
		final JLabel label13 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_NOM_TERRITOIRE)
						.getChildText("R"));
		this.panel2.add(label13);
		this.panel2.add(this.spinner13);

		final SpinnerNumberModel spinnerModel14 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NOM_TERRITOIRE)
										.getChildText(ChangeConfig.GREENNAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner14 = new JSpinner(spinnerModel14);
		final JLabel label14 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_NOM_TERRITOIRE)
						.getChildText("G"));
		this.panel2.add(label14);
		this.panel2.add(this.spinner14);

		final SpinnerNumberModel spinnerModel15 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NOM_TERRITOIRE)
										.getChildText(ChangeConfig.BLUENAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner15 = new JSpinner(spinnerModel15);
		final JLabel label15 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_NOM_TERRITOIRE)
						.getChildText("B"));
		this.panel2.add(label15);
		this.panel2.add(this.spinner15);

		final SpinnerNumberModel spinnerModel16 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_NOM_TERRITOIRE)
										.getChildText(ChangeConfig.ALPHANAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner16 = new JSpinner(spinnerModel16);
		final JLabel label16 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_NOM_TERRITOIRE)
						.getChildText("A"));
		this.panel2.add(label16);
		this.panel2.add(this.spinner16);

	}

	/**
	 * Couleurs du point du curseur
	 */
	private void colorPoint() {

		final SpinnerNumberModel spinnerModel17 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_POINT)
										.getChildText(ChangeConfig.REDNAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner17 = new JSpinner(spinnerModel17);
		final JLabel label17 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_POINT).getChildText("R"));
		this.panel2.add(label17);
		this.panel2.add(this.spinner17);

		final SpinnerNumberModel spinnerModel18 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_POINT)
										.getChildText(ChangeConfig.GREENNAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner18 = new JSpinner(spinnerModel18);
		final JLabel label18 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_POINT).getChildText("G"));
		this.panel2.add(label18);
		this.panel2.add(this.spinner18);

		final SpinnerNumberModel spinnerModel19 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_POINT)
										.getChildText(ChangeConfig.BLUENAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner19 = new JSpinner(spinnerModel19);
		final JLabel label19 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_POINT).getChildText("B"));
		this.panel2.add(label19);
		this.panel2.add(this.spinner19);

		final SpinnerNumberModel spinnerModel20 = new SpinnerNumberModel(
						Integer.valueOf(this.config
										.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
										.getChild(ChangeConfig.CONFIG_POINT)
										.getChildText(ChangeConfig.ALPHANAME)),
						Integer.valueOf(ChangeConfig.MIN_COLOR),
						Integer.valueOf(ChangeConfig.MAX_COLOR),
						Integer.valueOf(ChangeConfig.PAS));
		this.spinner20 = new JSpinner(spinnerModel20);
		final JLabel label20 = new JLabel(this.config
						.getChild(this.getClass().getName())
						.getChild(ChangeConfig.CONFIG_POINT).getChildText("A"));
		this.panel2.add(label20);
		this.panel2.add(this.spinner20);

	}

	private void exit() {

		this.saveColorNavire();
		this.saveColorNavireSelectionner();
		this.saveColorNavireJoueur();
		this.saveColorNomTerritoire();
		this.saveColorPoints();
		this.saveColorDirection();
		this.saveColorIntersection();
		this.hamecon.setConfig(this.config);
		this.dispose();

	}

	@Override
	protected void processWindowEvent(final WindowEvent e) {

		if (e.getID() == WindowEvent.WINDOW_CLOSING) {

			this.exit();

		}

	}

	private void saveColorDirection() {

		final Element red = new Element(ChangeConfig.REDNAME);
		red.addContent(String.valueOf(this.spinner21.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_DIRECTION)

						.removeChild(ChangeConfig.REDNAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_DIRECTION)

						.addContent(red);

		final Element green = new Element(ChangeConfig.GREENNAME);
		green.addContent(String.valueOf(this.spinner22.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_DIRECTION)

						.removeChild(ChangeConfig.GREENNAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_DIRECTION)

						.addContent(green);

		final Element blue = new Element(ChangeConfig.BLUENAME);
		blue.addContent(String.valueOf(this.spinner23.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_DIRECTION)

						.removeChild(ChangeConfig.BLUENAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_DIRECTION)

						.addContent(blue);

		final Element alpha = new Element(ChangeConfig.ALPHANAME);
		alpha.addContent(String.valueOf(this.spinner24.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_DIRECTION)

						.removeChild(ChangeConfig.ALPHANAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_DIRECTION)

						.addContent(alpha);
	}

	private void saveColorIntersection() {

		final Element red = new Element(ChangeConfig.REDNAME);
		red.addContent(String.valueOf(this.spinner25.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_COLOR_INTERSECTION)
						.removeChild(ChangeConfig.REDNAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_COLOR_INTERSECTION)
						.addContent(red);

		final Element green = new Element(ChangeConfig.GREENNAME);
		green.addContent(String.valueOf(this.spinner26.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_COLOR_INTERSECTION)
						.removeChild(ChangeConfig.GREENNAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_COLOR_INTERSECTION)
						.addContent(green);

		final Element blue = new Element(ChangeConfig.BLUENAME);
		blue.addContent(String.valueOf(this.spinner27.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_COLOR_INTERSECTION)
						.removeChild(ChangeConfig.BLUENAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_COLOR_INTERSECTION)
						.addContent(blue);

		final Element alpha = new Element(ChangeConfig.ALPHANAME);
		alpha.addContent(String.valueOf(this.spinner28.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_COLOR_INTERSECTION)
						.removeChild(ChangeConfig.ALPHANAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_COLOR_INTERSECTION)
						.addContent(alpha);
	}

	private void saveColorNavire() {

		final Element red = new Element(ChangeConfig.REDNAME);
		red.addContent(String.valueOf(this.spinner1.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE)
						.removeChild(ChangeConfig.REDNAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE)
						.addContent(red);

		final Element green = new Element(ChangeConfig.GREENNAME);
		green.addContent(String.valueOf(this.spinner2.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE)
						.removeChild(ChangeConfig.GREENNAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE)
						.addContent(green);

		final Element blue = new Element(ChangeConfig.BLUENAME);
		blue.addContent(String.valueOf(this.spinner3.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE)
						.removeChild(ChangeConfig.BLUENAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE)
						.addContent(blue);

		final Element alpha = new Element(ChangeConfig.ALPHANAME);
		alpha.addContent(String.valueOf(this.spinner4.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE)
						.removeChild(ChangeConfig.ALPHANAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE)
						.addContent(alpha);
	}

	private void saveColorNavireJoueur() {

		final Element red = new Element(ChangeConfig.REDNAME);
		red.addContent(String.valueOf(this.spinner9.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_JOUEUR)
						.removeChild(ChangeConfig.REDNAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_JOUEUR)
						.addContent(red);

		final Element green = new Element(ChangeConfig.GREENNAME);
		green.addContent(String.valueOf(this.spinner10.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_JOUEUR)
						.removeChild(ChangeConfig.GREENNAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_JOUEUR)
						.addContent(green);

		final Element blue = new Element(ChangeConfig.BLUENAME);
		blue.addContent(String.valueOf(this.spinner11.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_JOUEUR)
						.removeChild(ChangeConfig.BLUENAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_JOUEUR)
						.addContent(blue);

		final Element alpha = new Element(ChangeConfig.ALPHANAME);
		alpha.addContent(String.valueOf(this.spinner12.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_JOUEUR)
						.removeChild(ChangeConfig.ALPHANAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_JOUEUR)
						.addContent(alpha);
	}

	private void saveColorNavireSelectionner() {

		final Element red = new Element(ChangeConfig.REDNAME);
		red.addContent(String.valueOf(this.spinner5.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
						.removeChild(ChangeConfig.REDNAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
						.addContent(red);

		final Element green = new Element(ChangeConfig.GREENNAME);
		green.addContent(String.valueOf(this.spinner6.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
						.removeChild(ChangeConfig.GREENNAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
						.addContent(green);

		final Element blue = new Element(ChangeConfig.BLUENAME);
		blue.addContent(String.valueOf(this.spinner7.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
						.removeChild(ChangeConfig.BLUENAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
						.addContent(blue);

		final Element alpha = new Element(ChangeConfig.ALPHANAME);
		alpha.addContent(String.valueOf(this.spinner8.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
						.removeChild(ChangeConfig.ALPHANAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NAVIRE)
						.getChild(ChangeConfig.CONFIG_COULEUR_NAVIRE_SELECTIONNER)
						.addContent(alpha);
	}

	private void saveColorNomTerritoire() {

		final Element red = new Element(ChangeConfig.REDNAME);
		red.addContent(String.valueOf(this.spinner13.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NOM_TERRITOIRE)
						.removeChild(ChangeConfig.REDNAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NOM_TERRITOIRE)
						.addContent(red);

		final Element green = new Element(ChangeConfig.GREENNAME);
		green.addContent(String.valueOf(this.spinner14.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NOM_TERRITOIRE)
						.removeChild(ChangeConfig.GREENNAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NOM_TERRITOIRE)
						.addContent(green);

		final Element blue = new Element(ChangeConfig.BLUENAME);
		blue.addContent(String.valueOf(this.spinner15.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NOM_TERRITOIRE)
						.removeChild(ChangeConfig.BLUENAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NOM_TERRITOIRE)
						.addContent(blue);

		final Element alpha = new Element(ChangeConfig.ALPHANAME);
		alpha.addContent(String.valueOf(this.spinner16.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NOM_TERRITOIRE)
						.removeChild(ChangeConfig.ALPHANAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_NOM_TERRITOIRE)
						.addContent(alpha);
	}

	private void saveColorPoints() {

		final Element red = new Element(ChangeConfig.REDNAME);
		red.addContent(String.valueOf(this.spinner17.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_POINT)
						.removeChild(ChangeConfig.REDNAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_POINT).addContent(red);

		final Element green = new Element(ChangeConfig.GREENNAME);
		green.addContent(String.valueOf(this.spinner18.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_POINT)
						.removeChild(ChangeConfig.GREENNAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_POINT).addContent(green);

		final Element blue = new Element(ChangeConfig.BLUENAME);
		blue.addContent(String.valueOf(this.spinner19.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_POINT)
						.removeChild(ChangeConfig.BLUENAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_POINT).addContent(blue);

		final Element alpha = new Element(ChangeConfig.ALPHANAME);
		alpha.addContent(String.valueOf(this.spinner20.getValue()));
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_POINT)
						.removeChild(ChangeConfig.ALPHANAME);
		this.config.getChild(ChangeConfig.CONFIG_MAIN_PANEL)
						.getChild(ChangeConfig.CONFIG_POINT).addContent(alpha);
	}

}
