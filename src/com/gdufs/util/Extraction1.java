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
		new StopWord(C.FilePath.STOP_WORD_PATH);// 1.��ʼ��ͣ�ô�
	}

	public static void main(String[] args) {
		String path = "./newstxt/";// �����ļ���·��
		Extraction1 main = new Extraction1();
		main.init();
		ArrayList<Document> documents = main.getDocFromFile(path);
		main.start(documents);
	}

	/**
	 * 2.��������
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
	 * ��ʼ�����ĵ�
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
	 * ���´���͹�������
	 * 
	 * @param document
	 */

	private void createSetenceVSM(Document document) {
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
		// 6.ѵ����д���ļ�(ϵͳ����ǰ��׼����)
		saveToFile(setenceVSMs, document.getFileName());
		// �����ժ
		// printDocAbstract(setenceVSMs);
	}

	private void printDocAbstract(ArrayList<SetenceVSM> setenceVSMs) {
		System.out.println(classify(setenceVSMs));
	}

	/***
	 * ������Լ��Ͻ��б�Ҷ˹����(������ժ)
	 * 
	 * @param setenceVSMs
	 */
	public String classify(ArrayList<SetenceVSM> setenceVSMs) {
		String trainPath = "./train/1.txt";// ѵ����·��
		BayesClassify bayesClassify = new BayesClassify(trainPath);
		String docAbstract = getAbstract(bayesClassify.classify(setenceVSMs));
		return docAbstract;
	}

	/**
	 * �ҵ����շ�����Ϊ1�ľ��������ƽ������Ϊ��ժ��
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
		// ���ƽ�����
		return null;
	}

	/**
	 * д���ļ�
	 * 
	 * @param setenceVSMs
	 * @param filename
	 */
	public void saveToFile(ArrayList<SetenceVSM> setenceVSMs, String filename) {
		for (SetenceVSM setenceVSM : setenceVSMs) {
			if (setenceVSM.getId() == 0 && setenceVSM.getP_id() == 0) {
				continue;// ��������
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
