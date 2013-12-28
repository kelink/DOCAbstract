package com.gdufs.featureExtraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.gdufs.model.Term;

public class TFIDF {

	/**
	 * @param 计算句子集合归一化的tf
	 *            *idf表示
	 */
	private ArrayList<ArrayList<Integer>> tFNumList;// 句子频数集合
	private HashMap<String, Term> dictionary;// 字典
	private ArrayList<ArrayList<Double>> resultList;// 句子特征表示的 结果集合

	private ArrayList<ArrayList<Double>> TFSet;// 句子TF的集合
	private ArrayList<Double> IDFSet;// 句子IDF的集合

	public TFIDF(ArrayList<ArrayList<Integer>> tFNumList,
			HashMap<String, Term> dictionary) {
		this.tFNumList = tFNumList;
		this.dictionary = dictionary;
		this.resultList = new ArrayList<>();
		this.TFSet = new ArrayList<>();
		this.IDFSet = new ArrayList<>();
		this.init();
	}

	// 初始化
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

	// 生成频率空间向量(TF)
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

	// 计算单词出现的句子数目(N/ni)
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
				if (sum == 0) {// 当句子中不出现当前词语时候
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
		this.toOne();// 归一化

	}

	// 计算TF*IDF
	private double calTFIDF(double tf, double N_ni) {
		if (N_ni == 0.0) {
			return 0.0;
		} else {
			return tf * (Math.log(N_ni) / Math.log(2));
		}
	}

	// 实现归一化
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
					item.set(i, 0.0);// 如果有出现单词，最少的概率为0.001
					continue;
				} else {
					item.set(i, item.get(i) / Math.sqrt(temp));
				}

			}
		}
	}

	// 获得TF集合
	public ArrayList<ArrayList<Double>> get_TFSet() {
		return TFSet;
	}

	// 获得(N/ni)集合
	public ArrayList<Double> get_N_niSet() {
		return IDFSet;
	}

	// 获得句子特征TF*idf表示
	public ArrayList<ArrayList<Double>> get_IDFTFSet() {
		return resultList;
	}

}
