package com.gdufs.comment;

public class C {

	/**
	 * 存放定义的全局常量
	 * 
	 * @param args
	 */
	// 系统全局设置
	public static final class Global {

	}

	// 系统文件路径的定义
	public static final class FilePath {
		public static final String DATA_PARENT_PATH = "./";// 中科院分词系统使用到的内置的Data的数据
		public static final String STOP_WORD_PATH = "stopwords.txt";// 停用词的路径
		public static final String TRAIN = "./train/trainSet.txt";
		public static final String userDictionary = "./dictionary/user_dirct.tx";// 用户字典
	}

	// 单词模型的定义常量
	public static final class TermConst {
		public static final int TermType_A = 0;// 形容词
		public static final int TermType_V = 1;// 动词
		public static final int TermType_N = 2;// 名词

	}

	// 句子模型的定义常量
	public static final class SetenceConst {
		// 句子类型
		public static final int SetenceType_pre = 1;// 陈述句
		public static final int SetenceType_Notpre = 0;// 非陈述句
	}

	// 特征的权重
	public static final class WeightConst {
		public static final double W_pre = 1.0;// 陈述句
		public static final double W_Notpre = 0.0;// 非陈述句

		public static final double W_Length1 = 0.2;// 长度权重1
		public static final double W_Length2 = 0.4;// 长度权重2
		public static final double W_Length3 = 0.8;// 长度权重3
		public static final double W_Length4 = 0.7;// 长度权重4
		public static final double W_Length5 = 0.4;// 长度权重5
		public static final double W_Length6 = 0.2;// 长度权重6

		public static final double W_Position1 = 1.5;// 位置权重1(句子第一段第一句)
		public static final double W_Position2 = 1.0;// 位置权重2(句子段第一句)
		public static final double W_Position3 = 0.8;// 位置权重3(句子第一段第二句/句子最后一段最后一句)
		public static final double W_Position4 = 0.6;// 位置权重4(句子第二段第一句/句子段最后一句)
		public static final double W_Position5 = 0.1;// 位置权重5(其他位置)
	}

	// 文摘提取的阈值
	public static final class Rate {
		public static final double Threshold = 0.1;// 文摘的从平均值到最大值之间的百分数
	}

}
