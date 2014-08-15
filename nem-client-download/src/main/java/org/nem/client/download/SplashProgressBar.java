package org.nem.client.download;

import javax.jnlp.DownloadServiceListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class SplashProgressBar implements DownloadServiceListener {
	JFrame frame = null;
	JProgressBar progressBar = null;
	boolean uiCreated = false;

	public SplashProgressBar() {
		//
		System.out.println("SplashProgressBar created.");
	}

	public void downloadFailed(final java.net.URL url, final java.lang.String version) {
		System.out.println(String.format("downloadFailed: <%s> <%s>.", url.toExternalForm(), version));
	}

	public void progress(final URL url, final String version, final long readSoFar, final long total, final int overallPercent) {
		System.out.println(String.format("progress: <%s> <%s>. Bytes <%d> (%d) %d%%", url.toExternalForm(), version, readSoFar, total, overallPercent));
		this.updateProgressUI(overallPercent);
	}

	public void upgradingArchive(final java.net.URL url, final java.lang.String version, final int patchPercent, final int overallPercent) {
		System.out.println(String.format("progress: <%s> <%s>. Progress %d%% %d%%", url.toExternalForm(), version, patchPercent, overallPercent));
		this.updateProgressUI(overallPercent);
	}

	public void validating(final java.net.URL url, final java.lang.String version, final long entry, final long total, final int overallPercent) {
		System.out.println(String.format("validating: <%s> <%s>. entry %d total %d %d%%", url.toExternalForm(), version, entry, total, overallPercent));
		this.updateProgressUI(overallPercent);
	}

	private void updateProgressUI(final int overallPercent) {
		System.out.println(String.format("updateProgressUI: %d%%", overallPercent));
		if (overallPercent < 99) {
			if (!this.uiCreated) {
				this.uiCreated = true;
				// create custom progress indicator's UI only if
				// there is more work to do, meaning overallPercent > 0 and <
				// 100
				// this prevents flashing when RIA is loaded from cache
				this.create();
				System.out.println("Frame created.");
			}
			this.progressBar.setValue(overallPercent);
			SwingUtilities.invokeLater(() -> {
				this.frame.setVisible(true);
				System.out.println("Frame set visible.");
			});
		} else {
			// hide frame when overallPercent is above 99
			SwingUtilities.invokeLater(() -> {
				if (this.uiCreated) {
					this.frame.setVisible(false);
					this.frame.dispose();
					System.out.println("Frame disposed.");
				}
			});
		}
	}

	private void create() {
		final JPanel top = this.createComponents();
		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.frame = new JFrame(); // top level custom progress indicator UI
		this.frame.getContentPane().add(top, BorderLayout.CENTER);
		this.frame.getContentPane().setBounds(100, 100, 484, 405);
		this.frame.setBounds(100, 100, 484, 405);
		this.frame.setTitle("NEM Community Client - NCC");
		//		frame.pack();
		this.frame.setLocation(dim.width / 2 - this.frame.getSize().width / 2, dim.height / 2 - this.frame.getSize().height / 2);
		this.updateProgressUI(0);
	}

	private JPanel createComponents() {

		final JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		this.progressBar = new JProgressBar();
		this.progressBar.setToolTipText("URL");
		this.progressBar.setStringPainted(true);
		this.progressBar.setBounds(158, 212, 146, 14);
		this.progressBar.setForeground(new Color(0x41, 0xce, 0x7d));
		this.progressBar.setBackground(new Color(0xe2, 0xa9, 0x2b));
		contentPane.add(this.progressBar);

		final JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(5, 5, 456, 350);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		final ImageIcon logo = createImageIcon("/images/splash_screen.png", "logo");
		lblNewLabel.setIcon(logo);
		contentPane.add(lblNewLabel);

		return contentPane;
	}

	/**
	 * Returns an ImageIcon, or null if the path was invalid.
	 */
	protected static ImageIcon createImageIcon(final String path, final String description) {
		final java.net.URL imgURL = SplashProgressBar.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}