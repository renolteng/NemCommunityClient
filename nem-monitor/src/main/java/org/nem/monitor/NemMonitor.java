package org.nem.monitor;

import org.nem.core.async.*;
import org.nem.core.connect.*;
import org.nem.core.deploy.*;
import org.nem.core.model.*;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.AccountLookup;
import org.nem.core.utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.logging.*;

/**
 * The nem monitor program.
 */
public class NemMonitor {
	private static final Logger LOGGER = Logger.getLogger(NemMonitor.class.getName());

	static {
		// initialize logging before anything is logged; otherwise not all
		// settings will take effect
		//LoggingBootstrapper.bootstrap(new CommonConfiguration().getNemFolder());
	}

	private static interface NisStateUpdater {
		void setState(final boolean isRunning);
	}

	/**
	 * The main entry point.
	 *
	 * @param args The command line arguments.
	 */
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(NemMonitor::createAndShowGUI);
	}

	private static HttpMethodClient<ErrorResponseDeserializerUnion> createHttpMethodClient() {
		final int CONNECTION_TIMEOUT = 1000;
		final int SOCKET_TIMEOUT = 1000;
		final int REQUEST_TIMEOUT = 2000;
		return new HttpMethodClient<>(CONNECTION_TIMEOUT, SOCKET_TIMEOUT, REQUEST_TIMEOUT);
	}

	private static PopupMenu createPopupMenu() {
		final PopupMenu popup = new PopupMenu();

		final HttpMethodClient<ErrorResponseDeserializerUnion> client = createHttpMethodClient();
		addStatusMenuItems(popup, "NIS", new NemConnector(NodeEndpoint.fromHost("localhost"), client));
		popup.addSeparator();
		addStatusMenuItems(popup, "NCC", new NemConnector(new NodeEndpoint("http", "localhost", 8989), client));
		popup.addSeparator();
		return popup;
	}

	private static void addStatusMenuItems(final PopupMenu popup, final String name, final NemConnector connector) {
		final String connectingMessage = String.format("Connecting to %s ...", name);
		final MenuItem statusMenuItem = new MenuItem(connectingMessage);
		final MenuItem actionMenuItem = new MenuItem(connectingMessage);

		new AsyncTimer(() -> {
			return connector.isRunning().thenAccept(b -> {
				System.out.println("ping");
				statusMenuItem.setLabel(name + (b ? " is running" : " is not running"));
			});
		}, 100, new UniformDelayStrategy(500), null);

		actionMenuItem.addActionListener(e -> {
			actionMenuItem.setLabel(String.format("Stop %s", name));
			connector.shutdown();
		});

		popup.add(statusMenuItem);
		popup.add(actionMenuItem);
	}

	private static void createAndShowGUI() {
		//Check the SystemTray support
		if (!SystemTray.isSupported()) {
			System.out.println("SystemTray is not supported");
			return;
		}



		final SystemTray tray = SystemTray.getSystemTray();
		final Dimension size = tray.getTrayIconSize();

		final PopupMenu popup = createPopupMenu();
		final TrayIcon trayIcon = new TrayIcon(createImage(size, Color.GREEN));

		MenuItem exitItem = new MenuItem("Exit");
		popup.add(exitItem);
		exitItem.addActionListener(e -> {
			tray.remove(trayIcon);
			System.exit(0);
		});

		trayIcon.setPopupMenu(popup);
		try {
			tray.add(trayIcon);
		}
		catch (AWTException e) {

		}

		//final PopupMenu popup = new PopupMenu();

		//final TrayIcon trayIcon = new TrayIcon(createImage(size, Color.GREEN));
		//final TrayIcon trayIcon =
		//		new TrayIcon(createImage("images/bulb.gif", "tray icon"));


		// Create a popup menu components
	}

	////Obtain the image URL
	//protected static Image createImage(String path, String description) {
	//	URL imageURL = TrayIconDemo.class.getResource(path);
	//
	//	if (imageURL == null) {
	//		System.err.println("Resource not found: " + path);
	//		return null;
	//	} else {
	//		return (new ImageIcon(imageURL, description)).getImage();
	//	}
	//}
	//
	//
	//
	private static Image createImage(final Dimension size, final Color color) {
		BufferedImage img = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		g.setColor(new Color(0, 0, 0, 0));
		g.fillRect(0, 0, size.width, size.height);
		g.setColor(color);
		g.fillOval(2, 2, size.width - 4, size.height - 4);
		return (new ImageIcon(img, "ncc")).getImage();
	}

	//private void initSystemTray() {
	//	//	Check the SystemTray is supported
	//	if(!SystemTray.isSupported())
	//
	//	{
	//		System.out.println("SystemTray is not supported");
	//		return;
	//	}
	//
	//	final SystemTray tray = SystemTray.getSystemTray();
	//	final Dimension size = tray.getTrayIconSize();
	//
	//	final PopupMenu popup = new PopupMenu();
	//
	//	final TrayIcon trayIcon = new TrayIcon(createImage(size, Color.GREEN));
	//
	//
	//	// Create a pop-up menu components
	//	MenuItem aboutItem = new MenuItem("About");
	//	CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
	//	CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
	//	Menu displayMenu = new Menu("Display");
	//	MenuItem errorItem = new MenuItem("Error");
	//	MenuItem warningItem = new MenuItem("Warning");
	//	MenuItem infoItem = new MenuItem("Info");
	//	MenuItem noneItem = new MenuItem("None");
	//	MenuItem exitItem = new MenuItem("Exit");
	//
	//
	//	aboutItem.addActionListener(e -> {
	//		trayIcon.setImage(createImage(size, Color.RED));
	//	});
	//
	//	//Add components to pop-up menu
	//	popup.add(aboutItem);
	//	popup.addSeparator();
	//	popup.add(cb1);
	//	popup.add(cb2);
	//	popup.addSeparator();
	//	popup.add(displayMenu);
	//	displayMenu.add(errorItem);
	//	displayMenu.add(warningItem);
	//	displayMenu.add(infoItem);
	//	displayMenu.add(noneItem);
	//	popup.add(exitItem);
	//
	//	trayIcon.setPopupMenu(popup);
	//	ExceptionUtils.propagateVoid(() -> tray.add(trayIcon));
	//}
	//
	//private static Image createImage(final Dimension size, final Color color) {
	//	BufferedImage img = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
	//	Graphics2D g = img.createGraphics();
	//	g.setColor(new Color(0, 0, 0, 0));
	//	g.fillRect(0, 0, size.width, size.height);
	//	g.setColor(color);
	//	g.fillOval(2, 2, size.width - 4, size.height - 4);
	//	return (new ImageIcon(img, "ncc")).getImage();
	//}



}