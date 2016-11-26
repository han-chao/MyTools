package com.tools.html.util;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class HtmlUtilTest {

	@Test
	public void test() throws IOException {
	System.out.println(HtmlUtil.formatHtml(new File("D:/public/404.html")));	
		
	}

}
