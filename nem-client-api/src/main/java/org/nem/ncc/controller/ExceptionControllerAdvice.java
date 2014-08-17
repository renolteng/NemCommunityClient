package org.nem.ncc.controller;

import org.nem.core.connect.*;
import org.nem.core.serialization.MissingRequiredPropertyException;
import org.nem.core.time.TimeProvider;
import org.nem.ncc.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.ConnectException;
import java.util.MissingResourceException;
import java.util.concurrent.CompletionException;

/**
 * ControllerAdvice-annotated class that maps thrown exceptions to JSON errors.
 */
@ResponseBody
@ControllerAdvice
public class ExceptionControllerAdvice {
	private final TimeProvider timeProvider;

	/**
	 * Creates a new exception controller advice.
	 *
	 * @param timeProvider The time provider.
	 */
	@Autowired(required = true)
	public ExceptionControllerAdvice(final TimeProvider timeProvider) {
		this.timeProvider = timeProvider;
	}

	/**
	 * Handler for resource-not-found exceptions.
	 *
	 * @param e The exception.
	 * @return The appropriate response entity.
	 */
	@ExceptionHandler(MissingResourceException.class)
	public ResponseEntity<ErrorResponse> handleMissingResourceException(final Exception e) {
		return this.createResponse(e, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handler for invalid-parameter exceptions.
	 *
	 * @param e The exception.
	 * @return The appropriate JSON indicating an error.
	 */
	@ExceptionHandler({ IllegalArgumentException.class, MissingRequiredPropertyException.class })
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(final Exception e) {
		return this.createResponse(e, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handler for connection exceptions.
	 *
	 * @param e The exception.
	 * @return The appropriate JSON indicating an error.
	 */
	@ExceptionHandler({ ConnectException.class, InactivePeerException.class, FatalPeerException.class })
	public ResponseEntity<ErrorResponse> handleConnectionException(final Exception e) {
		return this.createResponse(NccException.Code.NIS_NOT_AVAILABLE);
	}

	/**
	 * Handler for NCC exceptions.
	 *
	 * @param e The exception.
	 * @return The appropriate JSON indicating an error.
	 */
	@ExceptionHandler(NccException.class)
	public ResponseEntity<ErrorResponse> handleNccException(final NccException e) {
		return this.createResponse(e.getCode());
	}

	/**
	 * Handler for future completion exceptions.
	 *
	 * @param e The exception.
	 * @return The appropriate JSON indicating an error.
	 */
	@ExceptionHandler(CompletionException.class)
	public ResponseEntity<ErrorResponse> handleCompletionException(final CompletionException e) {
		// TODO-CR: 20140817 J->T i think it would be more appropriate to fix this in an interceptor if possible
		Throwable ex = e.getCause();
		if(ex instanceof NisException) {
			return this.handleNccException((NisException)ex);
		}
		
		return this.createResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handler for general exceptions.
	 *
	 * @param e The exception.
	 * @return The appropriate JSON indicating an error.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(final Exception e) {
		return this.createResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Create a response entity which contains the error response as body
	 *
	 * @param code The value based enumeration code.
	 * @return The response entity.
	 */
	private ResponseEntity<ErrorResponse> createResponse(final ValueBasedEnum code) {
		// treat all expected NCC exceptions as BAD_REQUEST
		return new ResponseEntity<>(
				new ErrorResponse(this.timeProvider.getCurrentTime(), code.toString(), code.value()),
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * Create a response entity which contains the error response as body.
	 *
	 * @param e The exception.
	 * @param status The http status.
	 * @return The response entity.
	 */
	private ResponseEntity<ErrorResponse> createResponse(final Exception e, final HttpStatus status) {
		return new ResponseEntity<>(new ErrorResponse(this.timeProvider.getCurrentTime(), e, status), status);
	}
}
