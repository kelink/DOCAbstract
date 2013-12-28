package com.gdufs.abstractExtraction;

import java.util.ArrayList;
import java.util.Iterator;

import com.gdufs.comment.C;
import com.gdufs.model.SetenceVSM;

public class AbstractExtraction {
	private ArrayList<SetenceVSM> bayesSetenceVSMs;// ��Ҷ˹�Ѿ�������ĳ�����ժ
	private ArrayList<SetenceVSM> docAbstract;// ����ժҪ
	private ArrayList<Double> weightList;// Ȩ���б�
	public static double result_Rate = 0;// ��ժ���� ��ȡ����

	public AbstractExtraction(ArrayList<SetenceVSM> bayesSetenceVSMs) {
		this.bayesSetenceVSMs = bayesSetenceVSMs;
		weightList = new ArrayList<>();
		docAbstract = new ArrayList<>();
	}

	/**
	 * ��ʼ����
	 */
	public void start() {
		this.handle();
	}

	/**
	 * �����һ�ȡ��ժ��Ȩ��ǰ�ٷ�֮n��Ϊ��ժ���ӣ��������Ϊ��ժ���ӣ�����Ȩ�ص���ƽ��ֵ����Ϊ����ժ��
	 */
	private void handle() {
		double[] weight = getCom_Weight();
		double avg_Weight = weight[0];
		double com_Weight = weight[1];
		for (int i = 0; i < bayesSetenceVSMs.size(); i++) {
			if (bayesSetenceVSMs.size() == 1) {
				docAbstract.add(bayesSetenceVSMs.get(0));
				return;
			}
			// �ж���ȡ����
			if (docAbstract.size() < Math.ceil(bayesSetenceVSMs.size()
					* result_Rate)) {
				if (weightList.get(i) > com_Weight) {
					docAbstract.add(bayesSetenceVSMs.get(i));
					continue;// ����Ѿ������������´�ѭ��
				}
				if (bayesSetenceVSMs.get(i).getClassify() == 1
						&& weightList.get(i) > avg_Weight) {
					docAbstract.add(bayesSetenceVSMs.get(i));
					continue;// ����Ѿ������������´�ѭ��
				}
			}
		}
	}

	/**
	 * ��ȡǰ�ٷ�֮n��Ȩ����ֵ
	 * 
	 * @return
	 */
	private double[] getCom_Weight() {
		double avg_Position = 0.0;
		double avg_Length = 0.0;
		double avg_Type = 0.0;
		double avg_Sim = 0.0;
		double avg_TFC = 0.0;
		double avg_Dis = 0.0;
		double avg_weight = 0.0;
		double max_weight = 0.0;
		double[] result = new double[2];
		double temp = 0.0;
		for (Iterator<SetenceVSM> iterator = bayesSetenceVSMs.iterator(); iterator
				.hasNext();) {
			SetenceVSM or1 = (SetenceVSM) iterator.next();
			avg_Position += or1.getW_Distance();
			avg_Length += or1.getW_Distance();
			avg_Type += or1.getW_Type();
			avg_Dis += or1.getW_Distance();
			avg_Sim += or1.getW_similarity();
			avg_TFC += or1.getW_TFC();
			temp = or1.getW_Distance() + or1.getW_Distance() + or1.getW_Type()
					+ or1.getW_Distance() + or1.getW_similarity()
					+ or1.getW_TFC();
			weightList.add(temp);// ����ȫ����Ȩ��
			if (temp > max_weight) {
				max_weight = temp;
			}
		}
		avg_weight = (avg_Position + avg_Length + avg_Type + avg_Sim + avg_TFC + avg_Dis)
				/ bayesSetenceVSMs.size();
		result[0] = avg_weight;
		result[1] = avg_weight + (max_weight - avg_weight)
				* (1 - C.Rate.Threshold);
		return result;
	}

	/**
	 * ��ȡ��ժ ƽ�� 1.��û���з���֮ǰ���м�Ȩ�������,ֵ����ĳ��ֵn���Ϊ��ժ���� 2.��Ϸ�������Ͼ������ս��
	 */
	public String getAbstract() {
		StringBuffer buffer = new StringBuffer();
		for (Iterator<SetenceVSM> iterator = docAbstract.iterator(); iterator
				.hasNext();) {
			SetenceVSM setenceVSM = (SetenceVSM) iterator.next();
			buffer.append(setenceVSM.getContent().trim() + "\n");
		}
		return buffer.toString();
	}

}
