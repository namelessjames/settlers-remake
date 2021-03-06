package jsettlers.logic.objects;

import jsettlers.common.mapobject.EMapObjectType;
import jsettlers.common.player.IPlayerable;
import jsettlers.common.position.ShortPoint2D;

/**
 * Object that can show any {@link EMapObjectType} on the map for a given time. When the time is finished, it removes itself from the map.
 * 
 * @author Andreas Eberle
 * 
 */
public class SelfDeletingMapObject extends ProgressingObject implements IPlayerable {
	private static final long serialVersionUID = 261552015645456635L;

	private final EMapObjectType type;
	private final byte player;

	/**
	 * Creates and positions this map object on the map.
	 * 
	 * @param pos
	 *            position the map object should be on the grid
	 * @param type
	 *            type to be returned to jsettlers.graphics
	 */
	public SelfDeletingMapObject(ShortPoint2D pos, EMapObjectType type, float duration) {
		this(pos, type, duration, (byte) -1);
	}

	public SelfDeletingMapObject(ShortPoint2D pos, EMapObjectType type, float duration, byte player) {
		super(pos);
		this.player = player;
		this.type = type;
		super.setDuration(duration);
	}

	@Override
	public EMapObjectType getObjectType() {
		return type;
	}

	@Override
	public boolean cutOff() {
		return false;
	}

	@Override
	public boolean isBlocking() {
		return false;
	}

	@Override
	public boolean canBeCut() {
		return false;
	}

	@Override
	public byte getPlayerId() {
		return player;
	}

	@Override
	protected void changeState() {
		throw new UnsupportedOperationException();
	}
}
