package org.nem.ncc.test;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.nem.ncc.exceptions.NccException;
import org.nem.ncc.storage.StorableEntityStorageException;
import org.nem.ncc.wallet.storage.WalletStorageException;

import java.util.concurrent.CompletionException;
import java.util.function.Consumer;

/**
 * Helper class that contains functions for asserting that specific exceptions
 * are thrown.
 */
public class ExceptionAssert {

	/**
	 * Asserts that the execution of consumer throws an exception of the specific class.
	 *
	 * @param consumer The consumer.
	 * @param exceptionClass The expected exception class.
	 */
	public static void assertThrows(final Consumer<Void> consumer, final Class<?> exceptionClass) {
		assertThrows(consumer, exceptionClass, ex -> { });
	}

	/**
	 * Asserts that the execution of consumer throws an exception of the specific class.
	 *
	 * @param consumer The consumer.
	 * @param exceptionClass The expected exception class.
	 * @param assertExceptionProperties Consumer that is passed the matching exception to run any additional validation.
	 */
	@SuppressWarnings("unchecked")
	public static <T> void assertThrows(
			final Consumer<Void> consumer,
			final Class<T> exceptionClass,
			final Consumer<T> assertExceptionProperties) {
		try {
			consumer.accept(null);
		} catch (final Exception ex) {
			if (exceptionClass.isAssignableFrom(ex.getClass())) {
				assertExceptionProperties.accept((T)ex);
				return;
			}

			Assert.fail(String.format("unexpected exception of type %s was thrown. Expected %s", ex.getClass(), exceptionClass));
		}

		Assert.fail(String.format("expected exception of type %s was not thrown", exceptionClass));
	}

	/**
	 * Asserts that the execution of consumer throws a completion exception wrapping an exception of the
	 * specific class.
	 *
	 * @param consumer The consumer.
	 * @param exceptionClass The expected exception class.
	 */
	public static void assertThrowsCompletionException(final Consumer<Void> consumer, final Class<?> exceptionClass) {
		try {
			consumer.accept(null);
		} catch (final CompletionException completionEx) {
			final Throwable ex = completionEx.getCause();
			if (ex.getClass() == exceptionClass) {
				return;
			}

			Assert.fail(String.format("unexpected exception of type %s was thrown", ex.getClass()));
		}

		Assert.fail(String.format("expected exception of type %s was not thrown", exceptionClass));
	}

	/**
	 * Asserts that the execution of consumer throws a WalletStorageException with the specified code.
	 *
	 * @param consumer The consumer.
	 * @param code The expected code.
	 */
	public static void assertThrowsWalletStorageException(
			final Consumer<Void> consumer,
			final WalletStorageException.Code code) {
		assertThrows(
				consumer,
				WalletStorageException.class,
				ex -> Assert.assertThat(ex.getCode(), IsEqual.equalTo(code)));
	}

	/**
	 * Asserts that the execution of consumer throws a StorableEntityStorageException with the specified code.
	 *
	 * @param consumer The consumer.
	 * @param code The expected code.
	 */
	public static void assertThrowsStorableEntityStorageException(
			final Consumer<Void> consumer,
			final StorableEntityStorageException.Code code) {
		assertThrows(
				consumer,
				StorableEntityStorageException.class,
				ex -> Assert.assertThat(ex.getCode(), IsEqual.equalTo(code)));
	}

	/**
	 * Asserts that the execution of consumer throws an NccException with the specified code.
	 *
	 * @param consumer The consumer.
	 * @param code The expected code.
	 */
	public static void assertThrowsNccException(
			final Consumer<Void> consumer,
			final NccException.Code code) {
		assertThrows(
				consumer,
				NccException.class,
				ex -> Assert.assertThat(ex.getCode(), IsEqual.equalTo(code)));
	}
}
