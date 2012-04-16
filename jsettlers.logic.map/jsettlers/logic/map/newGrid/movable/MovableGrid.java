package jsettlers.logic.map.newGrid.movable;

import java.io.Serializable;

import jsettlers.common.movable.EMovableType;
import jsettlers.common.movable.IMovable;
import jsettlers.common.position.ShortPoint2D;
import jsettlers.logic.map.newGrid.landscape.IWalkableGround;
import jsettlers.logic.newmovable.NewMovable;

/**
 * This grid stores the position of the {@link IMovable}s.
 * 
 * @author Andreas Eberle
 */
public final class MovableGrid implements Serializable {
	private static final long serialVersionUID = 7003522358013103962L;

	private final NewMovable[] movableGrid;
	private final IWalkableGround ground;
	private final short width;

	public MovableGrid(short width, short height, IWalkableGround ground) {
		this.width = width;
		this.ground = ground;
		this.movableGrid = new NewMovable[width * height];
	}

	private final int getIdx(int x, int y) {
		return y * width + x;
	}

	public final NewMovable getMovableAt(short x, short y) {
		return this.movableGrid[getIdx(x, y)];
	}

	public final void setMovable(short x, short y, NewMovable movable) {
		this.movableGrid[getIdx(x, y)] = movable;
	}

	public final void movableLeft(ShortPoint2D position, NewMovable movable) {
		int idx = getIdx(position.getX(), position.getY());
		if (this.movableGrid[idx] == movable) {
			this.movableGrid[idx] = null;
		}
	}

	public final void movableEntered(ShortPoint2D position, NewMovable movable) {
		short x = position.getX();
		short y = position.getY();
		this.movableGrid[getIdx(x, y)] = movable;
		if (movable != null && movable.getMovableType() == EMovableType.BEARER) {
			ground.walkOn(x, y);
		}
	}

}
