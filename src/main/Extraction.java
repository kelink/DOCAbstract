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
	private ArrayList<Document> documents;// �ĵ�����
	private ArrayList<String> docAbstraces;// ���µ���ժ����

	public Extraction(ArrayList<Document> documents) {
		this.documents = documents;
		this.docAbstraces = new ArrayList<>();
		init();
	}

	private void init() {
		new StopWord(C.FilePath.STOP_WORD_PATH);// 1.��ʼ��ͣ�ô�
	}

	/**
	 * ��ʼ�����ĵ�����
	 * 
	 * @param documents
	 */
	public void start() {
		for (Iterator<Document> iterator = documents.iterator(); iterator
				.hasNext();) {
			Document document = (Document) iterator.next();
			ArrayList<SetenceVSM> setenceVSMs = createSetenceVSM(document);// ����VSMģ��
			ArrayList<SetenceVSM> classfiedVsms = classify(setenceVSMs);// ����
			String docAbstract = getAbstract(classfiedVsms);// ��ȡ��ժ
			System.out.println(classfiedVsms);
			System.out.println("��ժΪ��" + docAbstract);// �����ժ
			docAbstraces.add(docAbstract);
		}

	}

	/**
	 * ���´���͹�������
	 * 
	 * @param document
	 */

	private ArrayList<SetenceVSM> createSetenceVSM(Document document) {
		// 1�ֶ�,�־���,�ִ�
		ExtractSetenceDoc extractSetenceDoc = new ExtractSetenceDoc(document);
		extractSetenceDoc.progress_Data();
		// 2.��ǰ�ĵ��ֵ�,����,���Ӽ���(������)
		HashMap<String, Term> dictionary = extractSetenceDoc.getDictionary();
		ArrayList<Paragraph> paragraphs = extractSetenceDoc.getParagraphs();
		ArrayList<Setence> setences = extractSetenceDoc.getSetences();
		// 3.��ǰ�ĵ���Ƶ����ʾ
		ArrayList<ArrayList<Integer>> tFNumList = new ArrayList<>();
		for (Iterator<Setence> iterator = setences.iterator(); iterator
				.hasNext();) {
			Setence setence = (Setence) iterator.next();
			VectorSpacemodel too1 = new VectorSpacemodel(setence, dictionary);
			ArrayList<Integer> temp2 = too1.createTFVSM();// Ƶ����ʾ������
			tFNumList.add(temp2);
		}
		// 4.��ǰ�ĵ���tf*idf��ʾ
		TFIDF creator = new TFIDF(tFNumList, dictionary);
		ArrayList<ArrayList<Double>> tfidf = creator.get_IDFTFSet();
		// 5.�����ĵ���������ȡ
		FeatureExtraction extractor = new FeatureExtraction(setences, tfidf,
				tFNumList, paragraphs);
		extractor.start();
		ArrayList<SetenceVSM> setenceVSMs = extractor.getResultList();
		return setenceVSMs;
	}

	/***
	 * ������Լ��Ͻ��б�Ҷ˹����(���ر�źõ���ժ)
	 * 
	 * @param setenceVSMs
	 */
	private ArrayList<SetenceVSM> classify(ArrayList<SetenceVSM> setenceVSMs) {
		String trainPath = C.FilePath.TRAIN;// ѵ����·��
		BayesClassify bayesClassify = new BayesClassify(trainPath);
		ArrayList<SetenceVSM> classfiedVsms = bayesClassify
				.classify(setenceVSMs);
		return classfiedVsms;

	}

	/**
	 * ��ȡ��ժ����
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
	 * ��ȡ��ժ���Ӽ���
	 * 
	 * @return
	 */
	public ArrayList<String> getDocAbstraces() {
		return docAbstraces;
	}
}
