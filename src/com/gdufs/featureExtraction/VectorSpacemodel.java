package com.gdufs.featureExtraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.gdufs.model.Setence;
import com.gdufs.model.Term;

public class VectorSpacemodel {

	/**
	 * 处理VSM模型(频数表示)
	 */

	private Setence setence;// 句子
	private HashMap<String, Term> dictionary;// 字典
	private int counterTF = 0;// 统计数目
	private ArrayList<Integer> tFNumList;// 频数表示的向量

	public VectorSpacemodel(Setence setence, HashMap<String, Term> dictionary) {
		this.setence = setence;
		this.dictionary = dictionary;
		tFNumList = new ArrayList<>();
	}

	// 生成频数表示的向量空间模型
	public ArrayList<Integer> createTFVSM() {
		Iterator<?> iter = dictionary.entrySet().iterator();
		while (iter.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			counterTF = 0;// 清0
			counterTF = countNumbers(setence.getContent(), key);
			tFNumList.add(counterTF);
		}
		return tFNumList;

	}

	// 递归统计单词
	private int countNumbers(String content, String key) {
		if (content.indexOf(key) == -1) {
			return 0;
		} else if (content.indexOf(key) != -1) {
			counterTF++;
			countNumbers(
					content.substring(content.indexOf(key) + key.length()), key);
			return counterTF;
		}
		return 0;
	}

}
