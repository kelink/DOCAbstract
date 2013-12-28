package main;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

public class JPopupMenuDemo extends JFrame {

	JTextField jTextField1 = new JTextField(10);

	/* ����һ������ʽ�˵����� */

	JPopupMenu popup = new JPopupMenu();

	public JPopupMenuDemo() {

		Container contentPane = this.getContentPane();

		JPanel jPanel1 = new JPanel(new GridLayout(1, 2));

		JLabel jLabel1 = new JLabel("��ѡ�����ɫ:");

		jPanel1.add(jLabel1);

		jPanel1.add(jTextField1);

		ActionListener a1 = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jTextField1.setText(((JMenuItem) e.getSource()).getText());

			}

		};

		JMenuItem jMenuItem1 = new JMenuItem("��ɫ"); /* �����˵��� */

		jMenuItem1.addActionListener(a1); /* ע��˵��� */

		popup.add(jMenuItem1); /* ���˵�����ӵ�popup����ʽ�˵��� */

		jMenuItem1 = new JMenuItem("��ɫ");

		jMenuItem1.addActionListener(a1);

		popup.add(jMenuItem1);

		jMenuItem1 = new JMenuItem("��ɫ");

		jMenuItem1.addActionListener(a1);

		popup.add(jMenuItem1);

		popup.addSeparator(); // ��ӷָ���

		jMenuItem1 = new JMenuItem("��ɫ");

		jMenuItem1.addActionListener(a1);

		popup.add(jMenuItem1);

		MYPopupListener p1 = new MYPopupListener(); // ����һ������ʽ�˵�����������

		addMouseListener(p1);

		jTextField1.addMouseListener(p1);

		contentPane.add(jPanel1);

		this.setTitle("JPopupMenuDemo");

		this.setSize(300, 200);

		this.setLocation(400, 400);

		this.setVisible(true);

	}

	class MYPopupListener extends MouseAdapter {

		public void mousePressed(MouseEvent e) {

			showPopup(e); // ��ʾ����ʽ�˵�

		}

		public void mouseReleased(MouseEvent e) {

			showPopup(e); // ��ʾ����ʽ�˵�

		}

		private void showPopup(MouseEvent e) {

			/*
			 * boolean isPopupTrigger(MouseEvent e)
			 * 
			 * ��� JPopupMenu �ĵ�ǰ��װ UI ��
			 * 
			 * MouseEvent ��Ϊ�����˵����������򷵻� true��
			 */

			if (e.isPopupTrigger()) {

				/*
				 * void show(Component invoker, int x, int y)
				 * 
				 * ����������ߵ�����ռ��е�λ�� X��Y ��ʾ�����˵���
				 * 
				 * Component getComponent()
				 * 
				 * ���ش� JPopupMenu �����
				 * 
				 * public int getX() �������ԭ��ĵ�ǰ x ���ꡣ
				 * 
				 * public int getY() �������ԭ��ĵ�ǰ y ���ꡣ
				 */

				popup.show(e.getComponent(), e.getX(), e.getY());

			}

		}

	}

	public static void main(String args[]) {

		JPopupMenuDemo test = new JPopupMenuDemo();

	}

}