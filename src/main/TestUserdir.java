package main;

import java.io.UnsupportedEncodingException;

import kevin.zhang.NLPIR;

public class TestUserdir {
	public static String sinputString = "津京战北京连续三场负天津 孙悦砍下21+7+6难救主+新浪体育讯　北京时间12月18日天津消息，2013-2014赛季CBA[微博]联赛第14轮，主场作战的天津队以107-104艰难战胜北京队，收获四连胜的同时，也继续保持对北京队的三连胜。";

	public static void main(String[] args) throws UnsupportedEncodingException {

		NLPIR myNLPIR = new NLPIR();
		String argu = "./";
		System.out.println("NLPIR_Init");
		if (myNLPIR.NLPIR_Init(argu.getBytes("UTF-8"), 1) == false) {
			System.out.println("Init Fail!");
			return;
		}

		// 导入用户字典之后
		int count = myNLPIR.NLPIR_ImportUserDict("./dictionary/user_dirct.txt"
				.getBytes("UTF-8"));

		// 分词
		byte nativeBytes2[] = myNLPIR.NLPIR_ParagraphProcess(
				sinputString.getBytes("UTF-8"), 1);
		String nativeStr2 = new String(nativeBytes2, 0, nativeBytes2.length,
				"UTF-8");

		System.out.println("导入用户字典之后:" + nativeStr2);
	}
}