package com.tools.html.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.tools.file.util.FileUtil;
import com.tools.html.util.HtmlUtil;

public class HtmlFormatGUI extends JDialog {
	private static final long serialVersionUID = 1L;

	private JComboBox<String> fileRootPath = new JComboBox<>();
	private JTextArea result = new JTextArea();
	private JButton okBtn = new JButton("开始");
	private JButton selectFile = new JButton("选择目录");

	public HtmlFormatGUI() throws HeadlessException {
		super();
		this.setTitle("HTML格式化工具");
		setLayout(null);
		fileRootPath.setMaximumRowCount(10);
		List<String> selecteds = FileUtil.readFileToList(new File("./log"));
		for (String selected : selecteds) {
			fileRootPath.addItem(selected);
		}
		addComponent(fileRootPath, 10, 10, 200, 20);
		addComponent(selectFile, 215, 10, 90, 20);
		addComponent(okBtn, 310, 10, 60, 20);
		JScrollPane scrollPane = new JScrollPane(result);// 滚动条
		addComponent(scrollPane, 10, 40, 360, 220);
		addListenner();
		this.setSize(400, 300);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * 添加控件
	 * 
	 * @param c
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	private void addComponent(Component c, int x, int y, int w, int h) {
		c.setBounds(x, y, w, h);
		add(c);
	}

	/**
	 * 添加事件监听
	 */
	private void addListenner() {
		selectFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File starFile = null;
				String rootDirPath = (String) fileRootPath.getSelectedItem();
				if (rootDirPath != null) {
					starFile = new File(rootDirPath);
				} else {
					starFile = new File("./log");
				}
				JFileChooser chooser = new JFileChooser(starFile);
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.showDialog(selectFile, "选择");
				File selectedFile = chooser.getSelectedFile();
				if (selectedFile != null) {
					result.setText("");
					String path = selectedFile.getAbsolutePath();
					fileRootPath.addItem(path);
					fileRootPath.setSelectedItem(path);
					FileUtil.writeToFile(new File("./log"), path + "\n", true);
				}
			}
		});
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String rootDirPath = (String) fileRootPath.getSelectedItem();
				if (rootDirPath != null) {
					String log = "开始格式化。。。\n";
					result.setText(log);
					File file = new File(rootDirPath);
					if (file.exists()) {
						log += HtmlUtil.ForEachFiles(file);
						result.setText(log);
						okBtn.setBackground(Color.green);
					} else {
						log += "文件不存在" + file.getAbsolutePath();
						result.setText(log);
						okBtn.setBackground(Color.RED);
					}
					log += "格式化完成！";
					result.setText(log);
				}
			}
		});
		fileRootPath.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					okBtn.setBackground(null);
				}
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		new HtmlFormatGUI();
	}

}
