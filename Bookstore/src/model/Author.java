package model;

public class Author {
	// data corresponding to the relation
	private long ISBN;
	private String fname;
	private String lname;

	// constructor
	public Author() {

	}

	public Author(long ISBN, String fname, String lname) {
		setFname(fname);
		setISBN(ISBN);
		setLname(lname);
	}

	// setters and getters
	public long getISBN() {
		return ISBN;
	}

	public void setISBN(long iSBN) {
		ISBN = iSBN;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
}
