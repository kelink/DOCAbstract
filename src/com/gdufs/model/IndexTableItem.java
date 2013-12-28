package com.gdufs.model;

import java.util.ArrayList;

public class IndexTableItem {

	/**
	 * ����������ÿ��
	 * 
	 * @param ��ʽ��ͬ
	 *            �� ����1 TF{1,0,6,4...} NI:6
	 */
	private Term term;// ����
	private ArrayList<Integer> TF;// �ô�����ȫ�����ӳ��ֵĴ���
	private int NI;// �ô�����ֵľ�����Ŀ

	public IndexTableItem() {
		super();
	}

	public IndexTableItem(Term term, ArrayList<Integer> tF, int nI) {
		super();
		this.term = term;
		TF = tF;
		NI = nI;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public ArrayList<Integer> getTF() {
		return TF;
	}

	public void setTF(ArrayList<Integer> tF) {
		TF = tF;
	}

	public int getNI() {
		return NI;
	}

	public void setNI(int nI) {
		NI = nI;
	}

}
