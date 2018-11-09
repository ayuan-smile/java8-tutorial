package com.smile.markdown;

import com.github.houbb.markdown.toc.core.impl.AtxMarkdownToc;
import com.github.houbb.markdown.toc.vo.TocGen;

public class AtxMarkdownTocFileTest {

	public static void main(String[] args) {
		String path = "E:\\app\\youdao\\yuanxue200800@163.com\\59DF4C911A6C4C19A7BE752EE2E36FC7\\结算能力平台计算引擎支持jdk1.8说明.md";
		TocGen tocGen = AtxMarkdownToc.newInstance()
                .genTocFile(path);
        System.out.println(tocGen);
        
	}
}
