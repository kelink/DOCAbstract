package com.gdufs.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MyEditor extends JFrame {
	static Display display = new Display();
	static Shell shell = new Shell(display);

	public MyEditor() {
		this.setAlwaysOnTop(true);
		this.setFocusable(true);
	}

	/**
	 * 程序入口函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MyEditor testChooser = new MyEditor();
		testChooser.setVisible(true);
		System.out.println(testChooser.openFile());
	}

	/**
	 * 退出记事本
	 */
	private void exit() {
		System.exit(0);
	}

	/**
	 * 打开文件对话框
	 */
	public static File[] openFile() {
		FileDialog openFileDialog = new FileDialog(shell, SWT.OPEN | SWT.MULTI);// 支持多选
		openFileDialog.setText("Open");
		openFileDialog.setFilterPath("E:/各类活动/2013教学/软件工程/文摘语聊");
		String[] filterExt = { "*.txt" };
		openFileDialog.setFilterExtensions(filterExt);
		String selectedPath = openFileDialog.open();
		String names[] = openFileDialog.getFileNames();
		if (selectedPath != null) {
			String currDir = selectedPath.replaceFirst(
					openFileDialog.getFileName(), "");
			File[] files = new File[names.length];
			for (int i = 0; i < names.length; i++) {
				File file = new File(currDir + names[i]);
				files[i] = file;
			}
			return files;
		} else {
			return null;
		}

	}

	/**
	 * 文件另存为
	 * 
	 * @throws IOException
	 */
	public static void saveAs(String content) {
		FileWriter fw;
		BufferedWriter bw;
		FileDialog saveAsFileDialog = new FileDialog(shell, SWT.SAVE);
		saveAsFileDialog.setText("保存");
		saveAsFileDialog.setFilterPath("E:/各类活动/2013教学/软件工程/文摘语聊");
		String[] filterExt = { "*.txt" };
		saveAsFileDialog.setFilterExtensions(filterExt);
		String selectedPath = saveAsFileDialog.open();
		if (selectedPath != null) {
			char[] bytes = content.toCharArray();
			try {
				File file = new File(selectedPath);
				fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				bw.write(bytes, 0, bytes.length);
				bw.flush();
				bw.close();
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				MessageBox messageBox = new MessageBox(shell, SWT.YES);
				messageBox.setMessage("保存成功");
				messageBox.setText("温馨提示");
				int response = messageBox.open();
				if (response == SWT.YES)
					return;
			}
		}

	}

	/**
	 * 保存文件
	 */
	public static void save(File file, String content) {
		FileWriter fw;
		BufferedWriter bw;
		char[] bytes = content.toCharArray();
		if (!file.exists()) {
			try {
				file.createNewFile();
				fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				bw.write(bytes, 0, bytes.length);
				bw.flush();
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			saveAs(content);
		}

	}

	// 退出系统
	public static void Exit() {
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION
				| SWT.YES | SWT.NO);
		messageBox.setMessage("你真的想退出吗?");
		messageBox.setText("退出应用");
		int response = messageBox.open();
		if (response == SWT.YES)
			System.exit(0);
	}

	// 警告框
	public static void alertMsg() {
		MessageBox messageBox = new MessageBox(shell, SWT.YES | SWT.NO);
		messageBox.setMessage("当前文摘内容为空！");
		messageBox.setText("温馨提示");
		int response = messageBox.open();
		if (response == SWT.YES)
			return;
	}
}