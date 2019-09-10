package com.smile.designpattern.command;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-19 17:09
 */
public class Implementor {
    public void cmd( String type,int count){
        System.out.println(String.format("实现人员开始做%s，共%d个",type,count));
    }
}
