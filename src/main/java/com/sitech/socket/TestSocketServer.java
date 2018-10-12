package com.sitech.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestSocketServer {
    public static void main(String[] args) throws Exception{
        InputStream in = null;
        OutputStream out = null;
        try{
            ServerSocket ss = new ServerSocket(5888);//创建ServerSocket对象，绑定监听端口
            Socket socket = ss.accept();//接受来自客户端的请求
            in = socket.getInputStream();//得到来自客户端写入的数据
            out = socket.getOutputStream();//服务器端输出流对象
            DataOutputStream dos = new DataOutputStream(out);
            DataInputStream dis = new DataInputStream(in);
            String s =null;//定义从客户端读出的字符串
            //如果读出的不为空的话。向客户端发出本机的ip地址和连接的端口号
            if((s = dis.readUTF()) != null){
                System.out.println(s);
                System.out.println("from: " + socket.getInetAddress());
                System.out.println("port: " + socket.getPort());
            }
            dos.writeUTF("hello success");//向客户端写入
            dis.close();//关闭流对象
            dos.close();
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
