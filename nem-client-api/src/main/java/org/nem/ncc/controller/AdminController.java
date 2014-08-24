package org.nem.ncc.controller;

import org.nem.core.deploy.CommonStarter;
import org.nem.core.serialization.*;
import org.nem.core.utils.ExceptionUtils;
import org.nem.ncc.cache.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

/**
 * REST controller dealing with administrative functions.
 */
@RestController
public class AdminController {
	private final static Logger LOGGER = Logger.getLogger(AdminController.class.getName());
	private final static long SHUTDOWN_DELAY = 200;
	private final AccountsFileRepository repository;
	private final NccAccountCache accountCache;
	private final CommonStarter commonStarter;

	@Autowired(required = true)
	public AdminController(
			final AccountsFileRepository repository,
			final NccAccountCache accountCache,
			final CommonStarter commonStarter) {
		this.repository = repository;
		this.accountCache = accountCache;
		this.commonStarter = commonStarter;
	}

	/**
	 * Simple heartbeat api that can be pinged to test the online status of NCC.
	 *
	 * @return An entity representing a successful result.
	 */
	@RequestMapping(value = "/heartbeat", method = RequestMethod.GET)
	public SerializableEntity heartbeat() {
		return serializer -> serializer.writeString("status", "ok");
	}

	/**
	 * Stops the current NCC server. Afterwards it has to be started via WebStart again (or just running {@link org.nem.ncc.NccMain})
	 */
	@RequestMapping(value = "/shutdown", method = RequestMethod.GET)
	public void shutdown() {
		LOGGER.info(String.format("Async shut-down initiated in %d msec.", SHUTDOWN_DELAY));
		final Runnable r = () -> {
			ExceptionUtils.propagateVoid(() -> Thread.sleep(SHUTDOWN_DELAY));
			this.flushAccountsCache();
			this.commonStarter.stopServer();
		};

		final Thread thread = new Thread(r);
		thread.start();
	}

	/**
	 * Flushes (saves to disk) the account cache.
	 */
	private void flushAccountsCache() {
		this.repository.save(this.accountCache.getAccounts());
	}
}
