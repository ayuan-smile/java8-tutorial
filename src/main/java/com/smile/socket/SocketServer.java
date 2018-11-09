package com.smile.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws Exception {
        /**
         * 基于TCP协议的Socket通信，实现用户登录，服务端
         */
        //1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
        ServerSocket serverSocket = new ServerSocket(5888);//1024-65535的某个端口
        //2、调用accept()方法开始监听，等待客户端的连接
        Socket socket = serverSocket.accept();
        InputStream in = socket.getInputStream();//得到来自客户端写入的数据
        OutputStream out = socket.getOutputStream();//服务器端输出流对象
        /*
        DataOutputStream dos = new DataOutputStream(out);
        DataInputStream dis = new DataInputStream(in);
        String s =null;//定义从客户端读出的字符串
        //如果读出的不为空的话。向客户端发出本机的ip地址和连接的端口号
        if((s = dis.readUTF()) != null){
            System.out.println(s);
            System.out.println("from: " + socket.getInetAddress());
            System.out.println("port: " + socket.getPort());
        }
        */
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);
        String info = null;
        while ((info = br.readLine()) != null) {
            System.out.println("我是服务器，客户端说：" + info);
        }
        out.write("欢迎您！".getBytes(),0,3);
        out.flush();
        /*dis.close();//关闭流对象
        dos.close();*/
        socket.close();
    }
}
