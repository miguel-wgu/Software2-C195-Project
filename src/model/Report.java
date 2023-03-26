package model;

public class Report {
	private String arg1;
	private String arg2;
	private int total;

	public Report(String month, String type, int total) {
		this.arg1 = month;
		this.arg2 = type;
		this.total = total;
	}

	public String getArg1() {
		return arg1;
	}

	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}

	public String getArg2() {
		return arg2;
	}

	public void setArg2(String arg2) {
		this.arg2 = arg2;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
