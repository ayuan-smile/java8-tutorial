package com.smile.socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestSocketClient {

    public static void main(String[] args) throws Exception{
        InputStream is = null;
        OutputStream os = null;
        try{
            Socket socket = new Socket("localhost", 5888);//创建Socket对象，指明需要连接的服务器的地址和端口号
            is = socket.getInputStream();
            os = socket.getOutputStream();
            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF("hello");//连接建立后，通过输出流向服务器端发送请求信息
            String s = null;
            if((s = dis.readUTF()) != null){
                System.out.println(s);//通过输入流获取服务器响应的信息
            }
            dos.close();
            dis.close();
            socket.close();
        } catch (UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
