package org.nem.vanity.generator;

import java.awt.*;
import javax.swing.*;

public class VanityAddressRenderer extends JLabel implements ListCellRenderer<VanityAddress> {

	@Override
	public Component getListCellRendererComponent(JList<? extends VanityAddress> list, VanityAddress value, int index, boolean isSelected, boolean cellHasFocus) {
		String vanityText = value.getVanityText();
		String address = value.getAddress();
		int ind = address.indexOf(vanityText);
		String colorText = isSelected ? "e0e0e0" : "ffffff";
		setText(String
				.format(
						"<html><span style='color : #41ce7c; font-family : Monospaced; font-size : 18; background-color: #%s'>%s<span style='color : #e1a92b'>%s</span>%s</span></html>",
						colorText, address.substring(0, ind), vanityText, address.substring(ind + vanityText.length())));

		return this;
	}
}
