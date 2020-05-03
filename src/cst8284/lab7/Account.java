package cst8284.lab7;

import java.text.*;
import java.util.Calendar;
import java.util.Date;

public class Account {
	private String accountNumber = "000-000000"; // branch number - customer account number
	private String firstName, lastName;
	private static final Calendar ACCOUNTSTARTDATE = Calendar.getInstance();
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public Account(String accountNumber, String firstName, String lastName, String startDate) {
		dateFormat.setLenient(false);
		setAccountNumber(accountNumber);
		setFirstName(firstName);
		setLastName(lastName);
		setAccountStartDate(startDate);
	}

	private void setAccountNumber(String accountNumber) {

		if (accountNumber.length() - accountNumber.replace("-", "").length() > 1)
			throw new BadAccountInputException("Only one hyphen allowed in account number");
		else if (isCheckDigitCorrect(accountNumber))
			this.accountNumber = accountNumber;
		else
			throw new BadAccountInputException("Checkdigit failed; possible bad account number");
	}

	private static boolean isCheckDigitCorrect(String accountNumber) {

		int sum = 0;
		String number = accountNumber.split("-")[1];
		for (int i = 0; i < number.length() - 1; i++) {
			int num = Character.getNumericValue(number.charAt(i));
			sum += (i % 2 == 0) ? (2 * num) : (3 * num);
		}
		return sum % 10 == Character.getNumericValue(number.charAt(number.length() - 1));
	}

	private static boolean isInputNameCorrect(String name) {

		if (!name.matches("[a-zA-Z-.']"))
			throw new BadAccountInputException(
					"Name must contain alphabetical characters only, along with ., -, and '.");
		return true;
	}
	

	public void setAccountStartDate(String startDate) {

		try {
			Date date = dateFormat.parse(startDate);
			ACCOUNTSTARTDATE.setTime(date);
		} catch (ParseException ex) {
			throw new BadAccountInputException("Format is YYYY-MM-DD");
		} catch (RuntimeException ex) {
			throw new BadAccountInputException("General runtime exception thrown setting start date");
		}
	}

	private void setFirstName(String firstName) {
		if (isInputNameCorrect(firstName))
			this.firstName = firstName;
	}

	private void setLastName(String lastName) {
		if (isInputNameCorrect(lastName))
			this.lastName = lastName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Calendar getAccountStartDate() {
		return ACCOUNTSTARTDATE;
	}

	public String toString() {
		return "Customer name: " + getFirstName() + " " + getLastName() 
			+ "\nCustomer Account number: "	+ getAccountNumber() 
			+ "\nAccount Created: " + getAccountStartDate().getTime().toString();
	}

}
