package models;

import java.awt.Point;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import library.ObjectString;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.jdom2.Element;

/**
 * Cette classe est le model qui gerer les points constituant les territoires
 * (polygones).
 * Ces points peuvent etre des ports
 * Theoriquement cette classe est identique a celle que vous trouverez dans
 * trafalgarMap de mon binome
 * 
 * @author Cibert Gauthier
 * @author Martini Didier
 * @author Responsable: Martini Didier
 * @version 3.0
 */
public class PointModel extends Point implements Cloneable, Observer {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private static Element		configuration;

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {

		return PointModel.serialVersionUID;
	}

	private boolean			port;
	private String			name	= "";

	private final Logger	logger;

	/**
	 * @param getx
	 * @param gety
	 * @param getport
	 * @param name
	 * @param config
	 */
	public PointModel(final int getx, final int gety, final boolean getport,
					final String name, final Element config) {

		PointModel.configuration = config;
		DOMConfigurator.configure(config.getChild("logger").getChildText(
						"config"));
		this.logger = Logger.getLogger(this.getClass().getName());
		try {
			this.logger.debug("[contructor]int:" + getx + ";int:" + gety
							+ ";boolean:" + getport + ";string:" + name
							+ ";Element:" + ObjectString.toString(config));
		} catch (final IOException e) {

			this.logger.error("[" + e.getClass().getName() + "]"
							+ e.getMessage());

		}

		this.x = getx;
		this.y = gety;
		this.name = name;
		this.port = getport;

	}

	/**
	 * @return String
	 */
	public String getName() {

		return this.name;

	}

	/**
	 * @return boolean
	 */
	public boolean getPort() {

		this.logger.debug("[return][boolean]" + this.port);
		return this.port;

	}

	/**
	 * @param name
	 */
	public void setName(final String name) {

		this.name = name;

	}

	/**
	 */
	public void setPort() {

		if (this.port) {

			this.port = false;
			this.name = "";

		} else {

			this.port = true;
			this.name = "P";

		}

	}

	/**
	 * @return Element
	 */
	public Element toXML() {

		String texteport;

		final Element point = new Element(PointModel.configuration.getChild(
						this.getClass().getName()).getChildText("textPoint"));
		final Element x = new Element(PointModel.configuration.getChild(
						this.getClass().getName()).getChildText("textX"));
		x.setText(String.valueOf(this.x));
		final Element y = new Element(PointModel.configuration.getChild(
						this.getClass().getName()).getChildText("textY"));
		y.setText(String.valueOf(this.y));
		final Element namePoint = new Element(PointModel.configuration
						.getChild(this.getClass().getName()).getChildText(
										"textPoint"));
		namePoint.setText(String.valueOf(this.name));
		final Element port0 = new Element(PointModel.configuration.getChild(
						this.getClass().getName()).getChildText("textPort"));

		if (this.port) {

			texteport = "true";

		} else {

			texteport = "false";

		}

		point.addContent(namePoint);
		port0.setText(texteport);
		point.addContent(x);
		point.addContent(y);
		point.addContent(port0);
		return point;

	}

	public void update(final Observable o, final Object arg) {

		if (arg instanceof Element) {

			PointModel.configuration = (Element) arg;

		}
	}

}
