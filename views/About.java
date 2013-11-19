package views;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import library.ObjectString;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.jdom2.Element;

/**
 * Cette classe affiche un fenetre qui affiche le a propos
 * 
 * @author Cibert Gauthier
 * @author Martini Didier
 * @author Responsable: Martini Didier
 * @version 3.0
 */
public class About extends JFrame implements ActionListener {

	private static final long	serialVersionUID	= 1L;
	private static Element		configuration;
	private final Logger		logger;

	/**
	 * Cree la fenetre pour afficher le a propos
	 * 
	 * @param config
	 *            Element de configuration
	 */
	public About(final Element config) {

		About.configuration = config;

		this.setTitle(About.configuration.getChild(this.getClass().getName())
						.getChildText("title"));
		JPanel contentPane;

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

		this.setBounds(Integer.parseInt(About.configuration
						.getChild(this.getClass().getName())
						.getChild("position").getChildText("x")),
						Integer.parseInt(About.configuration
										.getChild(this.getClass().getName())
										.getChild("position").getChildText("y")),
						Integer.parseInt(About.configuration
										.getChild(this.getClass().getName())
										.getChild("taille").getChildText("x")),
						Integer.parseInt(About.configuration
										.getChild(this.getClass().getName())
										.getChild("taille").getChildText("y")));
		contentPane = new JPanel();
		try {
			contentPane.setLayout((LayoutManager) ObjectString
							.fromString(About.configuration.getChild(
											this.getClass().getName())
											.getChildText("layout")));
		} catch (final IOException e) {
			this.logger.error("[" + e.getClass().getName() + "]"
							+ e.getMessage());
		} catch (final ClassNotFoundException e) {
			this.logger.error("[" + e.getClass().getName() + "]"
							+ e.getMessage());

		}
		this.setContentPane(contentPane);
		final JLabel lbl = new JLabel(About.configuration.getChild(
						this.getClass().getName()).getChildText("label"));
		contentPane.add(lbl);
		final JButton btnEnregistrer = new JButton(About.configuration
						.getChild(this.getClass().getName()).getChildText(
										"button"));
		contentPane.add(btnEnregistrer);
		btnEnregistrer.addActionListener(this);

	}

	public void actionPerformed(final ActionEvent e) {

		if (e.getActionCommand().equals(
						About.configuration.getChild(this.getClass().getName())
										.getChildText("button"))) {
			this.logger.debug("[ActionEvent][Click]"
							+ About.configuration.getChild(
											this.getClass().getName())
											.getChildText("button"));
			this.dispose();

		}

	}

}
