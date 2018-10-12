package com.sitech.markdown;

import com.github.houbb.markdown.toc.core.impl.AtxMarkdownToc;
import com.github.houbb.markdown.toc.vo.TocGen;

public class AtxMarkdownTocFileTest {

	public static void main(String[] args) {
		String path = "E:\\app\\youdao\\yuanxue200800@163.com\\2686AB864AFD433580B236C2F67E9138\\markdown语法.md";
		TocGen tocGen = AtxMarkdownToc.newInstance()
                .genTocFile(path);
        System.out.println(tocGen);
        
	}
}
