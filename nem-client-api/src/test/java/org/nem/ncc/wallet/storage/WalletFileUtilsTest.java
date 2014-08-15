package org.nem.ncc.wallet.storage;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.ncc.wallet.WalletName;

public class WalletFileUtilsTest {

	//region getWalletFileName

	@Test
	public void getWalletFileNameReturnsCorrectFileNameForWalletNameThatDoesNotNeedToBeEncoded() {
		// Assert:
		Assert.assertThat(
				WalletFileUtils.getWalletFileName(new WalletName("foo")),
				IsEqual.equalTo("foo.wlt"));
	}

	@Test
	public void getWalletFileNameReturnsCorrectFileNameForWalletNameThatNeedsToBeEncoded() {
		// Assert:
		Assert.assertThat(
				WalletFileUtils.getWalletFileName(new WalletName("öäü@")),
				IsEqual.equalTo("%C3%B6%C3%A4%C3%BC%40.wlt"));
	}

	//endregion

	//region isValidWalletFileName

	@Test
	public void validWalletFileNameMustEndInWalletExtension() {
		// Assert:
		Assert.assertThat(WalletFileUtils.isValidWalletFileName("alpha.wlt"), IsEqual.equalTo(true));
		Assert.assertThat(WalletFileUtils.isValidWalletFileName("alpha.wltx"), IsEqual.equalTo(false));
		Assert.assertThat(WalletFileUtils.isValidWalletFileName("alpha.wl"), IsEqual.equalTo(false));
	}

	@Test
	public void validWalletFileNameCanBeInSubdirectoryWithWalletExtension() {
		// Assert:
		Assert.assertThat(WalletFileUtils.isValidWalletFileName("foo/alpha.wlt"), IsEqual.equalTo(true));
		Assert.assertThat(WalletFileUtils.isValidWalletFileName("foo\\alpha.wlt"), IsEqual.equalTo(true));
	}

	@Test
	public void validWalletFileNameCanEndInWalletExtensionOfAnyCasing() {
		// Assert:
		Assert.assertThat(WalletFileUtils.isValidWalletFileName("upper.WLT"), IsEqual.equalTo(true));
		Assert.assertThat(WalletFileUtils.isValidWalletFileName("lower.wlt"), IsEqual.equalTo(true));
		Assert.assertThat(WalletFileUtils.isValidWalletFileName("mixed.WlT"), IsEqual.equalTo(true));
	}

	@Test
	public void validWalletFileNameMustHaveNonZeroLength() {
		// Assert:
		Assert.assertThat(WalletFileUtils.isValidWalletFileName("n.wlt"), IsEqual.equalTo(true));
		Assert.assertThat(WalletFileUtils.isValidWalletFileName("name.wlt"), IsEqual.equalTo(true));
		Assert.assertThat(WalletFileUtils.isValidWalletFileName(".wlt"), IsEqual.equalTo(false));
	}

	@Test
	public void validWalletFileNameIsDeterminedByLastExtensionIfMultipleExtensionsArePresent() {
		// Assert:
		Assert.assertThat(WalletFileUtils.isValidWalletFileName("tw.tmp.wlt"), IsEqual.equalTo(true));
		Assert.assertThat(WalletFileUtils.isValidWalletFileName("wt.wlt.tmp"), IsEqual.equalTo(false));
	}

	//endregion
}