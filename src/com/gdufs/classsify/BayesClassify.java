package com.gdufs.classsify;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.gdufs.model.SetenceVSM;

public class BayesClassify {

	/**
	 * 贝叶斯分类 算法:
	 * 
	 * 输入：类标号未知的样本X={x1,x2,x3...,xn} 输出：未知样本X所属的标号 (1)for j=1 to n
	 * (2)计算X属于每一个类别的概率P(X|Cj)=P(X1|Cj)*P(X2|Cj)...*P(Xn|Cj) (3) 计算训练集中每个类别Cj的概率
	 * (4) 计算概率值w=P(X|Cj)*P(Cj) (5)end for (6)选择计算概率w中最大的类标作为输出
	 * 
	 */
	private ArrayList<SetenceVSM> trainSetences;// 训练集中读入的句子
	private int num_Of_Yes = 0;// 训练集和中表示为文摘句子的数目
	private int num_Of_No = 0;// 训练集和中表示为非文摘句子的数目

	public BayesClassify(String path) {
		// 读取文件，初始化trainSetence
		trainSetences = new ArrayList<SetenceVSM>();
		readTraininstance(path);
	}

	/**
	 * 计算最高后验证概率P(X|Ci)*P(Ci)
	 * 
	 * @param setences
	 */
	public ArrayList<SetenceVSM> classify(ArrayList<SetenceVSM> SetenceVSMs) {
		double yes = ((double) num_Of_Yes) / (num_Of_No + num_Of_Yes);
		double no = ((double) num_Of_No) / (num_Of_No + num_Of_Yes);
		for (SetenceVSM setenceVSM : SetenceVSMs) {
			double[] postion = DealDiscrete(setenceVSM.getW_Position(), 0);
			double[] length = DealDiscrete(setenceVSM.getW_length(), 1);
			double[] type = DealDiscrete(setenceVSM.getW_Type(), 2);
			// 分为10段进行离散化
			double[] sim = DealContinuous(setenceVSM.getW_similarity(), 10, 0);
			double[] TFC = DealContinuous(setenceVSM.getW_TFC(), 10, 1);
			double[] Dis = DealContinuous(setenceVSM.getW_Distance(), 10, 2);
			double P_no = postion[0] * length[0] * type[0] * sim[0] * TFC[0]
					* Dis[0] * no;
			double P_yes = postion[1] * length[1] * type[1] * sim[1] * TFC[1]
					* Dis[1] * yes;
			System.out.println("no的概率：" + P_no + "yes的概率：" + P_yes);
			if (P_yes > P_no) {
				setenceVSM.setClassify(1);
			} else {
				setenceVSM.setClassify(0);
			}
		}
		return SetenceVSMs;

	}

	/**
	 * 读取训练集，统计文摘和非文摘句子总数 1.句子的VSM 模型：id,位置，长度，句型，AVG-TFC，相似度，中心距离（类标） 句子的VSM
	 * 2.词语之间按照tab键进行分割
	 * 
	 * @param path
	 */

	private void readTraininstance(String path) {

		BufferedReader br = null;
		int calssify;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					path), "UTF-8"));
			String row = "";

			while ((row = br.readLine()) != null) {
				if (row.trim().length() == 0) {
					continue;
				}
				SetenceVSM setenceVSM = new SetenceVSM();
				String[] temp = row.split("\t");
				setenceVSM.setId(Integer.parseInt(temp[0]));
				setenceVSM.setW_Position(Double.parseDouble(temp[1]));
				setenceVSM.setW_length(Double.parseDouble(temp[2]));
				setenceVSM.setW_Type(Double.parseDouble(temp[3]));
				setenceVSM.setW_similarity(Double.parseDouble(temp[4]));
				setenceVSM.setW_TFC(Double.parseDouble(temp[5]));
				setenceVSM.setW_Distance(Double.parseDouble(temp[6]));
				calssify = Integer.parseInt(temp[7]);
				if (calssify == 0) {
					++num_Of_No;
				}
				if (calssify == 1) {
					++num_Of_Yes;
				}
				if (calssify == -1) {
					System.out.println("有不明类表的项在训练集中，id为"
							+ Integer.parseInt(temp[0]));
				}
				setenceVSM.setClassify(calssify);
				trainSetences.add(setenceVSM);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 处理离散属性(位置，长度，句型),0表示位置，1表示长度，2标书句型
	 * 
	 * @param value
	 * @param type
	 * @return
	 */
	private double[] DealDiscrete(double value, int type) {
		int yes_num = 0;// 统计个数
		int no_num = 0;// 统计个数
		for (int i = 0; i < trainSetences.size(); i++) {
			switch (type) {
			case 0:
				if (trainSetences.get(i).getW_Position() == value) {
					if (trainSetences.get(i).getClassify() == 1) {
						++yes_num;
					}
					if (trainSetences.get(i).getClassify() == 0) {
						++no_num;
					}
				}
				break;
			case 1:
				if (trainSetences.get(i).getW_length() == value) {
					if (trainSetences.get(i).getClassify() == 1) {
						++yes_num;
					}
					if (trainSetences.get(i).getClassify() == 0) {
						++no_num;
					}
				}
				break;
			case 2:
				if (trainSetences.get(i).getW_Type() == value) {
					if (trainSetences.get(i).getClassify() == 1) {
						++yes_num;
					}
					if (trainSetences.get(i).getClassify() == 0) {
						++no_num;
					}
				}
				break;
			}

		}
		return new double[] { (((double) no_num) + 1) / (num_Of_No + 1),
				(((double) yes_num) + 1) / (num_Of_Yes + 1) };

	}

	/**
	 * 处理连续属性(0:相识度，1:AVG-TFC；2：Distance) (对属性进行离散化)
	 * 
	 * @param value
	 * @param n
	 * @param type
	 * @return
	 */
	private double[] DealContinuous(double value, int n, int type) {
		int yes_num = 0;// 统计个数
		int no_num = 0;// 统计个数
		for (int i = 0; i < n; i++) {
			if (value >= i * (1.0 / n) && value < (i + 1) * (1.0 / n)) {// 判断区间范围
				for (int j = 0; j < trainSetences.size(); j++) {
					switch (type) {
					case 0:
						if (trainSetences.get(j).getW_similarity() >= (i * (1.0 / n))
								&& trainSetences.get(j).getW_similarity() < ((i + 1) * (1.0 / n))) {
							if (trainSetences.get(j).getClassify() == 1) {
								++yes_num;

							}
							if (trainSetences.get(j).getClassify() == 0) {
								++no_num;
							}
						}
						break;
					case 1:
						if (trainSetences.get(j).getW_TFC() >= (i * (1.0 / n))
								&& trainSetences.get(j).getW_TFC() < ((i + 1) * (1.0 / n))) {
							if (trainSetences.get(j).getClassify() == 1) {
								++yes_num;
							}
							if (trainSetences.get(j).getClassify() == 0) {
								++no_num;
							}
						}
						break;
					case 2:
						if (trainSetences.get(j).getW_Distance() >= (i * (1.0 / n))
								&& trainSetences.get(j).getW_Distance() < ((i + 1) * (1.0 / n))) {
							if (trainSetences.get(j).getClassify() == 1) {
								++yes_num;
							}
							if (trainSetences.get(j).getClassify() == 0) {
								++no_num;
							}
						}
						break;
					default:
						break;
					}
				}
			} else {
				continue;
			}

		}
		return new double[] { (((double) no_num) + 1) / (num_Of_No + 1),
				(((double) yes_num) + 1) / (num_Of_Yes + 1) };

	}
}
