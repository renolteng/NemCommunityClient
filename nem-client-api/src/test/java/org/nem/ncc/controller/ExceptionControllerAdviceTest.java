package org.nem.ncc.controller;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.connect.ErrorResponse;
import org.nem.core.time.*;
import org.nem.ncc.exceptions.NccException;
import org.nem.ncc.wallet.WalletException;
import org.springframework.http.*;

public class ExceptionControllerAdviceTest {
	private static final TimeInstant CURRENT_TIME = new TimeInstant(57);

	@Test
	public void handleMissingResourceExceptionCreatesAppropriateResponse() {
		// Arrange:
		final ExceptionControllerAdvice advice = createAdvice();
		final ResponseEntity<ErrorResponse> entity = advice.handleMissingResourceException(new Exception("badness"));

		// Assert:
		assertEntity(entity, HttpStatus.NOT_FOUND);
	}

	@Test
	public void handleIllegalArgumentExceptionCreatesAppropriateResponse() {
		// Arrange:
		final ExceptionControllerAdvice advice = createAdvice();
		final ResponseEntity<ErrorResponse> entity = advice.handleIllegalArgumentException(new Exception("badness"));

		// Assert:
		assertEntity(entity, HttpStatus.BAD_REQUEST);
	}

	@Test
	public void handleConnectionExceptionCreatesAppropriateResponse() {
		// Arrange:
		final ExceptionControllerAdvice advice = createAdvice();
		final ResponseEntity<ErrorResponse> entity = advice.handleConnectionException(new Exception("badness"));

		// Assert:
		Assert.assertThat(entity.getStatusCode(), IsEqual.equalTo(HttpStatus.BAD_REQUEST));
		Assert.assertThat(entity.getBody().getTimeStamp(), IsEqual.equalTo(CURRENT_TIME));
		Assert.assertThat(entity.getBody().getStatus(), IsEqual.equalTo(NccException.Code.NIS_NOT_AVAILABLE.value()));
		Assert.assertThat(entity.getBody().getMessage(), IsEqual.equalTo("NIS_NOT_AVAILABLE"));
	}

	@Test
	public void handleNccExceptionCreatesAppropriateResponse() {
		// Arrange:
		final WalletException.Code code = WalletException.Code.WALLET_ALREADY_CONTAINS_ACCOUNT;
		final ExceptionControllerAdvice advice = createAdvice();
		final ResponseEntity<ErrorResponse> entity = advice.handleNccException(new WalletException(code));

		// Assert:
		Assert.assertThat(entity.getStatusCode(), IsEqual.equalTo(HttpStatus.BAD_REQUEST));
		Assert.assertThat(entity.getBody().getTimeStamp(), IsEqual.equalTo(CURRENT_TIME));
		Assert.assertThat(entity.getBody().getStatus(), IsEqual.equalTo(code.value()));
		Assert.assertThat(entity.getBody().getMessage(), IsEqual.equalTo("WALLET_ALREADY_CONTAINS_ACCOUNT"));
	}

	@Test
	public void handleUnauthorizedAccessExceptionCreatesAppropriateResponse() {
		// Arrange:
		final ExceptionControllerAdvice advice = createAdvice();
		final ResponseEntity<ErrorResponse> entity = advice.handleUnauthorizedAccessException(new Exception("badness"));

		// Assert:
		assertEntity(entity, HttpStatus.UNAUTHORIZED);
	}

	@Test
	public void handleExceptionCreatesAppropriateResponse() {
		// Arrange:
		final ExceptionControllerAdvice advice = createAdvice();
		final ResponseEntity<ErrorResponse> entity = advice.handleException(new Exception("badness"));

		// Assert:
		assertEntity(entity, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private static void assertEntity(final ResponseEntity<ErrorResponse> entity, final HttpStatus expectedCode) {
		// Assert:
		Assert.assertThat(entity.getStatusCode(), IsEqual.equalTo(expectedCode));
		Assert.assertThat(entity.getBody().getTimeStamp(), IsEqual.equalTo(CURRENT_TIME));
		Assert.assertThat(entity.getBody().getStatus(), IsEqual.equalTo(expectedCode.value()));
		Assert.assertThat(entity.getBody().getMessage(), IsEqual.equalTo("badness"));
	}

	private static ExceptionControllerAdvice createAdvice() {
		final TimeProvider timeProvider = Mockito.mock(TimeProvider.class);
		Mockito.when(timeProvider.getCurrentTime()).thenReturn(CURRENT_TIME);
		return new ExceptionControllerAdvice(timeProvider);
	}
}
