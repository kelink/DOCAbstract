package main;

import java.io.UnsupportedEncodingException;

import kevin.zhang.NLPIR;

public class TestUserdir {
	public static String sinputString = "好开森，随后温家宝总理就离开了舟曲县城。以上就是今天上午的最新动态";

	public static void main(String[] args) throws UnsupportedEncodingException {
		/**
		 * 初始化
		 */
		NLPIR myNLPIR = new NLPIR();
		String argu = "./";
		System.out.println("NLPIR_Init");
		if (myNLPIR.NLPIR_Init(argu.getBytes("UTF-8"), 1) == false) {
			System.out.println("Init Fail!");
			return;
		}
		/****
		 * 没导入用户字典之前
		 */
		// 导入用户词典前
		byte nativeBytes[] = myNLPIR.NLPIR_ParagraphProcess(
				sinputString.getBytes("UTF-8"), 1);
		String nativeStr = new String(nativeBytes, 0, nativeBytes.length,
				"UTF-8");

		System.out.println("分词结果为： " + nativeStr);

		// 导入用户字典
		int count = myNLPIR
				.NLPIR_ImportUserDict("E:\\android\\android_workplace2\\ICT\\dictionary\\user_dirct.txt"
						.getBytes("UTF-8"));
		System.out.println(count);
		// 新词是识别

		myNLPIR.NLPIR_NWI_Start();
		myNLPIR.NLPIR_NWI_AddFile(sinputString.getBytes("UTF-8"));
		myNLPIR.NLPIR_NWI_Complete();
		nativeBytes = myNLPIR.NLPIR_NWI_GetResult(true);
		nativeStr = new String(nativeBytes, 0, nativeBytes.length, "UTF-8");
		System.out.println("新词识别结果 " + nativeStr);

		// 导入用户词典后
		byte nativeBytes2[] = myNLPIR.NLPIR_ParagraphProcess(
				sinputString.getBytes("UTF-8"), 1);
		String nativeStr2 = new String(nativeBytes2, 0, nativeBytes2.length,
				"UTF-8");

		System.out.println("分词结果为： " + nativeStr2);
	}
}