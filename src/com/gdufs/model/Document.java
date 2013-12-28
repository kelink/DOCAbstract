package com.gdufs.model;

public class Document {

	/**
	 * 文章Model
	 */
	private String fileName; // 文章ID
	private String title;// 文章标题
	private String content;// 段落

	public Document() {
		super();
	}

	public Document(String fileName, String title, String content) {
		super();
		this.fileName = fileName;
		this.title = title;
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Document [fileName=" + fileName + ", title=" + title
				+ ", content=" + content + "]";
	}

}
