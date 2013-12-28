package com.gdufs.model;

import java.util.ArrayList;

public class IndexTableItem {

	/**
	 * 倒排索引表每项
	 * 
	 * @param 形式如同
	 *            ： 单词1 TF{1,0,6,4...} NI:6
	 */
	private Term term;// 单词
	private ArrayList<Integer> TF;// 该词语在全部句子出现的次数
	private int NI;// 该词语出现的句子数目

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
