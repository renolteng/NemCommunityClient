package org.nem.ncc.connector;

import org.nem.core.connect.*;
import org.nem.core.connect.client.*;
import org.nem.ncc.exceptions.NisException;

/**
 * A default AsyncNisConnector implementation
 */
public class DefaultAsyncNisConnector extends DefaultAsyncNemConnector<NisApiId> implements AsyncNisConnector {

	/**
	 * Creates a new default NIS connector.
	 *
	 * @param httpClient The HTTP client.
	 */
	public DefaultAsyncNisConnector(final HttpMethodClient<ErrorResponseDeserializerUnion> httpClient) {
		super(httpClient, r -> { throw new NisException(r); });
	}
}
