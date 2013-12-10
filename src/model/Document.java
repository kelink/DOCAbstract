package model;

public class Document {

	/**
	 * 文章Model
	 */
	private int ID; // 文章ID
	private String title;// 文章标题
	private Paragraph[] paragraphs;// 段落

	public Document() {
		super();
	}

	public Document(String title, Paragraph[] paragraphs) {
		super();
		this.title = title;
		this.paragraphs = paragraphs;
	}

	public Document(int iD, String title, Paragraph[] paragraphs) {
		super();
		ID = iD;
		this.title = title;
		this.paragraphs = paragraphs;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Paragraph[] getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(Paragraph[] paragraphs) {
		this.paragraphs = paragraphs;
	}

}
