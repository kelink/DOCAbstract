package model;

public class IndexTable {

	/**
	 * 倒排索引表
	 * 
	 * @param 形式如同： 单词1 TF{1,0,6,4...} NI:6
	 */
	private int termID;// 词的ID
	private int[] TF;// 该词语在全部句子出现的次数
	private int NI;// 该词语出现的句子数目

	public IndexTable() {
		super();
	}

	public IndexTable(int termID, int[] tF, int nI) {
		super();
		this.termID = termID;
		TF = tF;
		NI = nI;
	}

	public int getTermID() {
		return termID;
	}

	public void setTermID(int termID) {
		this.termID = termID;
	}

	public int[] getTF() {
		return TF;
	}

	public void setTF(int[] tF) {
		TF = tF;
	}

	public int getNI() {
		return NI;
	}

	public void setNI(int nI) {
		NI = nI;
	}

}
