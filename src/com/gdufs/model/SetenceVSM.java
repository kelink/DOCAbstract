package com.gdufs.model;

public class SetenceVSM {

	/**
	 * ����VSM
	 */
	private int id;// ����ID
	private int p_id;// ���Ӷ���ID
	private String content;// ���ӵ�����

	private double w_Position;// ���ӵ�λ��Ȩ��
	private double w_length;// ���ӳ��ȵ�Ȩ��
	private double w_Type;// ��������ȡֵȨ��
	private double w_TFC;// ���ӵ�AVG-TFCȨ��
	private double w_similarity;// ���ӵ���ʶ��
	private double w_Distance;// ���Ӻ����ľ��ӵľ���
	private int classify = 0;// ���ų�ʼ��Ϊ-1��

	public SetenceVSM() {
		super();
	}

	public SetenceVSM(int id, int p_id, String content, double w_Position,
			double w_length, double w_Type, double w_TFC, double w_similarity,
			double w_Distance, int classify) {
		super();
		this.id = id;
		this.p_id = p_id;
		this.content = content;
		this.w_Position = w_Position;
		this.w_length = w_length;
		this.w_Type = w_Type;
		this.w_TFC = w_TFC;
		this.w_similarity = w_similarity;
		this.w_Distance = w_Distance;
		this.classify = classify;
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

	public double getW_Position() {
		return w_Position;
	}

	public void setW_Position(double w_Position) {
		this.w_Position = w_Position;
	}

	public double getW_length() {
		return w_length;
	}

	public void setW_length(double w_length) {
		this.w_length = w_length;
	}

	public double getW_Type() {
		return w_Type;
	}

	public void setW_Type(double w_Type) {
		this.w_Type = w_Type;
	}

	public double getW_TFC() {
		return w_TFC;
	}

	public void setW_TFC(double w_TFC) {
		this.w_TFC = w_TFC;
	}

	public double getW_similarity() {
		return w_similarity;
	}

	public void setW_similarity(double w_similarity) {
		this.w_similarity = w_similarity;
	}

	public double getW_Distance() {
		return w_Distance;
	}

	public void setW_Distance(double w_Distance) {
		this.w_Distance = w_Distance;
	}

	public int getClassify() {
		return classify;
	}

	public void setClassify(int classify) {
		this.classify = classify;
	}

	@Override
	public String toString() {
		return "SetenceVSM [id=" + id + ", p_id=" + p_id + ", content="
				+ content + ", w_Position=" + w_Position + ", w_length="
				+ w_length + ", w_Type=" + w_Type + ", w_TFC=" + w_TFC
				+ ", w_similarity=" + w_similarity + ", w_Distance="
				+ w_Distance + ", classify=" + classify + "]";
	}

}
