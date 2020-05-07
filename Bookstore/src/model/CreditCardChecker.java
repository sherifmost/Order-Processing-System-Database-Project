package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import utils.CreditCardData;

//This class validates that the user entered a valid credit card number
/*
 * rules are as follows for supported credit cards:
 * VISA: starts with 4 and has a length of 13/16/19
 * Master Card: starts with 51 to 55 or 222100 to 272099 and has a length of 16
 * American express: starts with 34 to 37 and has a length of 15
 */
public class CreditCardChecker {
	private String creditCardName;
	private String creditCardNumber;
	private Date expireDate;
	private SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");

	// Constructors
	public CreditCardChecker() {

	}

	public CreditCardChecker(String creditCardName, String creditCardNumber, String expireDate) throws ParseException {
		this.creditCardName = creditCardName;
		this.creditCardNumber = creditCardNumber;
		this.expireDate = sdFormat.parse(expireDate);
	}

	// The main function that checks the validity of the credit card number
	public boolean validateNumber() {
		if (creditCardNumber == null) {
			return false;
		}
		// Just to check that the given number is in numerical form
		@SuppressWarnings("unused")
		long intForm;
		try {
			intForm = Long.parseUnsignedLong(creditCardNumber);
		} catch (NumberFormatException nfe) {
			return false;
		}
		switch (creditCardName) {
		case CreditCardData.VISA: {

			return validateVisa();
		}
		case CreditCardData.MASTERCARD: {

			return validateMasterCard();
		}
		case CreditCardData.AMERICAN_EXPRESS: {

			return validateAmericanExpress();
		}
		default: {
			return false;
		}
		}
	}

	// validation for each credit card type
	private boolean validateVisa() {
		String creditCardNumber = getCreditCardNumber();
		// checking the card number length
		if (creditCardNumber.length() != 13 && creditCardNumber.length() != 16 && creditCardNumber.length() != 19) {
			return false;
		}
		// checking the validity of the credit card number
		if (creditCardNumber.charAt(0) != '4') {
			return false;
		}
		return true;
	}

	private boolean validateMasterCard() {
		String creditCardNumber = getCreditCardNumber();
		// checking the card length
		if (creditCardNumber.length() != 16)
			return false;
		// checking the validity of the credit card number
		int firstTwo = Integer.parseInt(creditCardNumber.substring(0, 2));
		int firstSix = Integer.parseInt(creditCardNumber.substring(0, 6));
		if (!(51 <= firstTwo && firstTwo <= 55) && !(222100 <= firstSix && firstSix <= 272099))
			return false;
		return true;
	}

	private boolean validateAmericanExpress() {
		// checking the length of the card number
		String creditCardNumber = getCreditCardNumber();
		if (creditCardNumber.length() != 15)
			return false;
		int firstTwo = Integer.parseInt(creditCardNumber.substring(0, 2));
		// Checking the validity of the number
		if (!(34 <= firstTwo && firstTwo <= 37))
			return false;
		return true;

	}

	// Validation for expire date
	public boolean validateDate() {
		Date now = new Date();
		// expire date should be in the future
		if (now.compareTo(getExpireDate()) != -1)
			return false;
		return true;
	}

	// Setters and getters
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getCreditCardName() {
		return creditCardName;
	}

	public void setCreditCardName(String creditCardName) {
		this.creditCardName = creditCardName;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

}
