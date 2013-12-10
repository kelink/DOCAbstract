package main;

import kevin.zhang.NLPIR;

public class NLPBase {

	/**
	 * NLPIR
	 * 
	 * @param args
	 */
	public static final String DATA_PARENT_PATH = "./";
	public static NLPIR myNLPIR;

	@SuppressWarnings("static-access")
	public static void inital() {
		try {
			myNLPIR = new NLPIR();

			String argu = DATA_PARENT_PATH;
			System.out.println("NLPIR_Init");
			if (myNLPIR.NLPIR_Init(argu.getBytes("UTF-8"), 1) == false) {
				System.out.println("Init Fail!");
				return;
			} else {
				System.out.println("Init successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
