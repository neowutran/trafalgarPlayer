package views;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import org.jdom2.Element;

import controllers.TrafalgarPlayerController;

/**
 * Cette classe gere la barre qui va afficher la meteo et les bouton tirer,
 * embarquer, debarquer
 * 
 * @author Cibert Gauthier
 * @author Martini Didier
 * @author Responsable: Martini Didier
 * @version 3.0
 */
public class ToolBarMeteo extends JToolBar implements ActionListener {

	/**
	 * 
	 */
	private static final long				serialVersionUID	= 1L;
	private final JLabel					meteo				= new JLabel();
	private int								idMeteo;
	private String							textMeteo;
	private final JButton					tirer;
	private final JButton					debarquer;
	private final JButton					embarquer;
	private final TrafalgarPlayerController	hamecon;
	private static Element					config;
	private static final String				CONFIG_METEO		= "meteo";

	/**
	 * @param controller
	 * @param config
	 */

	public ToolBarMeteo(final TrafalgarPlayerController controller,
					final Element config) {

		super(SwingConstants.VERTICAL);
		ToolBarMeteo.config = config;
		this.hamecon = controller;
		this.add(this.meteo);
		this.addSeparator();
		this.addSeparator();
		this.tirer = new JButton(config.getChild(this.getClass().getName())
						.getChildText("tirer"));
		this.debarquer = new JButton(config.getChild(this.getClass().getName())
						.getChildText("debarquer"));
		this.embarquer = new JButton(config.getChild(this.getClass().getName())
						.getChildText("embarquer"));
		this.tirer.addActionListener(this);
		this.debarquer.addActionListener(this);
		this.embarquer.addActionListener(this);
		this.add(this.tirer);
		this.addSeparator();
		this.add(this.debarquer);
		this.addSeparator();
		this.add(this.embarquer);
		this.addSeparator();

	}

	public void actionPerformed(final ActionEvent e) {

		if (e.getSource() == this.tirer) {

			this.hamecon.tirer();

		} else if (e.getSource() == this.embarquer) {

			this.hamecon.embarquer();

		} else if (e.getSource() == this.debarquer) {

			this.hamecon.debarquer();

		}

	}

	private void idToString() {

		switch (this.idMeteo) {

			case 0:
				this.textMeteo = ToolBarMeteo.config
								.getChild(this.getClass().getName())
								.getChild(ToolBarMeteo.CONFIG_METEO)
								.getChildText("etat0");
				break;
			case 1:
				this.textMeteo = ToolBarMeteo.config
								.getChild(this.getClass().getName())
								.getChild(ToolBarMeteo.CONFIG_METEO)
								.getChildText("etat1");
				break;
			case 2:
				this.textMeteo = ToolBarMeteo.config
								.getChild(this.getClass().getName())
								.getChild(ToolBarMeteo.CONFIG_METEO)
								.getChildText("etat2");
				break;
			case 3:
				this.textMeteo = ToolBarMeteo.config
								.getChild(this.getClass().getName())
								.getChild(ToolBarMeteo.CONFIG_METEO)
								.getChildText("etat3");
				break;
			case 4:
				this.textMeteo = ToolBarMeteo.config
								.getChild(this.getClass().getName())
								.getChild(ToolBarMeteo.CONFIG_METEO)
								.getChildText("etat4");
				break;
			case 5:
				this.textMeteo = ToolBarMeteo.config
								.getChild(this.getClass().getName())
								.getChild(ToolBarMeteo.CONFIG_METEO)
								.getChildText("etat5");
				break;
			case 6:
				this.textMeteo = ToolBarMeteo.config
								.getChild(this.getClass().getName())
								.getChild(ToolBarMeteo.CONFIG_METEO)
								.getChildText("etat6");
				break;
			case 7:
				this.textMeteo = ToolBarMeteo.config
								.getChild(this.getClass().getName())
								.getChild(ToolBarMeteo.CONFIG_METEO)
								.getChildText("etat7");
				break;
			case 8:
				this.textMeteo = ToolBarMeteo.config
								.getChild(this.getClass().getName())
								.getChild(ToolBarMeteo.CONFIG_METEO)
								.getChildText("etat8");
				break;
			case 9:
				this.textMeteo = ToolBarMeteo.config
								.getChild(this.getClass().getName())
								.getChild(ToolBarMeteo.CONFIG_METEO)
								.getChildText("etat9");
				break;
			case 10:
				this.textMeteo = ToolBarMeteo.config
								.getChild(this.getClass().getName())
								.getChild(ToolBarMeteo.CONFIG_METEO)
								.getChildText("etat10");
				break;
			case 11:
				this.textMeteo = ToolBarMeteo.config
								.getChild(this.getClass().getName())
								.getChild(ToolBarMeteo.CONFIG_METEO)
								.getChildText("etat11");
				break;
			case 12:
				this.textMeteo = ToolBarMeteo.config
								.getChild(this.getClass().getName())
								.getChild(ToolBarMeteo.CONFIG_METEO)
								.getChildText("etat12");
				break;
			case 13:
				this.textMeteo = ToolBarMeteo.config
								.getChild(this.getClass().getName())
								.getChild(ToolBarMeteo.CONFIG_METEO)
								.getChildText("etat13");
				break;
			case 14:
				this.textMeteo = ToolBarMeteo.config
								.getChild(this.getClass().getName())
								.getChild(ToolBarMeteo.CONFIG_METEO)
								.getChildText("etat14");
				break;
			case 15:
				this.textMeteo = ToolBarMeteo.config
								.getChild(this.getClass().getName())
								.getChild(ToolBarMeteo.CONFIG_METEO)
								.getChildText("etat15");
				break;
			case -1:
				this.textMeteo = ToolBarMeteo.config
								.getChild(this.getClass().getName())
								.getChild(ToolBarMeteo.CONFIG_METEO)
								.getChildText("etat-1");
				break;

		}

	}

	@Override
	public void paint(final Graphics g) {

		this.setOrientation(SwingConstants.VERTICAL);
		super.paint(g);

	}

	/**
	 * @param meteo
	 */
	public void setMeteo(final int meteo) {

		this.idMeteo = meteo;
		this.setOrientation(SwingConstants.VERTICAL);
		this.idToString();
		this.meteo.setText(this.textMeteo);

		this.repaint();
	}

}
