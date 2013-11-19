package demonstrateurs;

import java.io.File;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import config.GenererCarte;
import config.GenererConfig;
import config.GenererFond;
import config.Log4jConfig;
import controllers.TrafalgarPlayerController;

/**
 * Cette classe est le point d'entrer de trafalgarPlayer, elle generera le
 * dossier de configuration et son contenu, puis creera le controller
 * 
 * @author Cibert Gauthier
 * @author Martini Didier
 * @author Responsable: Martini Didier
 * @version 3.0
 */

public class Demonstrateur {

	private static Element					config;
	private final TrafalgarPlayerController	controller;
	public static final String				FOLDER					= "trafalgarPlayer"
																					+ File.separator;
	public static final String				LOG4J					= "log4j.xml";
	public static final String				BACKGROUND				= "fond.jpg";
	public static final String				CONFIG					= "config_1.0.0.xml";
	public static final String				CARTE					= "carte.xml";

	private static final String				CONFIG_LOGGER			= "logger";
	private static final String				CONFIG_LOGGER_CONFIG	= "config";

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		String pathConfig = null;

		if (args.length == 1) {

			pathConfig = args[0];

		}

		new Demonstrateur(pathConfig);

	}

	/**
	 * @param path
	 */
	public Demonstrateur(final String path) {

		if (!(new File(Demonstrateur.FOLDER)).isDirectory()) {

			this.createConfigFolder();

		}

		if (!new File(Demonstrateur.FOLDER + Demonstrateur.CONFIG).exists()) {

			final GenererConfig generer = new GenererConfig();
			generer.setPathFile(Demonstrateur.FOLDER + Demonstrateur.CONFIG);
			generer.genererXML();

		}

		if (!new File(Demonstrateur.FOLDER + Demonstrateur.LOG4J).exists()) {

			final Log4jConfig genererLog4j = new Log4jConfig();
			genererLog4j.copyfile(Demonstrateur.FOLDER + Demonstrateur.LOG4J);

		}

		if (!new File(Demonstrateur.FOLDER + Demonstrateur.CARTE).exists()) {

			final GenererCarte genererCarte = new GenererCarte();
			genererCarte.copyfile(Demonstrateur.FOLDER + Demonstrateur.CARTE);

		}

		if (!new File(Demonstrateur.FOLDER + Demonstrateur.BACKGROUND).exists()) {

			final GenererFond genererFond = new GenererFond();
			genererFond.copyfile(Demonstrateur.FOLDER
							+ Demonstrateur.BACKGROUND);

		}

		String pathConfig;
		if ((path == null) || (!(new File(path)).exists())) {

			pathConfig = Demonstrateur.FOLDER + Demonstrateur.CONFIG;

		} else {

			pathConfig = path;

		}

		this.chargerFichierConf(pathConfig);

		if (Demonstrateur.config == null) {

			pathConfig = Demonstrateur.FOLDER + Demonstrateur.CONFIG;
			this.chargerFichierConf(pathConfig);

		}

		String pathLog4jConfig = Demonstrateur.config.getChild(
						Demonstrateur.CONFIG_LOGGER).getChildText("config");

		if ((pathLog4jConfig == null)
						|| (Demonstrateur.config
										.getChild(Demonstrateur.CONFIG_LOGGER)
										.getChildText(Demonstrateur.CONFIG_LOGGER_CONFIG) == null)
						|| !(new File(
										Demonstrateur.config
														.getChild(Demonstrateur.CONFIG_LOGGER)
														.getChildText(Demonstrateur.CONFIG_LOGGER_CONFIG))
										.exists())) {

			pathLog4jConfig = Demonstrateur.FOLDER + Demonstrateur.LOG4J;

			final Element log4j = new Element(
							Demonstrateur.CONFIG_LOGGER_CONFIG);
			log4j.addContent(pathLog4jConfig);
			Demonstrateur.config.getChild(Demonstrateur.CONFIG_LOGGER)
							.removeChild(Demonstrateur.CONFIG_LOGGER_CONFIG);
			Demonstrateur.config.getChild(Demonstrateur.CONFIG_LOGGER)
							.addContent(log4j);

		}

		String background = null;
		if (((Demonstrateur.config.getChild("models.MapModel").getChildText(
						"backgroundImage") != null) && !(new File(
						Demonstrateur.config.getChild("models.MapModel")
										.getChildText("backgroundImage"))
						.exists()))
						|| Demonstrateur.config.getChild("models.MapModel")
										.getChildText("backgroundImage")
										.equals("SystemDefault")) {

			background = Demonstrateur.FOLDER + Demonstrateur.BACKGROUND;

		}

		this.controller = new TrafalgarPlayerController(Demonstrateur.config,
						background, pathConfig);

	}

	private void chargerFichierConf(final String xmlPath) {

		final SAXBuilder sxb = new SAXBuilder();

		try {

			Demonstrateur.config = sxb.build(new File(xmlPath))
							.getRootElement();

		} catch (final Exception e) {

		}

	}

	private void createConfigFolder() {

		(new File(Demonstrateur.FOLDER)).mkdirs();

	}

	/**
	 * @return the controller
	 */
	public TrafalgarPlayerController getController() {

		return this.controller;
	}

}
