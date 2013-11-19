package models;

import java.awt.Point;

/**
 * Cette classe va generer les lignes et les intersection (Point) entre celles
 * ci
 * 
 * @author Cibert Gauthier
 * @author Martini Didier
 * @author Responsable: Martini Didier
 * @version 3.0
 */
public class Ligne {

	private Point	p1;
	private Point	p2;

	/**
	 * @param p1
	 * @param p2
	 */
	public Ligne(final Point p1, final Point p2) {

		this.p1 = p1;
		this.p2 = p2;
	}

	/**
	 * @return Point
	 */
	public Point getP1() {

		return this.p1;
	}

	/**
	 * @return Point
	 */
	public Point getP2() {

		return this.p2;
	}

	/**
	 * Cette methode retourne le point d'intersection entre deux lignes
	 * 
	 * @param l2
	 * @return Point Point de l'intersection entre les lignes
	 */
	public Point intersection(final Ligne l2) {

		final Point l2p1 = l2.getP1();
		final Point l2p2 = l2.getP2();
		final int x1 = (int) l2p1.getX();
		final int y1 = (int) l2p1.getY();
		final int x2 = (int) l2p2.getX();
		final int y2 = (int) l2p2.getY();

		final int d = ((x1 - x2) * (this.p1.y - this.p2.y))
						- ((y1 - y2) * (this.p1.x - this.p2.x));
		if (d == 0) {
			return null;
		}

		final int xi = (((this.p1.x - this.p2.x) * ((x1 * y2) - (y1 * x2))) - ((x1 - x2) * ((this.p1.x * this.p2.y) - (this.p1.y * this.p2.x))))
						/ d;
		final int yi = (((this.p1.y - this.p2.y) * ((x1 * y2) - (y1 * x2))) - ((y1 - y2) * ((this.p1.x * this.p2.y) - (this.p1.y * this.p2.x))))
						/ d;

		final Point p = new Point(xi, yi);
		if ((xi < Math.min(x1, x2)) || (xi > Math.max(x1, x2))) {
			return null;
		}
		if ((xi < Math.min(this.p1.x, this.p2.x))
						|| (xi > Math.max(this.p1.x, this.p2.x))) {
			return null;
		}
		return p;
	}

	/**
	 * @param p1
	 */
	public void setP1(final Point p1) {

		this.p1 = p1;
	}

	/**
	 * @param p2
	 */
	public void setP2(final Point p2) {

		this.p2 = p2;
	}

}
