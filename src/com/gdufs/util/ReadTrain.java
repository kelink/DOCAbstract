package com.gdufs.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadTrain {

	/**
	 * @param 读取训练集
	 */
	public static int line_num = 4920;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String trainPath = "./news";
		File parentDir = new File(trainPath);
		File[] files = parentDir.listFiles();
		if (files.length > 0) {
			for (File file : files) {
				String content = readTraininstance(file.getAbsolutePath());
				FileUtil.write2File("./train/trainSet.txt", content, true);
			}
			System.out.println(line_num);
		}
	}

	public static String readTraininstance(String path) {
		BufferedReader br = null;
		StringBuilder builder = new StringBuilder();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					path), "UTF-8"));
			String row = "";

			while ((row = br.readLine()) != null) {
				if (row.trim().length() == 0) {
					continue;
				}
				String[] temp = row.split("\t");
				temp[0] = String.valueOf(line_num);
				try {
					if (temp[7] == null) {
						continue;// 当有错误时候跳过当前
					}
				} catch (Exception e) {
					continue;
				}

				++line_num;
				// 重组句子
				for (int i = 0; i < temp.length; i++) {
					builder.append(temp[i] + "\t");
				}
				builder.append("\n");
			}
			return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
