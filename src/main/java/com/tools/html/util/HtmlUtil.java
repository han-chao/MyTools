package com.tools.html.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 功能：通过指定根目录，递归遍历根目录下的所有html文件，并利用JSoup的格式化代码的功能功能，将所有的Html代码进行格式化。
 * 
 * @author hanchao
 *
 */
public class HtmlUtil {

	/**
	 * 格式化指定根目录下的所有HTML文件
	 * 
	 * @param rootDir
	 */
	public static String ForEachFiles(File rootDir) {
		String reuslt = "";
		if (rootDir.isDirectory()) {
			for (File file : rootDir.listFiles()) {
				if (file.isDirectory()) {
					reuslt += ForEachFiles(file);
				} else if (file.getName().endsWith(".html")) {
					reuslt += formatHtml(file);
				}
			}
		}
		if (rootDir.getName().endsWith(".html")) {
			reuslt += formatHtml(rootDir);
		}
		return reuslt;
	}

	/**
	 * 格式化指定的单个HTML文件
	 * 
	 * @param file
	 */
	public static String formatHtml(File file) {
		String result = "";
		try {
			Document doc = Jsoup.parse(file, "UTF-8");
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
			bufferedWriter.write(doc.toString());
			bufferedWriter.flush();
			bufferedWriter.close();
			result = file.getAbsolutePath() + "\n";
		} catch (IOException e) {
			result = "格式化文件失败：" + file.getAbsolutePath() + "\n";
		}
		return result;
	}

}
