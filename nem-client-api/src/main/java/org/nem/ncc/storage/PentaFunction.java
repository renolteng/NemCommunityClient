package org.nem.ncc.storage;

/**
 * Penta Function interface declaration.
 */
@FunctionalInterface
public interface PentaFunction<T, U, V, W, X, R> {
	R apply(T t, U u, V v, W w, X x);
}
