package com.gdufs.ui;

import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

public class Com_ActionListener extends JFrame {
	// txt������
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
		jf.setMultiSelectionEnabled(true);// ��ѡ�����ļ�
		TxtFileFilter txtFilter = new TxtFileFilter(); // Txt������
		jf.addChoosableFileFilter(txtFilter);
		jf.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
		// jf.setAcceptAllFileFilterUsed(false);// ��ֹȫ���ļ�

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

	// ���浱ǰ�ļ���FileChooser
	public static File saveFileByfileChooser(String fileName, String content) {
		JFileChooser jf = new JFileChooser(".");
		TxtFileFilter txtFilter = new TxtFileFilter(); // Txt������
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

	// ���Ϊ
	public static File saveAsByfileChooser(String fileName, String content) {
		JFileChooser jf = new JFileChooser(".");
		TxtFileFilter txtFilter = new TxtFileFilter(); // Txt������
		jf.addChoosableFileFilter(txtFilter);
		jf.setAcceptAllFileFilterUsed(false);// ��ֹȫ���ļ�
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
