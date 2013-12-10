package model;

public class Paragraph {

	/**
	 * ¶ÎÂäModel
	 */
	private int ID;// ¶ÎÂä±àºÅ
	private Setence[] setences;// ¾ä×Ó¼¯ºÏ

	public Paragraph() {
		super();
	}

	public Paragraph(Setence[] setences) {
		super();
		this.setences = setences;
	}

	public Paragraph(int iD, Setence[] setences) {
		super();
		ID = iD;
		this.setences = setences;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Setence[] getSetences() {
		return setences;
	}

	public void setSetences(Setence[] setences) {
		this.setences = setences;
	}

}