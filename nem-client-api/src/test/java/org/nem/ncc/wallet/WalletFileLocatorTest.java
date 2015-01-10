package org.nem.ncc.wallet;

import org.junit.BeforeClass;
import org.nem.ncc.storable.entity.*;

import java.io.*;

public class WalletFileLocatorTest extends StorableEntityFileLocatorTest {

	@BeforeClass
	public static void createTestFiles() throws IOException {
		createTestFiles(getDefaultFileExtension());
	}

	protected static String getDefaultFileExtension() {
		return WalletFileExtension.getDefaultFileExtension().toString();
	}

	@Override
	protected StorableEntityFileLocator createFileLocator(final File file) {
		return new WalletFileLocator(file);
	}
}