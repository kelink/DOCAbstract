package com.gdufs.ui;

import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

public class Com_ActionListener extends JFrame {
	// txt过滤器
	static class TxtFileFilter extends FileFilter {
		public String getDescription() {
			return "*.txt";
		}

		public boolean accept(File file) {
			String name = file.getName();
			return name.toLowerCase().endsWith(".txt");
		}
	}

	public static File[] getFileByfileChooser() {
		JFileChooser jf = new JFileChooser(".");
		jf.setMultiSelectionEnabled(true);// 可选择多个文件
		TxtFileFilter txtFilter = new TxtFileFilter(); // Txt过滤器
		jf.addChoosableFileFilter(txtFilter);
		jf.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
		// jf.setAcceptAllFileFilterUsed(false);// 禁止全部文件

		jf.showOpenDialog(null);
		File[] files = jf.getSelectedFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				files[i] = files[i].getAbsoluteFile();
			}
			return files;
		} else {
			return null;
		}
	}

	// 保存当前文件的FileChooser
	public static File saveFileByfileChooser(String fileName, String content) {
		JFileChooser jf = new JFileChooser(".");
		TxtFileFilter txtFilter = new TxtFileFilter(); // Txt过滤器
		jf.addChoosableFileFilter(txtFilter);
		jf.setFileSelectionMode(JFileChooser.SAVE_DIALOG
				| JFileChooser.DIRECTORIES_ONLY);
		jf.showSaveDialog(null);

		File fi = jf.getSelectedFile();
		if (fi != null) {
			String f = fi.getAbsolutePath() + File.separator + fileName;
			try {
				FileWriter out = new FileWriter(f);
				out.write(content);
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fi;
	}

	// 另存为
	public static File saveAsByfileChooser(String fileName, String content) {
		JFileChooser jf = new JFileChooser(".");
		TxtFileFilter txtFilter = new TxtFileFilter(); // Txt过滤器
		jf.addChoosableFileFilter(txtFilter);
		jf.setAcceptAllFileFilterUsed(false);// 禁止全部文件
		jf.showSaveDialog(null);
		File fi = jf.getSelectedFile();
		if (fi != null) {
			String f = fi.getAbsolutePath() + File.separator + fileName;
			System.out.println(f);
			try {
				FileWriter out = new FileWriter(f);
				out.write(content);
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fi;
	}

	// public static void main(String[] args) {
	// saveAsByfileChooser("sda", "sda");
	// }
}
