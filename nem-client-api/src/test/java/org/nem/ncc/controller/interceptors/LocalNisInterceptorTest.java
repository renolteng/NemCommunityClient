package org.nem.ncc.controller.interceptors;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.utils.ExceptionUtils;
import org.nem.ncc.controller.annotations.RequiresTrustedNis;
import org.nem.ncc.model.*;
import org.nem.ncc.test.ExceptionAssert;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.*;
import java.lang.reflect.Method;

public class LocalNisInterceptorTest {

	@Test
	public void remoteAccessIsAllowedForUnannotatedMethodAndLocalNisServer() {
		// Assert:
		assertAccessGranted("unannotatedMethod", true);
	}

	@Test
	public void remoteAccessIsAllowedForUnannotatedMethodAndRemoteNisServer() {
		// Assert:
		assertAccessGranted("unannotatedMethod", false);
	}

	@Test
	public void remoteAccessIsAllowedForTrustedNisMethodAndLocalNisServer() {
		// Assert:
		assertAccessGranted("trustedMethod", true);
	}

	@Test
	public void remoteAccessIsNotAllowedForTrustedNisMethodAndRemoteNisServer() {
		// Assert:
		assertAccessDenied("trustedMethod", false);
	}

	private static void assertAccessGranted(final String methodName, final boolean isNisLocal) {
		// Act:
		final boolean result = preHandle(methodName, isNisLocal);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(true));
	}

	private static void assertAccessDenied(final String methodName, final boolean isNisLocal) {
		// Act / Assert:
		ExceptionAssert.assertThrows(v -> preHandle(methodName, isNisLocal), UnauthorizedAccessException.class);
	}

	public static boolean preHandle(final String methodName, final boolean isNisLocal) {
		// Arrange:
		final Configuration configuration = Mockito.mock(Configuration.class);
		final NisBootInfo bootInfo = Mockito.mock(NisBootInfo.class);
		Mockito.when(configuration.getNisBootInfo()).thenReturn(bootInfo);
		Mockito.when(bootInfo.isNisLocal()).thenReturn(isNisLocal);

		final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		final HandlerMethod handlerMethod = Mockito.mock(HandlerMethod.class);
		final Method method = ExceptionUtils.propagate(() -> LocalNisInterceptorTest.class.getMethod(methodName));
		Mockito.when(handlerMethod.getMethod()).thenReturn(method);

		final LocalNisInterceptor interceptor = new LocalNisInterceptor(configuration);

		// Act:
		return ExceptionUtils.propagate(() -> interceptor.preHandle(request, response, handlerMethod));
	}

	//region annotated methods

	public static void unannotatedMethod() {
	}

	@RequiresTrustedNis
	public static void trustedMethod() {
	}

	//endregion
}