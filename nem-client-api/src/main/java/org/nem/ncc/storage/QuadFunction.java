package org.nem.ncc.storage;

/**
 * Quad Function interface declaration.
 */
@FunctionalInterface
public interface QuadFunction<T, U, V, W, R> {
	R apply(T t, U u, V v, W w);
}
