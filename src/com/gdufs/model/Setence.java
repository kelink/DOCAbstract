package com.gdufs.model;

public class Setence {
	/**
	 * 句子Model
	 */

	private int id;// 句子ID
	private int p_id;// 所属段落的id
	private String content;// 句子内容
	private int type = 0;// 句子的类型

	public Setence() {
		super();
	}

	public Setence(int id, int p_id, String content) {
		super();
		this.id = id;
		this.p_id = p_id;
		this.content = content;
	}

	public Setence(int id, int p_id, String content, int type) {
		super();
		this.id = id;
		this.p_id = p_id;
		this.content = content;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Setence [id=" + id + ", p_id=" + p_id + ", content=" + content
				+ ", type=" + type + "]";
	}

}
