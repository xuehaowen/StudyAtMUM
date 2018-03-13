package lesson5.labs.prob1.rulesets;

import java.awt.Component;

import lesson5.labs.prob1.gui.CDWindow;

/**
 * Rules: 1. All fields must be nonempty 2. Price must be a floating point
 * number with two decimal places 3. Price must be a number greater than 0.49.
 */

public class CDRuleSet implements RuleSet {

	@Override
	public void applyRules(Component ob) throws RuleException {
		CDWindow cw = (CDWindow) ob;

		checkArtistValue(cw.getArtistValue());
		checkPriceValue(cw.getPriceValue());
		checkTitleValue(cw.getTitleValue());
	}

	private void checkArtistValue(String artist) throws RuleException {
		if (artist == null || artist.isEmpty()) {
			throw new RuleException("Artist must be non-empty");
		}
	}

	private void checkPriceValue(String price) throws RuleException {
		if (price == null || price.isEmpty()) {
			throw new RuleException("Price must be non-empty");
		}
		float fPrice;
		try {
			fPrice = Float.parseFloat(price);
		} catch (Exception e) {
			throw new RuleException("Price must be a floating point number");
		}
		String[] strs = price.split("\\.");
		if(strs.length < 2 )
			throw new RuleException("Price must have 2 decimal places");
		if (strs[1].length() != 2)
			throw new RuleException("Price must have 2 decimal places");
		if (fPrice <= 0.49) {
			throw new RuleException("Price must be greater than 0.49");
		}
	}

	private void checkTitleValue(String title) throws RuleException {
		if (title == null || title.isEmpty()) {
			throw new RuleException("Title must be non-empty");
		}
	}

}
