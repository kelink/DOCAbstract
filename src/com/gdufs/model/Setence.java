package com.gdufs.model;

public class Setence {
	/**
	 * ����Model
	 */

	private int id;// ����ID
	private int p_id;// ���������id
	private String content;// ��������
	private int type = 0;// ���ӵ�����

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
