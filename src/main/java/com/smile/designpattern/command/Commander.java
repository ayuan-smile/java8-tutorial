package com.smile.designpattern.command;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-19 17:05
 */
public class Commander {
    private Implementor implementor = new Implementor();
    public void cmd1(Request persion, String type,int count){
        System.out.println(String.format("%s点了%s，共%d个",persion.name,type,count));
        implementor.cmd(type, count);
    }

}
