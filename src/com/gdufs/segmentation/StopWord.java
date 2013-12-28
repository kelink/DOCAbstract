package com.gdufs.segmentation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.gdufs.comment.C;

/**
 * 该程序在系统进行时候就读取，以后可以静态全局访问
 * 
 * @author Link
 * 
 */
public class StopWord {
	private static ArrayList<String> stopwordlist;

	public StopWord(String stopWordPath) {
		stopWordPath = C.FilePath.STOP_WORD_PATH;
		stopwordlist = new ArrayList<String>();

		String line = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					stopWordPath), "GBK"));
			while ((line = br.readLine()) != null)
				stopwordlist.add(line);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获得到stopwordlist
	 * 
	 * @return
	 */
	public static ArrayList<String> getStopWord() {
		return stopwordlist;
	}

	/**
	 * 判断是否含有该词语
	 * 
	 * @param key
	 * @return
	 */
	public static boolean isHas(String key) {
		if (stopwordlist.contains(key.trim())) {
			return true;
		} else {
			return false;
		}
	}

}
