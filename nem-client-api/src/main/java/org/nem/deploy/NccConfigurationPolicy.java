package org.nem.deploy;

import org.apache.commons.cli.Option;
import org.nem.core.deploy.*;
import org.nem.deploy.appconfig.NccAppConfig;
import org.nem.ncc.connector.NisController;
import org.nem.ncc.web.servlet.*;

import javax.servlet.http.HttpServlet;
import java.util.Arrays;

/**
 * Class for supplying addition NCC configuration information.
 */
public class NccConfigurationPolicy implements NemConfigurationPolicy {
	private final WebStartProxy proxy;
	private final NisController controller;

	/**
	 * Creates a new ncc configuration policy.
	 */
	public NccConfigurationPolicy() {
		this.proxy = new WebStartProxy();
		this.controller = new NisController();
	}

	/**
	 * Creates a new ncc configuration policy.
	 */
	public NccConfigurationPolicy(final WebStartProxy proxy, final NisController controller) {
		this.proxy = proxy;
		this.controller = controller;
	}

	@Override
	public Class getAppConfigClass() {
		return NccAppConfig.class;
	}

	@Override
	public Class getWebAppInitializerClass() {
		return NccWebAppInitializer.class;
	}

	@Override
	public Class<? extends HttpServlet> getJarFileServletClass() {
		return JarFileServlet.class;
	}

	@Override
	public Class<? extends HttpServlet> getDefaultServletClass() {
		return NccDefaultServlet.class;
	}

	@Override
	public boolean openWebBrowser(final String homeUrl) {
		return this.proxy.openWebBrowser(homeUrl);
	}

	@Override
	public void handleWebStart(final String[] args) {
		// TODO-CR: 20140818 can you add one test when config.webStart is false?
		NccConfiguration config = loadConfig(args);
		if (config.isWebStart()) {
			this.controller.startNisViaWebStart(config.getNisJnlpUrl());
		}
	}

	@Override
	public NccConfiguration loadConfig(final String[] args) {
		// TODO-CR: 20140818 also, consider adding a test for this function

		// TODO-CR: 20140818 can you maybe refactor this to a static function on NccConfiguration
		// (then the NccConfiguration constants can be private)?
		NemCommandLine commandLine = new NemCommandLine(Arrays.asList(
				new Option(NccConfiguration.WEBSTART, true, NccConfiguration.WEBSTART_DESCRIPTION),
				new Option(NccConfiguration.NIS_JNLP_URL, true, NccConfiguration.NIS_JNLP_URL_DESCRIPTION)));
		if (commandLine.parse(args)) {
			return new NccConfiguration(
					"1".equals(commandLine.getParameter(NccConfiguration.WEBSTART)),
					commandLine.getParameter(NccConfiguration.NIS_JNLP_URL));
		}

		return new NccConfiguration();
	}
}
