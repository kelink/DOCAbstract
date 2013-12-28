package main;

import java.io.UnsupportedEncodingException;

import kevin.zhang.NLPIR;

import com.gdufs.comment.C;

public class NLPBase {

	/**
	 * NLPIR 中科院分词(编码问题未解决)
	 * 
	 * @param args
	 */
	private String DATA_PARENT_PATH = C.FilePath.DATA_PARENT_PATH;
	private NLPIR myNLPIR;

	public NLPBase() {
		inital();
	}

	/**
	 * 初始化
	 */
	@SuppressWarnings("static-access")
	private void inital() {
		try {
			myNLPIR = new NLPIR();
			String argu = DATA_PARENT_PATH;
			if (myNLPIR.NLPIR_Init(argu.getBytes("UTF-8"), 1) == false) {
				System.out.println("Init Fail!");
				return;
			} else {
				// System.out.println("Init successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Init Fail!");
		}

	}

	/**
	 * 没导入用户字典时候对字符串进行分词
	 * 
	 * @param inputStr
	 * @return
	 */
	public String splitWord(String inputStr, int type) {
		// 导入用户词典前(参数1表示进行磁性标注)
		byte nativeBytes[];
		String nativeStr = null;
		try {
			nativeBytes = myNLPIR.NLPIR_ParagraphProcess(
					inputStr.getBytes("UTF-8"), type);
			nativeStr = new String(nativeBytes, 0, nativeBytes.length, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("分词Exception！");
		}
		return nativeStr;
	}

	// 导入用户字典进行分词
	public String splitWordWithDic(String inputStr, int type)
			throws UnsupportedEncodingException {
		// 导入用户字典之后
		int count = myNLPIR.NLPIR_ImportUserDict("./dictionary/user_dirct.txt"
				.getBytes("UTF-8"));

		// 分词
		byte nativeBytes[] = myNLPIR.NLPIR_ParagraphProcess(
				inputStr.getBytes("UTF-8"), 1);
		String nativeStr = new String(nativeBytes, 0, nativeBytes.length,
				"UTF-8");
		return nativeStr;
	}

	/**
	 * 新词识别
	 * 
	 * @param filePath
	 * @return
	 */

	public String getNewWord(String filePath) {
		byte[] nativeBytes = null;
		String nativeStr = null;
		String argu1 = filePath;// 输入内容的路径
		try {
			// 如果是处理内存，可以调用testNLPIR.NLPIR_GetNewWords
			nativeBytes = myNLPIR.NLPIR_GetFileNewWords(argu1.getBytes("GBK"),
					50, true);
			nativeStr = new String(nativeBytes, 0, nativeBytes.length, "UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("新词识别Exception！");
		}
		System.out.println("新词识别结果为： " + nativeStr);
		return nativeStr;
	}

	/**
	 ************************************************************* 
	 * 获取文件中的关键单词 |如果是处理内存，可以调用testNLPIR.NLPIR_GetKeyWords
	 ******************************************************** 
	 * @param filePath
	 * @return
	 */
	public String getKeyWord(String filePath) {
		byte[] nativeBytes = null;
		String nativeStr = null;
		String argu1 = filePath;// 输入内容的路径

		try {
			nativeBytes = myNLPIR.NLPIR_GetFileKeyWords(argu1.getBytes("GBK"),
					50, true);
			nativeStr = new String(nativeBytes, 0, nativeBytes.length, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("关键词识别Exception！");
		}

		System.out.println("关键词识别结果为： " + nativeStr);
		return nativeStr;
	}

	/********************************************
	 * 处理分词结果到文件中去
	 *******************************************/
	public boolean saveSplitWord2File(String src_FilePath, String dis_FielePath) {
		try {
			myNLPIR.NLPIR_FileProcess(src_FilePath.getBytes("GBK"),
					dis_FielePath.getBytes("UTF-8"), 1);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("saveSplitWord2File 可能是文件路径问题或者不支持编码");
			return false;
		}
	}

	/**
	 * 获取文件中的新词
	 * 
	 * @param filePath
	 * @return
	 */
	public String get_NewWords_From_File(String filePath) {
		String argu1 = filePath;
		byte[] nativeBytes = null;
		String nativeStr = null;
		try {
			myNLPIR.NLPIR_NWI_Start();
			myNLPIR.NLPIR_NWI_AddFile(argu1.getBytes("GBK"));

			myNLPIR.NLPIR_NWI_Complete();

			nativeBytes = myNLPIR.NLPIR_NWI_GetResult(true);
			nativeStr = new String(nativeBytes, 0, nativeBytes.length, "UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("get_NewWords_From_File 出现Exception");
		}
		System.out.println("新词识别结果: " + nativeStr);
		return nativeStr;
	}

	/**
	 * 退出中科院分词系统
	 */
	@SuppressWarnings("static-access")
	public void Exit() {
		try {
			if (myNLPIR != null) {
				myNLPIR.NLPIR_Exit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("myNLPIR 还没初始化");
		}

	}

}
