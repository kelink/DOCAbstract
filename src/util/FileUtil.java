package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtil {
	/**
	 * ÎÄ¼þ¶ÁÈ¡
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String path = "./News/news_sohusite_xml.smarty.dat";

		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(path), "GBK"));
		String row = "";
		StringBuilder builder = new StringBuilder();
		while ((row = br.readLine()) != null) {
			builder.append(row + "\n");
		}
		System.out.println(builder.toString());

	}
}
