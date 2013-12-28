package com.gdufs.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
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
	 * ������ں���
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MyEditor testChooser = new MyEditor();
		testChooser.setVisible(true);
		// testChooser.folderDig(shell);
	}

	/**
	 * �˳����±�
	 */
	private void exit() {
		System.exit(0);
	}

	/**
	 * ���ļ��Ի���
	 */
	public static File[] openFile() {
		FileDialog openFileDialog = new FileDialog(shell, SWT.OPEN | SWT.MULTI);// ֧�ֶ�ѡ
		openFileDialog.setText("Open");
		openFileDialog.setFilterPath("E:/����/2013��ѧ/�������/��ժ����");
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
	 * �ļ����Ϊ
	 * 
	 * @throws IOException
	 */
	public static void saveAs(String content) {
		FileWriter fw;
		BufferedWriter bw;
		FileDialog saveAsFileDialog = new FileDialog(shell, SWT.SAVE);
		saveAsFileDialog.setText("����");
		saveAsFileDialog.setFilterPath("E:/����/2013��ѧ/�������/��ժ����");
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
				return;
			}
		}

	}

	/**
	 * �����ļ�
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

	// �˳�ϵͳ
	public static void Exit() {
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION
				| SWT.YES | SWT.NO);
		messageBox.setMessage("��������˳���?");
		messageBox.setText("�˳�Ӧ��");
		int response = messageBox.open();
		if (response == SWT.YES)
			System.exit(0);
	}

	// �����
	public static void alertMsg() {
		MessageBox messageBox = new MessageBox(shell, SWT.YES | SWT.NO);
		messageBox.setMessage("��ǰ��ժ����Ϊ�գ�");
		messageBox.setText("��ܰ��ʾ");
		int response = messageBox.open();
		if (response == SWT.YES)
			return;
	}

	/**
	 * �ļ��У�Ŀ¼��ѡ��Ի���
	 * 
	 * @return
	 */
	public static void folderDig(Shell parent, ArrayList<File> files,
			ArrayList<String> docAbstractsList) {
		// �½��ļ��У�Ŀ¼���Ի���
		DirectoryDialog folderdlg = new DirectoryDialog(parent);
		// �����ļ��Ի���ı���
		folderdlg.setText("�ļ�ѡ��");
		// ���ó�ʼ·��
		folderdlg.setFilterPath("d:");
		// ���öԻ�����ʾ�ı���Ϣ
		folderdlg.setMessage("��ѡ����Ӧ���ļ���");
		// ���ļ��Ի��򣬷���ѡ���ļ���Ŀ¼
		String selecteddir = folderdlg.open();
		if (selecteddir == null) {
			FileWriter fw;
			BufferedWriter bw;
			for (int i = 0; i < docAbstractsList.size(); i++) {
				char[] bytes = docAbstractsList.get(i).toCharArray();
				String fileName = "abc_" + files.get(i).getName();
				try {
					fw = new FileWriter(new File(selecteddir + fileName));
					bw = new BufferedWriter(fw);
					bw.write(bytes, 0, bytes.length);
					bw.flush();
					bw.close();
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}

			}
			return;
		} else {
			System.out.println("��ѡ�е��ļ���Ŀ¼Ϊ��" + selecteddir);
		}
	}
}