package org.nem.monitor.ux;

import org.nem.core.connect.*;
import org.nem.monitor.*;
import org.nem.monitor.node.*;
import org.nem.monitor.visitors.NodeStatusToProgressBarAdapter;
import org.nem.vanity.generator.*;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class NemClientWindow {
	private static final Logger LOGGER = Logger.getLogger(NemClientWindow.class.getName());

	private TrayIconBuilder builder;
	private JFrame frmNemClient;
	private JTextField vanityText;
	private final Action openBrowser = new OpenBrowserAction();
	private final GenerateAction generate = new GenerateAction();
	private final Action stopGenerator = new StopGeneratorAction();
	private JList<String> addresses;
	private JProgressBar progressBar;
	private CompletableFuture<Long> asyncGenerator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		openWindow();
	}

	/**
	 * Launch the window.
	 */
	public static void openWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NemClientWindow window = new NemClientWindow();
					window.frmNemClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void setupSystemTray(
			final String nemFolder,
			final HttpMethodClient<ErrorResponseDeserializerUnion> httpMethodClient,
			final boolean isStartedViaWebStart,
			final String nisJnlpUrl,
			final String nccJnlpUrl) {
		LOGGER.info("setting up system tray");

		// fail if the system tray is not supported
		if (!SystemTray.isSupported()) {
			throw new SystemTrayException("SystemTray is not supported");
		}

		final SystemTray tray = SystemTray.getSystemTray();
		final WebStartLauncher launcher = new WebStartLauncher(nemFolder);
		builder = new TrayIconBuilder(httpMethodClient, launcher, new WebBrowser(), isStartedViaWebStart);
		builder.addStatusMenuItems(new NisNodePolicy(nemFolder), nisJnlpUrl);
		builder.addSeparator();
		builder.addStatusMenuItems(new NccNodePolicy(nemFolder), nccJnlpUrl);
		builder.addSeparator();
		builder.addExitMenuItem(tray);
		builder.addExitAndShutdownMenuItem(tray);

		TrayIcon icon = builder.create();
		try {
			tray.add(icon);
		} catch (final AWTException e) {
			// throw new SystemTrayException("Unable to add icon to system tray", e);
			LOGGER.log(Level.WARNING, "Unable to add icon to system tray", e);
		}
	}

	public void setupWindow(
			final String nemFolder,
			final HttpMethodClient<ErrorResponseDeserializerUnion> httpMethodClient,
			final boolean isStartedViaWebStart,
			final String nisJnlpUrl,
			final String nccJnlpUrl) {

		setupSystemTray(nemFolder, httpMethodClient, isStartedViaWebStart, nisJnlpUrl, nccJnlpUrl);
		NodeStatusToProgressBarAdapter visitor = new NodeStatusToProgressBarAdapter(percentage -> {
			progressBar.setValue(percentage.getPercentage());
		});
		
		VanityAddressVisitor addressVisitor = new VanityAddressVisitor(list -> {
//			addresses.getModel().
		});
		addresses.setModel(addressVisitor);

		builder.addVisitor(visitor);
		frmNemClient.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public NemClientWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNemClient = new JFrame();
		frmNemClient.setMinimumSize(new Dimension(0, 200));
		frmNemClient.setTitle("NEM Client");
		frmNemClient.setBounds(100, 100, 571, 400);
		frmNemClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 40, 38, 30, 20, 0, 40, 0, 0, 30, 38, 38, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 2.0, 2.0, 2.0, 2.0, Double.MIN_VALUE };
		frmNemClient.getContentPane().setLayout(gridBagLayout);

		JTextPane txtpnAnyImportantInformation = new JTextPane();
		txtpnAnyImportantInformation.setFont(new Font("Arial", Font.PLAIN, 12));
		txtpnAnyImportantInformation.setText("Any important information here?");
		txtpnAnyImportantInformation.setEditable(false);
		GridBagConstraints gbc_txtpnAnyImportantInformation = new GridBagConstraints();
		gbc_txtpnAnyImportantInformation.gridheight = 2;
		gbc_txtpnAnyImportantInformation.gridwidth = 3;
		gbc_txtpnAnyImportantInformation.fill = GridBagConstraints.BOTH;
		gbc_txtpnAnyImportantInformation.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnAnyImportantInformation.gridx = 0;
		gbc_txtpnAnyImportantInformation.gridy = 0;
		frmNemClient.getContentPane().add(txtpnAnyImportantInformation, gbc_txtpnAnyImportantInformation);
		
				JButton btnNewButton = new JButton("");
				btnNewButton.setAction(openBrowser);
				btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.gridwidth = 3;
				gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
				gbc_btnNewButton.gridx = 0;
				gbc_btnNewButton.gridy = 2;
				frmNemClient.getContentPane().add(btnNewButton, gbc_btnNewButton);
				btnNewButton.setSize(new Dimension(60, 25));
				btnNewButton.setMinimumSize(new Dimension(150, 25));
				btnNewButton.setMaximumSize(new Dimension(150, 25));

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setToolTipText("This will change over time...");
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar.gridwidth = 3;
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 3;
		frmNemClient.getContentPane().add(progressBar, gbc_progressBar);

		JLabel label = new JLabel("");
		label.setBorder(new LineBorder(Color.GRAY));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.gridwidth = 3;
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 4;
		frmNemClient.getContentPane().add(label, gbc_label);
		
				JLabel lblNemVanityGenerator = new JLabel("NEM Vanity Generator");
				lblNemVanityGenerator.setFont(new Font("Arial", Font.BOLD, 16));
				GridBagConstraints gbc_lblNemVanityGenerator = new GridBagConstraints();
				gbc_lblNemVanityGenerator.gridwidth = 3;
				gbc_lblNemVanityGenerator.insets = new Insets(0, 0, 5, 0);
				gbc_lblNemVanityGenerator.gridx = 0;
				gbc_lblNemVanityGenerator.gridy = 5;
				frmNemClient.getContentPane().add(lblNemVanityGenerator, gbc_lblNemVanityGenerator);

		JTextPane txtpnTheVanityGenerator = new JTextPane();
		txtpnTheVanityGenerator.setEditable(false);
		txtpnTheVanityGenerator.setFont(new Font("Arial", Font.PLAIN, 12));
		txtpnTheVanityGenerator
				.setText("The vanity generator tries to find a NEM address which contains the text from the entry field below.\r\nThe generator might run a long time, so please be patient until the first findings are listed. If it takes too long you can stop the generator at any time by pressing \"Enough\".");
		GridBagConstraints gbc_txtpnTheVanityGenerator = new GridBagConstraints();
		gbc_txtpnTheVanityGenerator.gridheight = 2;
		gbc_txtpnTheVanityGenerator.gridwidth = 3;
		gbc_txtpnTheVanityGenerator.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnTheVanityGenerator.fill = GridBagConstraints.BOTH;
		gbc_txtpnTheVanityGenerator.gridx = 0;
		gbc_txtpnTheVanityGenerator.gridy = 6;
		frmNemClient.getContentPane().add(txtpnTheVanityGenerator, gbc_txtpnTheVanityGenerator);

		vanityText = new JTextField();
		vanityText.setForeground(Color.GRAY);
		vanityText.setToolTipText("Type text that should be contained in NEM address");
		vanityText.setText("Type text that should be contained in NEM address");
		vanityText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String newText = vanityText.getText();
				if (newText == null) {
					generate.setEnabled(false);
					vanityText.setForeground(Color.GRAY);
					vanityText.setText("Type text that should be contained in NEM address");

					return;
				}

				if (newText.trim().isEmpty()) {
					generate.setEnabled(false);
					vanityText.setForeground(Color.GRAY);
					vanityText.setText("Type text that should be contained in NEM address");
				} else {
					generate.setEnabled(true);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (!generate.isEnabled()) {
					vanityText.setForeground(Color.BLACK);
					vanityText.setText("");
				}
			}
		});
		vanityText.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_vanityText = new GridBagConstraints();
		gbc_vanityText.fill = GridBagConstraints.HORIZONTAL;
		gbc_vanityText.insets = new Insets(0, 0, 5, 5);
		gbc_vanityText.gridx = 1;
		gbc_vanityText.gridy = 8;
		frmNemClient.getContentPane().add(vanityText, gbc_vanityText);
		vanityText.setColumns(10);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setAction(generate);
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 12));

		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 8;
		frmNemClient.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);

		addresses = new JList<String>();
		addresses.setFont(new Font("Arial", Font.PLAIN, 12));
		
		GridBagConstraints gbc_addresses = new GridBagConstraints();
		gbc_addresses.gridheight = 4;
		gbc_addresses.insets = new Insets(0, 0, 0, 5);
		gbc_addresses.fill = GridBagConstraints.BOTH;
		gbc_addresses.gridx = 1;
		gbc_addresses.gridy = 9;
		frmNemClient.getContentPane().add(addresses, gbc_addresses);

		JButton btnEnough = new JButton("");
		btnEnough.setEnabled(false);
		btnEnough.setAction(stopGenerator);
		btnEnough.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_btnEnough = new GridBagConstraints();
		gbc_btnEnough.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEnough.insets = new Insets(0, 0, 5, 0);
		gbc_btnEnough.gridx = 2;
		gbc_btnEnough.gridy = 9;
		frmNemClient.getContentPane().add(btnEnough, gbc_btnEnough);

		generate.setEnabled(false);
		stopGenerator.setEnabled(false);
		vanityText.grabFocus();
	}

	private class OpenBrowserAction extends AbstractAction {
		public OpenBrowserAction() {
			putValue(NAME, "Open Client Browser");
			putValue(SHORT_DESCRIPTION, "Opens a new browser tab/window to display the NEM Wallet Client");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}

	private class GenerateAction extends AbstractAction {
		private Vanity vanity;
		private boolean enabled;

		public GenerateAction() {
			putValue(NAME, "Generate");
			putValue(SHORT_DESCRIPTION, "Generates a NEM address containing the given text.");
			setEnabled(false);
		}

		@Override
		public void setEnabled(boolean newValue) {
			enabled = newValue;
			super.setEnabled(newValue);
		}

		public boolean isEnabled() {
			return this.enabled;
		}

		public void actionPerformed(ActionEvent e) {
			if (asyncGenerator != null) {
				asyncGenerator.cancel(true);
				try {
					asyncGenerator.get();
				} catch (InterruptedException | ExecutionException | CancellationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			vanity = new Vanity(addresses.getModel());
			asyncGenerator = CompletableFuture.supplyAsync(() -> vanity.generate(vanityText.getText()));

			this.setEnabled(false);
			stopGenerator.setEnabled(true);
		}

		public void stopGenerating() {
			if (asyncGenerator != null) {
				asyncGenerator.cancel(true);
				try {
					asyncGenerator.get();
				} catch (InterruptedException | ExecutionException | CancellationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			asyncGenerator = null;

			this.setEnabled(true);
			stopGenerator.setEnabled(false);
		}
	}

	private class StopGeneratorAction extends AbstractAction {
		public StopGeneratorAction() {
			putValue(NAME, "Enough");
			putValue(SHORT_DESCRIPTION, "Some short description");
			setEnabled(false);
		}

		public void actionPerformed(ActionEvent e) {
			generate.stopGenerating();
		}
	}
}
