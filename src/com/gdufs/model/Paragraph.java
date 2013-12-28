package com.gdufs.model;

public class Paragraph {

	/**
	 * ����Model
	 */
	private int ID;// ������
	private String content;// ���Ӽ���

	public Paragraph() {
		super();
	}

	public Paragraph(int iD, String content) {
		super();
		ID = iD;
		this.content = content;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Paragraph [ID=" + ID + ", content=" + content + "]";
	}

}