package views;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;

import library.ObjectString;
import models.NavireModel;
import models.TerritoireModel;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.jdom2.Element;

import controllers.TrafalgarPlayerController;

/**
 * Cette classe est la vue principale qui va gerer et creer toutes les autres
 * 
 * @author Cibert Gauthier
 * @author Martini Didier
 * @author Responsable: Martini Didier
 * @version 3.0
 */
public class MapView extends JFrame implements ActionListener, Observer,
				KeyListener {

	/**
	 * 
	 */
	private static final long				serialVersionUID	= 1L;
	private final TrafalgarPlayerController	controllerHamecon;
	private static Element					config;
	private final MainPanel					mainPanel;
	private final ToolBarInfo				info;
	private final ToolBarCommande			command;
	private final ToolBarMeteo				meteo;
	private static final String				MENUNAME			= "menu";
	private static final String				FICHIERNAME			= "fichier";
	private static final String				EDITIONNAME			= "edition";
	private static final String				CONFIG_KEYBOARD		= "keyboard";
	private static final String				CONFIG_KEY			= "key";
	private static final String				CONFIG_TAUX			= "taux";

	private final Logger					logger;

	/**
	 * Creation de toutes les autres vues
	 * 
	 * @param config
	 *            Element de configuration
	 * @param controllerHamecon
	 */
	public MapView(final Element config,
					final TrafalgarPlayerController controllerHamecon) {

		this.controllerHamecon = controllerHamecon;
		MapView.config = config;

		DOMConfigurator.configure(MapView.config.getChild("logger")
						.getChildText("config"));
		this.logger = Logger.getLogger(this.getClass().getName());
		try {

			this.logger.debug("[constructor]Element:"
							+ ObjectString.toString(config));

		} catch (final IOException e) {

			this.logger.error("[" + e.getClass().getName() + "]"
							+ e.getMessage());

		}
		this.setSize(this.getToolkit().getScreenSize());
		this.pack();
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setTitle(config.getChild(this.getClass().getName()).getChildText(
						"windowTitle"));
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		this.barreMenu();
		JPanel contentPane;

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		this.setContentPane(contentPane);

		this.mainPanel = new MainPanel(MapView.config, controllerHamecon, this);
		this.mainPanel.setFocusable(true);

		this.mainPanel.addKeyListener(this);
		this.add(this.mainPanel);

		this.info = new ToolBarInfo(MapView.config);
		this.command = new ToolBarCommande(this, controllerHamecon,
						MapView.config);
		this.meteo = new ToolBarMeteo(controllerHamecon, MapView.config);

		this.add("East", this.info);
		this.add("South", this.command);
		this.add("West", this.meteo);

		this.setVisible(true);
		this.mainPanel.requestFocus();

	}

	/**
	 * Action a effectuer quand l'utilisateur clique sur le bouton a propos
	 */
	private void actionAbout() {

		final About fenetre = new About(MapView.config);
		fenetre.setVisible(true);

	}

	public void actionPerformed(final ActionEvent e) {

		if (e.getActionCommand().equals(
						MapView.config.getChild(this.getClass().getName())
										.getChild(MapView.MENUNAME)
										.getChild(MapView.FICHIERNAME)
										.getChildText("quitter"))) {

			this.actionQuitter();

		}

		if (e.getActionCommand().equals(
						MapView.config.getChild(this.getClass().getName())
										.getChild(MapView.MENUNAME)
										.getChild(MapView.EDITIONNAME)
										.getChildText("preferences"))) {

			this.actionPreferences();

		}

		if (e.getActionCommand()
						.equals(MapView.config
										.getChild(this.getClass().getName())
										.getChild(MapView.MENUNAME)
										.getChild("aide").getChildText("about"))) {

			this.actionAbout();

		}

	}

	/**
	 * Action a effectuer quand l'utilisateur clique sur le bouton preference
	 */
	private void actionPreferences() {

		final ChangeConfig fenetre = new ChangeConfig(this.controllerHamecon,
						MapView.config);
		fenetre.setVisible(true);
	}

	/**
	 * Action a effectuer quand l'utilisateur veut quitter trafalgarPlayer
	 */
	private void actionQuitter() {

		this.exit();

	}

	/**
	 * Ajoute la barre de menu
	 */
	private void barreMenu() {

		final JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		final JMenu mnFichier = new JMenu(MapView.config
						.getChild(this.getClass().getName())
						.getChild(MapView.MENUNAME)
						.getChild(MapView.FICHIERNAME).getChildText("Titre"));
		menuBar.add(mnFichier);

		final JSeparator separator = new JSeparator();
		mnFichier.add(separator);

		final JMenuItem mntmQuitter = new JMenuItem(MapView.config
						.getChild(this.getClass().getName())
						.getChild(MapView.MENUNAME)
						.getChild(MapView.FICHIERNAME).getChildText("quitter"));
		mntmQuitter.addActionListener(this);
		mnFichier.add(mntmQuitter);

		final JMenu mnEdition = new JMenu(MapView.config
						.getChild(this.getClass().getName())
						.getChild(MapView.MENUNAME)
						.getChild(MapView.EDITIONNAME).getChildText("Titre"));
		menuBar.add(mnEdition);

		final JMenuItem mntmPrfrences = new JMenuItem(MapView.config
						.getChild(this.getClass().getName())
						.getChild(MapView.MENUNAME)
						.getChild(MapView.EDITIONNAME)
						.getChildText("preferences"));
		mntmPrfrences.addActionListener(this);
		mnEdition.add(mntmPrfrences);

		final JMenu menu = new JMenu(MapView.config
						.getChild(this.getClass().getName())
						.getChild(MapView.MENUNAME).getChild("aide")
						.getChildText("Titre"));
		menuBar.add(menu);

		final JMenuItem mntmPropos = new JMenuItem(MapView.config
						.getChild(this.getClass().getName())
						.getChild(MapView.MENUNAME).getChild("aide")
						.getChildText("about"));
		mntmPropos.addActionListener(this);
		menu.add(mntmPropos);
	}

	/**
	 * Code de fin de trafalgarPlayer
	 */
	private void exit() {

		System.exit(0);

	}

	/**
	 * @return MainPanel
	 */
	public MainPanel getMainPanel() {

		return this.mainPanel;

	}

	/**
	 * @return ToolBarCommande
	 */
	public ToolBarCommande getToolBarCommande() {

		return this.command;
	}

	public void keyPressed(final KeyEvent e) {

		if (!e.isControlDown()
						&& (e.getKeyCode() == Integer.valueOf(MapView.config
										.getChild(this.getClass().getName())
										.getChild(MapView.CONFIG_KEYBOARD)
										.getChild("clean")
										.getChildText(MapView.CONFIG_KEY)))) {
			MapView.this.mainPanel.echap();
		} else if (!e.isControlDown()
						&& (e.getKeyCode() == Integer.valueOf(MapView.config
										.getChild(this.getClass().getName())
										.getChild(MapView.CONFIG_KEYBOARD)
										.getChild("move_right")
										.getChildText(MapView.CONFIG_KEY)))) {
			this.mainPanel.translate(Integer.valueOf(MapView.config
							.getChild(this.getClass().getName())
							.getChild(MapView.CONFIG_KEYBOARD)
							.getChild("move_right")
							.getChildText(MapView.CONFIG_TAUX)), 0);

		} else if (!e.isControlDown()
						&& (e.getKeyCode() == Integer.valueOf(MapView.config
										.getChild(this.getClass().getName())
										.getChild(MapView.CONFIG_KEYBOARD)
										.getChild("move_left")
										.getChildText(MapView.CONFIG_KEY)))) {

			this.mainPanel.translate(Integer.valueOf(MapView.config
							.getChild(this.getClass().getName())
							.getChild(MapView.CONFIG_KEYBOARD)
							.getChild("move_left")
							.getChildText(MapView.CONFIG_TAUX)), 0);

		} else if (!e.isControlDown()
						&& (e.getKeyCode() == Integer.valueOf(MapView.config
										.getChild(this.getClass().getName())
										.getChild(MapView.CONFIG_KEYBOARD)
										.getChild("move_up")
										.getChildText(MapView.CONFIG_KEY)))) {

			this.mainPanel.translate(0, Integer.valueOf(MapView.config
							.getChild(this.getClass().getName())
							.getChild(MapView.CONFIG_KEYBOARD)
							.getChild("move_up")
							.getChildText(MapView.CONFIG_TAUX)));

		} else if (!e.isControlDown()
						&& (e.getKeyCode() == Integer.valueOf(MapView.config
										.getChild(this.getClass().getName())
										.getChild(MapView.CONFIG_KEYBOARD)
										.getChild("move_down")
										.getChildText(MapView.CONFIG_KEY)))) {

			this.mainPanel.translate(0, Integer.valueOf(MapView.config
							.getChild(this.getClass().getName())
							.getChild(MapView.CONFIG_KEYBOARD)
							.getChild("move_down")
							.getChildText(MapView.CONFIG_TAUX)));

		} else if (!e.isControlDown()
						&& (e.getKeyCode() == Integer.valueOf(MapView.config
										.getChild(this.getClass().getName())
										.getChild(MapView.CONFIG_KEYBOARD)
										.getChild("zoom_plus")
										.getChildText(MapView.CONFIG_KEY)))) {

			this.mainPanel.scale(Float.valueOf(MapView.config
							.getChild(this.getClass().getName())
							.getChild(MapView.CONFIG_KEYBOARD)
							.getChild("zoom_plus")
							.getChildText(MapView.CONFIG_TAUX)));

		} else if (!e.isControlDown()
						&& (e.getKeyCode() == Integer.valueOf(MapView.config
										.getChild(this.getClass().getName())
										.getChild(MapView.CONFIG_KEYBOARD)
										.getChild("zoom_moins")
										.getChildText(MapView.CONFIG_KEY)))) {

			this.mainPanel.scale(Float.valueOf(MapView.config
							.getChild(this.getClass().getName())
							.getChild(MapView.CONFIG_KEYBOARD)
							.getChild("zoom_moins")
							.getChildText(MapView.CONFIG_TAUX)));

		} else if (!e.isControlDown()
						&& (e.getKeyCode() == Integer.valueOf(MapView.config
										.getChild(this.getClass().getName())
										.getChild(MapView.CONFIG_KEYBOARD)
										.getChild("rotate_right")
										.getChildText(MapView.CONFIG_KEY)))) {

			this.mainPanel.rotate(Integer.valueOf(MapView.config
							.getChild(this.getClass().getName())
							.getChild(MapView.CONFIG_KEYBOARD)
							.getChild("rotate_right")
							.getChildText(MapView.CONFIG_TAUX)));

		} else if (!e.isControlDown()
						&& (e.getKeyCode() == Integer.valueOf(MapView.config
										.getChild(this.getClass().getName())
										.getChild(MapView.CONFIG_KEYBOARD)
										.getChild("rotate_left")
										.getChildText(MapView.CONFIG_KEY)))) {

			this.mainPanel.rotate(Integer.valueOf(MapView.config
							.getChild(this.getClass().getName())
							.getChild(MapView.CONFIG_KEYBOARD)
							.getChild("rotate_left")
							.getChildText(MapView.CONFIG_TAUX)));

		}

		this.mainPanel.repaint();
		this.meteo.repaint();
		this.command.repaint();
		this.info.repaint();
	}

	public void keyReleased(final KeyEvent e) {

		if (e.isControlDown()
						&& (e.getKeyCode() == Integer.valueOf(MapView.config
										.getChild(this.getClass().getName())
										.getChild(MapView.CONFIG_KEYBOARD)
										.getChild("undo")
										.getChildText(MapView.CONFIG_KEY)))) {

			MapView.this.mainPanel.removeLastLigne();
		} else if (e.isControlDown()
						&& (e.getKeyCode() == Integer.valueOf(MapView.config
										.getChild(this.getClass().getName())
										.getChild(MapView.CONFIG_KEYBOARD)
										.getChild("redo")
										.getChildText(MapView.CONFIG_KEY)))) {

			MapView.this.mainPanel.addLastRemovedLigne();
		} else if (e.isControlDown()
						&& (e.getKeyCode() == Integer.valueOf(MapView.config
										.getChild(this.getClass().getName())
										.getChild(MapView.CONFIG_KEYBOARD)
										.getChild("exit")
										.getChildText(MapView.CONFIG_KEY)))) {

			MapView.this.exit();
		}
	}

	public void keyTyped(final KeyEvent e) {

	}

	/**
	 * Cette methode demande l'affichage des informations d'un navire
	 * 
	 * @param navire
	 *            Navire dont il faut afficher les informations
	 */
	public void printNavire(final NavireModel navire) {

		this.info.printNavire(navire);
		this.mainPanel.repaint();

	}

	/**
	 * Cette methode demande l'affichage du detenteurs d'un territoire
	 * 
	 * @param territoire
	 *            Territoire dont il faut afficher le detenteur
	 */
	public void printTroupe(final TerritoireModel territoire) {

		this.info.printTroupe(territoire);
		this.mainPanel.repaint();

	}

	@Override
	protected void processWindowEvent(final WindowEvent e) {

		if (e.getID() == WindowEvent.WINDOW_CLOSING) {

			this.actionQuitter();

		}

	}

	/**
	 * Methode qui demande la suppression de toutes les lignes tracées
	 */
	public void reinitLignes() {

		this.mainPanel.echap();

	}

	/**
	 * Methode qui va demander au controller de mettre le necessité de
	 * redirection a true
	 */
	public void requireDir() {

		this.controllerHamecon.requireDir();
	}

	/**
	 * @param meteo
	 */
	public void setMeteo(final int meteo) {

		this.meteo.setMeteo(meteo);

	}

	public void update(final Observable o, final Object arg) {

		if (arg instanceof Element) {

			MapView.config = (Element) arg;
			this.mainPanel.setConfig((Element) arg);

		}

		if ((this.info.getNavireCourant() != null)
						&& this.info.getNavireCourant()
										.getJoueurEnControle()
										.equals(TrafalgarPlayerController.NOM_JOUEUR)) {

			this.info.printNavire(this.controllerHamecon.getNavireJoueur());

		}
		this.setMeteo(this.controllerHamecon.getMeteo());

		this.mainPanel.repaint();

	}
}
