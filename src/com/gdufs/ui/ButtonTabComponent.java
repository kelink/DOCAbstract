package com.gdufs.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicButtonUI;

import com.gdufs.util.FileUtil;

public class ButtonTabComponent extends JPanel {
	private final JTabbedPane pane;

	public ButtonTabComponent(final JTabbedPane pane) {
		// ���ñ�ǩͷҪˮƽ�ģ��������ҵ������������������ļ��Ϊ0�����������
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));

		if (pane == null)
			throw new NullPointerException("pane can not be null");
		this.pane = pane;

		// ������panel�ı߽�
		setOpaque(false);

		// ������ǩͷ����������
		JLabel label = new JLabel() {
			// ��д����������paneָ��λ�õ����֣������е��ƣ����Ժú�������
			// Ϊʲô��ֱ�����ñ�ǩ�����ݣ�����ͨ����д�������޸ı�ǩ����
			// ������ϣ��ҵ���߼�������Ҫɢ�����ⲿ
			@Override
			public String getText() {
				// ���Ի�õ�ǰpanel��tab�е�λ��
				int i = pane.indexOfTabComponent(ButtonTabComponent.this);
				if (i != -1)
					// �õ���ǰpanel�����֣�ʵ����tab�����֣�
					return pane.getTitleAt(i);
				return null;
			}
		};
		label.setBackground(new Color(189, 153, 111));
		pane.setBackground(new Color(189, 153, 111));
		add(label);
		// �����رհ�ť�������Ǹ������
		JButton button = new TabButton();
		add(button);

	}

	private class TabButton extends JButton implements ActionListener {
		public TabButton() {
			// ���ð����Ĵ�С
			final int size = 17;
			setPreferredSize(new Dimension(size, size));
			// ���ð�������ʾ��Ϣ
			setToolTipText("�رմ���");
			// ���ð����Ļ�������ͨ������ͬ
			setUI(new BasicButtonUI());
			// ����Button������䣬���ǰ�����͸����
			setContentAreaFilled(false);
			// �������ܻ�ý���
			setFocusable(false);
			// ���ð����ı߿�Ϊ�����ʽ
			setBorder(BorderFactory.createEtchedBorder());
			// ϵͳ���Զ����ư����߽磨����߽���������ȥ֮��Ż��ƣ�
			setBorderPainted(false);

			// ��Ӱ�������¼�
			addActionListener(TabButton.this);
			// �������¼�����Ҫ��mouseover �� mouse exit��
			addMouseListener(mouseListener);

		}

		// ��дButton�Ļ��ƺ���
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			// ����һ��graphics2D����Ϊ��Ҫ��Button�ϻ���
			Graphics2D g2 = (Graphics2D) g.create();

			// ���û��ʣ����Ϊ2
			g2.setStroke(new BasicStroke(2));
			// ���û�����ɫ
			g2.setColor(Color.BLACK);
			// ������ƶ���Button��ʱ������Ϊ��ɫ
			if (getModel().isRollover())
				g2.setColor(Color.PINK);
			// ���Ʋ�
			int delta = 6;
			g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight()
					- delta - 1);
			g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight()
					- delta - 1);
			// �ͷŻ�����Դ
			g2.dispose();
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// ��������������رյ�ǰ��ǩҳ
			int i = pane.indexOfTabComponent(ButtonTabComponent.this);
			if (i != -1) {
				TabContentComponent component = MainFrame.d_TabComponents
						.get(i);
				String title = component.getTitleField().trim();
				String content = component.getTextArea().trim();
				File file = MainFrame.d_Files.get(i);
				FileUtil.write2File(file.getAbsolutePath(), title + "\n"
						+ content, false);// д�뵱ǰ�ĵ�
				MainFrame.d_Files.remove(i);
				MainFrame.d_TabComponents.remove(i);
				if (!MainFrame.docAbstractsList.isEmpty()) {
					MainFrame.docAbstractsList.remove(i);
				}

				pane.remove(i);
				--MainFrame.count;// MainFrame ������1
			}
		}

		private final MouseListener mouseListener = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent event) {
				// ������밴�������ư����߽�
				Component c = event.getComponent();
				if (c instanceof AbstractButton)
					((AbstractButton) c).setBorderPainted(true);
			}

			@Override
			public void mouseExited(MouseEvent event) {
				// ����Ƴ������������ư����߽�
				Component c = event.getComponent();
				if (c instanceof AbstractButton)
					((AbstractButton) c).setBorderPainted(false);
			}

		};

	}

}