package com.gdufs.featureExtraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.gdufs.model.Setence;
import com.gdufs.model.Term;

public class VectorSpacemodel {

	/**
	 * ����VSMģ��(Ƶ����ʾ)
	 */

	private Setence setence;// ����
	private HashMap<String, Term> dictionary;// �ֵ�
	private int counterTF = 0;// ͳ����Ŀ
	private ArrayList<Integer> tFNumList;// Ƶ����ʾ������

	public VectorSpacemodel(Setence setence, HashMap<String, Term> dictionary) {
		this.setence = setence;
		this.dictionary = dictionary;
		tFNumList = new ArrayList<>();
	}

	// ����Ƶ����ʾ�������ռ�ģ��
	public ArrayList<Integer> createTFVSM() {
		Iterator<?> iter = dictionary.entrySet().iterator();
		while (iter.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			counterTF = 0;// ��0
			counterTF = countNumbers(setence.getContent(), key);
			tFNumList.add(counterTF);
		}
		return tFNumList;

	}

	// �ݹ�ͳ�Ƶ���
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
