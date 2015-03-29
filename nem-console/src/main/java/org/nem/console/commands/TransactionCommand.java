package org.nem.console.commands;

import org.nem.console.models.AliasedKeyPair;
import org.nem.console.utils.*;
import org.nem.core.time.TimeInstant;

import java.util.Collection;
import java.util.function.Function;

public abstract class TransactionCommand implements Command {
	private final String name;

	protected TransactionCommand(final String name) {
		this.name = name;
	}

	@Override
	public final String getName() {
		return this.name;
	}

	@Override
	public final void handle(final CommandLine commandLine) {
		final Collection<AliasedKeyPair> keyPairs = KeyPairsStorage.load(commandLine);

		final String senderAlias = commandLine.getOptionValue("sender");
		final Transaction transaction = this.createTransaction(
				new TimeInstant(Integer.parseInt(commandLine.getOptionValue("time"))),
				this.findByAlias(keyPairs, senderAlias),
				alias -> this.findByAlias(keyPairs, alias),
				commandLine);

		prepareAndSign(transaction);
		TransactionStorage.save(transaction, commandLine.getOptionValue("output"));
		System.out.println("Created transaction: ");
		System.out.println(String.format("     type: %d", transaction.getType()));
		System.out.println(String.format("  version: %d", transaction.getVersion()));
		System.out.println(String.format("   sender: %s", transaction.getSigner()));
		System.out.println(String.format("      fee: %s", transaction.getFee()));
	}

	protected abstract Transaction createTransaction(
			final TimeInstant timeStamp,
			final Account sender,
			final Function<String, Account> accountLookup,
			final CommandLine commandLine);

	private Account findByAlias(final Collection<AliasedKeyPair> keyPairs, final String alias) {
		for (final AliasedKeyPair keyPair : keyPairs) {
			if (keyPair.alias().equals(alias)) {
				return new Account(keyPair.keyPair());
			}
		}

		final Address address = Address.fromEncoded(alias);
		if (!address.isValid()) {
			throw new IllegalArgumentException(String.format("no known key pair for alias '%s'", alias));
		}

		return new Account(address);
	}

	private static void prepareAndSign(final Transaction transaction) {
		transaction.setFee(TransactionFeeCalculator.calculateMinimumFee(transaction, BlockHeight.MAX));
		transaction.setDeadline(transaction.getTimeStamp().addHours(12));
		transaction.sign();
	}

	@Override
	public final Options getOptions() {
		final Options options = new Options();
		OptionsUtils.addReadOptions(options);
		options.addOption("sender", true, "The sender alias");
		options.addOption("time", true, "The soft timestamp");
		options.addOption("output", true, "The output transaction file");
		this.addCustomOptions(options);
		return options;
	}

	protected abstract void addCustomOptions(Options options);
}
