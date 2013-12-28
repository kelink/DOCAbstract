package com.gdufs.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.undo.UndoManager;

import main.Extraction;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.gdufs.abstractExtraction.AbstractExtraction;
import com.gdufs.model.Document;
import com.gdufs.util.FileUtil;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JPanel toolBarPanel;
	private JTabbedPane tabbedPane;
	private JMenuBar menuBar;
	private JToolBar toolBar;
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu windowMenu;
	private JMenu helpMenu;
	private JMenuItem openMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem saveAsMenuItem;
	private JMenuItem closeMenuItem;
	private JMenuItem closeAllMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem undoMenuItem;
	private JMenuItem redoMenuItem;
	private JMenuItem cutMenuItem;
	private JMenuItem CopyMenuItem;
	private JMenuItem pasteMenuItem;
	private JMenuItem deleteMenuItem;
	private JMenuItem selectAllMenuItem;
	private JMenuItem hideMenuItem;
	private JMenuItem closeAllPerMenuItem;
	private JMenuItem preferenceMenuItem;
	private JMenuItem updateMenuItem;
	private JMenuItem aboutMenuItem;

	private JButton toolCopyButton;
	private JButton toolOpenButton;
	private JButton toolSaveButton;
	private JButton toolSaveAllButton;
	private JButton toolDeleteButton;
	private JButton toolCutButton;
	private JButton saveButton;
	private JButton toolPasteButton;

	private JScrollPane abstractScrollPane;
	private JPanel infoPanel;
	private JComboBox<?> rateComboBox;
	private JTextArea AbstractTextArea;
	private JLabel timeLabel;
	private JLabel rateLabel;
	private JLabel statueLabel;
	private JCheckBox isBatchCheckBox;
	private JButton btnStart;
	private NewTabPage newTabPage;
	private Clipboard clipboard;// 系统剪贴板
	private UndoManager um;// 重做和撤销

	// 数据域
	public static int count = 0;// 打开的TabComponent数目
	public static ArrayList<File> d_Files;// 打开的全部文档
	public static ArrayList<TabContentComponent> d_TabComponents;// 打开的TabComponent
	public static ArrayList<String> docAbstractsList;// 文摘列表
	private JMenuItem txtMenuItem;
	private JMenuItem infoMenuItem;
	private JMenuItem abstractMenuItem;
	private JMenu menu;
	private JPanel abstractPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setForeground(new Color(189, 153, 111));
		setResizable(false);// 不可改变大小
		// 初始化数据域
		d_Files = new ArrayList<>();
		d_TabComponents = new ArrayList<>();
		docAbstractsList = new ArrayList<>();
		/*********************** 初始工具 ***********************/
		um = new UndoManager();// 重做和撤销
		clipboard = getToolkit().getSystemClipboard();// 获取系统剪贴板。
		/*********************** 初始化组件实例 **************/
		contentPane = new JPanel();
		fileMenu = new JMenu("");
		fileMenu.setSelectedIcon(new ImageIcon("./source/pic/file_press.jpg"));
		fileMenu.setForeground(new Color(189, 153, 111));
		fileMenu.setIcon(new ImageIcon("./source/pic/file_normal.jpg"));
		fileMenu.setBackground(new Color(189, 153, 111));
		fileMenu.setHorizontalAlignment(SwingConstants.CENTER);
		fileMenu.setBounds(0, 0, 57, 34);
		fileMenu.setPressedIcon(new ImageIcon("./source/pic/file_press.jpg"));
		fileMenu.setMargin(new Insets(0, 0, 0, 0));

		toolSaveAllButton = new JButton("");
		toolSaveAllButton.setSize(16, 16);
		toolSaveAllButton.setBackground(new Color(189, 153, 111));
		toolSaveAllButton.setBackground(new Color(131, 86, 49));
		toolSaveAllButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				new Color(131, 86, 49), new Color(131, 86, 49)));
		tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setForeground(Color.WHITE);
		tabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		toolSaveButton = new JButton("");
		toolSaveButton.setSize(16, 16);
		toolSaveButton.setBackground(new Color(189, 153, 111));
		toolSaveButton.setBackground(new Color(131, 86, 49));
		toolSaveButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				new Color(131, 86, 49), new Color(131, 86, 49)));
		menuBar = new JMenuBar();
		openMenuItem = new JMenuItem("\u6253\u5F00");
		JSeparator separator = new JSeparator();
		saveMenuItem = new JMenuItem("\u4FDD\u5B58                        ");
		saveAsMenuItem = new JMenuItem("\u53E6\u5B58\u4E3A");
		JSeparator separator_1 = new JSeparator();
		closeMenuItem = new JMenuItem("\u5173\u95ED");
		closeAllMenuItem = new JMenuItem("\u5173\u95ED\u6240\u6709");
		exitMenuItem = new JMenuItem("\u9000\u51FA");
		JSeparator separator_2 = new JSeparator();
		editMenu = new JMenu("");

		editMenu.setForeground(new Color(189, 153, 111));
		editMenu.setBackground(new Color(189, 153, 111));
		editMenu.setHorizontalAlignment(SwingConstants.CENTER);
		editMenu.setBounds(0, 0, 57, 34);
		editMenu.setPressedIcon(new ImageIcon("./source/pic/edit normal.jpg"));
		editMenu.setMargin(new Insets(0, 0, 0, 0));
		editMenu.setSelectedIcon(new ImageIcon("./source/pic/edit press.jpg"));
		editMenu.setIcon(new ImageIcon("./source/pic/edit normal.jpg"));

		redoMenuItem = new JMenuItem("\u91CD\u505A");
		undoMenuItem = new JMenuItem(
				"\u64A4\u9500                                ");
		cutMenuItem = new JMenuItem("\u526A\u5207");
		CopyMenuItem = new JMenuItem("\u590D\u5236");
		JSeparator separator_3 = new JSeparator();
		deleteMenuItem = new JMenuItem("\u6E05\u7A7A\u5185\u5BB9");
		selectAllMenuItem = new JMenuItem("\u5168\u9009");

		windowMenu = new JMenu("");
		windowMenu.setForeground(new Color(189, 153, 111));
		windowMenu.setBackground(new Color(189, 153, 111));
		windowMenu.setHorizontalAlignment(SwingConstants.CENTER);
		windowMenu.setBounds(0, 0, 57, 34);
		windowMenu
				.setPressedIcon(new ImageIcon("./source/pic/window press.jpg"));
		windowMenu.setMargin(new Insets(0, 0, 0, 0));
		windowMenu.setSelectedIcon(new ImageIcon(
				"./source/pic/window press.jpg"));
		windowMenu.setIcon(new ImageIcon("./source/pic/window noamal.jpg"));

		hideMenuItem = new JMenuItem("\u9690\u85CF\u5DE5\u5177\u680F");
		menu = new JMenu("\u89C6\u56FE           ");
		txtMenuItem = new JMenuItem("\u5173\u95ED\u6587\u672C\u89C6\u56FE");
		infoMenuItem = new JMenuItem("\u5173\u95ED\u4FE1\u606F\u89C6\u56FE");
		abstractMenuItem = new JMenuItem("\u5173\u95ED\u6587\u6458\u89C6\u56FE");
		JSeparator separator_5 = new JSeparator();
		preferenceMenuItem = new JMenuItem("\u9009\u9879");

		helpMenu = new JMenu("");
		helpMenu.setForeground(new Color(189, 153, 111));
		helpMenu.setBackground(new Color(189, 153, 111));
		helpMenu.setHorizontalAlignment(SwingConstants.CENTER);
		helpMenu.setBounds(0, 0, 57, 34);
		helpMenu.setPressedIcon(new ImageIcon("./source/pic/help press.jpg"));
		helpMenu.setMargin(new Insets(0, 0, 0, 0));
		helpMenu.setIcon(new ImageIcon("./source/pic/help noamal.jpg"));
		helpMenu.setSelectedIcon(new ImageIcon("./source/pic/help press.jpg"));

		JSeparator separator_7 = new JSeparator();
		updateMenuItem = new JMenuItem("\u66F4\u65B0                update");
		aboutMenuItem = new JMenuItem("\u5173\u4E8E                 about");
		JLabel statueTitleLabel = new JLabel("\u72B6\u6001\uFF1A");
		infoPanel = new JPanel();
		JLabel label_2 = new JLabel("\u4FE1\u606F\uFF1A");
		JLabel label_3 = new JLabel("\u5904\u7406\u65F6\u95F4\uFF1A");
		timeLabel = new JLabel("0\u6BEB\u79D2");
		rateLabel = new JLabel("0%");
		statueLabel = new JLabel("\u51C6\u5907...");
		abstractPanel = new JPanel();
		abstractScrollPane = new JScrollPane();
		AbstractTextArea = new JTextArea();
		AbstractTextArea.setForeground(Color.WHITE);
		JLabel label_1 = new JLabel("\u63D0\u53D6\u6BD4\u7387\uFF1A");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		btnStart = new JButton("");
		btnStart.setPressedIcon(new ImageIcon(
				"E:\\android\\android_workplace2\\DOCAbstract\\source\\pic\\\u63D0\u53D6\u767E\u5206\u6BD42 press.jpg"));
		JLabel label = new JLabel("\u6587\u7AE0\u6587\u6458\uFF1A");
		label.setFont(new Font("微软雅黑", Font.BOLD, 14));
		isBatchCheckBox = new JCheckBox("\u6279\u91CF\u5904\u7406");
		saveButton = new JButton("");
		saveButton
				.setPressedIcon(new ImageIcon(
						"E:\\android\\android_workplace2\\DOCAbstract\\source\\pic\\\u4FDD\u5B58\u6587\u6458press.jpg"));
		saveButton
				.setIcon(new ImageIcon(
						"E:\\android\\android_workplace2\\DOCAbstract\\source\\pic\\\u4FDD\u5B58\u6587\u6458normal.jpg"));

		toolBarPanel = new JPanel();
		toolBar = new JToolBar();
		toolOpenButton = new JButton("");
		toolOpenButton.setSize(16, 16);
		toolOpenButton.setBackground(new Color(189, 153, 111));
		toolOpenButton.setBackground(new Color(131, 86, 49));
		toolOpenButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				new Color(131, 86, 49), new Color(131, 86, 49)));
		toolBarPanel.setBounds(10, 40, 824, 34);
		contentPane.add(toolBarPanel);
		toolCutButton = new JButton("");
		toolCutButton.setSize(16, 16);
		toolCutButton.setBackground(new Color(189, 153, 111));
		toolCutButton.setBackground(new Color(131, 86, 49));
		toolCutButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				new Color(131, 86, 49), new Color(131, 86, 49)));
		toolCopyButton = new JButton("");
		toolCopyButton.setSize(16, 16);
		toolCopyButton.setBackground(new Color(189, 153, 111));
		toolCopyButton.setBackground(new Color(131, 86, 49));
		toolCopyButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				new Color(131, 86, 49), new Color(131, 86, 49)));
		toolBarPanel.setLayout(null);
		toolPasteButton = new JButton("");
		toolPasteButton.setSize(16, 16);
		toolPasteButton.setBackground(new Color(189, 153, 111));
		toolPasteButton.setBackground(new Color(131, 86, 49));
		toolPasteButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				new Color(131, 86, 49), new Color(131, 86, 49)));
		toolDeleteButton = new JButton("");
		toolDeleteButton.setSize(16, 16);
		toolDeleteButton.setBackground(new Color(189, 153, 111));
		toolDeleteButton.setBackground(new Color(131, 86, 49));
		toolDeleteButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				new Color(131, 86, 49), new Color(131, 86, 49)));
		/*********************** 界面组件设置和初始化 ***********************/
		setTitle("\u81EA\u52A8\u6587\u6458\u5904\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 662);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		menuBar.setBackground(new Color(189, 153, 111));
		menuBar.setBounds(0, 0, 834, 35);
		contentPane.add(menuBar);

		menuBar.add(fileMenu);
		openMenuItem.setIcon(new ImageIcon("./source/menuIcons/open_File.gif"));
		fileMenu.add(openMenuItem);
		separator.setBackground(Color.WHITE);
		fileMenu.add(separator);

		/*********** 事件监听 **************/
		openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		openMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 菜单项Open File 添加事件
				statueLabel.setText("打开文件...");
				openFile();
			}
		});
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 菜单项Save 添加事件
				if (MainFrame.d_Files.isEmpty()) {
					return;
				}
				saveCurrAbs();// 保存文摘
			}
		});
		saveMenuItem.setIcon(new ImageIcon("./source/menuIcons/save.gif"));
		fileMenu.add(saveMenuItem);

		saveAsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 菜单项Save As添加事件
				if (MainFrame.d_Files.isEmpty()) {
					return;
				}
				saveAs();// 另存为
			}
		});
		saveAsMenuItem.setIcon(new ImageIcon("./source/menuIcons/save as.gif"));
		fileMenu.add(saveAsMenuItem);

		separator_1.setBackground(SystemColor.menu);
		separator_1.setForeground(SystemColor.window);
		fileMenu.add(separator_1);

		closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
				InputEvent.CTRL_MASK));
		closeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 菜单项Close 添加事件
				removeTabFrame();
			}
		});
		closeMenuItem.setIcon(new ImageIcon("./source/menuIcons/close.gif"));
		fileMenu.add(closeMenuItem);

		closeAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
				InputEvent.ALT_MASK));
		closeAllMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 菜单项Close All 添加事件
				removeAllTabFrame();
			}
		});
		fileMenu.add(closeAllMenuItem);

		separator_2.setForeground(SystemColor.window);
		fileMenu.add(separator_2);

		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.CTRL_MASK));
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 菜单项Exit 添加事件
				Exit();
			}
		});
		exitMenuItem.setIcon(new ImageIcon("./source/menuIcons/exit.gif"));
		fileMenu.add(exitMenuItem);

		editMenu.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(editMenu);

		undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				InputEvent.CTRL_MASK));
		undoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 菜单项Undo Typing 添加事件
				if (um.canUndo()) {
					um.undo();
				}
			}
		});
		undoMenuItem.setIcon(new ImageIcon("./source/menuIcons/Undo.gif"));

		editMenu.add(undoMenuItem);

		redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
				InputEvent.CTRL_MASK));
		redoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 菜单项Redo 添加事件
				if (um.canRedo()) {
					um.redo();
				}
			}
		});
		redoMenuItem.setIcon(new ImageIcon("./source/menuIcons/Redo.gif"));
		editMenu.add(redoMenuItem);

		separator_3.setForeground(SystemColor.window);
		editMenu.add(separator_3);

		cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.CTRL_MASK));
		cutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 菜单项Cut 添加事件
				cutPastCopyDel(e);
			}
		});
		cutMenuItem.setIcon(new ImageIcon("./source/menuIcons/cut.gif"));
		editMenu.add(cutMenuItem);

		CopyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_MASK));
		CopyMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 菜单项Copy 添加事件
				cutPastCopyDel(e);
			}
		});
		CopyMenuItem.setIcon(new ImageIcon("./source/menuIcons/copy.gif"));
		editMenu.add(CopyMenuItem);

		pasteMenuItem = new JMenuItem("\u7C98\u8D34");
		pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				InputEvent.CTRL_MASK));
		pasteMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 菜单项Paste 添加事件
				cutPastCopyDel(e);
			}
		});
		pasteMenuItem.setIcon(new ImageIcon("./source/menuIcons/paste.gif"));
		editMenu.add(pasteMenuItem);

		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(SystemColor.window);
		editMenu.add(separator_4);

		deleteMenuItem.setMnemonic(KeyEvent.VK_DELETE);
		deleteMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 菜单项Delete 添加事件
				cutPastCopyDel(e);
			}
		});
		deleteMenuItem.setIcon(new ImageIcon("./source/menuIcons/delete.gif"));
		editMenu.add(deleteMenuItem);

		selectAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.CTRL_MASK));
		selectAllMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 菜单项Selectt All 添加事件
				AbstractTextArea.selectAll();
				AbstractTextArea.setSelectionColor(Color.gray);
				String temp = AbstractTextArea.getSelectedText(); // 全部到粘贴板。
				StringSelection text = new StringSelection(temp);
				clipboard.setContents(text, null);
			}
		});
		selectAllMenuItem.setIcon(new ImageIcon(
				"./source/menuIcons/Select all.gif"));
		editMenu.add(selectAllMenuItem);

		windowMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// new window 添加事件

			}
		});
		windowMenu.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(windowMenu);

		hideMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 菜单项Hide Tool Bar添加事件
				if (toolBarPanel.isVisible() == true) {
					toolBarPanel.setVisible(false);
					hideMenuItem.setText("打开工具栏");
				} else {
					toolBarPanel.setVisible(true);
					hideMenuItem.setText("隐藏工具栏");
				}

			}
		});

		menu.setIcon(new ImageIcon(
				"E:\\android\\android_workplace2\\DOCAbstract\\source\\menuIcons\\new_persp.gif"));
		windowMenu.add(menu);

		txtMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 打开文本编辑界面
				if (tabbedPane.isVisible() == true) {
					tabbedPane.setVisible(false);
					txtMenuItem.setText("打开文本视图");
				} else {
					tabbedPane.setVisible(true);
					txtMenuItem.setText("关闭文本视图");
				}

			}
		});
		menu.add(txtMenuItem);

		infoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 打开文本编辑界面
				if (infoPanel.isVisible() == true) {
					infoPanel.setVisible(false);
					infoMenuItem.setText("打开信息视图");
					btnStart.setEnabled(false);
				} else {
					infoPanel.setVisible(true);
					btnStart.setEnabled(true);
					infoMenuItem.setText("关闭信息视图");
				}
			}
		});
		menu.add(infoMenuItem);

		abstractMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 打开文本编辑界面
				if (abstractPanel.isVisible() == true) {
					abstractPanel.setVisible(false);
					abstractMenuItem.setText("打开文摘视图");
				} else {
					abstractPanel.setVisible(true);
					abstractMenuItem.setText("关闭文摘视图");
				}
			}
		});
		menu.add(abstractMenuItem);

		hideMenuItem.setIcon(new ImageIcon("./source/menuIcons/toolbar.gif"));
		windowMenu.add(hideMenuItem);

		separator_5.setForeground(SystemColor.window);
		windowMenu.add(separator_5);

		closeAllPerMenuItem = new JMenuItem(
				"\u5173\u95ED\u6240\u6709\u89C6\u56FE");
		closeAllPerMenuItem
				.setIcon(new ImageIcon(
						"E:\\android\\android_workplace2\\DOCAbstract\\source\\menuIcons\\close_view.gif"));
		closeAllPerMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 菜单项Close All Perspectives添加事件
				if (closeAllPerMenuItem.getText().equals("关闭所有视图")) {
					if (abstractPanel.isVisible() == true) {
						abstractPanel.setVisible(false);
						abstractMenuItem.setText("打开文摘视图");
					}
					if (tabbedPane.isVisible() == true) {
						tabbedPane.setVisible(false);
						txtMenuItem.setText("打开文本视图");
					}
					if (infoPanel.isVisible() == true) {
						infoPanel.setVisible(false);
						infoMenuItem.setText("打开信息视图");
						btnStart.setEnabled(false);
					}
					closeAllPerMenuItem.setText("打开所有视图");
				} else {
					tabbedPane.setVisible(true);
					txtMenuItem.setText("关闭文本视图");
					infoPanel.setVisible(true);
					btnStart.setEnabled(true);
					infoMenuItem.setText("关闭信息视图");
					abstractPanel.setVisible(true);
					abstractMenuItem.setText("关闭文摘视图");
				}

			}
		});
		windowMenu.add(closeAllPerMenuItem);

		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(SystemColor.window);
		windowMenu.add(separator_6);

		preferenceMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		preferenceMenuItem
				.setIcon(new ImageIcon(
						"E:\\android\\android_workplace2\\DOCAbstract\\source\\menuIcons\\setting.gif"));
		windowMenu.add(preferenceMenuItem);

		helpMenu.setFont(new Font("微软雅黑", Font.BOLD, 12));
		menuBar.add(helpMenu);
		helpMenu.setHorizontalAlignment(SwingConstants.CENTER);

		separator_7.setForeground(SystemColor.window);
		helpMenu.add(separator_7);

		updateMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 菜单项Update添加事件
			}
		});
		updateMenuItem.setIcon(new ImageIcon("./source/menuIcons/update.gif"));
		helpMenu.add(updateMenuItem);

		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 菜单项About SoftWare添加事件
			}
		});
		aboutMenuItem.setIcon(new ImageIcon("./source/menuIcons/about.gif"));
		helpMenu.add(aboutMenuItem);
		if (tabbedPane.getSelectedIndex() == -1) {
			toolSaveButton.setEnabled(false);
		}
		// 设置默认
		if (tabbedPane.getSelectedIndex() == -1) {
			toolSaveButton.setEnabled(false);
			toolSaveAllButton.setEnabled(false);
			toolCopyButton.setEnabled(false);
			toolPasteButton.setEnabled(false);
			toolCutButton.setEnabled(false);
			toolDeleteButton.setEnabled(false);
		}

		isBatchCheckBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (isBatchCheckBox.isSelected()) {
					isBatchCheckBox.setBackground(new Color(131, 86, 49));
				} else {
					isBatchCheckBox.setBackground(new Color(189, 153, 111));
				}

			}
		});
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// 获得当前Tabpage的下标
				int index = tabbedPane.getSelectedIndex();
				if (index != -1) {
					if (isBatchCheckBox.isSelected()) {
						if (docAbstractsList.isEmpty()) {
							AbstractTextArea.setText("");
							return;
						}
						statueLabel.setText("文件" + tabbedPane.getTitleAt(index));
						AbstractTextArea.setText("");
						try {
							AbstractTextArea.setText(docAbstractsList
									.get(index));
						} catch (Exception e2) {
							return;
						}

					} else {
						statueLabel.setText("文件" + tabbedPane.getTitleAt(index));
						return;
					}
				} else {
					statueLabel.setText("准备...");
				}

			}
		});
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(10, 78, 537, 503);
		contentPane.add(tabbedPane);

		String[] items = new String[10];
		for (int i = 0; i < items.length; i++) {
			if (i == 0) {
				items[i] = "0%";
				continue;
			}
			items[i] = String.valueOf(i) + "0%";
		}

		statueTitleLabel.setBounds(10, 599, 43, 15);
		contentPane.add(statueTitleLabel);

		infoPanel.setBounds(557, 493, 277, 88);
		contentPane.add(infoPanel);
		infoPanel.setLayout(null);

		label_2.setBounds(10, 10, 54, 15);
		infoPanel.add(label_2);

		label_3.setBounds(10, 38, 72, 15);
		infoPanel.add(label_3);

		timeLabel.setBounds(83, 38, 54, 15);
		infoPanel.add(timeLabel);

		JLabel label_6 = new JLabel("\u63D0\u53D6\u6BD4\u7387\uFF1A");
		label_6.setBounds(10, 63, 72, 15);
		infoPanel.add(label_6);

		rateLabel.setBounds(83, 63, 54, 15);
		infoPanel.add(rateLabel);

		statueLabel.setBounds(53, 599, 148, 15);
		contentPane.add(statueLabel);

		abstractPanel.setBounds(557, 78, 277, 405);
		contentPane.add(abstractPanel);
		abstractPanel.setLayout(null);

		abstractScrollPane.setBounds(0, 70, 277, 288);
		abstractPanel.add(abstractScrollPane);
		AbstractTextArea.setLineWrap(true);// 自动换行
		// 隐藏滚动条
		abstractScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		abstractScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		abstractScrollPane.setViewportView(AbstractTextArea);
		AbstractTextArea.getDocument().addUndoableEditListener(um);// 设置可以撤销和重做

		label_1.setBounds(0, 0, 83, 32);
		abstractPanel.add(label_1);
		rateComboBox = new JComboBox(items);
		rateComboBox.setBackground(Color.WHITE);
		rateComboBox.setBounds(93, 8, 127, 21);
		abstractPanel.add(rateComboBox);
		rateComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 处理提取比率
				String rateString = rateComboBox.getSelectedItem().toString();
				String temp = rateString.replaceAll("%", "").trim();
				double rate = Double.parseDouble(temp) / 100;
				AbstractExtraction.result_Rate = rate;
			}
		});
		rateComboBox.setEditable(true);
		rateComboBox.setToolTipText("\u767E\u5206\u6BD4");

		btnStart.setBounds(241, 9, 26, 23);
		abstractPanel.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (infoPanel.isVisible()) {
					// 按钮"开始提取"添加事件
					btnStart.setEnabled(true);
					long timebefore = System.currentTimeMillis();
					int index = tabbedPane.getSelectedIndex();
					if (index == -1) {
						return;
					}
					String fileName = tabbedPane.getTitleAt(index);
					TabContentComponent component = d_TabComponents.get(index);
					String title = component.getTitleField().trim();
					String content = component.getTextArea().trim();
					if (title.length() <= 0 || content.length() <= 0) {
						JOptionPane.showMessageDialog(component, "标题或内容为空！");
						return;
					}
					ArrayList<Document> documents = new ArrayList<>();
					if (isBatchCheckBox.isSelected()) {
						for (int i = 0; i < tabbedPane.getTabCount(); i++) {
							fileName = tabbedPane.getTitleAt(i);
							component = d_TabComponents.get(i);
							title = component.getTitleField().trim();
							content = component.getTextArea().trim();
							Document document = new Document();
							document.setContent(content);
							document.setFileName(fileName);
							document.setTitle(title);
							documents.add(document);
						}
					} else {
						index = tabbedPane.getSelectedIndex();
						fileName = tabbedPane.getTitleAt(index);
						component = d_TabComponents.get(index);
						title = component.getTitleField().trim();
						content = component.getTextArea().trim();
						Document document = new Document();
						document.setContent(content);
						document.setFileName(fileName);
						document.setTitle(title);
						documents.add(document);
					}
					docAbstractsList.clear();// 清理之前全部
					docAbstractsList = getAbstract(documents);
					long timeafter = System.currentTimeMillis();
					if (!docAbstractsList.isEmpty()) {
						AbstractTextArea.setText(docAbstractsList.get(0));
						timeLabel.setText(String
								.valueOf((timeafter - timebefore)) + "毫秒");
						rateLabel.setText(rateComboBox.getSelectedItem()
								.toString());
						statueLabel.setText("提取完成");
					}
					return;

				} else {
					btnStart.setEnabled(false);
					return;
				}
			}

		});
		btnStart.setToolTipText("\u63D0\u53D6\u6587\u6458");
		btnStart.setIcon(new ImageIcon(
				"E:\\android\\android_workplace2\\DOCAbstract\\source\\pic\\\u63D0\u53D6\u767E\u5206\u6BD42 normal.jpg"));

		label.setBounds(0, 42, 75, 18);
		abstractPanel.add(label);

		isBatchCheckBox.setBounds(198, 41, 79, 23);
		abstractPanel.add(isBatchCheckBox);
		isBatchCheckBox
				.setToolTipText("\u662F\u5426\u6279\u91CF\u5904\u7406\u5F53\u524D\u9875\u9762\u4E2D\u7684\u6587\u7AE0");

		saveButton.setBounds(189, 363, 88, 42);
		abstractPanel.add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 按钮保存添加事件
				String docAbstract = AbstractTextArea.getText().trim();
				if (docAbstract.length() == 0
						|| tabbedPane.getSelectedIndex() == -1) {
					return;
				}
				MyEditor.saveAs(docAbstract);

			}
		});
		saveButton.setToolTipText("\u4FDD\u5B58\u6587\u6458");

		toolBar.setBounds(0, 2, 824, 28);
		toolBarPanel.add(toolBar);

		toolOpenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 工具栏New 添加事件
				openFile();
			}
		});
		toolOpenButton.setToolTipText("\u6253\u5F00\u6587\u4EF6");
		toolBar.add(toolOpenButton);
		toolOpenButton
				.setIcon(new ImageIcon(
						"E:\\android\\android_workplace2\\DOCAbstract\\source\\toolIcons\\new_persp.jpg"));

		toolSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 工具栏Save添加事件{
				saveCurrAbs();
			}
		});
		toolSaveButton.setToolTipText("\u4FDD\u5B58");
		toolBar.add(toolSaveButton);
		toolSaveButton
				.setIcon(new ImageIcon(
						"E:\\android\\android_workplace2\\DOCAbstract\\source\\toolIcons\\save.jpg"));

		toolSaveAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// (保存所有文摘)
				saveAllAbs();
			}
		});
		toolSaveAllButton.setToolTipText("\u5168\u90E8\u4FDD\u5B58");
		toolBar.add(toolSaveAllButton);
		toolSaveAllButton
				.setIcon(new ImageIcon(
						"E:\\android\\android_workplace2\\DOCAbstract\\source\\toolIcons\\save as.jpg"));

		toolCutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 工具栏Cut添加事件
				cutPastCopyDel(e);
			}
		});
		toolBar.add(toolCutButton);
		toolCutButton.setToolTipText("\u526A\u5207");
		toolCutButton
				.setIcon(new ImageIcon(
						"E:\\android\\android_workplace2\\DOCAbstract\\source\\toolIcons\\cut.jpg"));

		toolCopyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 工具栏Copy添加事件
				cutPastCopyDel(e);
			}
		});
		toolBar.add(toolCopyButton);
		toolCopyButton.setToolTipText("\u590D\u5236");
		toolCopyButton
				.setIcon(new ImageIcon(
						"E:\\android\\android_workplace2\\DOCAbstract\\source\\toolIcons\\copy.jpg"));

		toolPasteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 工具栏Paste添加事件
				cutPastCopyDel(e);
			}
		});
		toolBar.add(toolPasteButton);
		toolPasteButton.setToolTipText("\u7C98\u8D34");
		toolPasteButton
				.setIcon(new ImageIcon(
						"E:\\android\\android_workplace2\\DOCAbstract\\source\\toolIcons\\paste.jpg"));

		toolDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 工具栏Delete添加事件
				cutPastCopyDel(e);

			}
		});
		toolBar.add(toolDeleteButton);
		toolDeleteButton.setToolTipText("\u6E05\u9664");
		toolDeleteButton
				.setIcon(new ImageIcon(
						"E:\\android\\android_workplace2\\DOCAbstract\\source\\toolIcons\\delete.jpg"));
		// 基本样式设计(背景)
		contentPane.setBackground(new Color(189, 153, 111));
		menuBar.setBackground(new Color(189, 153, 111));
		tabbedPane.setBackground(new Color(131, 86, 49));
		abstractPanel.setBackground(new Color(189, 153, 111));
		infoPanel.setBackground(new Color(189, 153, 111));
		toolBarPanel.setBackground(new Color(131, 86, 49));
		toolBar.setBackground(new Color(131, 86, 49));
		AbstractTextArea.setBackground(new Color(131, 86, 49));
		isBatchCheckBox.setBackground(new Color(189, 153, 111));
		fileMenu.setBackground(new Color(189, 153, 111));

		// 设置边界
		tabbedPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(
				65, 43, 24), Color.DARK_GRAY));
		menuBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(189,
				153, 111), new Color(189, 153, 111)));
		toolBarPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				new Color(189, 153, 111), new Color(189, 153, 111)));
		toolBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(189,
				153, 111), new Color(189, 153, 111)));
		AbstractTextArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				new Color(65, 43, 24), Color.DARK_GRAY));
		fileMenu.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(
				189, 153, 111), new Color(189, 153, 111)));
		editMenu.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(
				189, 153, 111), new Color(189, 153, 111)));
		windowMenu.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(
				189, 153, 111), new Color(189, 153, 111)));
		helpMenu.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(
				189, 153, 111), new Color(189, 153, 111)));

		// 设置字体
		AbstractTextArea.setFont(new Font("宋体", Font.PLAIN, 14));
		toolBar.setFont(new Font("经典综艺体简", Font.PLAIN, 12));

		setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { menuBar }));
	}

	// 打开新的TabPage
	private void addTabPage() {
		// 菜单项New项 添加事件
		setEnabled(false);
		setFocusable(false);
		newTabPage = new NewTabPage(this);
		newTabPage.setVisible(true);
		newTabPage.setFocusable(true);
	}

	// 添加TabFrame,返回新添加的Tab的下标
	TabContentComponent addTabFrame(File file) {
		String name = null;
		if (file == null) {
			name = newTabPage.getPageName();
		} else {
			name = file.getName();
		}
		// 标签名
		TabContentComponent component = new TabContentComponent(tabbedPane);
		JPanel panel = component.getTabContentComponent();
		panel.setBackground(new Color(189, 153, 111));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(65,
				43, 24), Color.DARK_GRAY));
		tabbedPane.add(name, panel);
		MainFrame.d_Files.add(file);// 新建则添加当前路径的
		MainFrame.d_TabComponents.add(component);// 新建则添加
		initTabComponent(count);
		tabbedPane.setSelectedIndex(count);
		toolSaveButton.setEnabled(true);// 设置可以作用
		toolSaveAllButton.setEnabled(true);
		toolCopyButton.setEnabled(true);
		toolPasteButton.setEnabled(true);
		toolCutButton.setEnabled(true);
		toolDeleteButton.setEnabled(true);
		++count;
		return component;
	}

	// 初始化带有关闭按钮的标签头部
	private int initTabComponent(int index) {
		ButtonTabComponent buttonTabComponent = new ButtonTabComponent(
				tabbedPane);
		tabbedPane.setTabComponentAt(index, buttonTabComponent);
		return index;
	}

	// 删除一个TabFrame
	private void removeTabFrame() {
		int i = tabbedPane.getSelectedIndex();
		if (i != -1) {
			tabbedPane.remove(i);
			MainFrame.d_Files.remove(count - 1);
			MainFrame.d_TabComponents.remove(count - 1);
			--MainFrame.count;// MainFrame 计数减1
		}
		// 设置默认
		if (tabbedPane.getSelectedIndex() == -1) {
			toolSaveButton.setEnabled(false);
			toolSaveAllButton.setEnabled(false);
			toolCopyButton.setEnabled(false);
			toolPasteButton.setEnabled(false);
			toolCutButton.setEnabled(false);
			toolDeleteButton.setEnabled(false);
		}
	}

	// 删除全部的TabFrame
	private void removeAllTabFrame() {
		while (count != 0) {
			MainFrame.d_Files.remove(count - 1);
			MainFrame.d_TabComponents.remove(count - 1);
			tabbedPane.remove(count - 1);
			--count;// MainFrame 计数减1;
		}
		toolSaveButton.setEnabled(false);
		toolSaveAllButton.setEnabled(false);
		toolCopyButton.setEnabled(false);
		toolPasteButton.setEnabled(false);
		toolCutButton.setEnabled(false);
		toolDeleteButton.setEnabled(false);

	}

	// 退出Exit
	private void Exit() {
		Exit();
	}

	// 打开文件
	private void openFile() {
		File[] files = MyEditor.openFile();
		if (files != null) {
			for (File file : files) {
				Document document = FileUtil.getADocument(file);
				TabContentComponent tab_component = addTabFrame(file);
				tab_component.setTitleField(document.getTitle().trim() + "\n");
				tab_component.setTextArea(document.getContent());
			}
		}
	}

	// 保存当前一篇文摘
	private void saveCurrAbs() {
		int index = tabbedPane.getSelectedIndex();
		String docAbstract = AbstractTextArea.getText().trim();
		if (docAbstract.length() == 0 || index == -1
				|| docAbstractsList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "文摘内容为空！");
			return;
		}
		System.out.println("docAbstractsList的大小为" + docAbstractsList.size());
		System.out.println("index的大小为" + index);
		String content = docAbstractsList.get(index);
		MyEditor.saveAs(content);
	}

	// 保存所有的文摘
	private void saveAllAbs() {
		int index = tabbedPane.getSelectedIndex();
		if (index == -1 || docAbstractsList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "文摘内容为空！");
			return;
		}
		for (int i = 0; i < docAbstractsList.size(); i++) {
			// 重新创建File对象
			File temp = d_Files.get(index);
			String fileName = temp.getName();
			String filePath = temp.getAbsolutePath();
			String dir = filePath.replaceAll(fileName, "");
			File file = new File(dir + "abs_" + fileName);
			MyEditor.save(file, docAbstractsList.get(index));
		}
	}

	// 另存为(有问题。。。)
	private void saveAs() {
		int index = tabbedPane.getSelectedIndex();
		TabContentComponent component = d_TabComponents.get(index);
		File file = MainFrame.d_Files.get(index);
		if (file != null) {
			String title = component.getTitleField().trim();
			String content = component.getTextArea().trim();
			try {
				MyEditor.saveAs(title + "\n" + content);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	// 获得到文摘
	public ArrayList<String> getAbstract(ArrayList<Document> documents) {
		Extraction extraction = new Extraction(documents);
		extraction.start();
		ArrayList<String> docAbstract = extraction.getDocAbstraces();
		return docAbstract;
	}

	// 实现粘贴，复制和剪切
	public void cutPastCopyDel(ActionEvent e) {
		if (e.getSource() == CopyMenuItem || e.getSource() == toolCopyButton) // 拷贝到剪贴板。
		{
			String temp = AbstractTextArea.getSelectedText(); // 拖动鼠标选取文本。
			StringSelection text = new StringSelection(temp);
			clipboard.setContents(text, null);
		} else if (e.getSource() == cutMenuItem
				|| e.getSource() == toolCutButton) // 剪贴到剪贴板。
		{
			String temp = AbstractTextArea.getSelectedText(); // 拖动鼠标选取文本。
			StringSelection text = new StringSelection(temp);
			clipboard.setContents(text, null);
			int start = AbstractTextArea.getSelectionStart();
			int end = AbstractTextArea.getSelectionEnd();
			AbstractTextArea.replaceRange("", start, end); // 从TextArea中删除被选取的文本。
		} else if (e.getSource() == toolPasteButton
				|| e.getSource() == pasteMenuItem) // 从剪贴板粘贴数据。
		{
			Transferable contents = clipboard.getContents(this);
			DataFlavor flavor = DataFlavor.stringFlavor;
			if (contents.isDataFlavorSupported(flavor))
				try {
					String str;
					str = (String) contents.getTransferData(flavor);
					AbstractTextArea.append(str);
				} catch (Exception ee) {
				}
		} else if (e.getSource() == toolDeleteButton
				|| e.getSource() == deleteMenuItem) {
			AbstractTextArea.setText("");
		}
	}
}
