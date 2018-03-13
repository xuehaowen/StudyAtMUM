package lesson5.labs.prob1.rulesets;

import java.awt.Component;

import lesson5.labs.prob1.gui.BookWindow;

/**
 * Rules: 1. All fields must be nonempty 2. Isbn must be numeric and consist of
 * either 10 or 13 characters 3. If Isbn has length 10, the first digit must be
 * 0 or 1 4. If Isbn has length 13, the first 3 digits must be either 978 or 979
 * 5. Price must be a floating point number with two decimal places 6. Price
 * must be a number greater than 0.49.
 *
 */
public class BookRuleSet implements RuleSet {

	@Override
	public void applyRules(Component ob) throws RuleException {
		BookWindow bw = (BookWindow) ob;

		checkIsbn(bw.getIsbnValue());
		checkPrice(bw.getPriceValue());
		checkTitle(bw.getTitleValue());

	}

	private void checkIsbn(String isbn) throws RuleException {
		if (isbn == null || isbn.isEmpty()) {
			throw new RuleException("Isbn must be non-empty");
		}
		try {
			Integer.parseInt(isbn);
		} catch (Exception e) {
			throw new RuleException("Isbn must be numeric");
		}
		if (isbn.length() != 10 && isbn.length() != 13)
			throw new RuleException("Isbn must be consist of 10 or 13 characters");
		if (isbn.length() == 10) {
			if (!isbn.startsWith("0") && !isbn.startsWith("1"))
				throw new RuleException("10 digits isbn must start with 0 or 1");
		}
		if (isbn.length() == 13) {
			if (!isbn.startsWith("978") && !isbn.startsWith("979"))
				throw new RuleException("13 digits isbn must start with 978 or 979");
		}

	}

	private void checkPrice(String price) throws RuleException {
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

	private void checkTitle(String title) throws RuleException {
		if (title == null || title.isEmpty()) {
			throw new RuleException("Title must be non-empty");
		}
	}

}
