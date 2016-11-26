package com.tools.file.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	/**
	 * 向指定文件写内容
	 * 
	 * @param file
	 *            要写入的文件
	 * @param content
	 *            要写入的内容
	 * @param append
	 *            true 追加 /false 覆盖
	 * @return 0 ：成功 | -1：失败 | 1:不是文件
	 */
	public static int writeToFile(File file, String content, boolean append) {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				return -1;
			}
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, append));
			writer.write(content);
			writer.flush();
			writer.close();
			return 0;
		} catch (IOException e) {
			return -1;
		}
	}

	/**
	 * 将文件中的内容全部读出来
	 * 
	 * @param file
	 * @return
	 */
	public static String readFile(File file) {
		StringBuilder result = new StringBuilder();
		if (file.isFile()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String temp = "";
				while ((temp = reader.readLine()) != null) {
					result.append(temp);
				}
				reader.close();
			} catch (IOException e) {
			}
		}
		return result.toString();
	}

	/**
	 * 见文件中的内容独到List中，一行文本对应List中的一项
	 * 
	 * @param file
	 * @return
	 */
	public static List<String> readFileToList(File file) {
		List<String> result = new ArrayList<>();
		if (file.isFile()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String temp = "";
				while ((temp = reader.readLine()) != null) {
					result.add(temp);
				}
				reader.close();
			} catch (IOException e) {
			}
		}
		return result;
	}
}
