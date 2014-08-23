package org.nem.monitor.ux;

import org.nem.monitor.*;
import org.nem.monitor.node.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Visitor that updates a tray icon image on a status change.
 * TODO-J: add some tests
 * TODO-J: set appropriate image descriptions
 */
public class IconNodeStatusVisitor implements NodeStatusVisitor {
	private final TrayIcon icon;
	private boolean isNccRunning;
	private boolean isNisRunning;
	private String lastImagePath;

	/**
	 * Creates a new visitor.
	 *
	 * @param icon The icon.
	 */
	public IconNodeStatusVisitor(final TrayIcon icon) {
		this.icon = icon;

		this.notifyStatus(NemNodeType.NIS, NemNodeStatus.UNKNOWN);
	}

	@Override
	public void notifyStatus(final NemNodeType type, final NemNodeStatus status) {
		switch (type) {
			case NIS:
				this.isNisRunning = NemNodeStatus.RUNNING == status;
				break;

			case NCC:
				this.isNccRunning = NemNodeStatus.RUNNING == status;
				break;
		}

		final String imagePath = this.getImagePath();
		if (imagePath.equals(this.lastImagePath)) {
			return;
		}

		this.lastImagePath = imagePath;
		this.icon.setImage(createImage(imagePath, "test"));
		return;
	}

	private String getImagePath() {
		if (this.isNisRunning)
			return this.isNccRunning ? "all_good.png" : "nis_online.png";

		return this.isNccRunning ? "ncc_only.png" : "all_bad.png";
	}


	private static Image createImage(String path, String description) {
		URL imageURL = IconNodeStatusVisitor.class.getClassLoader().getResource(path);

		if (imageURL == null) {
			System.err.println("Resource not found: " + path);
			return null;
		} else {
			return (new ImageIcon(imageURL, description)).getImage();
		}
	}
}
