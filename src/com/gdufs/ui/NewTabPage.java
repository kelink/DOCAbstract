package com.gdufs.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NewTabPage extends JFrame {

	private JPanel contentPane;
	private final JPanel panel;
	private JTextField newPageTextField;
	private JTextField pathTextField;
	private JButton browserButton;
	private JButton cancelButton;
	private JButton newAPageButton;
	// 数据域
	private MainFrame frame;
	private String pageName;

	/**
	 * Create the frame.
	 */
	public NewTabPage(final MainFrame frame) {
		this.frame = frame;// 获得主界面对象
		// setTitle("\u65B0\u5EFA\u9875\u9762");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frame.setEnabled(true);
			}
		});
		setBounds(100, 100, 424, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 9, 388, 333);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label_1 = new JLabel(
				"\u65B0\u5EFA\u9875\u9762\u7684\u540D\u79F0\uFF1A");
		label_1.setBounds(39, 55, 245, 15);
		panel.add(label_1);

		newPageTextField = new JTextField();
		newPageTextField.setBounds(39, 80, 328, 21);
		panel.add(newPageTextField);
		newPageTextField.setColumns(10);

		JLabel label = new JLabel(
				"\u65B0\u5EFA\u9875\u9762\u4FDD\u5B58\u8DEF\u5F84:");
		label.setBounds(39, 121, 245, 15);
		panel.add(label);

		pathTextField = new JTextField();
		pathTextField.setBounds(39, 146, 233, 21);
		panel.add(pathTextField);
		pathTextField.setColumns(10);

		browserButton = new JButton("Browser");
		browserButton.setBounds(282, 145, 85, 23);
		panel.add(browserButton);

		cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exit();
			}
		});
		cancelButton.setBounds(199, 234, 73, 23);
		panel.add(cancelButton);

		newAPageButton = new JButton("\u65B0\u5EFA");
		newAPageButton.setBounds(294, 234, 73, 23);
		panel.add(newAPageButton);
		newAPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 添加事件
				String fileName = newPageTextField.getText().trim();
				String pathStr = pathTextField.getText().trim();
				if (fileName.length() == 0 || pathStr.length() == 0) {
					JOptionPane.showMessageDialog(panel, "有空项，请完善！");
					return;
				}
				pageName = newPageTextField.getText().trim();
				setVisible(false);
				setFocusable(false);
				// 主界面设置
				frame.setEnabled(true);
				frame.setFocusable(true);
				// 添加Page
				frame.addTabFrame(new File(pathStr + fileName + ".txt"));
			}
		});
		browserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf = new JFileChooser();
				jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jf.showOpenDialog(null);
				File file = jf.getSelectedFile();
				if (file != null) {
					pathTextField.setText(file.getAbsolutePath());
				}
				return;
			}
		});
	}

	// 获得pageName
	public String getPageName() {
		return pageName;
	}

	// 关闭当前窗口
	private void Exit() {
		this.setVisible(false);
		// 主界面设置
		frame.setEnabled(true);
		frame.setFocusable(true);
	}

}
