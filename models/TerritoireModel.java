package models;

import java.awt.Color;
import java.awt.Polygon;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.jdom2.Element;

/**
 * Cette classe est le model qui gere les territoires
 * Theoriquement cette classe est identique a celle que vous trouverez dans
 * trafalgarMap de mon binome
 * 
 * @author Cibert Gauthier
 * @author Martini Didier
 * @author Responsable: Martini Didier
 * @version 3.0
 */
public class TerritoireModel extends Polygon implements Cloneable, Observer {

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;
	/**
	 * 
	 */
	private String					name;
	private Color					color;
	private final List<PointModel>	points;
	private final Logger			logger;
	private static Element			configuration;
	private final MapModel			parent;
	private String					appartenance;

	/**
	 * @param listepoint
	 * @param getcolor
	 * @param getname
	 * @param config
	 * @param parent
	 */
	public TerritoireModel(final List<PointModel> listepoint,
					final Color getcolor, final String getname,
					final Element config, final MapModel parent) {

		this.parent = parent;
		TerritoireModel.configuration = config;
		DOMConfigurator.configure(config.getChild("logger").getChildText(
						"config"));
		this.logger = Logger.getLogger(this.getClass().getName());

		int nbboucle;

		for (nbboucle = 0; nbboucle < listepoint.size(); nbboucle++) {

			this.addPoint((int) listepoint.get(nbboucle).getX(),
							(int) listepoint.get(nbboucle).getY());

		}

		this.npoints = listepoint.size();
		this.points = listepoint;
		this.color = getcolor;
		this.name = getname;

	}

	/**
	 * @return String
	 */
	public String getAppartenance() {

		return this.appartenance;

	}

	/**
	 * @return Color getter color
	 */
	public Color getColor() {

		return this.color;

	}

	/**
	 * @return the logger
	 */
	public Logger getLogger() {

		return this.logger;
	}

	/**
	 * @return String
	 */
	public String getName() {

		return this.name;

	}

	/**
	 * @return the parent
	 */
	public MapModel getParent() {

		return this.parent;
	}

	/**
	 * @return Map
	 */
	public List<PointModel> getPoint() {

		this.logger.debug("[return][List<PointModel>]" + this.points);
		return this.points;

	}

	/**
	 * @param appartenance
	 */
	public void setAppartenance(final String appartenance) {

		this.appartenance = appartenance;

	}

	/**
	 * @param c
	 * @return boolean Setter color
	 */
	public boolean setColor(final Color c) {

		this.color = c;
		return true;

	}

	/**
	 * @param name
	 * @return boolean
	 */
	public boolean setName(final String name) {

		if (name == null) {

			return false;

		}

		this.name = name;
		return true;

	}

	/**
	 * @return Element
	 */
	public Element toXML() {

		int r, b, g, a;
		final String couleurName = "couleur";
		r = this.color.getRed();
		b = this.color.getBlue();
		g = this.color.getGreen();
		a = this.color.getAlpha();

		final Element territoire = new Element(TerritoireModel.configuration
						.getChild(this.getClass().getName()).getChildText(
										"textTerritoire"));

		final Element nom = new Element(TerritoireModel.configuration.getChild(
						this.getClass().getName()).getChildText("textNom"));
		nom.addContent(this.name);
		final Element couleur = new Element(TerritoireModel.configuration
						.getChild(this.getClass().getName()).getChildText(
										"textCouleur"));
		final Element couleurr = new Element(TerritoireModel.configuration
						.getChild(this.getClass().getName())
						.getChild(couleurName).getChildText("textRed"));
		couleurr.setText(String.valueOf(r));
		final Element couleurg = new Element(TerritoireModel.configuration
						.getChild(this.getClass().getName())
						.getChild(couleurName).getChildText("textGreen"));
		couleurg.setText(String.valueOf(g));
		final Element couleurb = new Element(TerritoireModel.configuration
						.getChild(this.getClass().getName())
						.getChild(couleurName).getChildText("textBlue"));
		couleurb.setText(String.valueOf(b));
		final Element couleura = new Element(TerritoireModel.configuration
						.getChild(this.getClass().getName())
						.getChild(couleurName).getChildText("textAlpha"));
		couleura.setText(String.valueOf(a));

		couleur.addContent(couleurr);
		couleur.addContent(couleurg);
		couleur.addContent(couleurb);
		couleur.addContent(couleura);

		territoire.addContent(couleur);
		territoire.addContent(nom);

		int nbboucle;
		for (nbboucle = 0; nbboucle < this.points.size(); nbboucle++) {

			territoire.addContent((this.points.get(nbboucle)).toXML());

		}

		return territoire;

	}

	@Override
	public void translate(final int deltaX, final int deltaY) {

		super.translate(deltaX, deltaY);
		int nbboucle;

		for (nbboucle = 0; nbboucle < this.points.size(); nbboucle++) {

			this.points.get(nbboucle).translate(deltaX, deltaY);

		}

	}

	public void update(final Observable o, final Object arg) {

		if (arg instanceof Element) {

			TerritoireModel.configuration = (Element) arg;

		}
	}

}
