package go.graphics.event.mouse;

import go.graphics.UIPoint;
import go.graphics.event.GOEvent;

/**
 * This is a pan event.
 * 
 * Each pan event has a distance the user paned and a centerwhere the user started panning.
 * 
 * @author michael
 *
 */
public interface GOPanEvent extends GOEvent {
	/**
	 * The distance (as vector) the user panned.
	 * 
	 * @return The distance.
	 */
	UIPoint getPanDistance();

	/**
	 * The center the user started panning.
	 * 
	 * @return The pan center.
	 */
	UIPoint getPanCenter();

}
