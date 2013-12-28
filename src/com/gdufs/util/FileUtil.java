package com.gdufs.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.gdufs.model.Document;

public class FileUtil {
	/**
	 * 文件助手类
	 */

	/**
	 * 获取当前目录下的全部文件对象
	 * 
	 * @return File[]
	 */
	public static File[] getFilesList(String path) {
		File parentDir = new File(path);
		File[] files = parentDir.listFiles();
		if (files.length > 0) {
			return files;
		} else {
			return null;
		}
	}

	/**
	 * 将一个File 对象转化为一个Document对象
	 * 
	 * @return Document
	 * @throws IOException
	 */
	public static Document getADocument(File file) {
		String fileName = file.getName();
		String title = "";
		String content = "";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					file), "GBK"));
			String row = "";
			StringBuilder builder = new StringBuilder();

			int i = 0;
			while ((row = br.readLine()) != null) {
				if (row.trim().equals("") || row.length() == 0) {
					continue;
				}
				if (i == 0) {
					title = row.trim();
					i++;
				} else {
					builder.append(row + "\n");
				}
			}
			content = builder.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new Document(fileName, title, content);
	}

	/**
	 * 按照每行写入文件
	 * 
	 * @param dec_FilePath
	 * @param content
	 * @param type
	 * @return
	 */

	public static boolean write2File(String dec_FilePath, String content,
			boolean type) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(
					dec_FilePath), type));
			writer.append(content);
			writer.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
