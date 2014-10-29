package org.nem.vanity.generator;

import org.nem.core.crypto.KeyPair;
import org.nem.core.model.Address;

import org.apache.commons.codec.binary.Base32;

import java.util.concurrent.TimeUnit;
import javax.swing.ListModel;

/**
 *
 * Main class for generating a new NEM address which contains some provided text phrases
 */
public class Vanity {
	final private VanityAddressModel addressModel;

	/**
	 * Create a new instance of the vanity generator which will update the provided list model
	 * 
	 * @param addressModel
	 */
	public Vanity(final ListModel<VanityAddress> addressModel) {
		if (addressModel instanceof VanityAddressModel) {
			this.addressModel = (VanityAddressModel) addressModel;
		} else {
			throw new IllegalArgumentException(String.format("Model of type VanityAddressVisitor expected: <%s>", addressModel.getClass()));
		}
	}
	
	/**
	 * starts the generation of new NEM accounts / addresses which included the provided text
	 * It also differentiate between testnet and mainnet (via version).
	 * Method can be canceled / interrupted and runs until 99 nem address were found. 
	 * 
	 * @param vanity
	 * @param version
	 * @return number of created nem addresses
	 */
	public Long generate(final String vanity, final byte version) {
		final String wantedAddress = vanity.toUpperCase();
		KeyPair keyPair = null;
		Long count = 0L;
		int found = 0;
		boolean calc = true;
		while (calc && found < 100) {
			keyPair = new KeyPair();
			final String address = Address.fromPublicKey(version, keyPair.getPublicKey()).getEncoded();
			count++;

			if (address.contains(wantedAddress)) {
				found++;
				addressModel.addressFound(address, keyPair.getPrivateKey(), vanity, version);
			}
			
			try {
				TimeUnit.MILLISECONDS.sleep(2);
			} catch (InterruptedException e) {
				//leave the endless loop
				break;
			}
			
			calc = !Thread.currentThread().isInterrupted();
		}
		
		return count;
	}
	
	public static String normalizeVanityText(final String vanityText) {
		StringBuilder strB = new StringBuilder();
		char ch;
		Base32 encoder = new Base32(true);
		for(int i = 0; i < vanityText.length(); i++) {
			ch = Character.toUpperCase(vanityText.charAt(i));
			if(encoder.isInAlphabet((byte)ch)) {
				strB.append(ch);
			}
		}
		return strB.toString();
	}
}
