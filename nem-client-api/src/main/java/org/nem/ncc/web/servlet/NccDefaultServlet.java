package org.nem.ncc.web.servlet;

import org.nem.ncc.NccConfiguration;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class NccDefaultServlet extends HttpServlet {
	private final NccConfiguration configuration = ServletUtils.getNccConfiguration();

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(this.configuration.getHomeUrl());
		resp.flushBuffer();
	}
}