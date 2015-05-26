package org.nem.ncc.storable.entity.storage;

/**
 * Enumeration of read modes for a storable entity.
 */
public enum StorableEntityReadMode {
	/**
	 * Open the entity for reading without decoding the stream.
	 */
	Raw,

	/**
	 * Open the entity for reading with prior decoding the stream.
	 */
	Decode
}
