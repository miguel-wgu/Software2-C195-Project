package model;

/**
 * The Report class.
 */
public class Report {
	/**
	 * The Arg 1.
	 */
	private String arg1;
	/**
	 * The Arg 2.
	 */
	private String arg2;
	/**
	 * The Total.
	 */
	private int total;

	/**
	 * Instantiates a new Report.
	 *
	 * @param month the month
	 * @param type  the type
	 * @param total the total
	 */
	public Report(String month, String type, int total) {
		this.arg1 = month;
		this.arg2 = type;
		this.total = total;
	}

	/**
	 * Gets arg 1.
	 *
	 * @return the arg 1
	 */
	public String getArg1() {
		return arg1;
	}

	/**
	 * Sets arg 1.
	 *
	 * @param arg1 the arg 1
	 */
	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}

	/**
	 * Gets arg 2.
	 *
	 * @return the arg 2
	 */
	public String getArg2() {
		return arg2;
	}

	/**
	 * Sets arg 2.
	 *
	 * @param arg2 the arg 2
	 */
	public void setArg2(String arg2) {
		this.arg2 = arg2;
	}

	/**
	 * Gets total.
	 *
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * Sets total.
	 *
	 * @param total the total
	 */
	public void setTotal(int total) {
		this.total = total;
	}
}
