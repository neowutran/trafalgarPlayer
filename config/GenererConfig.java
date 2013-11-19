package config;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.IOException;

import library.ObjectString;

import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

/**
 * Cette classe genere le fichier de configuration
 * 
 * @author Cibert Gauthier
 * @author Martini Didier
 * @author Responsable: Martini Didier
 * @version 3.0
 */
public class GenererConfig {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		final GenererConfig exemple = new GenererConfig();
		exemple.setPathFile(args[1], args[2], null);
		exemple.genererXML();

	}

	private final Element		racine;

	private String				pathFile;

	private String				extensionConfig	= "";
	private static final String	REDNAME			= "red";
	private static final String	GREENNAME		= "green";
	private static final String	BLUENAME		= "blue";
	private static final String	ALPHANAME		= "alpha";
	private static final String	DEFAULTSLIDER	= "200";
	private static final String	MAXALPHA		= "255";
	private static final String	CONFIG_KEY		= "key";
	private static final String	CONFIG_TAUX		= "taux";
	private static final String	CONFIG_TAILLE	= "taille";
	private static final String	LAYOUTNAME		= "layout";

	/**
	 * 
	 */
	public GenererConfig() {

		this.racine = new Element("config");
	}

	/**
	 * @param config
	 */
	public GenererConfig(final Element config) {

		this.racine = config;

	}

	private Element about() {

		final Element about = new Element("views.About");

		final Element button = new Element("button");
		button.addContent("Ok");

		final Element title = new Element("title");
		title.addContent("About");

		final Element taille = new Element(GenererConfig.CONFIG_TAILLE);
		final Element x = new Element("x");
		x.addContent("567");
		final Element y = new Element("y");
		y.addContent("130");
		taille.addContent(x);
		taille.addContent(y);

		final Element position = new Element("position");
		final Element positionX = new Element("x");
		positionX.addContent("120");
		final Element positionY = new Element("y");
		positionY.addContent("140");
		position.addContent(positionX);
		position.addContent(positionY);

		final Element label = new Element("label");
		label.addContent("TrafalgarPlayer");

		final Element layout = new Element(GenererConfig.LAYOUTNAME);

		try {
			layout.addContent(ObjectString.toString(new FlowLayout()));
		} catch (final IOException e) {

		}

		about.addContent(title);
		about.addContent(button);
		about.addContent(taille);
		about.addContent(position);
		about.addContent(label);
		about.addContent(layout);

		return about;
	}

	private Element changeConfig() {

		final Element changeConfig = new Element("views.ChangeConfig");

		changeConfig.addContent(this.changeConfig1());
		changeConfig.addContent(this.changeConfig2());
		changeConfig.addContent(this.changeConfig3());
		changeConfig.addContent(this.changeConfig4());
		changeConfig.addContent(this.changeConfig5());
		changeConfig.addContent(this.changeConfig6());
		changeConfig.addContent(this.changeConfig7());
		changeConfig.addContent(this.changeConfig8());
		changeConfig.addContent(this.changeConfig9());

		return changeConfig;

	}

	private Element changeConfig1() {

		final Element fenetre = new Element("fenetre");
		final Element largeur = new Element("largeur");
		final Element longueur = new Element("longueur");
		final Element positionX = new Element("positionX");
		final Element positionY = new Element("positionY");
		largeur.addContent("500");
		longueur.addContent("500");
		positionX.addContent("400");
		positionY.addContent("400");
		fenetre.addContent(largeur);
		fenetre.addContent(longueur);
		fenetre.addContent(positionX);
		fenetre.addContent(positionY);

		return fenetre;

	}

	private Element changeConfig2() {

		final Element tab = new Element("tab");
		final Element tab1 = new Element("tab1");
		final Element tab2 = new Element("tab2");
		final Element nomTab1 = new Element("nom");
		final Element nomTab2 = new Element("nom");
		final Element nbColonneTab1 = new Element("colonne");
		final Element nbColonneTab2 = new Element("colonne");
		final Element nbLignesTab1 = new Element("ligne");
		final Element nbLignesTab2 = new Element("ligne");
		final Element nbColonne = new Element("colonne");
		final Element nbLigne = new Element("ligne");

		nbColonne.addContent("1");
		nbLigne.addContent("1");
		nomTab1.addContent("tab1");
		nomTab2.addContent("tab2");
		nbColonneTab1.addContent("2");
		nbColonneTab2.addContent("2");
		nbLignesTab1.addContent("20");
		nbLignesTab2.addContent("20");

		tab1.addContent(nomTab1);
		tab1.addContent(nbColonneTab1);
		tab1.addContent(nbLignesTab1);
		tab2.addContent(nomTab2);
		tab2.addContent(nbColonneTab2);
		tab2.addContent(nbLignesTab2);
		tab1.addContent("tab1");
		tab2.addContent("tab2");

		tab.addContent(nbColonne);
		tab.addContent(nbLigne);
		tab.addContent(tab1);
		tab.addContent(tab2);

		return tab;
	}

	private Element changeConfig3() {

		final Element colorPoint = new Element("point");
		final Element colorPointR = new Element("R");
		final Element colorPointG = new Element("G");
		final Element colorPointB = new Element("B");
		final Element colorPointA = new Element("A");
		colorPointR.addContent("Taux de rouge des points");
		colorPointG.addContent("Taux de vert des points");
		colorPointB.addContent("Taux de bleu des points");
		colorPointA.addContent("Taux d'opacité des points");
		colorPoint.addContent(colorPointR);
		colorPoint.addContent(colorPointG);
		colorPoint.addContent(colorPointB);
		colorPoint.addContent(colorPointA);

		return colorPoint;
	}

	private Element changeConfig4() {

		final Element colorNomTerritoire = new Element("nomTerritoire");
		final Element colorNomTerritoireR = new Element("R");
		final Element colorNomTerritoireG = new Element("G");
		final Element colorNomTerritoireB = new Element("B");
		final Element colorNomTerritoireA = new Element("A");
		colorNomTerritoireR
						.addContent("Taux de rouge des noms des territoires");
		colorNomTerritoireG.addContent("Taux de vert des noms des territoires");
		colorNomTerritoireB.addContent("Taux de bleu des noms des territoires");
		colorNomTerritoireA
						.addContent("Taux d'opacité des noms des territoires");
		colorNomTerritoire.addContent(colorNomTerritoireR);
		colorNomTerritoire.addContent(colorNomTerritoireG);
		colorNomTerritoire.addContent(colorNomTerritoireB);
		colorNomTerritoire.addContent(colorNomTerritoireA);

		return colorNomTerritoire;
	}

	private Element changeConfig5() {

		final Element colorNavireSelectionner = new Element(
						"couleur_navire_selectionner");
		final Element colorNavireSelectionnerR = new Element("R");
		final Element colorNavireSelectionnerG = new Element("G");
		final Element colorNavireSelectionnerB = new Element("B");
		final Element colorNavireSelectionnerA = new Element("A");
		colorNavireSelectionnerR
						.addContent("Taux de rouge du navire selectionné");
		colorNavireSelectionnerG
						.addContent("Taux de vert du navire selectionné");
		colorNavireSelectionnerB
						.addContent("Taux de bleu du navire selectionné");
		colorNavireSelectionnerA
						.addContent("Taux d'opacité du navire selectionné");

		colorNavireSelectionner.addContent(colorNavireSelectionnerR);
		colorNavireSelectionner.addContent(colorNavireSelectionnerG);
		colorNavireSelectionner.addContent(colorNavireSelectionnerB);
		colorNavireSelectionner.addContent(colorNavireSelectionnerA);
		return colorNavireSelectionner;

	}

	private Element changeConfig6() {

		final Element colorNavireJoueur = new Element("couleur_navire_joueur");
		final Element colorNavireJoueurR = new Element("R");
		final Element colorNavireJoueurG = new Element("G");
		final Element colorNavireJoueurB = new Element("B");
		final Element colorNavireJoueurA = new Element("A");
		colorNavireJoueurR.addContent("Taux de rouge du navire du joueur");
		colorNavireJoueurG.addContent("Taux de vert du navire du joueur");
		colorNavireJoueurB.addContent("Taux de bleu du navire du joueur");
		colorNavireJoueurA.addContent("Taux d'opacité du navire du joueur");
		colorNavireJoueur.addContent(colorNavireJoueurR);
		colorNavireJoueur.addContent(colorNavireJoueurG);
		colorNavireJoueur.addContent(colorNavireJoueurB);
		colorNavireJoueur.addContent(colorNavireJoueurA);
		return colorNavireJoueur;

	}

	private Element changeConfig7() {

		final Element colorNavire = new Element("couleur_navire");
		final Element colorNavireR = new Element("R");
		final Element colorNavireG = new Element("G");
		final Element colorNavireB = new Element("B");
		final Element colorNavireA = new Element("A");
		colorNavireR.addContent("Taux de rouge des navires");
		colorNavireG.addContent("Taux de vert des navires");
		colorNavireB.addContent("Taux de bleu des navires");
		colorNavireA.addContent("Taux d'opacité des navires");
		colorNavire.addContent(colorNavireR);
		colorNavire.addContent(colorNavireG);
		colorNavire.addContent(colorNavireB);
		colorNavire.addContent(colorNavireA);

		return colorNavire;

	}

	private Element changeConfig8() {

		final Element colorDirection = new Element("couleur_direction");

		final Element colorDirectionR = new Element("R");
		final Element colorDirectionG = new Element("G");
		final Element colorDirectionB = new Element("B");
		final Element colorDirectionA = new Element("A");
		colorDirectionR.addContent("Taux de rouge des trais de direction");
		colorDirectionG.addContent("Taux de vert des trais de direction");
		colorDirectionB.addContent("Taux de bleu des trais de direction");
		colorDirectionA.addContent("Taux d'opacité des trais de direction");
		colorDirection.addContent(colorDirectionR);
		colorDirection.addContent(colorDirectionG);
		colorDirection.addContent(colorDirectionB);
		colorDirection.addContent(colorDirectionA);
		return colorDirection;

	}

	private Element changeConfig9() {

		final Element colorIntersection = new Element("colorIntersection");

		final Element colorIntersectionR = new Element("R");
		final Element colorIntersectionG = new Element("G");
		final Element colorIntersectionB = new Element("B");
		final Element colorIntersectionA = new Element("A");

		colorIntersectionR
						.addContent("Taux de rouge des points d'intersection");
		colorIntersectionG.addContent("Taux de vert des points d'intersection");
		colorIntersectionB.addContent("Taux de bleu des points d'intersection");
		colorIntersectionA
						.addContent("Taux d'opacité des points d'intersection");

		colorIntersection.addContent(colorIntersectionR);
		colorIntersection.addContent(colorIntersectionG);
		colorIntersection.addContent(colorIntersectionB);
		colorIntersection.addContent(colorIntersectionA);
		return colorIntersection;

	}

	/**
	 * 
	 */

	public void genererXML() {

		final Element extensionConfigName = new Element("configExtension");
		extensionConfigName.addContent(this.extensionConfig);
		final Element logger = new Element("logger");
		final Element loggerConfig = new Element("config");
		loggerConfig.addContent("log4j.xml");

		logger.addContent(loggerConfig);

		this.racine.addContent(extensionConfigName);
		this.racine.addContent(logger);
		this.racine.addContent(this.territoireModel());
		this.racine.addContent(this.pointModel());
		this.racine.addContent(this.mapModel());
		this.racine.addContent(this.mapView());
		this.racine.addContent(this.mainPanel());
		this.racine.addContent(this.about());
		this.racine.addContent(this.renamePoint());
		this.racine.addContent(this.renameTerritoire());
		this.racine.addContent(this.mouseList());
		this.racine.addContent(this.toolBarCommande());
		this.racine.addContent(this.toolBarInfo());
		this.racine.addContent(this.toolBarMeteo());
		this.racine.addContent(this.changeConfig());

		this.sauvegarderXML();

	}

	/**
	 * @return String
	 */
	public String getPathFile() {

		return this.pathFile + this.extensionConfig;

	}

	private Element mainPanel() {

		final Element mainPanel = new Element("views.MainPanel");

		mainPanel.addContent(this.mainPanel1());
		mainPanel.addContent(this.mainPanel2());
		mainPanel.addContent(this.mainPanel3());
		mainPanel.addContent(this.mainPanel4());
		mainPanel.addContent(this.mainPanel5());
		mainPanel.addContent(this.mainPanel6());
		mainPanel.addContent(this.mainPanel7());
		mainPanel.addContent(this.mainPanel8());
		mainPanel.addContent(this.navire());

		return mainPanel;

	}

	private Element mainPanel1() {

		final Element nomTerritoire = new Element("nomTerritoire");
		final Element nomTerritoireR = new Element(GenererConfig.REDNAME);
		final Element nomTerritoireG = new Element(GenererConfig.GREENNAME);
		final Element nomTerritoireB = new Element(GenererConfig.BLUENAME);
		final Element nomTerritoireA = new Element(GenererConfig.ALPHANAME);

		nomTerritoireR.addContent("0");
		nomTerritoireG.addContent("0");
		nomTerritoireB.addContent(GenererConfig.MAXALPHA);
		nomTerritoireA.addContent(GenererConfig.MAXALPHA);

		nomTerritoire.addContent(nomTerritoireR);
		nomTerritoire.addContent(nomTerritoireG);
		nomTerritoire.addContent(nomTerritoireB);
		nomTerritoire.addContent(nomTerritoireA);
		return nomTerritoire;

	}

	private Element mainPanel2() {

		final Element couleurMainPanelPoint = new Element("point");
		final Element couleurMainPanelPointR = new Element(
						GenererConfig.REDNAME);
		final Element couleurMainPanelPointG = new Element(
						GenererConfig.GREENNAME);
		final Element couleurMainPanelPointB = new Element(
						GenererConfig.BLUENAME);
		final Element couleurMainPanelPointA = new Element(
						GenererConfig.ALPHANAME);

		couleurMainPanelPointR.addContent(GenererConfig.MAXALPHA);
		couleurMainPanelPointG.addContent("0");
		couleurMainPanelPointB.addContent("0");
		couleurMainPanelPointA.addContent(GenererConfig.MAXALPHA);

		couleurMainPanelPoint.addContent(couleurMainPanelPointR);
		couleurMainPanelPoint.addContent(couleurMainPanelPointG);
		couleurMainPanelPoint.addContent(couleurMainPanelPointB);
		couleurMainPanelPoint.addContent(couleurMainPanelPointA);
		return couleurMainPanelPoint;

	}

	private Element mainPanel3() {

		final Element colorIntersection = new Element("colorIntersection");
		final Element red = new Element(GenererConfig.REDNAME);
		final Element green = new Element(GenererConfig.GREENNAME);
		final Element blue = new Element(GenererConfig.BLUENAME);
		final Element alpha = new Element(GenererConfig.ALPHANAME);

		red.addContent("0");
		green.addContent(GenererConfig.MAXALPHA);
		blue.addContent(GenererConfig.MAXALPHA);
		alpha.addContent(GenererConfig.MAXALPHA);

		colorIntersection.addContent(red);
		colorIntersection.addContent(green);
		colorIntersection.addContent(blue);
		colorIntersection.addContent(alpha);
		return colorIntersection;

	}

	private Element mainPanel4() {

		final Element couleurMainPanelTrace = new Element("trace");
		final Element couleurMainPanelTraceR = new Element(
						GenererConfig.REDNAME);
		final Element couleurMainPanelTraceG = new Element(
						GenererConfig.GREENNAME);
		final Element couleurMainPanelTraceB = new Element(
						GenererConfig.BLUENAME);
		final Element couleurMainPanelTraceA = new Element(
						GenererConfig.ALPHANAME);

		couleurMainPanelTraceR.addContent(GenererConfig.MAXALPHA);
		couleurMainPanelTraceG.addContent("0");
		couleurMainPanelTraceB.addContent("0");
		couleurMainPanelTraceA.addContent(GenererConfig.MAXALPHA);

		couleurMainPanelTrace.addContent(couleurMainPanelTraceR);
		couleurMainPanelTrace.addContent(couleurMainPanelTraceG);
		couleurMainPanelTrace.addContent(couleurMainPanelTraceB);
		couleurMainPanelTrace.addContent(couleurMainPanelTraceA);
		return couleurMainPanelTrace;

	}

	private Element mainPanel5() {

		final Element taillePoint = new Element("taillePoint");
		final Element taillePointPosX = new Element("positionX");
		final Element taillePointPosY = new Element("positionY");
		final Element taillePointTailleX = new Element("tailleX");
		final Element taillePointTailleY = new Element("tailleY");

		taillePointPosX.addContent("1");
		taillePointPosY.addContent("1");
		taillePointTailleX.addContent("4");
		taillePointTailleY.addContent("4");

		taillePoint.addContent(taillePointPosX);
		taillePoint.addContent(taillePointPosY);
		taillePoint.addContent(taillePointTailleX);
		taillePoint.addContent(taillePointTailleY);

		return taillePoint;

	}

	private Element mainPanel6() {

		final Element taillePort = new Element("taillePort");
		final Element taillePortPosX = new Element("positionX");
		final Element taillePortPosY = new Element("positionY");
		final Element taillePortTailleX = new Element("tailleX");
		final Element taillePortTailleY = new Element("tailleY");

		taillePortPosX.addContent("5");
		taillePortPosY.addContent("5");
		taillePortTailleX.addContent("10");
		taillePortTailleY.addContent("10");

		taillePort.addContent(taillePortPosX);
		taillePort.addContent(taillePortPosY);
		taillePort.addContent(taillePortTailleX);
		taillePort.addContent(taillePortTailleY);
		return taillePort;

	}

	private Element mainPanel7() {

		final Element grid = new Element("grid");

		final Element gridStep = new Element("gridStep");
		gridStep.addContent("15");

		grid.addContent(gridStep);

		return grid;

	}

	private Element mainPanel8() {

		final Element tailleIntersection = new Element("taille_intersection");
		tailleIntersection.addContent("4");
		return tailleIntersection;

	}

	private Element mapModel() {

		final Element mapModel = new Element("models.MapModel");
		final Element extensionMap = new Element("extensionMap");
		extensionMap.addContent(".xml");
		final Element image = new Element("textImagefond");
		image.addContent("imagefond");
		final Element backgroundImage = new Element("backgroundImage");
		backgroundImage.addContent("SystemDefault");

		mapModel.addContent(backgroundImage);
		mapModel.addContent(extensionMap);
		mapModel.addContent(image);

		return mapModel;
	}

	private Element mapView() {

		final Element mapView = new Element("views.MapView");

		final Element windowTitle = new Element("windowTitle");
		windowTitle.addContent("Trafalgar Player");
		mapView.addContent(this.mapView1());
		mapView.addContent(windowTitle);
		mapView.addContent(this.mapView4());
		mapView.addContent(this.mapView2());
		return mapView;

	}

	private Element mapView1() {

		final Element menu = new Element("menu");
		final Element menuFichier = new Element("fichier");
		final Element menuEdition = new Element("edition");
		final Element menuAide = new Element("aide");
		final Element menuFichierTitre = new Element("Titre");
		menuFichierTitre.addContent("Fichier");

		final Element menuFichierQuitter = new Element("quitter");
		menuFichierQuitter.addContent("Quitter");
		menuFichier.addContent(menuFichierTitre);
		menuFichier.addContent(menuFichierQuitter);

		final Element menuEditionTitre = new Element("Titre");
		menuEditionTitre.addContent("Edition");
		final Element menuEditionPreferences = new Element("preferences");
		menuEditionPreferences.addContent("Préférences");

		menuEdition.addContent(menuEditionTitre);
		menuEdition.addContent(menuEditionPreferences);

		final Element menuAideTitre = new Element("Titre");
		menuAideTitre.addContent("Aide");
		final Element menuAideAbout = new Element("about");
		menuAideAbout.addContent("A propos");
		menuAide.addContent(menuAideTitre);
		menuAide.addContent(menuAideAbout);

		menu.addContent(menuFichier);
		menu.addContent(menuEdition);
		menu.addContent(menuAide);
		return menu;
	}

	private Element mapView2() {

		final Element key = new Element("keyboard");

		final Element moveLeft = new Element("move_left");
		final Element moveRight = new Element("move_right");
		final Element moveUp = new Element("move_up");
		final Element moveDown = new Element("move_down");
		final Element zoomPlus = new Element("zoom_plus");
		final Element zoomMoins = new Element("zoom_moins");
		final Element exit = new Element("exit");
		final Element undo = new Element("undo");
		final Element redo = new Element("redo");
		final Element clean = new Element("clean");
		final Element rotateLeft = new Element("rotate_left");
		final Element rotateRight = new Element("rotate_right");

		final Element moveLeftKey = new Element(GenererConfig.CONFIG_KEY);
		final Element moveRightKey = new Element(GenererConfig.CONFIG_KEY);
		final Element moveUpKey = new Element(GenererConfig.CONFIG_KEY);
		final Element moveDownKey = new Element(GenererConfig.CONFIG_KEY);
		final Element zoomPlusKey = new Element(GenererConfig.CONFIG_KEY);
		final Element zoomMoinsKey = new Element(GenererConfig.CONFIG_KEY);
		final Element exitKey = new Element(GenererConfig.CONFIG_KEY);
		final Element undoKey = new Element(GenererConfig.CONFIG_KEY);
		final Element redoKey = new Element(GenererConfig.CONFIG_KEY);
		final Element cleanKey = new Element(GenererConfig.CONFIG_KEY);
		final Element rotateLeftKey = new Element(GenererConfig.CONFIG_KEY);
		final Element rotateRightKey = new Element(GenererConfig.CONFIG_KEY);

		final Element moveLeftTaux = new Element(GenererConfig.CONFIG_TAUX);
		final Element moveRightTaux = new Element(GenererConfig.CONFIG_TAUX);
		final Element moveUpTaux = new Element(GenererConfig.CONFIG_TAUX);
		final Element moveDownTaux = new Element(GenererConfig.CONFIG_TAUX);
		final Element zoomPlusTaux = new Element(GenererConfig.CONFIG_TAUX);
		final Element zoomMoinsTaux = new Element(GenererConfig.CONFIG_TAUX);
		final Element rotateLeftTaux = new Element(GenererConfig.CONFIG_TAUX);
		final Element rotateRightTaux = new Element(GenererConfig.CONFIG_TAUX);

		moveLeftKey.addContent(String.valueOf(KeyEvent.VK_LEFT));
		moveRightKey.addContent(String.valueOf(KeyEvent.VK_RIGHT));
		moveUpKey.addContent(String.valueOf(KeyEvent.VK_UP));
		moveDownKey.addContent(String.valueOf(KeyEvent.VK_DOWN));
		zoomPlusKey.addContent(String.valueOf(KeyEvent.VK_Z));
		zoomMoinsKey.addContent(String.valueOf(KeyEvent.VK_S));
		exitKey.addContent(String.valueOf(KeyEvent.VK_C));
		undoKey.addContent(String.valueOf(KeyEvent.VK_Z));
		redoKey.addContent(String.valueOf(KeyEvent.VK_Y));
		cleanKey.addContent(String.valueOf(KeyEvent.VK_ESCAPE));
		rotateLeftKey.addContent(String.valueOf(KeyEvent.VK_Q));
		rotateRightKey.addContent(String.valueOf(KeyEvent.VK_D));

		moveLeftTaux.addContent("10");
		moveRightTaux.addContent("-10");
		moveUpTaux.addContent("10");
		moveDownTaux.addContent("-10");
		zoomPlusTaux.addContent("1.01");
		zoomMoinsTaux.addContent("0.99");
		rotateLeftTaux.addContent("1");
		rotateRightTaux.addContent("-1");

		moveLeft.addContent(moveLeftKey);
		moveLeft.addContent(moveLeftTaux);
		moveUp.addContent(moveUpTaux);
		moveUp.addContent(moveUpKey);
		moveDown.addContent(moveDownKey);
		moveDown.addContent(moveDownTaux);
		moveRight.addContent(moveRightKey);
		moveRight.addContent(moveRightTaux);
		zoomPlus.addContent(zoomPlusKey);
		zoomPlus.addContent(zoomPlusTaux);
		zoomMoins.addContent(zoomMoinsKey);
		zoomMoins.addContent(zoomMoinsTaux);
		exit.addContent(exitKey);
		undo.addContent(undoKey);
		redo.addContent(redoKey);
		clean.addContent(cleanKey);
		rotateLeft.addContent(rotateLeftKey);
		rotateRight.addContent(rotateRightKey);
		rotateLeft.addContent(rotateLeftTaux);
		rotateRight.addContent(rotateRightTaux);

		key.addContent(moveLeft);
		key.addContent(moveUp);
		key.addContent(moveDown);
		key.addContent(moveRight);
		key.addContent(exit);
		key.addContent(undo);
		key.addContent(redo);
		key.addContent(zoomPlus);
		key.addContent(zoomMoins);
		key.addContent(clean);
		key.addContent(rotateLeft);
		key.addContent(rotateRight);

		return key;
	}

	private Element mapView4() {

		final Element colorChooser = new Element("colorChooser");
		final Element colorChooserRed = new Element(GenererConfig.REDNAME);
		final Element colorChooserBlue = new Element(GenererConfig.BLUENAME);
		final Element colorChooserGreen = new Element(GenererConfig.GREENNAME);
		final Element colorChooserAlpha = new Element(GenererConfig.ALPHANAME);
		final Element colorChooserLayout = new Element(GenererConfig.LAYOUTNAME);

		colorChooserLayout.addContent(BorderLayout.SOUTH);
		colorChooserRed.addContent(GenererConfig.DEFAULTSLIDER);
		colorChooserBlue.addContent(GenererConfig.DEFAULTSLIDER);
		colorChooserGreen.addContent(GenererConfig.DEFAULTSLIDER);
		colorChooserAlpha.addContent(GenererConfig.DEFAULTSLIDER);

		colorChooser.addContent(colorChooserLayout);
		colorChooser.addContent(colorChooserRed);
		colorChooser.addContent(colorChooserGreen);
		colorChooser.addContent(colorChooserBlue);
		colorChooser.addContent(colorChooserAlpha);
		return colorChooser;
	}

	private Element mouseList() {

		final Element mouseList = new Element("views.MouseList");
		final Element valeurChangement = new Element("valeurChangement");
		valeurChangement.addContent("5");
		mouseList.addContent(valeurChangement);
		return mouseList;

	}

	private Element navire() {

		final Element navire = new Element("navire");

		navire.addContent(this.navire5());
		navire.addContent(this.navire3());
		navire.addContent(this.navire4());
		navire.addContent(this.navire2());
		navire.addContent(this.navire6());
		navire.addContent(this.navire7());
		return navire;

	}

	private Element navire2() {

		final Element couleurDirection = new Element("couleur_direction");

		final Element couleurDirectionR = new Element(GenererConfig.REDNAME);
		final Element couleurDirectionG = new Element(GenererConfig.GREENNAME);
		final Element couleurDirectionB = new Element(GenererConfig.BLUENAME);
		final Element couleurDirectionA = new Element(GenererConfig.ALPHANAME);

		couleurDirection.addContent(couleurDirectionR);
		couleurDirection.addContent(couleurDirectionG);
		couleurDirection.addContent(couleurDirectionB);
		couleurDirection.addContent(couleurDirectionA);

		couleurDirectionR.addContent("0");
		couleurDirectionG.addContent("0");
		couleurDirectionB.addContent(GenererConfig.MAXALPHA);
		couleurDirectionA.addContent(GenererConfig.MAXALPHA);

		return couleurDirection;

	}

	private Element navire3() {

		final Element couleurNavire = new Element("couleur_navire");

		final Element couleurNavireR = new Element(GenererConfig.REDNAME);
		final Element couleurNavireG = new Element(GenererConfig.GREENNAME);
		final Element couleurNavireB = new Element(GenererConfig.BLUENAME);
		final Element couleurNavireA = new Element(GenererConfig.ALPHANAME);

		couleurNavire.addContent(couleurNavireR);
		couleurNavire.addContent(couleurNavireG);
		couleurNavire.addContent(couleurNavireB);
		couleurNavire.addContent(couleurNavireA);

		couleurNavireR.addContent("0");
		couleurNavireG.addContent("0");
		couleurNavireB.addContent(GenererConfig.DEFAULTSLIDER);
		couleurNavireA.addContent(GenererConfig.MAXALPHA);

		return couleurNavire;

	}

	private Element navire4() {

		final Element couleurNavireSelectionner = new Element(
						"couleur_navire_selectionner");

		final Element couleurNavireSelectionnerR = new Element(
						GenererConfig.REDNAME);
		final Element couleurNavireSelectionnerG = new Element(
						GenererConfig.GREENNAME);
		final Element couleurNavireSelectionnerB = new Element(
						GenererConfig.BLUENAME);
		final Element couleurNavireSelectionnerA = new Element(
						GenererConfig.ALPHANAME);

		couleurNavireSelectionner.addContent(couleurNavireSelectionnerR);
		couleurNavireSelectionner.addContent(couleurNavireSelectionnerG);
		couleurNavireSelectionner.addContent(couleurNavireSelectionnerB);
		couleurNavireSelectionner.addContent(couleurNavireSelectionnerA);

		couleurNavireSelectionnerR.addContent("0");
		couleurNavireSelectionnerG.addContent(GenererConfig.DEFAULTSLIDER);
		couleurNavireSelectionnerB.addContent("0");
		couleurNavireSelectionnerA.addContent(GenererConfig.MAXALPHA);

		return couleurNavireSelectionner;

	}

	private Element navire5() {

		final Element couleurNavireJoueur = new Element("couleur_navire_joueur");

		final Element couleurNavireJoueurR = new Element(GenererConfig.REDNAME);
		final Element couleurNavireJoueurG = new Element(
						GenererConfig.GREENNAME);
		final Element couleurNavireJoueurB = new Element(GenererConfig.BLUENAME);
		final Element couleurNavireJoueurA = new Element(
						GenererConfig.ALPHANAME);

		couleurNavireJoueur.addContent(couleurNavireJoueurR);
		couleurNavireJoueur.addContent(couleurNavireJoueurG);
		couleurNavireJoueur.addContent(couleurNavireJoueurB);
		couleurNavireJoueur.addContent(couleurNavireJoueurA);

		couleurNavireJoueurR.addContent(GenererConfig.DEFAULTSLIDER);
		couleurNavireJoueurG.addContent("0");
		couleurNavireJoueurB.addContent("0");
		couleurNavireJoueurA.addContent(GenererConfig.MAXALPHA);

		return couleurNavireJoueur;

	}

	private Element navire6() {

		final Element tailleNavire = new Element(GenererConfig.CONFIG_TAILLE);
		tailleNavire.addContent("8");
		return tailleNavire;

	}

	private Element navire7() {

		final Element tailleDetectionNavire = new Element("taille_detection");
		tailleDetectionNavire.addContent("10");
		return tailleDetectionNavire;

	}

	private Element pointModel() {

		final Element pointModel = new Element("models.PointModel");
		final Element textX = new Element("textX");
		textX.addContent("X");
		final Element textY = new Element("textY");
		textY.addContent("Y");
		final Element textPort = new Element("textPort");
		textPort.addContent("port");
		final Element point = new Element("textPoint");
		point.addContent("point");

		pointModel.addContent(textX);
		pointModel.addContent(textY);
		pointModel.addContent(textPort);
		pointModel.addContent(point);

		return pointModel;

	}

	private Element renamePoint() {

		final Element renamePoint = new Element("views.RenamePoint");

		final Element renamePointTaille = new Element(
						GenererConfig.CONFIG_TAILLE);
		final Element renamePointX = new Element("x");
		renamePointX.addContent("567");
		final Element renamePointY = new Element("y");
		renamePointY.addContent("130");
		renamePointTaille.addContent(renamePointX);
		renamePointTaille.addContent(renamePointY);

		final Element renamePointNbColumns = new Element("nbColumns");
		renamePointNbColumns.addContent("10");

		final Element renamePointPosition = new Element("position");
		final Element renamePointPositionX = new Element("x");
		renamePointPositionX.addContent("120");
		final Element renamePointPositionY = new Element("y");
		renamePointPositionY.addContent("110");
		renamePointPosition.addContent(renamePointPositionX);
		renamePointPosition.addContent(renamePointPositionY);

		final Element renamePointlayout = new Element(GenererConfig.LAYOUTNAME);

		try {
			renamePointlayout.addContent(ObjectString
							.toString(new FlowLayout()));
		} catch (final IOException e) {

		}

		final Element buttonOk = new Element("button");
		buttonOk.addContent("Ok");

		renamePoint.addContent(renamePointNbColumns);
		renamePoint.addContent(renamePointlayout);
		renamePoint.addContent(renamePointTaille);
		renamePoint.addContent(renamePointPosition);
		renamePoint.addContent(buttonOk);
		return renamePoint;

	}

	private Element renameTerritoire() {

		final Element renameTerritoire = new Element("views.RenameTerritoire");

		final Element renameTerritoireTaille = new Element(
						GenererConfig.CONFIG_TAILLE);
		final Element renameTerritoireX = new Element("x");
		renameTerritoireX.addContent("567");
		final Element renameTerritoireY = new Element("y");
		renameTerritoireY.addContent("150");
		renameTerritoireTaille.addContent(renameTerritoireX);
		renameTerritoireTaille.addContent(renameTerritoireY);

		final Element renameTerritoireNbColumns = new Element("nbColumns");
		renameTerritoireNbColumns.addContent("10");

		final Element renameTerritoirePosition = new Element("position");
		final Element renameTerritoirePositionX = new Element("x");
		renameTerritoirePositionX.addContent("160");
		final Element renameTerritoirePositionY = new Element("y");
		renameTerritoirePositionY.addContent("170");
		renameTerritoirePosition.addContent(renameTerritoirePositionX);
		renameTerritoirePosition.addContent(renameTerritoirePositionY);

		final Element renameTerritoirelayout = new Element(
						GenererConfig.LAYOUTNAME);

		try {
			renameTerritoirelayout.addContent(ObjectString
							.toString(new FlowLayout()));
		} catch (final IOException e) {

		}

		final Element renameTerritoirebuttonOk = new Element("button");
		renameTerritoirebuttonOk.addContent("Ok");

		renameTerritoire.addContent(renameTerritoireNbColumns);
		renameTerritoire.addContent(renameTerritoirelayout);
		renameTerritoire.addContent(renameTerritoireTaille);
		renameTerritoire.addContent(renameTerritoirePosition);
		renameTerritoire.addContent(renameTerritoirebuttonOk);
		return renameTerritoire;

	}

	/**
	 * 
	 */
	public void sauvegarderXML() {

		try {

			final XMLOutputter sortie = new XMLOutputter(
							org.jdom2.output.Format.getPrettyFormat());
			sortie.output(this.racine, new FileOutputStream(this.pathFile
							+ this.extensionConfig));

		} catch (final java.io.IOException e) {

		}

	}

	/**
	 * @param pathFile
	 */
	public void setPathFile(final String pathFile) {

		this.pathFile = pathFile;

	}

	/**
	 * @param pathFile
	 * @param version
	 * @param extensionConfig
	 */
	public void setPathFile(final String pathFile, final String version,
					final String extensionConfig) {

		this.pathFile = pathFile + version;
		if (extensionConfig == null) {
			this.extensionConfig = ".xml";
		}

	}

	/**
	 *  
	 */

	private Element territoireModel() {

		final Element territoireModel = new Element("models.TerritoireModel");
		final Element couleur = new Element("couleur");
		final Element nom = new Element("textNom");
		nom.addContent("nom");
		final Element red = new Element("textRed");
		red.addContent(GenererConfig.REDNAME);
		final Element blue = new Element("textBlue");
		blue.addContent(GenererConfig.BLUENAME);

		final Element green = new Element("textGreen");
		green.addContent(GenererConfig.GREENNAME);
		final Element alpha = new Element("textAlpha");
		alpha.addContent(GenererConfig.ALPHANAME);
		couleur.addContent(red);
		couleur.addContent(green);
		couleur.addContent(blue);
		couleur.addContent(alpha);

		final Element textCouleur = new Element("textCouleur");
		textCouleur.addContent("couleur");

		final Element territoire = new Element("textTerritoire");
		territoire.addContent("territoire");

		territoireModel.addContent(couleur);
		territoireModel.addContent(territoire);
		territoireModel.addContent(nom);
		territoireModel.addContent(textCouleur);

		return territoireModel;

	}

	private Element toolBarCommande() {

		final Element toolBarCommande = new Element("views.ToolBarCommande");

		final Element effacerLigne = new Element("effacerLignes");
		final Element vitesse = new Element("vitesse");

		effacerLigne.addContent("Effacer les lignes");
		vitesse.addContent("vitesse");

		toolBarCommande.addContent(vitesse);
		toolBarCommande.addContent(effacerLigne);

		return toolBarCommande;

	}

	private Element toolBarInfo() {

		final Element toolBarInfo = new Element("views.ToolBarInfo");

		final Element textNoName = new Element("noName");
		final Element textAppartenance = new Element("appartenance");
		final Element textIdentifiant = new Element("identifiant");
		final Element textFlotte = new Element("flotte");
		final Element textJoueur = new Element("joueur");
		final Element textVitesse = new Element("vitesse");
		final Element textVitesseVoulue = new Element("vitesseVoulue");
		final Element textEtat = new Element("etat");
		final Element textSoldat = new Element("soldat");
		final Element textMunition = new Element("munition");
		final Element textDirection = new Element("direction");
		final Element textDirectionVoulue = new Element("directionVoulue");
		final Element textAngle = new Element("angle");
		final Element textAngleVoulu = new Element("angleVoulu");

		textNoName.addContent("----------");
		textAppartenance.addContent("Ce territoire appartient a: ");
		textIdentifiant.addContent("Identifiant: ");
		textFlotte.addContent("Flotte: ");
		textJoueur.addContent("Joueur: ");
		textVitesse.addContent("Vitesse: ");
		textVitesseVoulue.addContent("Vitesse voulue: ");
		textEtat.addContent("Etat: ");
		textSoldat.addContent("Nombre de soldats: ");
		textMunition.addContent("Quantité de munitions: ");
		textDirection.addContent("Direction: ");
		textDirectionVoulue.addContent("Direction voulue: ");
		textAngle.addContent("Angle: ");
		textAngleVoulu.addContent("Angle voulu: ");

		toolBarInfo.addContent(textNoName);
		toolBarInfo.addContent(textAppartenance);
		toolBarInfo.addContent(textIdentifiant);
		toolBarInfo.addContent(textFlotte);
		toolBarInfo.addContent(textJoueur);
		toolBarInfo.addContent(textVitesse);
		toolBarInfo.addContent(textVitesseVoulue);
		toolBarInfo.addContent(textEtat);
		toolBarInfo.addContent(textSoldat);
		toolBarInfo.addContent(textMunition);
		toolBarInfo.addContent(textDirection);
		toolBarInfo.addContent(textDirectionVoulue);
		toolBarInfo.addContent(textAngle);
		toolBarInfo.addContent(textAngleVoulu);

		return toolBarInfo;
	}

	private Element toolBarMeteo() {

		final Element toolBarMeteo = new Element("views.ToolBarMeteo");

		final Element tirer = new Element("tirer");
		final Element embarquer = new Element("embarquer");
		final Element debarquer = new Element("debarquer");
		final Element meteo = new Element("meteo");
		final Element etatMeteo0 = new Element("etat0");
		final Element etatMeteo1 = new Element("etat1");
		final Element etatMeteo2 = new Element("etat2");
		final Element etatMeteo3 = new Element("etat3");
		final Element etatMeteo4 = new Element("etat4");
		final Element etatMeteo5 = new Element("etat5");
		final Element etatMeteo6 = new Element("etat6");
		final Element etatMeteo7 = new Element("etat7");
		final Element etatMeteo8 = new Element("etat8");
		final Element etatMeteo9 = new Element("etat9");
		final Element etatMeteo10 = new Element("etat10");
		final Element etatMeteo11 = new Element("etat11");
		final Element etatMeteo12 = new Element("etat12");
		final Element etatMeteo13 = new Element("etat13");
		final Element etatMeteo14 = new Element("etat14");
		final Element etatMeteo15 = new Element("etat15");
		final Element etatMeteoNegatif = new Element("etat-1");

		tirer.addContent("Tirer!");
		embarquer.addContent("Embarquez les troupes");
		debarquer.addContent("Debarquez les troupes");

		etatMeteo0.addContent("mer calme et soleil");
		etatMeteo1.addContent("mer légèrement agitée et soleil");
		etatMeteo2.addContent("mer légèrement agitée et ciel modérément couvert");
		etatMeteo3.addContent("mer légèrement agitée et ciel couvert");
		etatMeteo4.addContent("mer légèrement agitée et pluie");
		etatMeteo5.addContent("mer légèrement agitée et pluie forte");
		etatMeteo6.addContent("mer agitée et soleil");
		etatMeteo7.addContent("mer agitée et ciel modérément couvert");
		etatMeteo8.addContent("mer agitée et ciel couvert");
		etatMeteo9.addContent("mer agitée et pluie");
		etatMeteo10.addContent("mer agitée et pluie forte");
		etatMeteo11.addContent("mer agitée et pluie forte mer fortement agitée et soleil");
		etatMeteo12.addContent("mer fortement agitée et ciel modérément couvert");
		etatMeteo13.addContent("mer fortement agitée et ciel couvert");
		etatMeteo14.addContent("mer fortement agitée et pluie");
		etatMeteo15.addContent("mer fortement agitée et pluie forte");
		etatMeteoNegatif.addContent("nuit");

		meteo.addContent(etatMeteo0);
		meteo.addContent(etatMeteo1);
		meteo.addContent(etatMeteo2);
		meteo.addContent(etatMeteo3);
		meteo.addContent(etatMeteo4);
		meteo.addContent(etatMeteo5);
		meteo.addContent(etatMeteo6);
		meteo.addContent(etatMeteo7);
		meteo.addContent(etatMeteo8);
		meteo.addContent(etatMeteo9);
		meteo.addContent(etatMeteo10);
		meteo.addContent(etatMeteo11);
		meteo.addContent(etatMeteo12);
		meteo.addContent(etatMeteo13);
		meteo.addContent(etatMeteo14);
		meteo.addContent(etatMeteo15);
		meteo.addContent(etatMeteoNegatif);

		toolBarMeteo.addContent(tirer);
		toolBarMeteo.addContent(embarquer);
		toolBarMeteo.addContent(debarquer);
		toolBarMeteo.addContent(meteo);

		return toolBarMeteo;
	}
}
