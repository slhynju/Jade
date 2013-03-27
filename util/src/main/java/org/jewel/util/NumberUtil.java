package org.jewel.util;

import java.text.NumberFormat;

public final class NumberUtil {

	public static NumberFormat newFormat(int minFractionDigits,
			int maxFractionDigits, boolean groupingUsed) {
		NumberFormat f = NumberFormat.getInstance();
		f.setMinimumFractionDigits(minFractionDigits);
		f.setMaximumFractionDigits(maxFractionDigits);
		f.setGroupingUsed(groupingUsed);
		return f;
	}

	public static NumberFormat newFormat(int fraction) {
		return newFormat(fraction, fraction, false);
	}
}
