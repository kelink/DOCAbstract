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
	private Clipboard clipboard;// ϵͳ������
	private UndoManager um;// �����ͳ���

	// ������
	public static int count = 0;// �򿪵�TabComponent��Ŀ
	public static ArrayList<File> d_Files;// �򿪵�ȫ���ĵ�
	public static ArrayList<TabContentComponent> d_TabComponents;// �򿪵�TabComponent
	public static ArrayList<String> docAbstractsList;// ��ժ�б�
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
		setResizable(false);// ���ɸı��С
		// ��ʼ��������
		d_Files = new ArrayList<>();
		d_TabComponents = new ArrayList<>();
		docAbstractsList = new ArrayList<>();
		/*********************** ��ʼ���� ***********************/
		um = new UndoManager();// �����ͳ���
		clipboard = getToolkit().getSystemClipboard();// ��ȡϵͳ�����塣
		/*********************** ��ʼ�����ʵ�� **************/
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
		tabbedPane.setFont(new Font("΢���ź�", Font.PLAIN, 14));
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
		label_1.setFont(new Font("΢���ź�", Font.BOLD, 14));
		btnStart = new JButton("");
		btnStart.setPressedIcon(new ImageIcon(
				"E:\\android\\android_workplace2\\DOCAbstract\\source\\pic\\\u63D0\u53D6\u767E\u5206\u6BD42 press.jpg"));
		JLabel label = new JLabel("\u6587\u7AE0\u6587\u6458\uFF1A");
		label.setFont(new Font("΢���ź�", Font.BOLD, 14));
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
		/*********************** ����������úͳ�ʼ�� ***********************/
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

		/*********** �¼����� **************/
		openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		openMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �˵���Open File ����¼�
				statueLabel.setText("���ļ�...");
				openFile();
			}
		});
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �˵���Save ����¼�
				if (MainFrame.d_Files.isEmpty()) {
					return;
				}
				saveCurrAbs();// ������ժ
			}
		});
		saveMenuItem.setIcon(new ImageIcon("./source/menuIcons/save.gif"));
		fileMenu.add(saveMenuItem);

		saveAsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �˵���Save As����¼�
				if (MainFrame.d_Files.isEmpty()) {
					return;
				}
				saveAs();// ���Ϊ
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
				// �˵���Close ����¼�
				removeTabFrame();
			}
		});
		closeMenuItem.setIcon(new ImageIcon("./source/menuIcons/close.gif"));
		fileMenu.add(closeMenuItem);

		closeAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
				InputEvent.ALT_MASK));
		closeAllMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �˵���Close All ����¼�
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
				// �˵���Exit ����¼�
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
				// �˵���Undo Typing ����¼�
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
				// �˵���Redo ����¼�
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
				// �˵���Cut ����¼�
				cutPastCopyDel(e);
			}
		});
		cutMenuItem.setIcon(new ImageIcon("./source/menuIcons/cut.gif"));
		editMenu.add(cutMenuItem);

		CopyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_MASK));
		CopyMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �˵���Copy ����¼�
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
				// �˵���Paste ����¼�
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
				// �˵���Delete ����¼�
				cutPastCopyDel(e);
			}
		});
		deleteMenuItem.setIcon(new ImageIcon("./source/menuIcons/delete.gif"));
		editMenu.add(deleteMenuItem);

		selectAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.CTRL_MASK));
		selectAllMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �˵���Selectt All ����¼�
				AbstractTextArea.selectAll();
				AbstractTextArea.setSelectionColor(Color.gray);
				String temp = AbstractTextArea.getSelectedText(); // ȫ����ճ���塣
				StringSelection text = new StringSelection(temp);
				clipboard.setContents(text, null);
			}
		});
		selectAllMenuItem.setIcon(new ImageIcon(
				"./source/menuIcons/Select all.gif"));
		editMenu.add(selectAllMenuItem);

		windowMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// new window ����¼�

			}
		});
		windowMenu.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(windowMenu);

		hideMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �˵���Hide Tool Bar����¼�
				if (toolBarPanel.isVisible() == true) {
					toolBarPanel.setVisible(false);
					hideMenuItem.setText("�򿪹�����");
				} else {
					toolBarPanel.setVisible(true);
					hideMenuItem.setText("���ع�����");
				}

			}
		});

		menu.setIcon(new ImageIcon(
				"E:\\android\\android_workplace2\\DOCAbstract\\source\\menuIcons\\new_persp.gif"));
		windowMenu.add(menu);

		txtMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���ı��༭����
				if (tabbedPane.isVisible() == true) {
					tabbedPane.setVisible(false);
					txtMenuItem.setText("���ı���ͼ");
				} else {
					tabbedPane.setVisible(true);
					txtMenuItem.setText("�ر��ı���ͼ");
				}

			}
		});
		menu.add(txtMenuItem);

		infoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���ı��༭����
				if (infoPanel.isVisible() == true) {
					infoPanel.setVisible(false);
					infoMenuItem.setText("����Ϣ��ͼ");
					btnStart.setEnabled(false);
				} else {
					infoPanel.setVisible(true);
					btnStart.setEnabled(true);
					infoMenuItem.setText("�ر���Ϣ��ͼ");
				}
			}
		});
		menu.add(infoMenuItem);

		abstractMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���ı��༭����
				if (abstractPanel.isVisible() == true) {
					abstractPanel.setVisible(false);
					abstractMenuItem.setText("����ժ��ͼ");
				} else {
					abstractPanel.setVisible(true);
					abstractMenuItem.setText("�ر���ժ��ͼ");
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
				// �˵���Close All Perspectives����¼�
				if (closeAllPerMenuItem.getText().equals("�ر�������ͼ")) {
					if (abstractPanel.isVisible() == true) {
						abstractPanel.setVisible(false);
						abstractMenuItem.setText("����ժ��ͼ");
					}
					if (tabbedPane.isVisible() == true) {
						tabbedPane.setVisible(false);
						txtMenuItem.setText("���ı���ͼ");
					}
					if (infoPanel.isVisible() == true) {
						infoPanel.setVisible(false);
						infoMenuItem.setText("����Ϣ��ͼ");
						btnStart.setEnabled(false);
					}
					closeAllPerMenuItem.setText("��������ͼ");
				} else {
					tabbedPane.setVisible(true);
					txtMenuItem.setText("�ر��ı���ͼ");
					infoPanel.setVisible(true);
					btnStart.setEnabled(true);
					infoMenuItem.setText("�ر���Ϣ��ͼ");
					abstractPanel.setVisible(true);
					abstractMenuItem.setText("�ر���ժ��ͼ");
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

		helpMenu.setFont(new Font("΢���ź�", Font.BOLD, 12));
		menuBar.add(helpMenu);
		helpMenu.setHorizontalAlignment(SwingConstants.CENTER);

		separator_7.setForeground(SystemColor.window);
		helpMenu.add(separator_7);

		updateMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �˵���Update����¼�
			}
		});
		updateMenuItem.setIcon(new ImageIcon("./source/menuIcons/update.gif"));
		helpMenu.add(updateMenuItem);

		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �˵���About SoftWare����¼�
			}
		});
		aboutMenuItem.setIcon(new ImageIcon("./source/menuIcons/about.gif"));
		helpMenu.add(aboutMenuItem);
		if (tabbedPane.getSelectedIndex() == -1) {
			toolSaveButton.setEnabled(false);
		}
		// ����Ĭ��
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
				// ��õ�ǰTabpage���±�
				int index = tabbedPane.getSelectedIndex();
				if (index != -1) {
					if (isBatchCheckBox.isSelected()) {
						if (docAbstractsList.isEmpty()) {
							AbstractTextArea.setText("");
							return;
						}
						statueLabel.setText("�ļ�" + tabbedPane.getTitleAt(index));
						AbstractTextArea.setText("");
						try {
							AbstractTextArea.setText(docAbstractsList
									.get(index));
						} catch (Exception e2) {
							return;
						}

					} else {
						statueLabel.setText("�ļ�" + tabbedPane.getTitleAt(index));
						return;
					}
				} else {
					statueLabel.setText("׼��...");
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
		AbstractTextArea.setLineWrap(true);// �Զ�����
		// ���ع�����
		abstractScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		abstractScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		abstractScrollPane.setViewportView(AbstractTextArea);
		AbstractTextArea.getDocument().addUndoableEditListener(um);// ���ÿ��Գ���������

		label_1.setBounds(0, 0, 83, 32);
		abstractPanel.add(label_1);
		rateComboBox = new JComboBox(items);
		rateComboBox.setBackground(Color.WHITE);
		rateComboBox.setBounds(93, 8, 127, 21);
		abstractPanel.add(rateComboBox);
		rateComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ������ȡ����
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
					// ��ť"��ʼ��ȡ"����¼�
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
						JOptionPane.showMessageDialog(component, "���������Ϊ�գ�");
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
					docAbstractsList.clear();// ����֮ǰȫ��
					docAbstractsList = getAbstract(documents);
					long timeafter = System.currentTimeMillis();
					if (!docAbstractsList.isEmpty()) {
						AbstractTextArea.setText(docAbstractsList.get(0));
						timeLabel.setText(String
								.valueOf((timeafter - timebefore)) + "����");
						rateLabel.setText(rateComboBox.getSelectedItem()
								.toString());
						statueLabel.setText("��ȡ���");
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
				// ��ť��������¼�
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
				// ������New ����¼�
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
				// ������Save����¼�{
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
				// (����������ժ)
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
				// ������Cut����¼�
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
				// ������Copy����¼�
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
				// ������Paste����¼�
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
				// ������Delete����¼�
				cutPastCopyDel(e);

			}
		});
		toolBar.add(toolDeleteButton);
		toolDeleteButton.setToolTipText("\u6E05\u9664");
		toolDeleteButton
				.setIcon(new ImageIcon(
						"E:\\android\\android_workplace2\\DOCAbstract\\source\\toolIcons\\delete.jpg"));
		// ������ʽ���(����)
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

		// ���ñ߽�
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

		// ��������
		AbstractTextArea.setFont(new Font("����", Font.PLAIN, 14));
		toolBar.setFont(new Font("�����������", Font.PLAIN, 12));

		setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { menuBar }));
	}

	// ���µ�TabPage
	private void addTabPage() {
		// �˵���New�� ����¼�
		setEnabled(false);
		setFocusable(false);
		newTabPage = new NewTabPage(this);
		newTabPage.setVisible(true);
		newTabPage.setFocusable(true);
	}

	// ���TabFrame,��������ӵ�Tab���±�
	TabContentComponent addTabFrame(File file) {
		String name = null;
		if (file == null) {
			name = newTabPage.getPageName();
		} else {
			name = file.getName();
		}
		// ��ǩ��
		TabContentComponent component = new TabContentComponent(tabbedPane);
		JPanel panel = component.getTabContentComponent();
		panel.setBackground(new Color(189, 153, 111));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(65,
				43, 24), Color.DARK_GRAY));
		tabbedPane.add(name, panel);
		MainFrame.d_Files.add(file);// �½�����ӵ�ǰ·����
		MainFrame.d_TabComponents.add(component);// �½������
		initTabComponent(count);
		tabbedPane.setSelectedIndex(count);
		toolSaveButton.setEnabled(true);// ���ÿ�������
		toolSaveAllButton.setEnabled(true);
		toolCopyButton.setEnabled(true);
		toolPasteButton.setEnabled(true);
		toolCutButton.setEnabled(true);
		toolDeleteButton.setEnabled(true);
		++count;
		return component;
	}

	// ��ʼ�����йرհ�ť�ı�ǩͷ��
	private int initTabComponent(int index) {
		ButtonTabComponent buttonTabComponent = new ButtonTabComponent(
				tabbedPane);
		tabbedPane.setTabComponentAt(index, buttonTabComponent);
		return index;
	}

	// ɾ��һ��TabFrame
	private void removeTabFrame() {
		int i = tabbedPane.getSelectedIndex();
		if (i != -1) {
			tabbedPane.remove(i);
			MainFrame.d_Files.remove(count - 1);
			MainFrame.d_TabComponents.remove(count - 1);
			--MainFrame.count;// MainFrame ������1
		}
		// ����Ĭ��
		if (tabbedPane.getSelectedIndex() == -1) {
			toolSaveButton.setEnabled(false);
			toolSaveAllButton.setEnabled(false);
			toolCopyButton.setEnabled(false);
			toolPasteButton.setEnabled(false);
			toolCutButton.setEnabled(false);
			toolDeleteButton.setEnabled(false);
		}
	}

	// ɾ��ȫ����TabFrame
	private void removeAllTabFrame() {
		while (count != 0) {
			MainFrame.d_Files.remove(count - 1);
			MainFrame.d_TabComponents.remove(count - 1);
			tabbedPane.remove(count - 1);
			--count;// MainFrame ������1;
		}
		toolSaveButton.setEnabled(false);
		toolSaveAllButton.setEnabled(false);
		toolCopyButton.setEnabled(false);
		toolPasteButton.setEnabled(false);
		toolCutButton.setEnabled(false);
		toolDeleteButton.setEnabled(false);

	}

	// �˳�Exit
	private void Exit() {
		Exit();
	}

	// ���ļ�
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

	// ���浱ǰһƪ��ժ
	private void saveCurrAbs() {
		int index = tabbedPane.getSelectedIndex();
		String docAbstract = AbstractTextArea.getText().trim();
		if (docAbstract.length() == 0 || index == -1
				|| docAbstractsList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "��ժ����Ϊ�գ�");
			return;
		}
		System.out.println("docAbstractsList�Ĵ�СΪ" + docAbstractsList.size());
		System.out.println("index�Ĵ�СΪ" + index);
		String content = docAbstractsList.get(index);
		MyEditor.saveAs(content);
	}

	// �������е���ժ
	private void saveAllAbs() {
		int index = tabbedPane.getSelectedIndex();
		if (index == -1 || docAbstractsList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "��ժ����Ϊ�գ�");
			return;
		}
		for (int i = 0; i < docAbstractsList.size(); i++) {
			// ���´���File����
			File temp = d_Files.get(index);
			String fileName = temp.getName();
			String filePath = temp.getAbsolutePath();
			String dir = filePath.replaceAll(fileName, "");
			File file = new File(dir + "abs_" + fileName);
			MyEditor.save(file, docAbstractsList.get(index));
		}
	}

	// ���Ϊ(�����⡣����)
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

	// ��õ���ժ
	public ArrayList<String> getAbstract(ArrayList<Document> documents) {
		Extraction extraction = new Extraction(documents);
		extraction.start();
		ArrayList<String> docAbstract = extraction.getDocAbstraces();
		return docAbstract;
	}

	// ʵ��ճ�������ƺͼ���
	public void cutPastCopyDel(ActionEvent e) {
		if (e.getSource() == CopyMenuItem || e.getSource() == toolCopyButton) // �����������塣
		{
			String temp = AbstractTextArea.getSelectedText(); // �϶����ѡȡ�ı���
			StringSelection text = new StringSelection(temp);
			clipboard.setContents(text, null);
		} else if (e.getSource() == cutMenuItem
				|| e.getSource() == toolCutButton) // �����������塣
		{
			String temp = AbstractTextArea.getSelectedText(); // �϶����ѡȡ�ı���
			StringSelection text = new StringSelection(temp);
			clipboard.setContents(text, null);
			int start = AbstractTextArea.getSelectionStart();
			int end = AbstractTextArea.getSelectionEnd();
			AbstractTextArea.replaceRange("", start, end); // ��TextArea��ɾ����ѡȡ���ı���
		} else if (e.getSource() == toolPasteButton
				|| e.getSource() == pasteMenuItem) // �Ӽ�����ճ�����ݡ�
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
