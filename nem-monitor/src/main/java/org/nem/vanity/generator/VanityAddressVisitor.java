package org.nem.vanity.generator;

import org.nem.core.crypto.*;
import org.nem.core.model.Address;

import java.util.*;
import java.util.function.Consumer;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 * Visitor that keeps node menu item text in sync with status changes.
 */
public class VanityAddressVisitor implements ListModel<String>{
	final private List<VanityAddress> vanityAddresses;
	final private Consumer<List<VanityAddress>> addressListConsumer;
	final private List<ListDataListener> listeners;

	/**
	 * Creates a new visitor.
	 *
	 * @param nodeType The node type being monitored.
	 * @param statusDescriptionConsumer The function to call when a description change is triggered.
	 */
	public VanityAddressVisitor(final Consumer<List<VanityAddress>> addressListConsumer) {
		this.vanityAddresses = new ArrayList<VanityAddress>();
		this.addressListConsumer = addressListConsumer;
		this.listeners = new ArrayList<ListDataListener>();
	}

	public void addressFound(final String address, final PrivateKey privateKey) {
		vanityAddresses.add(new VanityAddress(address, privateKey));
		addressListConsumer.accept(vanityAddresses);
		listeners.stream().forEach(l -> l.contentsChanged(null)); 
	}
	
	public String getPrivateKeyAt(int index) {
		String result = vanityAddresses.get(index).getPrivateKey();
		return result;
	}
	
	public String getAddressAndPrivateKeyAt(int index) {
		VanityAddress vanityAddress = vanityAddresses.get(index);
		String result = String.format("%s / %s", vanityAddress.getAddress(), vanityAddress.getPrivateKey());
		return result;
	}

	@Override
	public int getSize() {
		// 
		return vanityAddresses.size();
	}

	@Override
	public String getElementAt(int index) {
		// 
		return vanityAddresses.get(index).getAddress();
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
