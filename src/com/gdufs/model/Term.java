package com.gdufs.model;

public class Term {

	/**
	 * ����
	 */
	private String content;// ��������
	private String type;// ���ʵ�����
	private double weight = 0.0;// �ʵ�Ȩ��

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
