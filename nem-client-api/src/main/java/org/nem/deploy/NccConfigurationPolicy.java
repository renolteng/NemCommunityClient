package org.nem.deploy;

import org.nem.core.deploy.NemConfigurationPolicy;
import org.nem.deploy.appconfig.NccAppConfig;
import org.nem.ncc.connector.NisController;
import org.nem.ncc.web.servlet.*;

import javax.servlet.http.HttpServlet;

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
		final NccConfiguration config = NccConfiguration.loadConfig(args);
		if (config.isWebStart()) {
			this.controller.startNisViaWebStart(config.getNisJnlpUrl());
		}
	}

	@Override
	public NccConfiguration loadConfig(final String[] args) {
		return NccConfiguration.loadConfig(args);
	}
}
