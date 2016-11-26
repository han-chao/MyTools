package com.tools.file.util;

import java.io.File;

import org.junit.Test;

public class FileUtilTest {

	@Test
	public void testWriteToFile() {
		FileUtil.writeToFile(new File("./sele"), "test", true);
	}

}
