package com.gdufs.featureExtraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.gdufs.model.Term;

public class TFIDF {

	/**
	 * @param ������Ӽ��Ϲ�һ����tf
	 *            *idf��ʾ
	 */
	private ArrayList<ArrayList<Integer>> tFNumList;// ����Ƶ������
	private HashMap<String, Term> dictionary;// �ֵ�
	private ArrayList<ArrayList<Double>> resultList;// ����������ʾ�� �������

	private ArrayList<ArrayList<Double>> TFSet;// ����TF�ļ���
	private ArrayList<Double> IDFSet;// ����IDF�ļ���

	public TFIDF(ArrayList<ArrayList<Integer>> tFNumList,
			HashMap<String, Term> dictionary) {
		this.tFNumList = tFNumList;
		this.dictionary = dictionary;
		this.resultList = new ArrayList<>();
		this.TFSet = new ArrayList<>();
		this.IDFSet = new ArrayList<>();
		this.init();
	}

	// ��ʼ��
	public void init() {
		this.createTF();
		this.createN_ni();
		this.createTDIDF();
	}

	private void createTF() {
		for (Iterator<ArrayList<Integer>> iterator = tFNumList.iterator(); iterator
				.hasNext();) {
			ArrayList<Integer> item1 = (ArrayList<Integer>) iterator.next();
			TFSet.add(this.calTF(item1));
		}
	}

	// ����Ƶ�ʿռ�����(TF)
	private ArrayList<Double> calTF(ArrayList<Integer> input) {
		ArrayList<Double> list = new ArrayList<Double>();
		int sum = 0;
		for (Iterator<Integer> iterator = input.iterator(); iterator.hasNext();) {
			Integer temp = (Integer) iterator.next();
			sum += temp;
		}
		double item = 0.0;
		for (Iterator<Integer> iterator = input.iterator(); iterator.hasNext();) {
			Integer temp2 = (Integer) iterator.next();
			item = ((double) temp2) / sum;
			list.add(item);
		}
		return list;

	}

	// ���㵥�ʳ��ֵľ�����Ŀ(N/ni)
	private void createN_ni() {
		for (int i = 0; i < dictionary.size(); i++) {
			int sum = 0;
			double result = 0.0;
			for (int j = 0; j < tFNumList.size(); j++) {
				if (tFNumList.get(j).get(i) != 0) {
					++sum;
				}
			}
			for (int j = 0; j < tFNumList.size(); j++) {
				if (sum == 0) {// �������в����ֵ�ǰ����ʱ��
					result = 0.0;
				} else {
					result = ((double) tFNumList.size()) / sum;
				}
			}
			IDFSet.add(result);
		}
	}

	private void createTDIDF() {
		for (Iterator<ArrayList<Double>> iterator = TFSet.iterator(); iterator
				.hasNext();) {
			ArrayList<Double> arrayList = (ArrayList<Double>) iterator.next();
			double weight = 0.0;
			ArrayList<Double> list = new ArrayList<>();
			for (int i = 0; i < arrayList.size(); i++) {
				weight = calTFIDF(arrayList.get(i), IDFSet.get(i));
				list.add(weight);
			}
			resultList.add(list);
		}
		this.toOne();// ��һ��

	}

	// ����TF*IDF
	private double calTFIDF(double tf, double N_ni) {
		if (N_ni == 0.0) {
			return 0.0;
		} else {
			return tf * (Math.log(N_ni) / Math.log(2));
		}
	}

	// ʵ�ֹ�һ��
	public void toOne() {
		for (Iterator<ArrayList<Double>> iterator = resultList.iterator(); iterator
				.hasNext();) {
			ArrayList<Double> item = (ArrayList<Double>) iterator.next();
			double temp = 0.0;
			for (int i = 0; i < item.size(); i++) {
				temp += (item.get(i) * item.get(i));
			}
			System.out.println();
			for (int i = 0; i < item.size(); i++) {
				if (temp == 0.0) {
					item.set(i, 0.0);// ����г��ֵ��ʣ����ٵĸ���Ϊ0.001
					continue;
				} else {
					item.set(i, item.get(i) / Math.sqrt(temp));
				}

			}
		}
	}

	// ���TF����
	public ArrayList<ArrayList<Double>> get_TFSet() {
		return TFSet;
	}

	// ���(N/ni)����
	public ArrayList<Double> get_N_niSet() {
		return IDFSet;
	}

	// ��þ�������TF*idf��ʾ
	public ArrayList<ArrayList<Double>> get_IDFTFSet() {
		return resultList;
	}

}
