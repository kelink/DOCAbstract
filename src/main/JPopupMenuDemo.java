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

	/* 创建一个弹出式菜单对象 */

	JPopupMenu popup = new JPopupMenu();

	public JPopupMenuDemo() {

		Container contentPane = this.getContentPane();

		JPanel jPanel1 = new JPanel(new GridLayout(1, 2));

		JLabel jLabel1 = new JLabel("你选择的颜色:");

		jPanel1.add(jLabel1);

		jPanel1.add(jTextField1);

		ActionListener a1 = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jTextField1.setText(((JMenuItem) e.getSource()).getText());

			}

		};

		JMenuItem jMenuItem1 = new JMenuItem("红色"); /* 创建菜单项 */

		jMenuItem1.addActionListener(a1); /* 注册菜单项 */

		popup.add(jMenuItem1); /* 将菜单项添加到popup弹出式菜单中 */

		jMenuItem1 = new JMenuItem("黄色");

		jMenuItem1.addActionListener(a1);

		popup.add(jMenuItem1);

		jMenuItem1 = new JMenuItem("紫色");

		jMenuItem1.addActionListener(a1);

		popup.add(jMenuItem1);

		popup.addSeparator(); // 添加分隔符

		jMenuItem1 = new JMenuItem("蓝色");

		jMenuItem1.addActionListener(a1);

		popup.add(jMenuItem1);

		MYPopupListener p1 = new MYPopupListener(); // 创建一个弹出式菜单监听器对象

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

			showPopup(e); // 显示弹出式菜单

		}

		public void mouseReleased(MouseEvent e) {

			showPopup(e); // 显示弹出式菜单

		}

		private void showPopup(MouseEvent e) {

			/*
			 * boolean isPopupTrigger(MouseEvent e)
			 * 
			 * 如果 JPopupMenu 的当前安装 UI 将
			 * 
			 * MouseEvent 视为弹出菜单触发器，则返回 true。
			 */

			if (e.isPopupTrigger()) {

				/*
				 * void show(Component invoker, int x, int y)
				 * 
				 * 在组件调用者的坐标空间中的位置 X、Y 显示弹出菜单。
				 * 
				 * Component getComponent()
				 * 
				 * 返回此 JPopupMenu 组件。
				 * 
				 * public int getX() 返回组件原点的当前 x 坐标。
				 * 
				 * public int getY() 返回组件原点的当前 y 坐标。
				 */

				popup.show(e.getComponent(), e.getX(), e.getY());

			}

		}

	}

	public static void main(String args[]) {

		JPopupMenuDemo test = new JPopupMenuDemo();

	}

}