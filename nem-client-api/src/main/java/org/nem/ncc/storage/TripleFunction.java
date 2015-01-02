package org.nem.ncc.storage;

/**
 * Triple Function interface declaration.
 */
@FunctionalInterface
public interface TripleFunction<T, U, V, R> {
	R apply(T t, U u, V v);
}
