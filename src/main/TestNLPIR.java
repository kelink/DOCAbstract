package main;

import kevin.zhang.NLPIR;

public class TestNLPIR {

	public static void main(String[] args) throws Exception {
		try {
			String sInput = "�Ż�ƽ�Ƴ���NLPIR�ִ�ϵͳ������ICTCLAS2013�������´�ʶ�𡢹ؼ�����ȡ��΢���ִʹ��ܡ�";

			// ����Ӧ�ִ�
			test(sInput);

		} catch (Exception ex) {
		}

	}

	@SuppressWarnings("static-access")
	public static void test(String sInput) {
		try {
			NLPIR testNLPIR = new NLPIR();

			String argu = "./";
			System.out.println("NLPIR_Init");
			if (testNLPIR.NLPIR_Init(argu.getBytes("UTF-8"), 1) == false) {
				System.out.println("Init Fail!");
				return;
			}

			// �����û��ʵ�ǰ
			byte nativeBytes[] = testNLPIR.NLPIR_ParagraphProcess(
					sInput.getBytes("UTF-8"), 1);
			String nativeStr = new String(nativeBytes, 0, nativeBytes.length,
					"UTF-8");

			System.out.println("�ִʽ��Ϊ�� " + nativeStr);

			// ��ʼ���ִ����
			String argu1 = "./test/test-utf8.TXT";
			String argu2 = "./test/test_result1.TXT";

			nativeBytes = testNLPIR.NLPIR_GetFileNewWords(
					argu1.getBytes("UTF-8"), 50, true);
			// ����Ǵ����ڴ棬���Ե���testNLPIR.NLPIR_GetNewWords
			nativeStr = new String(nativeBytes, 0, nativeBytes.length, "UTF-8");
			System.out.println("�´�ʶ����Ϊ�� " + nativeStr);

			/************************************************
			 * ��ȡ�ļ��еĹؼ����� |����Ǵ����ڴ棬���Ե���testNLPIR.NLPIR_GetKeyWords
			 ********************************************/
			nativeBytes = testNLPIR.NLPIR_GetFileKeyWords(
					argu1.getBytes("UTF-8"), 50, true);

			nativeStr = new String(nativeBytes, 0, nativeBytes.length, "UTF-8");
			System.out.println("�ؼ���ʶ����Ϊ�� " + nativeStr);

			/********************************************
			 * �����µ���
			 *******************************************/
			testNLPIR.NLPIR_FileProcess(argu1.getBytes("UTF-8"),
					argu2.getBytes("UTF-8"), 1);

			testNLPIR.NLPIR_NWI_Start();
			testNLPIR.NLPIR_NWI_AddFile(argu1.getBytes("UTF-8"));

			testNLPIR.NLPIR_NWI_Complete();

			nativeBytes = testNLPIR.NLPIR_NWI_GetResult(true);
			String nativeStr3 = new String(nativeBytes, 0, nativeBytes.length,
					"UTF-8");

			System.out.println("�´�ʶ����333 " + nativeStr3);

			int n = testNLPIR.NLPIR_NWI_Result2UserDict();// �´�ʶ����
			System.out.println("n=" + n);
			argu2 = "./test/test_result2.TXT";
			// testNLPIR.NLPIR_FileProcess(argu1.getBytes("UTF-8"),
			// argu2.getBytes("UTF-8"), 1);

			testNLPIR.NLPIR_Exit();
		} catch (Exception ex) {
		}
	}
}