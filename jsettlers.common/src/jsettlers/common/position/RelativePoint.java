package jsettlers.common.position;

import java.io.Serializable;

/**
 * class to specify a relative position on the grid
 * 
 * @author Andreas Eberle
 * 
 */
public class RelativePoint implements Serializable {
	private static final long serialVersionUID = 7216627427441018520L;
	private final short dy;
	private final short dx;

	public RelativePoint(short dx, short dy) {
		this.dy = dy;
		this.dx = dx;
	}

	/**
	 * constructor for easy use (ints instead of shorts)<br>
	 * NOTE: all arguments will be cast to short !!
	 * 
	 * @param dx
	 * @param dy
	 */
	public RelativePoint(int dx, int dy) {
		this((short) dx, (short) dy);
	}

	/**
	 * calculates the point on the grid from the given start point.
	 * 
	 * @param start
	 * @return
	 */
	public final ShortPoint2D calculatePoint(ShortPoint2D start) {
		return new ShortPoint2D((short) (start.x + dx), (short) (start.y + dy));
	}

	public final int calculateX(int x) {
		return x + dx;
	}

	public final int calculateY(int y) {
		return y + dy;
	}

	public static final RelativePoint getRelativePoint(ShortPoint2D start, ShortPoint2D end) {
		short dx = (short) (end.x - start.x);
		short dy = (short) (end.y - start.y);

		return new RelativePoint(dx, dy);
	}

	@Override
	public final boolean equals(Object o) {
		if (o != null && (o instanceof RelativePoint)) {
			RelativePoint other = (RelativePoint) o;
			return other.getDy() == this.getDy() && other.getDx() == this.getDx();
		} else {
			return false;
		}
	}

	@Override
	public final int hashCode() {
		return getDy() << 16 + getDx();
	}

	@Override
	public final String toString() {
		return "dx=" + getDx() + ", dy=" + getDy();
	}

	public final short getDy() {
		return dy;
	}

	public final short getDx() {
		return dx;
	}

	public RelativePoint invert() {
		return new RelativePoint(-dx, -dy);
	}

}