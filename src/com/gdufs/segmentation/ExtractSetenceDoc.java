package com.gdufs.segmentation;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.NLPBase;

import com.gdufs.model.Document;
import com.gdufs.model.Paragraph;
import com.gdufs.model.Setence;
import com.gdufs.model.Term;

public class ExtractSetenceDoc {
	/**
	 * 文本预处理阶段，分词标注和段落，句子划分
	 */
	private Document document;// 文档对象
	private HashMap<String, Term> dictionary;// 字典
	private ArrayList<Paragraph> paragraphs;// 段落集
	private ArrayList<Setence> setences;// 句子集合
	public static int paragraphs_Num = 0;// 段落数目，为后面使用

	public ExtractSetenceDoc(Document document) {
		this.document = document;
		this.dictionary = new HashMap<String, Term>();
		paragraphs = new ArrayList<Paragraph>();
		setences = new ArrayList<Setence>();
	}

	public void progress_Data() {
		// 分段处理(标题为第一段第一句)
		paragraphs = this.splitToparagraph(document.getTitle() + "。\n"
				+ document.getContent());
		// 分句处理
		for (Paragraph paragraph : paragraphs) {
			setences.addAll(this.splitToSetence(paragraph));
		}
		// 分词处理
		ArrayList<Term> list = splitToWord(document.getTitle()
				+ document.getContent());
		// 创建当前文档的字典
		createDictionary(list);

	}

	// 将文本分为段落,处理成为对应的模型
	private ArrayList<Paragraph> splitToparagraph(String content) {
		ArrayList<Paragraph> list = new ArrayList<Paragraph>();
		String[] pa = content.split("\n");
		for (int i = 0; i < pa.length; i++) {
			if (pa[i].trim().equals("")) {
				continue;
			}
			if (pa[i].endsWith("。") || pa[i].endsWith("！")
					|| pa[i].endsWith("”") || pa[i].endsWith("？")
					|| pa[i].endsWith("...")) {
				Paragraph paragraph = new Paragraph();
				paragraph.setID(i);
				paragraph.setContent(pa[i]);
				list.add(paragraph);
			}
		}
		paragraphs_Num = list.size();
		return list;

	}

	// 将段落分为句子,处理成为对应的模型
	public ArrayList<Setence> splitToSetence(Paragraph paragraph) {
		ArrayList<Setence> list = new ArrayList<Setence>();
		String regEx = "[。？！?!]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(paragraph.getContent());
		/* 按照句子结束符分割句子 */
		String[] substrs = p.split(paragraph.getContent());
		/* 将句子结束符连接到相应的句子后 */
		if (substrs.length > 0) {
			int count = 0;
			for (int i = 0; i < substrs.length; i++) {
				if (m.find()) {
					Setence setence = new Setence();
					substrs[count] += m.group();
					setence.setContent(substrs[count]);
					setence.setId(i);
					setence.setP_id(paragraph.getID());
					if (m.group().equals("。") || m.group().equals(".")) {// 判断是否为陈述句
						setence.setType(1);
					}
					list.add(setence);
				}
				count++;
			}
			return list;
		} else {
			return null;
		}

	}

	// 将文本进行分词,处理成为对应的模型
	private ArrayList<Term> splitToWord(String content) {
		ArrayList<Term> list = new ArrayList<Term>();
		NLPBase NLP = new NLPBase();
		String result;
		try {
			result = NLP.splitWordWithDic(content, 1);
			/********** 处理成对应的单词 ************/
			String[] terms = result.split(" ");
			for (String termStr : terms) {
				Term term = new Term();
				String[] tempStrings = termStr.trim().split("/");
				if (!StopWord.isHas(tempStrings[0])) {
					term.setContent(tempStrings[0]);
					term.setType(tempStrings[1]);
					list.add(term);
				}
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			list = null;
		}
		return list;
	}

	// 创建当前文本的字典,处理成为对应的模型
	public void createDictionary(ArrayList<Term> list) {
		for (Iterator<Term> iterator = list.iterator(); iterator.hasNext();) {
			Term term = (Term) iterator.next();
			dictionary.put(term.getContent(), term);
		}
	}

	// 获取当前文档
	public Document getDocument() {
		return document;
	}

	// 获取当前文档字典
	public HashMap<String, Term> getDictionary() {
		return dictionary;
	}

	// 获取当前文档段落
	public ArrayList<Paragraph> getParagraphs() {
		return paragraphs;
	}

	// 获取当前文档的句子集合
	public ArrayList<Setence> getSetences() {
		return setences;
	}

}
