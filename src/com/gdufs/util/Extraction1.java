package com.gdufs.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.gdufs.classsify.BayesClassify;
import com.gdufs.comment.C;
import com.gdufs.featureExtraction.FeatureExtraction;
import com.gdufs.featureExtraction.TFIDF;
import com.gdufs.featureExtraction.VectorSpacemodel;
import com.gdufs.model.Document;
import com.gdufs.model.Paragraph;
import com.gdufs.model.Setence;
import com.gdufs.model.SetenceVSM;
import com.gdufs.model.Term;
import com.gdufs.segmentation.ExtractSetenceDoc;
import com.gdufs.segmentation.StopWord;

public class Extraction1 {

	private void init() {
		new StopWord(C.FilePath.STOP_WORD_PATH);// 1.初始化停用词
	}

	public static void main(String[] args) {
		String path = "./newstxt/";// 读入文件的路径
		Extraction1 main = new Extraction1();
		main.init();
		ArrayList<Document> documents = main.getDocFromFile(path);
		main.start(documents);
	}

	/**
	 * 2.读入文章
	 * 
	 * @param path
	 * @return
	 */
	private ArrayList<Document> getDocFromFile(String path) {
		ArrayList<Document> documents = new ArrayList<>();
		File[] files = FileUtil.getFilesList(path);
		for (File file : files) {
			documents.add(FileUtil.getADocument(file));
		}
		return documents;
	}

	/**
	 * 开始处理文档
	 * 
	 * @param documents
	 */
	private void start(ArrayList<Document> documents) {
		for (Iterator<Document> iterator = documents.iterator(); iterator
				.hasNext();) {
			Document document = (Document) iterator.next();
			createSetenceVSM(document);
		}
	}

	/**
	 * 文章处理和构造向量
	 * 
	 * @param document
	 */

	private void createSetenceVSM(Document document) {
		// 1分段,分句子,分词
		ExtractSetenceDoc extractSetenceDoc = new ExtractSetenceDoc(document);
		extractSetenceDoc.progress_Data();
		// 2.当前文档字典,段落,句子集合(含标题)
		HashMap<String, Term> dictionary = extractSetenceDoc.getDictionary();
		ArrayList<Paragraph> paragraphs = extractSetenceDoc.getParagraphs();
		ArrayList<Setence> setences = extractSetenceDoc.getSetences();
		// 3.当前文档的频数表示
		ArrayList<ArrayList<Integer>> tFNumList = new ArrayList<>();
		for (Iterator<Setence> iterator = setences.iterator(); iterator
				.hasNext();) {
			Setence setence = (Setence) iterator.next();
			VectorSpacemodel too1 = new VectorSpacemodel(setence, dictionary);
			ArrayList<Integer> temp2 = too1.createTFVSM();// 频数表示的向量
			tFNumList.add(temp2);
		}
		// 4.当前文档的tf*idf表示
		TFIDF creator = new TFIDF(tFNumList, dictionary);
		ArrayList<ArrayList<Double>> tfidf = creator.get_IDFTFSet();
		// 5.当期文档的特征提取
		FeatureExtraction extractor = new FeatureExtraction(setences, tfidf,
				tFNumList, paragraphs);
		extractor.start();
		ArrayList<SetenceVSM> setenceVSMs = extractor.getResultList();
		// 6.训练集写入文件(系统运行前就准备好)
		saveToFile(setenceVSMs, document.getFileName());
		// 输出文摘
		// printDocAbstract(setenceVSMs);
	}

	private void printDocAbstract(ArrayList<SetenceVSM> setenceVSMs) {
		System.out.println(classify(setenceVSMs));
	}

	/***
	 * 输入测试集合进行贝叶斯分类(返回文摘)
	 * 
	 * @param setenceVSMs
	 */
	public String classify(ArrayList<SetenceVSM> setenceVSMs) {
		String trainPath = "./train/1.txt";// 训练集路径
		BayesClassify bayesClassify = new BayesClassify(trainPath);
		String docAbstract = getAbstract(bayesClassify.classify(setenceVSMs));
		return docAbstract;
	}

	/**
	 * 找到最终分类标号为1的句子组进行平滑处理为文摘局
	 * 
	 * @param setenceVSMs
	 * @return
	 */
	private String getAbstract(ArrayList<SetenceVSM> setenceVSMs) {

		ArrayList<SetenceVSM> temp_Abstract = new ArrayList<>();
		for (Iterator<SetenceVSM> iterator = setenceVSMs.iterator(); iterator
				.hasNext();) {
			SetenceVSM setenceVSM = (SetenceVSM) iterator.next();
			if (setenceVSM.getClassify() == 1) {
				temp_Abstract.add(setenceVSM);
			}
		}
		// 输出平滑结果
		return null;
	}

	/**
	 * 写入文件
	 * 
	 * @param setenceVSMs
	 * @param filename
	 */
	public void saveToFile(ArrayList<SetenceVSM> setenceVSMs, String filename) {
		for (SetenceVSM setenceVSM : setenceVSMs) {
			if (setenceVSM.getId() == 0 && setenceVSM.getP_id() == 0) {
				continue;// 跳过标题
			}
			String resultStr = setenceVSM.getContent() + "\n"
					+ setenceVSM.getId() + "\t" + setenceVSM.getW_Position()
					+ "\t" + setenceVSM.getW_length() + "\t"
					+ setenceVSM.getW_Type() + "\t"
					+ setenceVSM.getW_similarity() + "\t"
					+ setenceVSM.getW_TFC() + "\t" + setenceVSM.getW_Distance()
					+ "\t" + setenceVSM.getClassify() + "\n\n\n";
			FileUtil.write2File("./News/" + filename, resultStr, true);

		}
	}
}
