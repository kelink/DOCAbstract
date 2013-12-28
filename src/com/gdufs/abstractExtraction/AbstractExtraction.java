package com.gdufs.abstractExtraction;

import java.util.ArrayList;
import java.util.Iterator;

import com.gdufs.comment.C;
import com.gdufs.model.SetenceVSM;

public class AbstractExtraction {
	private ArrayList<SetenceVSM> bayesSetenceVSMs;// 贝叶斯已经标号类别的初步文摘
	private ArrayList<SetenceVSM> docAbstract;// 内容摘要
	private ArrayList<Double> weightList;// 权重列表
	public static double result_Rate = 0;// 文摘句子 抽取比率

	public AbstractExtraction(ArrayList<SetenceVSM> bayesSetenceVSMs) {
		this.bayesSetenceVSMs = bayesSetenceVSMs;
		weightList = new ArrayList<>();
		docAbstract = new ArrayList<>();
	}

	/**
	 * 开始处理
	 */
	public void start() {
		this.handle();
	}

	/**
	 * 处理并且获取文摘：权重前百分之n的为文摘句子，如果归类为文摘句子，但是权重低于平均值的列为非文摘局
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
			// 判断提取比率
			if (docAbstract.size() < Math.ceil(bayesSetenceVSMs.size()
					* result_Rate)) {
				if (weightList.get(i) > com_Weight) {
					docAbstract.add(bayesSetenceVSMs.get(i));
					continue;// 如果已经加入则跳到下次循环
				}
				if (bayesSetenceVSMs.get(i).getClassify() == 1
						&& weightList.get(i) > avg_Weight) {
					docAbstract.add(bayesSetenceVSMs.get(i));
					continue;// 如果已经加入则跳到下次循环
				}
			}
		}
	}

	/**
	 * 获取前百分之n的权重数值
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
			weightList.add(temp);// 计算全部的权重
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
	 * 获取文摘 平滑 1.从没进行分类之前进行加权线性拟合,值大于某阈值n这归为文摘句子 2.结合分类标号拟合决策最终结果
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
