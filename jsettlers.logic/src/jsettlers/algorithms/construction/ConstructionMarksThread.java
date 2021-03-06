package jsettlers.algorithms.construction;

import jsettlers.algorithms.AlgorithmConstants;
import jsettlers.common.buildings.EBuildingType;
import jsettlers.common.logging.MilliStopWatch;
import jsettlers.common.logging.StopWatch;
import jsettlers.common.map.shapes.MapRectangle;
import jsettlers.network.client.interfaces.IPausingSupplier;

/**
 * Thread to calculate the markings for the user if he want's to construct a new building.<br>
 * This is a singleton class.
 * 
 * @author Andreas Eberle
 * 
 */
public final class ConstructionMarksThread implements Runnable {

	private final NewConstructionMarksAlgorithm algorithm;
	private final IPausingSupplier pausingSupplier;
	private final Thread thread;

	private boolean canceled;

	/**
	 * area of tiles to be checked.
	 */
	private MapRectangle mapArea = null;
	private EBuildingType buildingType = null;

	public ConstructionMarksThread(AbstractConstructionMarkableMap map, IPausingSupplier pausingSupplier, byte player) {
		this.algorithm = new NewConstructionMarksAlgorithm(map, player);
		this.pausingSupplier = pausingSupplier;

		thread = new Thread(this, "ConstructionMarksThread");
		thread.setDaemon(true);
		thread.start();
	}

	@Override
	public void run() {
		while (!canceled) {
			try {
				synchronized (this) {
					while (buildingType == null) {
						this.wait();
					}
				}

				while (buildingType != null && !canceled) {
					if (!pausingSupplier.isPausing()) {
						StopWatch watch = new MilliStopWatch();
						watch.restart();

						EBuildingType buildingType = this.buildingType;
						MapRectangle mapArea = this.mapArea;
						if (buildingType != null && mapArea != null) { // if the task has already been canceled
							algorithm.calculateConstructMarks(mapArea, buildingType.getBuildingAreaBitSet(), buildingType.getGroundtypes(),
									buildingType.getBlockedTiles());
						}

						watch.stop("calculation of construction marks");
					}
					synchronized (this) {
						wait(AlgorithmConstants.CONSTRUCT_MARKS_MAX_REFRESH_TIME);
					}
				}
				algorithm.removeConstructionMarks();
			} catch (InterruptedException e) {
				// do nothing
			} catch (Throwable t) { // this thread must never be destroyed due to errors
				t.printStackTrace();
			}
		}
	}

	public synchronized void setScreen(MapRectangle mapArea) {
		this.mapArea = mapArea;
		this.notifyAll();
	}

	public synchronized void setBuildingType(EBuildingType buildingType) {
		this.buildingType = buildingType;
		this.notifyAll();
	}

	public void cancel() {
		canceled = true;
		thread.interrupt();
	}
}
