package com.gdufs.ui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TabContentComponent extends JFrame {
	private JTabbedPane tabbedPane;// 主界面的应用，用于更新
	private boolean isEditNow = false;// 标记当前界面是否在编辑
	private int isFirst = 0;// 标记是否第一次打开界面
	private JTextField titleField;
	private JTextArea textArea;
	private JLabel label_1;
	private JScrollPane scrollPane;

	public boolean isEditNow() {
		return isEditNow;
	}

	public void setEditNow(boolean isEdit) {
		this.isEditNow = isEdit;
	}

	public TabContentComponent(JTabbedPane tabbedPane) {
		getContentPane().setBackground(new Color(131, 86, 49));
		this.tabbedPane = tabbedPane;
	}

	public String getTitleField() {
		return titleField.getText();
	}

	public void setTitleField(String title) {
		this.titleField.setText(title);
	}

	public String getTextArea() {
		return textArea.getText();
	}

	public void setTextArea(String content) {
		this.textArea.setText(content);
	}

	public JTextField getTitleFieldObj() {
		return this.titleField;
	}

	public JTextArea getTextAreaObj() {
		return this.textArea;
	}

	// 获得一个文档内容的JPanel
	public JPanel getTabContentComponent() {
		titleField = new JTextField();
		JPanel panel = new JPanel();
		panel.setBounds(30, 36, 415, 216);
		panel.setLayout(null);

		JLabel label = new JLabel("\u6807\u9898\uFF1A");
		label.setBounds(10, 10, 42, 32);
		panel.add(label);

		titleField = new JTextField();
		titleField.setBounds(55, 16, 430, 25);
		panel.add(titleField);
		titleField.setBackground(new Color(131, 86, 49));
		titleField.setColumns(10);
		titleField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				int index = tabbedPane.getSelectedIndex();
				String title = tabbedPane.getTitleAt(index).replace("*", "");
				tabbedPane.setTitleAt(index, title + "*");
				isEditNow = true;
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if (isFirst == 0) {
					isFirst = 1;
					return;
				}
				int index = tabbedPane.getSelectedIndex();
				String title = tabbedPane.getTitleAt(index).replace("*", "");
				tabbedPane.setTitleAt(index, title + "*");
				isEditNow = true;
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				System.out.println("asdadd");

			}
		});
		titleField.setEditable(false);
		titleField.setForeground(Color.WHITE);
		label_1 = new JLabel("\u5185\u5BB9\uFF1A");
		label_1.setBounds(10, 52, 42, 21);
		panel.add(label_1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 51, 430, 400);

		textArea = new JTextArea();
		textArea.setBounds(55, 51, 430, 400);
		textArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
		});
		scrollPane.add(textArea);
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBackground(new Color(131, 86, 49));
		textArea.setForeground(Color.WHITE);
		// 隐藏滚动条
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

		panel.add(scrollPane);
		titleField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(
				65, 43, 24), Color.DARK_GRAY));
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(65,
				43, 24), Color.DARK_GRAY));
		scrollPane.setBackground(new Color(131, 86, 49));
		return panel;
	}

}
