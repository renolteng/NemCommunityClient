package org.nem.vanity.generator;

import org.nem.core.crypto.KeyPair;
import org.nem.core.model.Address;

import java.util.*;
import java.util.function.Consumer;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 * Visitor that keeps node menu item text in sync with status changes.
 */
public class VanityAddressVisitor implements ListModel<String>{
	final private List<KeyPair> vanityAddresses;
	final private Consumer<List<KeyPair>> addressListConsumer;
	final private List<ListDataListener> listeners;

	/**
	 * Creates a new visitor.
	 *
	 * @param nodeType The node type being monitored.
	 * @param statusDescriptionConsumer The function to call when a description change is triggered.
	 */
	public VanityAddressVisitor(final Consumer<List<KeyPair>> addressListConsumer) {
		this.vanityAddresses = new ArrayList<KeyPair>();
		this.addressListConsumer = addressListConsumer;
		this.listeners = new ArrayList<ListDataListener>();
	}

	public void addressFound(final KeyPair keyPair) {
		vanityAddresses.add(keyPair);
		addressListConsumer.accept(vanityAddresses);
		listeners.stream().forEach(l -> l.contentsChanged(null)); 
	}

	@Override
	public int getSize() {
		// 
		return vanityAddresses.size();
	}

	@Override
	public String getElementAt(int index) {
		// 
		return Address.fromPublicKey(vanityAddresses.get(index).getPublicKey()).getEncoded();
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// 
		listeners.add(l);
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// 
		listeners.remove(l);
	}
}
