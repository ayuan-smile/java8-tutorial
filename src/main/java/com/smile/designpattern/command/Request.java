package com.smile.designpattern.command;

import java.io.Console;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-08-19 17:08
 */
public class Request {
    String name = null;

    public Request(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Request request = new Request("ayuan");
        Commander commander = new Commander();
        commander.cmd1(request,"羊肉串",2);
    }

    public void setName(String name) {
        this.name = name;
    }
}
