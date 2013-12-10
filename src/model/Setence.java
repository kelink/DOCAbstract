package model;

public class Setence {
	/**
	 * 句子Model
	 */
	private int id;// 句子ID
	private Term[] terms;// 单词组
	private int type;// 句子的类型

	public Setence() {
		super();
	}

	public Setence(Term[] terms, int type) {
		super();
		this.terms = terms;
		this.type = type;
	}

	public Setence(int id, Term[] terms, int type) {
		super();
		this.id = id;
		this.terms = terms;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Term[] getTerms() {
		return terms;
	}

	public void setTerms(Term[] terms) {
		this.terms = terms;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
