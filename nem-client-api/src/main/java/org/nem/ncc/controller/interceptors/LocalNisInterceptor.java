package org.nem.ncc.controller.interceptors;

import org.nem.ncc.controller.annotations.RequiresTrustedNis;
import org.nem.ncc.model.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.*;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * Interceptor that rejects trusted calls to a remote NIS server.
 */
public class LocalNisInterceptor extends HandlerInterceptorAdapter {
	private static final Logger LOGGER = Logger.getLogger(LocalNisInterceptor.class.getName());

	private final Configuration configuration;

	/**
	 * Creates a new interceptor.
	 *
	 * @param configuration The configuration.
	 */
	public LocalNisInterceptor(final Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public boolean preHandle(
			final HttpServletRequest request,
			final HttpServletResponse response,
			final Object handler) throws Exception {
		final HandlerMethod handlerMethod = (HandlerMethod)handler;
		final Method method = handlerMethod.getMethod();
		final boolean isTrustedApi = method.isAnnotationPresent(RequiresTrustedNis.class);
		if (!isTrustedApi || this.configuration.getNisBootInfo().isNisLocal()) {
			return true;
		}

		final String message = String.format("remote NIS cannot service %s", request.getRequestURI());
		LOGGER.warning(message);
		throw new UnauthorizedAccessException(message);
	}
}
