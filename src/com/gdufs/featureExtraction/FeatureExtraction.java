package com.gdufs.featureExtraction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import com.gdufs.comment.C;
import com.gdufs.model.Paragraph;
import com.gdufs.model.Setence;
import com.gdufs.model.SetenceVSM;

public class FeatureExtraction {

	/**
	 * ����������ȡ
	 */
	private ArrayList<Setence> setences;// ��ǰ�ĵ���ȫ�����Ӽ���
	private ArrayList<ArrayList<Double>> tfidf;// ��ǰ�ĵ���tfidf��ʾ�ļ���
	private ArrayList<ArrayList<Integer>> tf;// ��ǰ�ĵ���tf��ʾ�ļ���
	private ArrayList<Paragraph> paragraphs;// ��ǰ�ĵ��Ķ��伯��

	private ArrayList<Integer> title;// ���±���ı�ʾ
	private ArrayList<Integer> max;// AVG-TFCֵ���ľ���
	private ArrayList<Double> weightList;// Ȩ���б�
	private ArrayList<SetenceVSM> resultList;// ���������������ʾ����

	public FeatureExtraction(ArrayList<Setence> setences,
			ArrayList<ArrayList<Double>> tfidf,
			ArrayList<ArrayList<Integer>> tf, ArrayList<Paragraph> paragraphs) {
		this.setences = setences;
		this.tfidf = tfidf;
		this.tf = tf;
		this.paragraphs = paragraphs;

		this.title = tf.get(0);// tf��tfidf��һ������ʾtitle
		this.resultList = new ArrayList<>();
		this.weightList = getWeightList();
		this.max = this.getMax();
	}

	// �õ�Ȩ���б�
	private ArrayList<Double> getWeightList() {
		ArrayList<Double> result = new ArrayList<>();
		for (Iterator<ArrayList<Double>> iterator = tfidf.iterator(); iterator
				.hasNext();) {
			double weight = 0.0;
			ArrayList<Double> item = (ArrayList<Double>) iterator.next();
			for (int i = 0; i < item.size(); i++) {
				weight += item.get(i);
			}
			result.add(weight / item.size());
		}
		return result;
	}

	// �ҵ����AVG-TFC�ľ���
	private ArrayList<Integer> getMax() {
		ArrayList<Double> temp = new ArrayList<>(weightList);
		Collections.sort(temp);
		double num1 = temp.get(temp.size() - 1);
		if (weightList.size() > 1) {
			double num2 = temp.get(temp.size() - 2);
			if (weightList.get(0) != num1) {
				int index = weightList.indexOf(num1);
				return tf.get(index);
			} else {
				int index = weightList.indexOf(num2);
				return tf.get(index);
			}
		} else {
			return tf.get(0);
		}

	}

	// ��ʼ����
	public void start() {
		int num = 0;// �������
		for (Iterator<Setence> iterator = setences.iterator(); iterator
				.hasNext();) {
			Setence setence = (Setence) iterator.next();
			SetenceVSM model = new SetenceVSM();

			model.setId(setence.getId());
			model.setP_id(setence.getP_id());
			model.setContent(setence.getContent());
			// ��ȡ����ֵ
			double W_Distance = ExtractSimToCenSen(max, tf.get(num));
			double W_TFC = ExtractAVG_TFC(num);
			double W_length = ExtractSetenceLength(setence);
			double W_similarity = ExtractSimToTitle(title, tf.get(num));
			double W_Type = ExtractPresentationOrNot(setence);
			double W_Position = ExtractSetencePosition(setence);
			model.setW_Distance(W_Distance);
			model.setW_TFC(W_TFC);
			model.setW_length(W_length);
			model.setW_Position(W_Position);
			model.setW_similarity(W_similarity);
			model.setW_Type(W_Type);
			// ��ӵ������
			resultList.add(model);
			++num;
		}

	}

	// ��ȡ���������������ʾ����,���ڵ���handle()����֮��
	public ArrayList<SetenceVSM> getResultList() {
		return resultList;
	}

	// ��ȡ����λ������
	private double ExtractSetencePosition(Setence setence) {
		int setenceID = setence.getId();// ����ID
		int setenceP_ID = setence.getP_id();// ����ID
		int paragrahNum = paragraphs.size() - 1;// ������
		int setenceNum = setences.size() - 1;// ��������
		if (setenceID == 0 && setenceP_ID == 1) {
			return C.WeightConst.W_Position1;
		} else if (setenceID == 0) {
			return C.WeightConst.W_Position2;
		} else if ((setenceID == 1 && setenceP_ID == 1)
				|| (setenceID == setenceNum && setenceP_ID == paragrahNum)) {
			return C.WeightConst.W_Position3;
		} else if ((setenceID == 0 && setenceP_ID == 2)
				|| (setenceID == setenceNum)) {
			return C.WeightConst.W_Position4;
		} else {
			return C.WeightConst.W_Position5;
		}

	}

	// ��ȡ���ӳ�������
	private double ExtractSetenceLength(Setence setence) {
		int length = setence.getContent().length();
		if (length > 0 && length < 10) {
			return C.WeightConst.W_Length1;
		} else if (length >= 10 && length < 20) {
			return C.WeightConst.W_Length2;
		} else if (length >= 20 && length < 30) {
			return C.WeightConst.W_Length3;
		} else if (length >= 30 && length < 40) {
			return C.WeightConst.W_Length4;
		} else if (length >= 40 && length < 50) {
			return C.WeightConst.W_Length5;
		} else {
			return C.WeightConst.W_Length6;
		}
	}

	// ��ȡ���ӳ���������
	private double ExtractPresentationOrNot(Setence setence) {
		if (setence.getType() == 1) {
			return C.WeightConst.W_pre;
		} else {
			return C.WeightConst.W_Notpre;
		}

	}

	// ��ȡ����AVG-TFC����
	private double ExtractAVG_TFC(int index) {
		return weightList.get(index);
	}

	/**
	 * ��ȡ���Ӻ����ľ���Sim���� (ʹ�þ��ӵ�Ƶ��ģ��)
	 * 
	 * @param title
	 * @param current
	 * @return
	 */
	private double ExtractSimToTitle(ArrayList<Integer> title,
			ArrayList<Integer> current) {
		return CalCosSimilarity(title, current);
	}

	/**
	 * ��ȡ���Ӻ����ƽ��AVG���ӵ�Distance���� (ʹ�þ��ӵ�Ƶ��ģ��)
	 * 
	 * @param max
	 * @param current
	 * @return
	 */
	private double ExtractSimToCenSen(ArrayList<Integer> max,
			ArrayList<Integer> current) {
		return CalCosSimilarity(max, current);
	}

	/**
	 * �������֮����������ƶ�(ʹ�þ��ӵ�Ƶ��ģ��)
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	private double CalCosSimilarity(ArrayList<Integer> s1, ArrayList<Integer> s2) {
		int sum = 0;
		int m1 = 0;
		double m2 = 0.0;
		for (int i = 0; i < s1.size(); i++) {
			sum += s1.get(i) * s2.get(i);
			m1 += s1.get(i) * s1.get(i);
			m2 += s2.get(i) * s2.get(i);
		}
		return ((double) sum) / (Math.sqrt(m1) * Math.sqrt(m2));
	}

}
