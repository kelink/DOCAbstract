package com.gdufs.comment;

public class C {

	/**
	 * ��Ŷ����ȫ�ֳ���
	 * 
	 * @param args
	 */
	// ϵͳȫ������
	public static final class Global {

	}

	// ϵͳ�ļ�·���Ķ���
	public static final class FilePath {
		public static final String DATA_PARENT_PATH = "./";// �п�Ժ�ִ�ϵͳʹ�õ������õ�Data������
		public static final String STOP_WORD_PATH = "stopwords.txt";// ͣ�ôʵ�·��
		public static final String TRAIN = "./train/trainSet.txt";
		public static final String userDictionary = "./dictionary/user_dirct.tx";// �û��ֵ�
	}

	// ����ģ�͵Ķ��峣��
	public static final class TermConst {
		public static final int TermType_A = 0;// ���ݴ�
		public static final int TermType_V = 1;// ����
		public static final int TermType_N = 2;// ����

	}

	// ����ģ�͵Ķ��峣��
	public static final class SetenceConst {
		// ��������
		public static final int SetenceType_pre = 1;// ������
		public static final int SetenceType_Notpre = 0;// �ǳ�����
	}

	// ������Ȩ��
	public static final class WeightConst {
		public static final double W_pre = 1.0;// ������
		public static final double W_Notpre = 0.0;// �ǳ�����

		public static final double W_Length1 = 0.2;// ����Ȩ��1
		public static final double W_Length2 = 0.4;// ����Ȩ��2
		public static final double W_Length3 = 0.8;// ����Ȩ��3
		public static final double W_Length4 = 0.7;// ����Ȩ��4
		public static final double W_Length5 = 0.4;// ����Ȩ��5
		public static final double W_Length6 = 0.2;// ����Ȩ��6

		public static final double W_Position1 = 1.5;// λ��Ȩ��1(���ӵ�һ�ε�һ��)
		public static final double W_Position2 = 1.0;// λ��Ȩ��2(���Ӷε�һ��)
		public static final double W_Position3 = 0.8;// λ��Ȩ��3(���ӵ�һ�εڶ���/�������һ�����һ��)
		public static final double W_Position4 = 0.6;// λ��Ȩ��4(���ӵڶ��ε�һ��/���Ӷ����һ��)
		public static final double W_Position5 = 0.1;// λ��Ȩ��5(����λ��)
	}

	// ��ժ��ȡ����ֵ
	public static final class Rate {
		public static final double Threshold = 0.1;// ��ժ�Ĵ�ƽ��ֵ�����ֵ֮��İٷ���
	}

}
