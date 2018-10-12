package com.sitech.designpattern;

public class SingleInstance {

    public static volatile SingleInstance singleInstance = null;

    public SingleInstance getSingleInstance(){
        if(singleInstance == null){
            synchronized (singleInstance){
                if(singleInstance == null){
                    if(singleInstance == null){
                        singleInstance = new SingleInstance();
                    }
                }
            }
        }
        return singleInstance;
    }
}
