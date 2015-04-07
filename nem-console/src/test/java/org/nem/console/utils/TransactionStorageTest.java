package org.nem.console.utils;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.io.*;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.console.test.Utils;
import org.nem.core.crypto.*;
import org.nem.core.model.*;
import org.nem.core.model.ncc.RequestAnnounce;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.*;
import org.nem.core.time.TimeInstant;
import org.nem.core.utils.ExceptionUtils;

import java.io.*;
import java.util.function.BiConsumer;

import static org.junit.Assert.*;

public class TransactionStorageTest {
	private static final String WORKING_DIRECTORY = System.getProperty("user.dir");
	private static final File TEST_FILE_DIRECTORY = new File(WORKING_DIRECTORY, "test_files");

	//region BeforeClass / AfterClass

	@BeforeClass
	public static void createTestFiles() throws IOException {
		if (!TEST_FILE_DIRECTORY.mkdir()) {
			throw new IllegalStateException("unable to create test files");
		}
	}

	@AfterClass
	public static void removeTestFiles() throws IOException {
		FileUtils.deleteDirectory(TEST_FILE_DIRECTORY);
	}

	//endregion

	@Test
	public void canWriteTransaction() {
		// Assert:
		assertCanSave("default.kp", TransactionStorage::save);
	}

	@Test
	public void canWriteTransactionUsingCommandLineOverload() {
		// Assert:
		assertCanSave("commandline.kp", (transaction, fileName) -> {
			final CommandLine saveOptions = Utils.createCommandLine(String.format("--output=%s", fileName));
			TransactionStorage.save(transaction, saveOptions);
		});
	}

	private static void assertCanSave(final String shortFileName, final BiConsumer<Transaction, String> saveToFile) {
		// Arrange:
		final String fileName = new File(TEST_FILE_DIRECTORY, shortFileName).toString();
		final Transaction originalTransaction = createTransaction();
		final Hash originalHash = HashUtils.calculateHash(originalTransaction);

		// Act:
		saveToFile.accept(originalTransaction, fileName);
		final RequestAnnounce announce = load(fileName);
		final Transaction transaction = TransactionFactory.NON_VERIFIABLE.deserialize(
				new BinaryDeserializer(
						announce.getData(),
						new DeserializationContext(Account::new)));
		final Hash hash = HashUtils.calculateHash(transaction);
		final Signature signature = new Signature(announce.getSignature());

		// Assert:
		Assert.assertThat(hash, IsEqual.equalTo(originalHash));
		Assert.assertThat(signature, IsEqual.equalTo(originalTransaction.getSignature()));
	}

	private static RequestAnnounce load(final String fileName) {
		final byte[] bytes = ExceptionUtils.propagate(() -> loadBytes(fileName));
		return new RequestAnnounce(new BinaryDeserializer(bytes, new DeserializationContext(null)));
	}

	private static byte[] loadBytes(final String fileName) throws IOException {
		try (final InputStream inputStream = new FileInputStream(fileName)) {
			return IOUtils.toByteArray(inputStream);
		}
	}

	private static Transaction createTransaction() {
		final Transaction transaction = new TransferTransaction(
				new TimeInstant(123),
				Utils.generateRandomAccount(),
				Utils.generateRandomAccount(),
				Amount.fromNem(444),
				null);
		transaction.sign();
		return transaction;
	}
}