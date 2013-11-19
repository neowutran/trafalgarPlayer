package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdom2.Element;

import controllers.TrafalgarPlayerController;

/**
 * Cette classe gere la barre de commande contenant le slider vitesse et le
 * bouton "effacer lignes"
 * 
 * @author Cibert Gauthier
 * @author Martini Didier
 * @author Responsable: Martini Didier
 * @version 3.0
 */
public class ToolBarCommande extends JToolBar implements ActionListener,
				ChangeListener {

	/**
	 * 
	 */
	private static final long				serialVersionUID	= 1L;
	private final JSlider					speed;
	private final JButton					reinitLignes;
	private final MapView					view;
	private final TrafalgarPlayerController	hamecon;
	private static final int				MAXSLIDER			= 100;
	private static final int				MINSLIDER			= 0;
	private static Element					config;

	/**
	 * @param view
	 * @param hamecon
	 * @param config
	 */
	public ToolBarCommande(final MapView view,
					final TrafalgarPlayerController hamecon,
					final Element config) {

		super(SwingConstants.HORIZONTAL);
		ToolBarCommande.config = config;
		this.hamecon = hamecon;
		this.view = view;

		this.add(new JLabel(ToolBarCommande.config.getChild(
						this.getClass().getName()).getChildText("vitesse")));
		this.speed = new JSlider(SwingConstants.HORIZONTAL,
						ToolBarCommande.MINSLIDER, ToolBarCommande.MAXSLIDER, 0);
		this.speed.addChangeListener(this);
		this.add(this.speed);

		this.reinitLignes = new JButton(config.getChild(
						this.getClass().getName())
						.getChildText("effacerLignes"));
		this.reinitLignes.addActionListener(this);
		this.add(this.reinitLignes);

	}

	public void actionPerformed(final ActionEvent e) {

		if (e.getSource() == this.reinitLignes) {
			this.view.reinitLignes();
			this.view.repaint();
		}

	}

	/**
	 * @return JSlider
	 */
	public JSlider getSpeedSlider() {

		return this.speed;

	}

	public void stateChanged(final ChangeEvent e) {

		final JSlider source = (JSlider) e.getSource();

		if (source.getBounds().equals(this.speed.getBounds())) {

			this.hamecon.setSpeedVoulue(source.getValue());

		}

	}

}
