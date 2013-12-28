package com.gdufs.model;

public class Term {

	/**
	 * 单词
	 */
	private String content;// 单词内容
	private String type;// 单词的类型
	private double weight = 0.0;// 词的权重

	public Term() {
		super();
	}

	public Term(String content, String type, double weight) {
		super();
		this.content = content;
		this.type = type;
		this.weight = weight;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Term [content=" + content + ", type=" + type + ", weight="
				+ weight + "]";
	}

}
