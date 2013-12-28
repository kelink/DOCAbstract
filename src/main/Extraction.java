package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.gdufs.abstractExtraction.AbstractExtraction;
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

public class Extraction {
	private ArrayList<Document> documents;// 文档对象
	private ArrayList<String> docAbstraces;// 文章的文摘集合

	public Extraction(ArrayList<Document> documents) {
		this.documents = documents;
		this.docAbstraces = new ArrayList<>();
		init();
	}

	private void init() {
		new StopWord(C.FilePath.STOP_WORD_PATH);// 1.初始化停用词
	}

	/**
	 * 开始处理文档集合
	 * 
	 * @param documents
	 */
	public void start() {
		for (Iterator<Document> iterator = documents.iterator(); iterator
				.hasNext();) {
			Document document = (Document) iterator.next();
			ArrayList<SetenceVSM> setenceVSMs = createSetenceVSM(document);// 构建VSM模型
			ArrayList<SetenceVSM> classfiedVsms = classify(setenceVSMs);// 分类
			String docAbstract = getAbstract(classfiedVsms);// 提取文摘
			System.out.println(classfiedVsms);
			System.out.println("文摘为：" + docAbstract);// 输出文摘
			docAbstraces.add(docAbstract);
		}

	}

	/**
	 * 文章处理和构造向量
	 * 
	 * @param document
	 */

	private ArrayList<SetenceVSM> createSetenceVSM(Document document) {
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
		return setenceVSMs;
	}

	/***
	 * 输入测试集合进行贝叶斯分类(返回标号好的文摘)
	 * 
	 * @param setenceVSMs
	 */
	private ArrayList<SetenceVSM> classify(ArrayList<SetenceVSM> setenceVSMs) {
		String trainPath = C.FilePath.TRAIN;// 训练集路径
		BayesClassify bayesClassify = new BayesClassify(trainPath);
		ArrayList<SetenceVSM> classfiedVsms = bayesClassify
				.classify(setenceVSMs);
		return classfiedVsms;

	}

	/**
	 * 获取文摘句子
	 * 
	 * @param setenceVSMs
	 * @return
	 */
	private String getAbstract(ArrayList<SetenceVSM> setenceVSMs) {
		AbstractExtraction abstractExtraction = new AbstractExtraction(
				setenceVSMs);
		abstractExtraction.start();
		String docAbstract = abstractExtraction.getAbstract();
		return docAbstract;
	}

	/**
	 * 获取文摘句子集合
	 * 
	 * @return
	 */
	public ArrayList<String> getDocAbstraces() {
		return docAbstraces;
	}
}
