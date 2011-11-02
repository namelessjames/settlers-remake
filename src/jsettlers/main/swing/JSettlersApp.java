package jsettlers.main.swing;

import go.graphics.swing.AreaContainer;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

import jsettlers.common.resources.ResourceManager;
import jsettlers.graphics.JOGLPanel;
import jsettlers.graphics.JoglLibraryPathInitializer;

public class JSettlersApp {
	static { // sets the native library path for the system dependent jogl libs
		JoglLibraryPathInitializer.initLibraryPath();
	}

	/**
	 * 
	 * @param args
	 *            args can have no entries or <br>
	 *            args[0] must be "host" or "client"
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ResourceManager.setProvider(new ResourceProvider());
		new Thread(new SettlersGame(args.length > 0 ? args[0] : "single")).start();
		// NetworkTimer.loadLogging("logs/2011_11_02-11_39_44.log");
		// NetworkTimer.activateLogging("logs");
	}

	private static class SettlersGame extends jsettlers.main.JSettlersApp {
		public SettlersGame(String networkMode) {
			super(networkMode, "localhost", "test");
		}

		@Override
		protected void startGui(JOGLPanel content) {
			JFrame jsettlersWnd = new JFrame("jsettlers");
			AreaContainer panel = new AreaContainer(content.getArea());
			panel.setPreferredSize(new Dimension(640, 480));
			jsettlersWnd.add(panel);
			panel.requestFocusInWindow();

			jsettlersWnd.pack();
			jsettlersWnd.setSize(1200, 800);
			jsettlersWnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jsettlersWnd.setVisible(true);
			jsettlersWnd.setLocationRelativeTo(null);
		}
	}
}
