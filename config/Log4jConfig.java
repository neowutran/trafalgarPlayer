package config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Cette classe va copier le fichier de configuration de log4j contenu dans le
 * jar ou le dossier de
 * ressources vers un autre emplacement
 * 
 * @author Cibert Gauthier
 * @author Martini Didier
 * @author Responsable: Martini Didier
 * @version 3.0
 */
public class Log4jConfig {

	private String				destination;
	private final InputStream	sources			= this.getClass()
																.getClassLoader()
																.getResourceAsStream(
																				"log4j.xml");
	private static final int	TAILLEBUFFER	= 1024;

	/**
	 * Copie le fichier
	 * 
	 * @param destination
	 */
	public void copyfile(final String destination) {

		this.setDestination(destination);

		try {

			final File f2 = new File(destination);

			final OutputStream out = new FileOutputStream(f2);

			final byte[] buf = new byte[Log4jConfig.TAILLEBUFFER];
			int len;
			while ((len = this.sources.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			this.sources.close();
			out.close();
		} catch (final FileNotFoundException ex) {
			System.exit(0);
		} catch (final IOException e) {
		}
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {

		return this.destination;
	}

	/**
	 * @param destination
	 *            the destination to set
	 */
	public void setDestination(final String destination) {

		this.destination = destination;
	}

}
